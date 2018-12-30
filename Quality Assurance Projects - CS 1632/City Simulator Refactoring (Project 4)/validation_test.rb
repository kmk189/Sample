require 'simplecov'
SimpleCov.start

require "minitest/autorun"
require_relative "validation_helpers"

class Validation_test < Minitest::Test

	def setup
		@methods = ValidationHelpers.new
	end

	# UNIT TESTS FOR METHOD check_new
	# Equivalence classes
	# 'Hospital' -> 1
	# Anything else -> 0
	
	# If 'Hospital' is passed in, check_new should return 1
	def test_hospital_new
		assert_equal 1, @methods.check_new('Hospital')
	end
	
	# If anything other than 'Hospital' is passed in, it should return 0
	def test_not_hospital_new
		assert_equal 0, @methods.check_new('Cathedral')
	end
	
	
	# UNIT TESTS FOR METHOD check_new_b
	# Equivalence classes
	# 'Downtown', not 'Hillman' -> 0
	# 'Monroeville', not 'Cathedral' -> 0
	# Anything else -> 1
	
	# If 'Downtown', 'Hillman' are passed in, it should return 0
	def test_down_hill_b
		assert_equal 0, @methods.check_new_b('Downtown', 'Cathedral')
	end
	
	# If 'Monroeville', 'Cathedral' are passed in, it should return 0
	def test_mon_cath_b
		assert_equal 0, @methods.check_new_b('Monroeville', 'Hillman')
	end
	
	# If anything else is passed in, it should return 1
	def test_other_b
		assert_equal 1, @methods.check_new_b('Downtown', 'Hillman')
	end
	
	
	# UNIT TESTS FOR METHOD check_new_c
	# Equivalence classes
	# 'Hospital', not 'Hillman' -> 0
	# 'Museum', not 'Cathedral' -> 0
	# Anything else -> 1
	
	# If 'Hospital', 'Hillman' are passed in, it should return 0
	def test_hosp_hill_c
		assert_equal 0, @methods.check_new_c('Hospital', 'Cathedral')
	end
	
	# If 'Museum', 'Cathedral' are passed in, it should return 0
	def test_mus_cath_c
		assert_equal 0, @methods.check_new_c('Museum', 'Hillman')
	end
	
	# If anything else is passed in, it should return 1
	def test_other_c
		assert_equal 1, @methods.check_new_c('Hospital', 'Hillman')
	end
	
	# UNIT TESTS FOR METHOD fourth_check
	# Equivalence classes
	# 'Hospital', 'Cathedral', 'Monroeville' passed in any combination -> 'valid'
	# Anything else for either value -> nil
	
	# If one of the valid values is passed in, it should return 'valid'
	def test_fourth_valid
		assert_equal 'valid', @methods.fourth_check('Hospital', 'Monroeville')
	end
	
	# If an invalid value is passed in, it should return nil
	def test_fourth_invalid
		value = @methods.fourth_check('Hospital', 'Hillman')
		assert_nil value
	end
	
	
	# UNIT TESTS FOR METHOD fifth_check
	# Equivalence classes
	# 'Downtown', 'Hillman', 'Museum' passed in any combination -> 1
	# Anything else for either value -> 0

	# If one of the valid values is passed in, it should return 1
	def test_fifth_valid
		assert_equal 1, @methods.fifth_check('Downtown', 'Museum')
	end
	
	# If an invalid value is passed in, it should return 0
	def test_fifth_invalid
		value = @methods.fifth_check('Hillman', 'Hospital')
		assert_equal 0, value
	end
	
	
	# UNIT TESTS FOR METHOD foo_check
	# Equivalence classes
	# 'Hospital' and 'Hillman' passed in any order -> 'yes'
	# Anything else for either value -> 'no'

	# If one of the valid values is passed in, it should return 'yes'
	def test_foo_valid
		assert_equal 'yes', @methods.foo_check('Hillman', 'Hospital')
	end
	
	# If an invalid value is passed in, it should return 'no'
	def test_foo_invalid
		value = @methods.foo_check('Hillman', 'Cathedral')
		assert_equal 'no', value
	end
	
	
	# UNIT TESTS FOR METHOD bar_check
	# Equivalence classes
	# 'Cathedral' and 'Museum' passed in any order -> 'Bar St.'
	# Anything else for either value -> nil
	
	# If one of the valid values is passed in, it should return 'Bar St.'
	def test_bar_valid
		assert_equal 'Bar St.', @methods.bar_check('Museum', 'Cathedral')
	end
	
	# If an invalid value is passed in, it should return nil
	def test_bar_invalid
		value = @methods.bar_check('Museum', 'Hospital')
		assert_nil value
	end
	
end
