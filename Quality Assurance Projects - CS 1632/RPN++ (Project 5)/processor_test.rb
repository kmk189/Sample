require 'simplecov'

require 'minitest/autorun'
require_relative 'processor'

class Processor_test < Minitest::Test

	def setup
		@process = Processor::new
	end
  
  
  # UNIT TEST FOR METHOD line_splitter
  # Splits a line by spaces and uppercases all letters
  
  # If an line is passed in, it is split and uppercased
  def test_splitter_normal
    assert_equal ['LET', 'A', '5'], @process.line_splitter('let a 5')
  end
  
  # If a blank line is passed in, it returns an empty array
  def test_splitter_empty
    assert_equal [], @process.line_splitter('')
  end
  
  # If extra spaces are entered, it should just return the other characters
  def test_splitter_space
    assert_equal ['A', '5', '+'], @process.line_splitter('    a   5    +         ')
  end
  
  
  # UNIT TESTS FOR METHOD let, let_validate
  # Equivalence Classes:
  # Invalid expression -> returns an error code
  # Invalid variable name -> returns error code 5
  # Valid let function -> returns 0
  
  # If an invalid expression is passed in, it returns the error code
  def test_let_invalid_expr
    e1 = Minitest::Mock::new 'evaluator 1'
    e1.expect(:evaluate, [2, nil], ['+'])
    assert_equal 2, @process.let('b', ['+'])
  end
  
  # If an invalid variable name is passed in, it returns error code 5
  def test_let_invalid_var
    e1 = Minitest::Mock::new 'evaluator 1'
    e1.expect(:evaluate, [15, 'valid'], [15])
    assert_equal 5, @process.let('K19K', [15])
  end
  
  # If valid function and variable are passed in, it returns 0
  def test_let_valid
    e1 = Minitest::Mock::new 'evaluator 1'
    e1.expect(:evaluate, [5, 'valid'], [5])
    assert_equal 0, @process.let('b', [5])
  end
  
  
  # UNIT TESTS FOR METHOD var_val
  # Equivalence Classes:
  # Variable assigned -> returns its value
  # Variable not assigned -> returns nil
  
  # If the variable has been assigned, it will return the value
  def test_var_val_exists
    @process.let('a', [5])
    assert_equal 5, @process.var_val('a')
  end
  
  # If the variable has not been assigned, it will return nil
  def test_var_val_nil
    assert_equal nil, @process.var_val('b')
  end
  
  
  # UNIT TESTS FOR METHOD print
  # Equivalence Classes:
  # Valid expression -> prints result, returns 0
  # Invalid expression -> returns error code

  # If the expression is valid, it will return 0 after printing the result
  def test_valid_print
    e1 = Minitest::Mock::new 'evaluator 1'
    e1.expect(:evaluate, [15, 'valid'], [15])
    assert_equal 0, @process.print([15])
  end
  
  # If the expression is invalid, it will return the error code
  def test_invalid_print
    e1 = Minitest::Mock::new 'evaluator 1'
    e1.expect(:evaluate, [2, nil], ['*'])
    assert_equal 2, @process.print(['*'])
  end
  
  
  # UNIT TESTS FOR METHOD line_add
  # Increases the line number for both processor and evaluator
  
  # When called, the line numbers are incremented by 1
  def test_line_add
    @process.line_add
    assert_equal 2, @process.line_num
  end
  
  
  # UNIT TESTS FOR METHOD unknown_keyword
  # Prints a statement for when there is an unknown keyword
  
  # If called, it will add the line number and word to a statement
  def test_unknown_keyword
    assert_output (/Line 1: Unknown keyword BLARGLE/) {@process.unknown_keyword('BLARGLE')}
  end
  
  
  # UNIT TESTS FOR METHOD check_var_names
  # Equivalence Classes:
  # Integer value -> 'ok'
  # Single letter -> 'also ok'
  # +, -, *, / -> 'also ok'
  # Other input -> nil
  
  # If an integer is passed in, it returns 'ok'
  def test_check_var_int
    assert_equal 'ok', @process.check_var_names('-18')
  end
  
  # If a single letter is passed in, it returns 'also ok'
  def test_check_var_let
    assert_equal 'also ok', @process.check_var_names('F')
  end
  
  # If an appropriate symbol is passed in, it returns 'also ok'
  def test_check_var_sym
    assert_equal 'also ok', @process.check_var_names('-')
  end
  
  # If anything else is passed in, it returns nil
  def test_check_var_oth
    assert_output (/Line 1: Variable names may only be one character (a-z,A-Z)/) {@process.check_var_names('BAD')}
    assert_nil @process.check_var_names('BAD')
  end
  
  
  # UNIT TESTS FOR METHOD replace
  # Equivalence Classes:
  # Whole number -> Integer representation
  # +, -, *, / -> Symbol passed in
  # Valid variable -> its number value
  # Other input -> nil
  
  # If an integer is passed in, it returns the integer version
  def test_replace_int
    assert_equal -18, @process.replace('-18')
  end
  
  # If a single symbol is passed in, it returns the symbol passed in
  def test_replace_let
    assert_equal '+', @process.replace('+')
  end
  
  # If a variable is passed in, it returns the numerical value
  def test_check_var_sym
    @process.let('A', [5])
    assert_equal 5, @process.replace('A')
  end
  
  # If anything else is passed in, it returns nil
  def test_check_var_oth
    assert_output (/Line 1: Variable B is not initialized./) {@process.replace('B')}
    assert_nil @process.replace('B')
  end
  
  
  # UNIT TESTS FOR METHOD check_validity
  # Equivalence Classes:
  # Invalid input in the array -> returns 5
  # Uninitialized variable -> return the variable
  # Valid input in the array -> returns a new, edited array
  
  # If there is invalid input in the array, it will return error code 5
  def test_validity_not
    assert_equal 5, @process.check_validity(['5', 'BAD', '+'])
  end
  
  # If there is an uninitialized variable, it returns nil
  def test_validity_var
    assert_nil @process.check_validity(['5', 'b', '-'])
  end
  
  # If the array is valid, it returns the new edited array
  def test_validity_valid
    @process.let('C', [15])
    assert_equal [15, 5, '+'], @process.check_validity(['C', '5', '+'])
  end
end
