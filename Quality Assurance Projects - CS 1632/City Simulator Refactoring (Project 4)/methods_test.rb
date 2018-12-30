require 'simplecov'
SimpleCov.start

require 'minitest/autorun'
require_relative 'city_sim_methods'
require_relative 'driver'

class Methods_test < Minitest::Test

	def setup
		@methods = CitySimMethods::new nil
	end
	
	# UNIT TESTS FOR METHOD print_move
	# Equivalence Classes:
	# Valid arguments -> prints
	# Invalid arguments (nil) -> does not print
	
	# If arguments are passed in, they will be printed in format
	def test_print_move_good
		d1 = Minitest::Mock::new 'driver 1'
		d1.expect(:location, 'Hillman', [nil])
		@methods.add_driver d1
		assert_output (/Driver 1 heading from Hillman to Hospital via Foo St./) {@methods.print_move 1, 'Hospital', 'Foo St.'}
	end
	
	# If any values passed into the print method are nil, it will not print 
	# EDGE CASE
	def test_print_move_bad
		d1 = Minitest::Mock::new 'driver 1'
		d1.expect(:location, 'Hillman', [nil])
		@methods.add_driver d1
		assert_output ('') {@methods.print_move(1, nil, nil)}
	end
	
	
	# UNIT TESTS FOR METHOD print_books
	# Equivalence Classes:
	# 1 book -> book
	# Not 1 book -> books
	
	# If there are zero books, it should print plural 'books'
	def test_zero_books
		d1 = Minitest::Mock::new 'driver 1'
		d1.expect(:book, 0, [0])
		@methods.add_driver d1
		assert_output (/Driver 1 obtained 0 books!/) {@methods.print_books 1}
	end
	
	# If there is one book, it should print singular 'book'
	def test_one_books
		d1 = Minitest::Mock::new 'driver 1'
		d1.expect(:book, 1, [0])
		@methods.add_driver d1
		assert_output (/Driver 1 obtained 1 book!/) {@methods.print_books 1}
	end

	# If there are more than 1 book, it should print plural 'books'
	def test_two_books
		d1 = Minitest::Mock::new 'driver 1'
		d1.expect(:book, 2, [0])
		@methods.add_driver d1
		assert_output (/Driver 1 obtained 2 books!/) {@methods.print_books 1}
	end


	# UNIT TESTS FOR METHOD print_dinos
	# Equivalence Classes:
	# 1 dino -> dinosaur toy
	# Not 1 dino -> dinosaur toys
	
	# If there are zero dinos, it should print plural 'dinosaur toys'
	def test_zero_dinos
		d1 = Minitest::Mock::new 'driver 1'
		d1.expect(:dino, 0, [0])
		@methods.add_driver d1
		assert_output (/Driver 1 obtained 0 dinosaur toys!/) {@methods.print_dinos 1}
	end
	
	# If there is one dinosaur toy, it should print singular 'dinosaur toy'
	def test_one_dino
		d1 = Minitest::Mock::new 'driver 1'
		d1.expect(:dino, 1, [0])
		@methods.add_driver d1
		assert_output (/Driver 1 obtained 1 dinosaur toy!/) {@methods.print_dinos 1}
	end

	# If there are more than 1 dino, it should print plural 'dinosaur toys'
	def test_two_dinos
		d1 = Minitest::Mock::new 'driver 1'
		d1.expect(:dino, 2, [0])
		@methods.add_driver d1
		assert_output (/Driver 1 obtained 2 dinosaur toys!/) {@methods.print_dinos 1}
	end

	
	# UNIT TESTS FOR METHOD print_classes
	# Equivalence Classes:
	# 1 class -> class
	# Not 1 class -> classes
	
	# If there are zero classes, it should print plural 'classes'
	def test_zero_classes
		d1 = Minitest::Mock::new 'driver 1'
		d1.expect(:class, 0, [0])
		@methods.add_driver d1
		assert_output (/Driver 1 attended 0 classes!/) {@methods.print_classes 1}
	end
	
	# If there is one class, it should print singular 'class'
	def test_one_classes
		d1 = Minitest::Mock::new 'driver 1'
		d1.expect(:class, 1, [0])
		@methods.add_driver d1
		assert_output (/Driver 1 attended 1 class!/) {@methods.print_classes 1}
	end

	# If there are more than 1 class, it should print plural 'classes'
	def test_two_classes
		d1 = Minitest::Mock::new 'driver 1'
		d1.expect(:class, 2, [0])
		@methods.add_driver d1
		assert_output (/Driver 1 attended 2 classes!/) {@methods.print_classes 1}
	end


	# UNIT TESTS FOR METHOD check_final
	# Possible Results:
	# Monroeville or Downtown -> true
	# Anything else -> false
	
	# If the location of driver is Monroeville, it will return true
	def test_final_true
		d1 = Minitest::Mock::new 'driver 1'
		d1.expect(:location, 'Monroeville', [nil])
		@methods.add_driver d1
		result = @methods.check_final
		assert_equal(true, result)
	end
	
	# If the location of driver is anything other than Monroeville or 
	# Downtown, then it will return false
	def test_final_false
		d1 = Minitest::Mock::new 'driver 1'
		d1.expect(:location, 'Hillman', [nil])
		@methods.add_driver d1
		result = @methods.check_final
		assert_equal(false, result)
	end
	
	# UNIT TESTS FOR METHOD update_location
	
	# If called, the driver's current_location will be updated with the input
	def test_update_location
		d1 = CitySimMethods::new 'Hillman'
		location = d1.update_location 'Hospital'
		assert_equal('Hospital', location)
	end
	
	
	
	# UNIT TESTS FOR METHOD get_street
	# Equivalence Classes:
	# Returns Fourth Ave, Fifth Ave, Foo St, or Bar St based on locations
	# Returns nil if invalid locations or moves
	
	# If both locations are on fourth ave, return Fourth Ave.
	def test_street_fourth
		d1 = Minitest::Mock::new 'driver 1'
		d1.expect(:location, 'Hospital', [nil])
		@methods.add_driver d1
		street = @methods.get_street 'Cathedral'
		assert_equal 'Fourth Ave.', street
	end
	
	# If both locations are on fifth ave, return Fifth Ave.
	def test_street_fifth
		d1 = Minitest::Mock::new 'driver 1'
		d1.expect(:location, 'Hillman', [nil])
		@methods.add_driver d1
		street = @methods.get_street 'Museum'
		assert_equal 'Fifth Ave.', street
	end
	
	# If both locations are on foo st, return Foo St.
	def test_street_foo
		d1 = Minitest::Mock::new 'driver 1'
		d1.expect(:location, 'Hospital', [nil])
		@methods.add_driver d1
		street = @methods.get_street 'Hillman'
		assert_equal 'Foo St.', street
	end
	
	# If both locations are on Bar St, return Bar St.
	def test_street_bar
		d1 = Minitest::Mock::new 'driver 1'
		d1.expect(:location, 'Museum', [nil])
		@methods.add_driver d1
		street = @methods.get_street 'Cathedral'
		assert_equal 'Bar St.', street
	end	
	
	
	# UNIT TESTS FOR METHOD adders
	# Possible Results:
	# Hillman -> books + 1
	# Museum -> dinos + 1
	# Cathedral -> classes added
	# Other -> No change to any
	
	# If Hillman, book count goes up 1
	def test_adder_book
		d1 = CitySimMethods::new 'Hospital'
		d1.adders 'Hillman'
		result = d1.adders 'Hillman'
		assert_equal 2, result
	end
	
	# If Museum, dino count goes up 1
	def test_adder_dino
		d1 = CitySimMethods::new 'Cathedral'
		result = d1.adders 'Museum'
		assert_equal 1, result
	end
	
	# If Cathedral, class count goes up 1
	def test_adder_class
		d1 = CitySimMethods::new 'Museum'
		result = d1.adders 'Cathedral'
		assert_equal 1, result
	end
	
	# If Cathedral more than once, class count goes up x2
	def test_adder_class_multiple
		d1 = CitySimMethods::new 'Museum'
		d1.adders 'Cathedral'
		d1.adders 'Cathedral'
		result = d1.adders 'Cathedral'
		assert_equal 4, result
	end
	# If anything else, returns nil
	def test_adder_book
		assert_nil @methods.adders('Hospital')
	end
	
	
	
	# UNIT TESTS FOR METHOD check_valid_move
	# Equivalence Classes:
	# Valid moves -> 1
	# Invalid moves -> 0

	# If the same location is given for both the current and new location, it returns 0
	def test_check_move_same
		d1 = Minitest::Mock::new 'driver 1'
		d1.expect(:location, 'Hillman', [nil])
		@methods.add_driver d1
		result = @methods.check_valid_move 'Hillman'
		assert_equal 0, result
	end
	
	# If an invalid move is made (diagonal), it will return 0
	def test_check_move_diagonal
		d1 = Minitest::Mock::new 'driver 1'
		d1.expect(:location, 'Hillman', [nil])
		v1 = Minitest::Mock::new 'validation 1'
		v1.expect(:check_new, 0, ['Hillman'])
		@methods.add_driver d1
		result = @methods.check_valid_move 'Cathedral'
		assert_equal 0, result
	end
	
	# If a valid move is made (Hospital -> Hillman), it will return 0
	def test_check_valid_move
		d1 = Minitest::Mock::new 'driver 1'
		d1.expect(:location, 'Hospital', [nil])
		v1 = Minitest::Mock::new 'validation 1'
		v1.expect(:check_new, 0, ['Hillman'])
		@methods.add_driver d1
		result = @methods.check_valid_move 'Hillman'
		assert_equal 1, result
	end
	
	# If an invalid move (backwards on Fourth) is made, it returns 0
	def test_check_move_backwards
		d1 = Minitest::Mock::new 'driver 1'
		d1.expect(:location, 'Cathedral', [nil])
		v1 = Minitest::Mock::new 'validation 1'
		v1.expect(:check_new_c, 0, ['Hospital', 'Cathedral'])
		@methods.add_driver d1
		result = @methods.check_valid_move 'Hospital'
		assert_equal 0, result
	end
	
	# If a valid move is made, it will return 1
	def test_check_move_valid
		d1 = Minitest::Mock::new 'driver 1'
		d1.expect(:location, 'Hillman', [nil])
		@methods.add_driver d1
		result = @methods.check_valid_move 'Downtown'
		assert_equal 1, result
	end
	
	
	# UNIT TESTS FOR METHOD check_final_valid_move
	# Equivalence Classes:
	# Valid moves -> 1
	# Invalid moves -> 0
	
	# If the current location is an ending location, it returns 0
	# EDGE CASE
	def test_final_already_reached
		d1 = Minitest::Mock::new 'driver 1'
		d1.expect(:location, 'Downtown', [nil])
		@methods.add_driver d1
		result = @methods.check_final_valid_move 'Hillman'
		assert_equal 0, result
	end
	
	# If the new location is a final destination, and the move is invalid, it returns 0
	def test_final_move_invalid
		d1 = Minitest::Mock::new 'driver 1'
		d1.expect(:location, 'Museum', [nil])
		v1 = Minitest::Mock::new 'validation 1'
		v1.expect(:check_new_b, 0, ['Downtown', 'Museum'])
		@methods.add_driver d1
		result = @methods.check_final_valid_move 'Downtown'
		assert_equal 0, result
	end
	
	# If the move is valid, it returns 1
	def test_final_move_valid
		d1 = Minitest::Mock::new 'driver 1'
		d1.expect(:location, 'Cathedral', [nil])
		@methods.add_driver d1
		result = @methods.check_final_valid_move 'Museum'
		assert_equal 1, result
	end
end