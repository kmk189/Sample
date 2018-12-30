require "minitest/autorun"
require_relative "argschecker"

class Args_test < Minitest::Test

	# UNIT TESTS FOR METHOD check_args
	# Equivalence Classes:
	# 1 argument -> does not raise exception
	# More or less than 1 argument -> raises exception

	# If no argument is entered, exception is raised
	def test_args_none
		assert_raises "Enter a seed and only a seed (RuntimeError)" do
			args_checker = ArgsChecker::new
			args_checker.check_args []
		end
	end
	
	# If more than one argument is entered, exception is raised
	def test_args_two
		assert_raises "Enter a seed and only a seed (RuntimeError)" do
			args_checker = ArgsChecker::new
			args_checker.check_args [1, 2]
		end
	end
	
	# If one argument is entered, no exception is raised
	def test_args_one
		args_checker = ArgsChecker::new
		value = args_checker.check_args [1]
		assert_equal 1, value
	end

	
	# UNIT TESTS FOR METHOD check_value
	# Equivalence Classes:
	# Integer -> returns integer passed
	# Non-integer -> returns 0
	
	# If the seed value entered cannot be converted to an int, return 0
	def test_invalid_seed
		args_checker = ArgsChecker::new
		arr = ["Hello"]
		assert_equal 0, args_checker.check_value(arr)
	end
	
	# If the seed value entered can be converted to an int, return the int
	def test_valid_seed
		args_checker = ArgsChecker::new
		arr = [87952]
		value = args_checker.check_value(arr)
		assert_equal 87952, value
	end
	
	# If the seed value entered is numbers followed by letters, 
	# it will return the preceding numbers
	# EDGE CASE
	def test_weird_valid_seed
		args_checker = ArgsChecker::new
		arr = ["777hjdskf"]
		value = args_checker.check_value(arr)
		assert_equal 777, value
	end
	
end