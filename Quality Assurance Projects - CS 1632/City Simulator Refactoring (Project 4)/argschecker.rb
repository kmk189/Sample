# Checks the initial argument given on command line
class ArgsChecker
  def check_args(arr)
    return 1 if arr.count == 1
    raise 'Enter a seed and only a seed (RuntimeError)'
  end

  def check_value(arr)
    value = arr[0].to_i
    return 0 unless value.is_a? Integer
    value
  end
end
