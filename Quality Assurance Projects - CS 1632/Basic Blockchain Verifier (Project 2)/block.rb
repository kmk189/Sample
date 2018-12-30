class Block

	attr_accessor :block_num
	attr_accessor :start_hash
	attr_accessor :addresses
	attr_accessor :timestamp
	attr_accessor :end_hash
	attr_accessor :hashable
	
	def initialize arr
		@block_num = arr[0].to_i
		@start_hash = arr[1]
		@addresses = arr[2].split(":")
		@timestamp = arr[3].split(".")
		@end_hash = arr[4]
		@hashable = arr[0].concat("|").concat(arr[1]).concat("|").concat(arr[2]).concat("|").concat(arr[3])
	end
	
end