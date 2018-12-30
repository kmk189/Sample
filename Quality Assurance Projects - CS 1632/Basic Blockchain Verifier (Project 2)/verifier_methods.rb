class Verifier_Methods

	def parse_line new_line
		values = new_line.split("|")
		raise "Entry string invalid (Runtime Error)" if values.length != 5
		values
	end
	
	def parse_transactions line
		#starts as NAME>NAME(##)
		raise "Transaction invalid (Runtime Error)" unless line.include?(">") && line.include?("(")
		values = []
		values1 = line.split(">")
		values2 = values1[1].split("(")
		values3 = values2[1].split(")")
		values << values1[0]
		values << values2[0]
		values << values3[0]
		values
	end
	
	def check_block_number num1, num2
		#num1 = previous block #, num2 = current block #
		return 0 if (num2.to_i - num1.to_i) != 1
		1
	end
	
	def check_hash_equals hash1, hash2
		#hash1 = previous block's end hash, #hash2 = current block's start hash
		return 0 if hash1 != hash2
		1
	end
	
	def check_timestamp stamp1, stamp2
		#stamp1 = previous block's timestamp array
		#stamp2 = current block's timestamp array
		return 0 if stamp1[0].to_i > stamp2[0].to_i
		return 0 if stamp1[1].to_i >= stamp2[1].to_i && stamp1[0].to_i == stamp2[0].to_i
		1
	end

	def check_hash_value whole_line, hash_value
		calculated_hash = (whole_line.unpack('U*').map { |x| (x ** 2000) * ((x + 2) ** 21) - ((x + 5) ** 3) }.reduce(:+) % 65536).to_s(16) 
		return calculated_hash if calculated_hash != hash_value
		nil
	end
	
	def check_coin_totals hash1, arr
		x = 0
		while x < arr.length
			return x if hash1[arr[x]] < 0 && arr[x] != "SYSTEM"
			x += 1
		end
		nil
	end
	
end



















