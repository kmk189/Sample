require "minitest/autorun"
require_relative "verifier_methods"
require_relative "block"

class Verifier_methods_test < Minitest::Test

	def setup
		@methods = Verifier_Methods::new
	end
	
	# UNIT TESTS FOR METHOD parse_line
	# Equivalence Classes:
	# 5 arguments separated by '|' -> returns array of the 5 elements
	# Not 5 arguments -> raises exception
	
	# If the line is a proper format, return an array
	def test_good_parse_line
		line = "0|849|Name1>Name2(100)|8347000.834092000|93a3"
		result = @methods.parse_line line
		assert_equal ["0", "849", "Name1>Name2(100)", "8347000.834092000", "93a3"], result
	end	
	
	# If the line is the wrong number of arguments, raise an exception
	def test_bad_parse_line
		assert_raises "Entry string invalid (Runtime Error)" do
			@methods.parse_line "0|849|Name1>Name2(100)|843984.94328098"
		end
	end
	
	
	# UNIT TESTS FOR METHOD parse_transactions
	# Equivalence Classes:
	# Proper format (NAME>NAME(NUMBER)) -> split into array ([NAME, NAME, NUMBER])
	# Does not contain '>' or '(' -> raises exception
	
	# If the line has proper format, return an array
	def test_good_parse_transactions
		line = "Name1>Name2(100)"
		result = @methods.parse_transactions line
		assert_equal ["Name1", "Name2", "100"], result
	end
	
	# If the line is missing the splitting characters, raise an exception
	def test_bad_parse_transactions
		assert_raises "Transaction invalid (Runtime Error)" do
			@methods.parse_transactions "Name1<Name2(100)"
		end
	end
	
	
	# UNIT TESTS FOR METHOD check_block_number
	# Equivalence Classes:
	# Numbers are 1 different in ascending order -> returns 1
	# Numbers are not 1 apart in ascending order -> returns 0
	
	# The numbers are counting in a valid manner, return 1
	def test_good_check_block
		result = @methods.check_block_number 3, 4
		assert_equal 1, result
	end
	
	# The numbers are out of order, return 0
	def test_bad_check_block
		result = @methods.check_block_number 9, 3
		assert_equal 0, result
	end
	
	
	# UNIT TESTS FOR METHOD check_hash_equals
	# Equivalence Classes:
	# Hash values are equal -> return 1
	# Hash values are not equal -> return 0
	
	# Values provided are equal, so return 1
	def test_hash_equals
		result = @methods.check_hash_equals "95a7", "95a7"
		assert_equal 1, result
	end
	
	# Values provided are not equal, so return 0
	def test_hash_inequal
		result = @methods.check_hash_equals "95a7", "95a8"
		assert_equal 0, result
	end
	
	
	# UNIT TESTS FOR METHOD check_timestamp
	# Equivalence Classes:
	# Seconds timestamp is out of order -> return 0
	# Seconds timestamp equal, nanoseconds timestamp out of order -> return 0
	# Both timestamps in order -> return 1
	
	# First timestamp (seconds) is incorrect, so return 0
	def test_seconds_wrong
		result = @methods.check_timestamp ["9423801", "729482900"], ["9423800", "729482901"]
		assert_equal 0, result
	end
	
	# Second timestamp (nanoseconds) is incorrect, so return 0
	def test_nanoseconds_wrong
		result = @methods.check_timestamp ["9423800", "729482900"], ["9423800", "729482900"]
		assert_equal 0, result
	end
	
	# Both seconds and nanoseconds are correct, so return 1
	def test_timestamp_correct
		result = @methods.check_timestamp ["9423800", "729482900"], ["9423800", "729482999"]
		assert_equal 1, result
	end
	
	
	# UNIT TESTS FOR METHOD check_hash_value
	# Equivalence Classes:
	# Hash value calculated equals provided value -> return nil
	# Hash value provided is incorrect -> return calculated hash
	
	# Incorrect hash value, so return the correct value
	def test_incorrect_hash
		result = @methods.check_hash_value "0|0|SYSTEM>Henry(100)|1518892051.737141000", "1111"
		assert_equal "1c12", result
	end
	
	# Correct hash value, so return nil
	def test_correct_hash
		result = @methods.check_hash_value "0|0|SYSTEM>Henry(100)|1518892051.737141000", "1c12"
		assert_nil result
	end
	
	
	# UNIT TESTS FOR METHOD check_coin_totals
	# Equivalence Classes:
	# One of the hash values (excluding SYSTEM) is less than 0 -> return the position in array it is found at
	# No hash values are below 0 -> return nil
	
	# All has values (excluding SYSTEM) are valid, so return nil
	def test_valid_coin_totals
		test_hash = Hash::new
		test_hash["Bob"] = 100
		test_hash["Joe"] = 200
		test_hash["Ann"] = 300
		test_array = ["Bob", "Joe", "Ann"]
		result = @methods.check_coin_totals test_hash, test_array
		assert_nil result
	end
	
	# One of the hash values (not SYSTEM) is below 0, so return that position in the array
	def test_bad_coin_total
		test_hash = Hash::new
		test_hash["Bob"] = 100
		test_hash["Joe"] = -200
		test_hash["Ann"] = 300
		test_array = ["Bob", "Joe", "Ann"]
		result = @methods.check_coin_totals test_hash, test_array
		assert_equal 1, result
	end
	
	# SYSTEM is below zero which should not matter, so return nil
	def test_system_coins
		test_hash = Hash::new
		test_hash["Bob"] = 100
		test_hash["Joe"] = 200
		test_hash["SYSTEM"] = -300
		test_array = ["Bob", "Joe", "SYSTEM"]
		result = @methods.check_coin_totals test_hash, test_array
		assert_nil result
	end
	
	# Coin totals are at 0 which should not matter, so return nil
	def test_zero_coins
		test_hash = Hash::new
		test_hash["Bob"] = 0
		test_hash["Joe"] = 0
		test_hash["Ann"] = 0
		test_array = ["Bob", "Joe", "Ann"]
		result = @methods.check_coin_totals test_hash, test_array
		assert_nil result
	end
	
end