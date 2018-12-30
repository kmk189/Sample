require_relative 'stack'

# Methods for evaluating RPN expressions
class Evaluator
  attr_accessor :num
  attr_accessor :op_stack
  attr_accessor :n_stack

  def initialize
    @num = 1
    @op_stack = Stack.new
    @n_stack = Stack.new
  end

  def evaluate(array)
    clear_stacks
    load_stacks(array)
    result, value = maths
    return result, nil if value.nil?
    return 3, nil if check_final_validity.nil?
    [@n_stack.pop, 'valid']
  end

  def maths
    until @op_stack.empty?
      return 2, nil if empty_stack == true
      result = quick_maffs_a(@op_stack.pop)
      return 5, nil if result.nil?
      @n_stack.push(result)
    end
    [@n_stack.peek, 'success']
  end

  def empty_stack
    if @n_stack.size? < 2 && @op_stack.size? > 0
      puts "Line #{@num}: Operator #{@op_stack.peek} applied to empty stack."
      return true
    end
    false
  end

  def quick_maffs_a(operator)
    return (@n_stack.pop + @n_stack.pop) if operator == '+'
    return (@n_stack.pop - @n_stack.pop) if operator == '-'
    quick_maffs_b(operator)
  end

  def quick_maffs_b(operator)
    return (@n_stack.pop * @n_stack.pop) if operator == '*'
    return division if operator == '/'
  end

  def division
    var1 = @n_stack.pop
    if @n_stack.peek.zero?
      @n_stack.put_back(var1)
      puts "Line #{@num}: Division by zero."
      return nil
    end
    var1 / @n_stack.pop
  end

  def load_stacks(array)
    array.each do |x|
      @n_stack.push(x.to_i) if x.is_a? Integer
      @op_stack.push(x) if %w[+ - / *].include?(x)
    end
  end

  def clear_stacks
    @op_stack.clear
    @n_stack.clear
  end

  def check_final_validity
    if @n_stack.size? != 1
      puts "Line #{@num}: #{@n_stack.size?} elements in stack after evaluation"
      return nil
    end
    'valid'
  end
end
