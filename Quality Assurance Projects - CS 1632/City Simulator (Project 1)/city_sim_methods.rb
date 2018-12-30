require_relative "driver"

class City_sim_methods
	
	def initialize initial_location
		@driver = Driver::new
		@driver.current_location = initial_location
	end
	
	# This only exists for stubbing in methods_test.rb
	def add_driver driver
		@driver = driver
	end
	
	# Updates the driver's location
	# Returns the new location (should be the same as the value passed in)
	def update_location new_location
		@driver.current_location = new_location
		@driver.current_location
	end
	
	# Prints out the driver's movement: old location, new location, and street traveled
	# Returns nil if any values are invalid
	def print_move driver_num, new_location, street
		return nil if driver_num == 0 || new_location == nil || street == nil || @driver.current_location == nil
		puts "Driver #{driver_num} heading from #{@driver.current_location} to #{new_location} via #{street}"
	end
	
	# Prints out the driver's book count, checking for correct plurality of 'book'
	def print_books driver_num
		puts "Driver #{driver_num} obtained #{@driver.books} books!" if @driver.books != 1
		puts "Driver #{driver_num} obtained #{@driver.books} book!" if @driver.books == 1
	end
	
	# Prints out the driver's dinosaur toy count, checking for correct plurality of 'toy'
	def print_dinos driver_num
		puts "Driver #{driver_num} obtained #{@driver.dinos} dinosaur toys!" if @driver.dinos != 1
		puts "Driver #{driver_num} obtained #{@driver.dinos} dinosaur toy!" if @driver.dinos == 1
	end
	
	# Prints out the driver's class count, checking for correct plurality of 'class'
	def print_classes driver_num	
		puts "Driver #{driver_num} attended #{@driver.classes} classes!" if @driver.classes != 1
		puts "Driver #{driver_num} attended #{@driver.classes} class!" if @driver.classes == 1
	end
	
	# Checks if the driver is at one of the two final locations
	# Returns true if they have arrived at a final destination
	# Returns false if they have not arrived at a final destination
	def check_final 
		@driver.current_location == "Monroeville" || @driver.current_location == "Downtown"
	end
	
	# Adds book, dinosaur toy, or class for the appropriate location if necessary
	def adders new_location
		@driver.add_book if new_location == "Hillman"
		@driver.add_dino if new_location == "Museum"
		@driver.add_class if new_location == "Cathedral"
	end

	# Determines the street traveled to move from the old location to the new one
	# Returns the street name if valid
	# Returns nil if there is an issue (edge case, should never happen)
	def get_street new_location
		fourth = ["Hospital", "Cathedral", "Monroeville"]
		fifth = ["Downtown", "Hillman", "Museum"]
		both = [new_location, @driver.current_location]
		
		return nil if both[0] == both[1]
		return "Fourth Ave." if fourth.include?(both[0]) && fourth.include?(both[1])
		return "Fifth Ave." if fifth.include?(both[0]) && fifth.include?(both[1])
		return "Foo St." if both.include?("Hospital") && both.include?("Hillman")
		return "Bar St." if both.include?("Cathedral") && both.include?("Museum")
		
		nil #this should never be returned unless something went wrong
	end

	# Determines if the current move is valid based on street directions
	# Returns 1 if the move is valid
	# Returns 0 if the move is not valid
	def check_valid_move new_location
		old_location = @driver.current_location

		return 0 if old_location == "Downtown" || old_location == "Monroeville"
		return 0 if new_location == old_location
		return 0 if new_location == "Hospital" && old_location != "Hillman"
		return 0 if new_location == "Hillman" && (old_location != "Hospital" && old_location != "Museum")
		return 0 if new_location == "Cathedral" && (old_location != "Hospital" && old_location != "Museum")
		return 0 if new_location == "Museum" && old_location != "Cathedral"
		return 0 if new_location == "Downtown" && old_location != "Hillman"
		return 0 if new_location == "Monroeville" && old_location != "Cathedral"
		1 #if the move is valid
	end
	
end