require 'simplecov'

require 'minitest/autorun'
require_relative 'evaluator'

class Evaluator_test < Minitest::Test

	def setup
		@eval = Evaluator::new
	end
  
  
  # UNIT TESTS FOR METHOD evaluate
  # Equivalence Classes:
  # Valid array of numbers/symbols -> [result, 'valid']
  # Invalid array of numbers/symbols -> [error code, nil]
  
  # If a valid array is passed in, it will return the result and 'valid'
  def test_valid_eval
    assert_equal [11, 'valid'], @eval.evaluate([5, 6, '+'])
  end
  
  # If an invalid array is passed in (#/0 for example), 
  # It will return an error code and nil
  def test_invalid_eval_a
    assert_equal [5, nil], @eval.evaluate([8, 0, '/'])
  end
  
  # If an invalid array is passed in (wrong amount of variables),
  # It will return an error code and nil
  def test_invalid_eval_b
    assert_equal [3, nil], @eval.evaluate([5, 4, 6, '+'])
  end
  
  
  # UNIT TESTS FOR METHOD maths
  # Equivalence Classes:
  # Ran out of numbers early -> [2, nil]
  # Divide by zero -> [5, nil]
  # Valid math -> [result, 'success']
  
  # If valid math is attempted, it will return the result and 'success'
  def test_valid_maths
    @eval.load_stacks([4, 5, '+'])
    assert_equal [9, 'success'], @eval.maths
  end
  
  # If the values will cause a divide by zero error, it will return 5 and nil
  def test_zero_maths
    @eval.load_stacks([4, 0, '/'])
    assert_equal [5, nil], @eval.maths
  end
  
  # If there are not enough operators, it will return 2 and nil
  def test_operator_maths
    @eval.load_stacks([5, 6, '-', '*'])
    assert_equal [2, nil], @eval.maths
  end
  
  
  # UNIT TESTS FOR METHOD quick_maffs_a, quick_maffs_b
  # Equivalence Classes:
  # Valid operator and numbers -> result of the math
  # Divide by zero -> nil
  
  # If the operator is +, it will add the values
  def test_addition
    @eval.load_stacks([5, 5, '+'])
    assert_equal 10, @eval.quick_maffs_a('+')
  end
  
  # If the operator is -, it will subtract the values
  def test_subtract
    @eval.load_stacks([5, 5, '-'])
    assert_equal 0, @eval.quick_maffs_a('-')
  end
  
  # If the operator is *, it will multiply the values
  def test_multiply
    @eval.load_stacks([5, 5, '*'])
    assert_equal 25, @eval.quick_maffs_a('*')
  end
  
  # If the operator is / and it is not dividing by zero, it will divide the values
  def test_divide
    @eval.load_stacks([25, 5, '/'])
    assert_equal 5, @eval.quick_maffs_a('/')
  end
  
  
  # UNIT TESTS FOR METHOD empty_stack
  # Equivalence Classes:
  # Not enough numbers for the operators -> true
  # Enough numbers for the operators -> false
  
  # Less than 2 numbers with operators remaining, return true
  def test_true_empty
    @eval.load_stacks([5, '+'])
    assert_equal true, @eval.empty_stack
  end
  
  # At least 2 numbers remaining with operators remaining, return false
  def test_false_empty
    @eval.load_stacks([5, 5, '+'])
    assert_equal false, @eval.empty_stack
  end
  
  
  # UNIT TESTS FOR METHOD clear_stacks
  # When called, both the operator and number stacks are reset
  
  # If called, the stacks will be empty_stack
  def test_clear_stacks
    @eval.load_stacks([5, 5, 6, '+', '-'])
    @eval.clear_stacks
    assert_equal 0, @eval.op_stack.size?
    assert_equal 0, @eval.n_stack.size?
  end
  
  
  # UNIT TESTS FOR METHOD check_final_validity
  # Equivalence Classes:
  # If number stack has not-one element -> nil
  # If number stack has one element -> 'valid
  
  # If there is one element in the number stack, return 'valid'
  def test_one_validity
    @eval.load_stacks([5])
    assert_equal 'valid', @eval.check_final_validity
  end
  
  # If there is not one element in the number stack, return nil
  def test_multiple_validity
    @eval.load_stacks([5, 6, 7])
    assert_equal nil, @eval.check_final_validity
  end  
end
