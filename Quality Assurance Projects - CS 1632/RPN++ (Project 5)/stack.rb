# Array stack to hold values from the rpn calculator
class Stack
  def initialize
    @arr = []
  end

  # Adds any values to the end of the stack
  def push(new_val)
    @arr << new_val
    new_val
  end

  def put_back(val)
    @arr.unshift(val)
  end

  # Removes a value from the beginning of the stack
  def pop
    value = @arr[0]
    @arr[0] = nil
    @arr = @arr.compact
    value
  end

  # Returns the next value without removing it
  def peek
    @arr[0]
  end

  # Returns the current count of the stack
  def empty?
    return true if @arr.count.zero?
    false
  end

  # Returns the current size of the stack
  def size?
    @arr.count
  end

  def clear
    @arr = []
  end
end
