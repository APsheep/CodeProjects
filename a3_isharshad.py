def isHarshad(num):
  digits = [int(d) for d in str(num)]
  total = sum(digits)
  if total == 0:
    return False
  if num % total == 0:
    return True
  else:
    return False

harshad_numbers = []

for n in range(1, 501):
  if isHarshad(n):
        harshad_numbers.append(n)
print(harshad_numbers)
