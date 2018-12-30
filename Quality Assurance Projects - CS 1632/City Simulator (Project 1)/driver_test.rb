require "minitest/autorun"
require_relative "driver"

class Driver_test < Minitest::Test

	def setup
		@new_driver = Driver::new
	end

	# UNIT TEST FOR METHOD add_book
	
	# When called, the book count should be raised by one
	def tests_add_book
		assert_equal 1, @new_driver.add_book
	end
	
	# UNIT TEST FOR METHOD add_dino
	
	# When called, the dinosaur toy count should be raised by one
	def test_add_dino
		assert_equal 1, @new_driver.add_dino
	end
	
	# UNIT TESTS FOR METHOD add_class
	# Possible Return Values:
	# First class added -> 1
	# Not first class added -> classes * 2 (1,2,4,8...)
	
	# If class count is 0, only one class should be added
	def test_add_initial_class
		assert_equal 1, @new_driver.add_class
	end
	
	# If class count is >0, the class count should be doubled
	def test_add_class
		@new_driver.add_class
		@new_driver.add_class
		assert_equal 4, @new_driver.add_class
	end
end