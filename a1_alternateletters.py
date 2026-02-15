#Akshata Padalkar(anp178) U2A1
s = str(input("Please enter a word: "))
word = list(s)
for i in range(0,len(word)):
  if i % 2 == 0:
    word[i] = word[i].upper()
  else:
    word[i] = word[i].lower()
print("".join(word))
