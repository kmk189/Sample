require 'simplecov'
SimpleCov.start

require "minitest/autorun"
require_relative "driver"

class Driver_test < Minitest::Test

	def setup
		@new_driver = Driver::new
	end

	# UNIT TEST FOR METHOD book
	
	# When called, the book count should be raised by the given value
	# Return value should be the total number of books
	def test_add_book
		assert_equal 1, @new_driver.book(1)
	end
	
	# Tests when there is no book added, should work like a get() function
	def test_get_book
		assert_equal 0, @new_driver.book(0)
	end
	
	
	# UNIT TEST FOR METHOD dino
	
	# When called, the dinosaur toy count should be raised by one
	def test_add_dino
		assert_equal 1, @new_driver.dino(1)
	end
	
	# Tests when there is no dino added, should work like a get() function
	def test_get_dino
		@new_driver.dino 5
		assert_equal 5, @new_driver.dino(0)
	end
	
	
	# UNIT TESTS FOR METHOD class
	# Possible Return Values:
	# First class added -> 1
	# Not first class added -> classes * 2 (1,2,4,8...)
	# 0 passed in -> returns current class count
	
	# If class count is 0, only one class should be added
	def test_add_initial_class
		assert_equal 1, @new_driver.class(1)
	end
	
	# If class count is >0, the class count should be doubled
	def test_add_class
		@new_driver.class 1
		@new_driver.class 1
		assert_equal 4, @new_driver.class(1)
	end
	
	# If 0 is passed into the class function, the current class count is returned
	def test_get_class
		@new_driver.class 1
		@new_driver.class 1
		@new_driver.class 1
		assert_equal 4, @new_driver.class(0)
	end
	
	
	# UNIT TESTS FOR METHOD location
	# Equivalence Classes:
	# nil -> returns current location
	# not nil -> updates current location and returns the new one
	
	# If a string is passed into location function, it updates and returns
	def test_new_location
		@new_driver.location 'Cathedral'
		assert_equal 'Hillman', @new_driver.location('Hillman')
	end
	
	# If nil is passed into location function, it returns the current value
	def test_nil_location
		@new_driver.location 'Cathedral'
		assert_equal 'Cathedral', @new_driver.location(nil)
	end
end