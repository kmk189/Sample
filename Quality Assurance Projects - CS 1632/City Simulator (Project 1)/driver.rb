class Driver
	
	attr_accessor :books
	attr_accessor :dinos
	attr_accessor :classes
	attr_accessor :current_location
	
	def initialize
		@books = 0
		@dinos = 0
		@classes = 0
		@current_location = nil
	end
	
	def add_book
		@books += 1
	end
	
	def add_dino
		@dinos += 1
	end
	
	def add_class
		@classes *= 2 if @classes != 0
		@classes += 1 if @classes == 0
		@classes
	end
	
end