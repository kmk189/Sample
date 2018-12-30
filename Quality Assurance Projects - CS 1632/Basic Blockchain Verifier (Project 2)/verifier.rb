require "flamegraph"

require_relative "verifier_methods"
require_relative "block"

class Verifier

	#EXECUTION BEGINS HERE
	Flamegraph.generate('verifier.html') do
		
		raise "Enter input file" unless ARGV.count == 1
		methods = Verifier_Methods::new
		
		input_file = ARGV[0]
		blocks = []

		File.open(input_file, "r").each_line do |line|
			split_line = methods.parse_line line.chomp
			block = Block::new split_line
			blocks << block
		end
		
		#FIRST BLOCK (requires different checks)
		addresses = Hash::new
		addresses_array = []
		block = blocks[0]
		
		#check that the hash is correct for this line
		result = methods.check_hash_value block.hashable, block.end_hash
		if result != nil
			puts "Line #{block.block_num}: String '#{block.hashable}' hash set to #{block.end_hash}, should be #{result}"
			puts "BLOCKCHAIN INVALID"
			exit
		end
		
		#load all the addresses into a hash with their current values
		index = 0
		temp_addresses = []
		
		while index < block.addresses.length
			split_line = methods.parse_transactions block.addresses[index]
			
			if addresses[split_line[0]] == nil
				addresses[split_line[0]] = 0 - split_line[2].to_i
			else
				addresses[split_line[0]] = addresses[split_line[0]] - split_line[2].to_i
			end
			
			if addresses[split_line[1]] == nil
				addresses[split_line[1]] = split_line[2].to_i
			else
				addresses[split_line[1]] = addresses[split_line[1]] + split_line[2].to_i
			end
			
			temp_addresses << split_line[0] unless temp_addresses.include?(split_line[0])
			temp_addresses << split_line[1] unless temp_addresses.include?(split_line[1])
			addresses_array << split_line[0] unless addresses_array.include?(split_line[0])
			addresses_array << split_line[1] unless addresses_array.include?(split_line[1])
			index += 1
		end
		
		#check no addresses have negative coin values
		result = methods.check_coin_totals addresses, temp_addresses
		if result != nil
			puts "Line #{block.block_num}: Invalid block, address #{temp_addresses[result]} has #{addresses[temp_addresses[result]]} billcoins!"
			puts "BLOCKCHAIN INVALID"
			exit 
		end
		temp_addresses.clear
		
		#REMAINING BLOCKS
		index = 1
		while index < blocks.length
			current = blocks[index]
			previous = blocks[index - 1]

			#check for a valid block number
			result = methods.check_block_number previous.block_num, current.block_num
			if result == 0
				puts "Line #{previous.block_num + 1}: Invalid block number #{current.block_num}, should be #{previous.block_num + 1}"
				puts "BLOCKCHAIN INVALID"
				exit
			end

			#check that the start hash equals previous block's end hash
			result = methods.check_hash_equals previous.end_hash, current.start_hash
			if result == 0 
				puts "Line #{current.block_num}: Previous hash was #{current.start_hash}, should be #{previous.end_hash}"
				puts "BLOCKCHAIN INVALID"
				exit
			end
			
			#check if the timestamp is valid
			result = methods.check_timestamp previous.timestamp, current.timestamp
			if result == 0
				puts "Line #{current.block_num}: Previous timestamp #{previous.timestamp[0]}.#{previous.timestamp[1]} >= new timestamp #{current.timestamp[0]}.#{current.timestamp[1]}"
				puts "BLOCKCHAIN INVALID"
				exit
			end
			
			#check if hash value is valid
			result = methods.check_hash_value current.hashable, current.end_hash
			if result != nil
				puts "Line #{current.block_num}: String '#{current.hashable}' hash set to #{current.end_hash}, should be #{result}"
				puts "BLOCKCHAIN INVALID"
				exit
			end
		
			#load all the addresses into a hash with their current values
			index2 = 0
			while index2 < current.addresses.length
				split_line = methods.parse_transactions current.addresses[index2]
				
				if addresses[split_line[0]] == nil
					addresses[split_line[0]] = 0 - split_line[2].to_i
				else
					addresses[split_line[0]] = addresses[split_line[0]] - split_line[2].to_i
				end
				
				if addresses[split_line[1]] == nil
					addresses[split_line[1]] = split_line[2].to_i
				else
					addresses[split_line[1]] = addresses[split_line[1]] + split_line[2].to_i
				end
				
				temp_addresses << split_line[0] unless temp_addresses.include?(split_line[0])
				temp_addresses << split_line[1] unless temp_addresses.include?(split_line[1])
				addresses_array << split_line[0] unless addresses_array.include?(split_line[0])
				addresses_array << split_line[1] unless addresses_array.include?(split_line[1])
				
				index2 += 1
			end

			#check no addresses have negative coin values
			result = methods.check_coin_totals addresses, temp_addresses
			if result != nil
				puts "Line #{current.block_num}: Invalid block, address #{temp_addresses[result]} has #{addresses[temp_addresses[result]]} billcoins!"
				puts "BLOCKCHAIN INVALID"
				exit 
			end
			temp_addresses.clear
			
			index += 1
		end

		#print all the addresses and their coin count
		index = -1
		while index < (addresses_array.length - 1)
			index += 1
			next if addresses_array[index] == "SYSTEM"
			puts "#{addresses_array[index]}: #{addresses[addresses_array[index]]} billcoins"
		end
	end
end