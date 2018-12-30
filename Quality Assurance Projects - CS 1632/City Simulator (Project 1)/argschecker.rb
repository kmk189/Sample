class ArgsChecker
	def check_args arr
		raise "Enter a seed and only a seed (RuntimeError)" unless arr.count == 1
		return 1 if arr.count == 1
	end
	
	def check_value arr
		n = arr[0].to_i
		return 0 unless n.is_a? Integer
		n
	end
end