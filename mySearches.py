# binary search
def bsearch(target, data_list):
    count = 0
    low = 0
    item = 0
    high = len(data_list) - 1

    while low <= high:
      count += 1
      mid = (low + high) // 2
      item = data_list[mid]

      if target == item:
        return mid, count
      elif target < item:
        high = mid - 1
      else:
        low = mid + 1
    return -1, count


# linear search
def lsearch(target, data_list):
  count = 0
  for i, num in enumerate(data_list):
        count += 1
        if target == num:
            return i, count
  return -1, count
