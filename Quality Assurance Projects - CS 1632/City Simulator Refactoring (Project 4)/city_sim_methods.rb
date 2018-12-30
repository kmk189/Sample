require_relative 'driver'
require_relative 'validation_helpers'

# Methods for handling the drivers and their movements
class CitySimMethods
  def initialize(initial_location)
    @driver = Driver.new
    @driver.location initial_location
    @val = ValidationHelpers.new
  end

  # This only exists for stubbing in methods_test.rb
  def add_driver(driver)
    @driver = driver
  end

  # Updates the driver's location
  # Returns the new location (should be the same as the value passed in)
  def update_location(new_location)
    @driver.location new_location
    @driver.location nil
  end

  # Prints out the driver's movement
  # Returns nil if any values are invalid
  def print_move(num, new_loc, street)
    curr_loc = @driver.location nil
    return nil if num.zero? || !(new_loc.is_a? String)
    return nil if !(street.is_a? String) || !(curr_loc.is_a? String)
    puts "Driver #{num} heading from #{curr_loc} to #{new_loc} via #{street}"
  end

  # Prints out the driver's book count
  # Checks for correct plurality of 'book'
  def print_books(num)
    books = @driver.book 0
    puts "Driver #{num} obtained #{books} books!" if books != 1
    puts "Driver #{num} obtained #{books} book!" if books == 1
  end

  # Prints out the driver's dinosaur toy count
  # Checks for correct plurality of 'toy'
  def print_dinos(num)
    dinos = @driver.dino 0
    puts "Driver #{num} obtained #{dinos} dinosaur toys!" if dinos != 1
    puts "Driver #{num} obtained #{dinos} dinosaur toy!" if dinos == 1
  end

  # Prints out the driver's class count
  # Checks for correct plurality of 'class'
  def print_classes(num)
    classes = @driver.class 0
    puts "Driver #{num} attended #{classes} classes!" if classes != 1
    puts "Driver #{num} attended #{classes} class!" if classes == 1
  end

  # Checks if the driver is at one of the two final locations
  # Returns true if they have arrived at a final destination
  # Returns false if they have not arrived at a final destination
  def check_final
    %w[Monroeville Downtown].include?(@driver.location(nil))
  end

  # Adds book, dinosaur toy, or class for the appropriate location if necessary
  def adders(new_location)
    new = new_location
    return (@driver.book 1) if new == 'Hillman'
    return (@driver.dino 1) if new == 'Museum'
    return (@driver.class 1) if new == 'Cathedral'
    nil
  end

  # Determines the street traveled to move from the old location to the new one
  # Returns the street name if valid
  # Returns nil if there is an issue (edge case, should never happen)
  def get_street(new_loc)
    curr_loc = @driver.location nil

    return 'Fourth Ave.' if (@val.fourth_check new_loc, curr_loc).is_a? String
    return 'Fifth Ave.' unless (@val.fifth_check new_loc, curr_loc).zero?
    return 'Foo St.' unless (@val.foo_check new_loc, curr_loc) == 'no'
    @val.bar_check new_loc, curr_loc
  end

  # Determines if the current move is valid based on street directions
  # Returns 1 if the move is valid
  # Returns 0 if the move is not valid
  def check_valid_move(new)
    old = @driver.location nil

    return 0 if new == old

    return (@val.check_new old) if %w[Hillman Cathedral].include?(new)
    return (@val.check_new_c new, old) if %w[Hospital Museum].include?(new)
    1 # if the move is valid
  end

  def check_final_valid_move(new_loc)
    old = @driver.location nil
    final = %w[Downtown Monroeville]

    return 0 if final.include?(old)
    return (@val.check_new_b new_loc, old) if final.include?(new_loc)
    1 # if the move is valid
  end
end
