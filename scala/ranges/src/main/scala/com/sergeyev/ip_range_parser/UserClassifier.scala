package com.sergeyev.ip_range_parser

import scala.io.Source
import java.nio.file.{Paths, Files}
import java.nio.charset.StandardCharsets

object UserClassifier {
  def main(args: Array[String]): Unit = {
    val networks_filename = "ranges.tsv"
    val users_filename = "transactions.tsv"
    val output_filename = "output.tsv"

    val networks_mapping = parse_networks_mapping(networks_filename)
    val data = classify(users_filename, networks_mapping).map{ case (id, name) => s"$id\t$name"}.mkString("\n")
    write(output_filename, data)
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

  def classify(filename: String, networks: Map[(Long,Long), String]): List[(Long, String)] = {
    var result: List[(Long, String)] = List()

    for (line <- io.Source.fromFile(filename).getLines()) {
      val split = line.split("[\t]")
      val user_id = split(0).toLong
      val ip = convert_ip_to_int(split(1))

      for (((start, end), name) <- networks) {
         if (ip >= start && ip <= end){
           result = result :+ (user_id, name)
         }
      }
    }

    result
  }

  def convert_ip_to_int(ip: String): Long = {
    ip.split("\\.").reverse.zipWithIndex.map(octet => octet._1.toInt * math.pow(256, octet._2).toLong).sum
  }

  def write(path: String, txt: String): Unit = {
    Files.write(Paths.get(path), txt.getBytes(StandardCharsets.UTF_8))
  }
}
