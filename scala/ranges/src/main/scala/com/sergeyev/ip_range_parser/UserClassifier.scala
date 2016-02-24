package com.sergeyev.ip_range_parser

import scala.io.Source


object UserClassifier {
  def main(args: Array[String]): Unit = {
    val networks_filename = "ranges_copy.tsv"
    val users_filename = "tr2.tsv"

    val networks_mapping = parse_networks_mapping(networks_filename)
    val users_stats = classify(users_filename, networks_mapping)
    println(users_stats)

  }

  def parse_networks_mapping(filename: String): Map[(Long, Long), String] = {
     var parsed: Map[(Long,Long), String] = Map()

     for (line <- io.Source.fromFile(filename).getLines()) {
        val split = line.split("[\t-]")
        val start_ip = convert_ip_to_int(split(0))
        val end_ip = convert_ip_to_int(split(1))
        val name = split(2)
        parsed += ((start_ip, end_ip) -> name)
     }

     parsed
  }

  def classify(filename: String, networks: Map[(Long,Long), String]): Map[Long, Set[String]] = {
    var result: Map[Long, Set[String]] = Map()

    for (line <- io.Source.fromFile(filename).getLines()) {
      val split = line.split("[\t]")
      val user_id = split(0).toLong
      val ip = convert_ip_to_int(split(1))
      var new_subnets: Set[String] = Set()
      for (((start, end), name) <- networks) {
         if (ip >= start && ip <= end){
           new_subnets = new_subnets + name
         }
      }

      result += (user_id -> (result.getOrElse(user_id, Set()) ++ new_subnets))
    }

    result
  }

  def convert_ip_to_int(ip: String): Long = {
    ip.split("\\.").reverse.zipWithIndex.map(octet => octet._1.toInt * math.pow(256, octet._2).toLong).sum
  }
}
