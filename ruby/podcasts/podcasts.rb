require 'fileutils'

module Podcasts
  class Source
    attr_reader :filename

    def initialize(filename)
      @filename = filename
    end

    def duration
      %x{sox #{@filename} -n stat 2>&1 | grep "Length (seconds):" | awk '{print $3}'}.to_f
    end

    def to_s
      @filename
    end
  end

  class Converter
    attr_accessor :source

    def initialize(source)
      @source = source
    end

    def change_speed(speed_ratio = 1.25, out_file = nil)
      out_file ||= "#{speed_ratio}x_#{source}"
      %x{sox #{source} #{out_file} speed #{speed_ratio}}
      update_source(out_file)
      self
    rescue => e
      puts "ERROR: Can't change speed of #{source} to #{speed_ratio} due to #{e.inspect}"
    end

    def extract(start, finish, out_file = nil)
      out_file ||= "extracted(#{start}:#{finish})_#{source}"
      %x{sox #{source} #{out_file} trim #{start} =#{finish}}
      update_source(out_file)
      self
    rescue => e
      puts "ERROR: Can't extract #{start}:#{finish} from #{source} due to #{e.inspect}"
    end

    def split_by(slice_length = 1*60, prefix = "")
      slices_number = source.duration / slice_length

      0.upto(slices_number).each_with_object([]) do |slice, paths_array|
        slice_name = "#{prefix}_#{slice}_#{source}"
        %x{sox #{source} #{slice_name} trim #{slice_length * slice} #{slice_length}}
        paths_array << slice_name
      end
    rescue => e
      puts "ERROR: Can't split #{source} to #{slice_length} due to #{e.inspect}"
    end

    private
    def update_source(filename)
      @source = Source.new(filename)
    end
  end

  def self.speed_up_and_trim(source)
    Converter.new(source).change_speed(1.25).split_by(60)
  end

  def self.prepare(file)
    slices = speed_up_and_trim(Source.new(file))
    copy_files(slices, "#{file}_slices")
  end

  def self.copy_files(files, dir_name)
    FileUtils.rm_rf(dir_name) if File.directory?(dir_name)
    FileUtils.mkdir_p(dir_name)
    FileUtils.mv(files, dir_name)
  end
end

Podcasts.prepare('04-22.mp3')
