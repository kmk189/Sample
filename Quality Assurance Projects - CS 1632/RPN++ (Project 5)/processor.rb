require_relative 'stack'
require_relative 'evaluator'

# Methods to process the line input
class Processor
  attr_accessor :line_num

  def initialize
    @variables = {}
    @line_num = 1
    @eval = Evaluator.new
  end

  def line_splitter(line)
    values = line.split(' ')
    values = values.map(&:upcase)
    values
  end

  def let(var_id, expression)
    value, valid = @eval.evaluate(expression)
    return value if valid.nil?
    return 5 if let_validate(var_id).nil?
    @variables[var_id] = value
    0
  end

  def let_validate(var_id)
    return 'v' if var_id.length == 1 && var_id[/[a-zA-Z]+/] == var_id
    puts "Line #{@line_num}: Invalid variable name for LET function."
    nil
  end

  def var_val(var_id)
    if @variables[var_id].nil?
      puts "Line #{@line_num}: Variable not initialized."
      return nil
    end
    @variables[var_id]
  end

  def print(expression)
    value, valid = @eval.evaluate(expression)
    return value if valid.nil?
    puts value.to_s
    0
  end

  def line_add
    @line_num += 1
    @eval.num += 1
  end

  def check_validity(arr)
    new = []
    arr.map! do |x|
      return 5 if check_var_names(x).nil?
      x = replace(x)
      return x unless x.is_a? Integer or %w[+ - * /].include?(x)
      new << x
    end
    new
  end

  def replace(value)
    return value.to_i if value[/[-+]?[0-9]+/] == value
    return value if %w[+ - * /].include?(value)
    return @variables[value] unless @variables[value].nil?
    puts "Line #{@line_num}: Variable #{value} is not initialized."
    nil
  end

  def check_var_names(value)
    return 'ok' if value[/[-+]?[0-9]+/] == value
    return 'also ok' if value[/[a-zA-Z]+/] == value && value.length == 1
    return 'also ok' if %w[+ - * /].include?(value)
    puts "Line #{@line_num}: Variable names may only be one character (a-z,A-Z)"
    nil
  end

  def unknown_keyword(word)
    puts "Line #{@line_num}: Unknown keyword #{word}"
  end
end
