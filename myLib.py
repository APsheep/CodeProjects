
Hodges = 14

def isHarshad(num):
  digits = [int(d) for d in str(num)]
  total = sum(digits)
  if total == 0:
    return False
  if num % total == 0:
    return True
  else:
    return False


def isSiete(num):
    return (num // 10) % 10 == 7
