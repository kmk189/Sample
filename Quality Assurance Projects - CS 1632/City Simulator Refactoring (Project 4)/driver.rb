# Object to represent the drivers in city_sim_9006
class Driver
  def initialize
    @books = 0
    @dinos = 0
    @classes = 0
    @current_location = nil
  end

  def location(loc)
    @current_location = loc if loc.is_a? String
    @current_location
  end

  def book(num)
    @books += num
    @books
  end

  def dino(num_add)
    @dinos += num_add
    @dinos
  end

  def class(bool_num)
    return @classes if bool_num.zero?
    @classes *= 2 if @classes != 0
    @classes += 1 if @classes.zero?
    @classes
  end
end
