require_relative "argschecker"
require_relative "city_sim_methods"

class City_sim_9006
	
	arr = ["Hillman", "Cathedral", "Museum", "Hospital", "Monroeville", "Downtown"]
	
	arg_checker = ArgsChecker::new
	arg_checker.check_args ARGV
	seed_value = arg_checker.check_value ARGV
	
	puts seed_value
	
	prng = Random.new(seed_value)
	driver_num = 1
	
	while driver_num < 6
		new_location = arr[(prng.rand * 500).to_i % 4]
		methods = City_sim_methods::new new_location
		methods.adders new_location
		
		while !methods.check_final
			valid = 0
			
			while valid == 0
				new_location = arr[(prng.rand * 500).to_i % 6]
				valid = methods.check_valid_move new_location
			end

			methods.adders new_location
			street = methods.get_street new_location

			methods.print_move driver_num, new_location, street 
			methods.update_location new_location			
		
		end	
		
		#later after Monroeville or downtown reached
		methods.print_books driver_num
		methods.print_dinos driver_num
		methods.print_classes driver_num
		
		driver_num += 1
	end
	
end