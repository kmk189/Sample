# Helper methods for get_street() and check_valid_move()
class ValidationHelpers
  def check_new(old_location)
    old = old_location
    return 0 unless %w[Hospital Museum].include?(old)
    1
  end

  def check_new_b(new_loc, old_loc)
    old = old_loc
    new = new_loc
    return 0 if new == 'Downtown' && old != 'Hillman'
    return 0 if new == 'Monroeville' && old != 'Cathedral'
    1
  end

  def check_new_c(new_loc, old_loc)
    old = old_loc
    new = new_loc
    return 0 if new == 'Hospital' && old != 'Hillman'
    return 0 if new == 'Museum' && old != 'Cathedral'
    1
  end

  def fourth_check(new, curr)
    new_loc = new
    curr_loc = curr
    fourth = %w[Hospital Cathedral Monroeville]
    return 'valid' if fourth.include?(new_loc) && fourth.include?(curr_loc)
    nil
  end

  def fifth_check(new_loc, curr_loc)
    new = new_loc
    curr = curr_loc
    fifth = %w[Downtown Hillman Museum]
    return 1 if fifth.include?(new) && fifth.include?(curr)
    0
  end

  def foo_check(new_loc, curr_loc)
    new = new_loc
    curr = curr_loc
    foo = %w[Hospital Hillman]
    return 'yes' if foo.include?(new) && foo.include?(curr)
    'no'
  end

  def bar_check(new, curr)
    new_loc = new
    curr_loc = curr
    bar = %w[Cathedral Museum]
    return 'Bar St.' if bar.include?(new_loc) && bar.include?(curr_loc)
    nil
  end
end
