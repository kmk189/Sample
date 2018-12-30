require 'simplecov'

require 'minitest/autorun'
require_relative 'stack'

class Methods_test < Minitest::Test

	def setup
		@stack = Stack::new
	end
	
	# UNIT TESTS FOR METHOD push
	# Value added to stack -> returns value passed in

  # Value passed in, returns the value passed in
  def test_push_number
		assert_equal 7, @stack.push(7)
	end

  # Value passed in, returns the value passed in
  def test_push_symbol
		assert_equal '+', @stack.push('+')
	end
  
  
  # UNIT TEST FOR METHOD pop
  # Removes and returns the first value in the stack
  
  # Value removed from stack and returned, stack cut down
  def test_pop_value
    @stack.push(7)
    @stack.push(8)
    assert_equal 7, @stack.pop
    assert_equal 8, @stack.peek
  end
  
  # UNIT TEST FOR METHOD peek
  # Returns the first value in the stack without changing the stack
  
  # First value in the stack returned, not removed from stack
  def test_peek_value
    @stack.push(7)
    @stack.push(8)
    assert_equal 7, @stack.peek
    assert_equal 7, @stack.peek # should be the same value both times
  end
  
  # UNIT TEST FOR METHOD empty?
  # Equivalence Classes:
  # Empty stack -> true
  # Not empty stack -> false
  
  # If the stack is empty, return true
  def test_empty_stack
    assert_equal true, @stack.empty?
  end
  
  # If the stack is not empty, return false
  def test_not_empty_stack
    @stack.push(5)
    @stack.push(6)
    assert_equal false, @stack.empty?
  end
  
  
  # UNIT TEST FOR METHOD size?
  # Returns the current number of items in the stack
  
  def test_size_stack
    @stack.push(7)
    @stack.push(8)
    assert_equal 2, @stack.size?
  end
  
  
  # UNIT TEST FOR METHOD clear
  # After called, the size of the stack should be zero
  
  def test_clear_stack
    @stack.push(7)
    @stack.push(8)
    @stack.clear
    assert_equal 0, @stack.size?
  end
end
