#Akshata Padalkar(anp178) U2A2
MyList = [ 23, -45, 6, -23, -9, 77, 54, -54, 21, -2, 8, -3, -23, 45, 93, -43, 999, -2, 3, 78, 90 ]
sum = 0
c = 0
for i in MyList:
  if i == 999:
    break
  elif i < 0:
    sum += i
    c = c + 1
print(sum/c)
