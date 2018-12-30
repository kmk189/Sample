require_relative 'processor'

# Primary class of RPN++
class RPN
  # EXECUTION BEGINS HERE
  repl_flag = true
  repl_flag = false if ARGV.count > 0

  if repl_flag == false
    index = 0
    lines = []
    while index < ARGV.length
      next unless File.file?(ARGV[index])
      File.open(ARGV[index], 'r').each_line do |line|
        lines << line.chomp
      end
      index += 1
    end
    process = Processor.new

    lines.each do |line|
      single = process.line_splitter(line)
      start = single[0]
      exit if start.eql?('QUIT')
      if start.nil?
        print ''
      elsif start.eql?('LET')
        replaced = process.check_validity(single[2..single.length])
        exit(replaced.to_i) unless replaced.is_a? Array
        value = process.let(single[1], replaced)
        exit(value) unless value.zero?
      elsif start.eql?('PRINT')
        replaced = process.check_validity(single[1..single.length])
        exit(replaced.to_i) unless replaced.is_a? Array
        value = process.print(replaced)
        exit(value) unless value.zero?
      elsif start.length > 1 && start[/[-+]?[0-9]/] != start
        process.unknown_keyword(start)
        exit(4)
      else
        replaced = process.check_validity(single)
        exit(replaced.to_i) unless replaced.is_a? Array
        value = process.no_keyword(replaced)
        exit(value) unless value.zero?
      end
      process.line_add
    end
    exit(0)
  end

  ar = []
  process = Processor.new
  line_num = 1

  until ar[0].eql?('QUIT')
    print '> '
    line = gets
    ar = process.line_splitter(line)
    start = ar[0]

    next if start.eql?('QUIT')
    if ar.length.zero?
      print ''
    elsif start.eql?('PRINT')
      replaced = process.check_validity(ar[1..ar.length])
      process.print(replaced) if replaced.is_a? Array
    elsif start.eql?('LET')
      replaced = process.check_validity(ar[2..ar.length])
      process.let(ar[1], replaced) if replaced.is_a? Array
      puts process.var_val(ar[1]).to_s unless process.var_val(ar[1]).nil?
    elsif start.length > 1 && start[/[-+]?[0-9]+/] != start
      puts "Line #{line_num}: Unknown keyword #{start}"
    else
      replaced = process.check_validity(ar)
      process.print(replaced) if replaced.is_a? Array
    end

    line_num += 1
    process.line_add
  end
  exit
end
