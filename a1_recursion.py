#Akshata Padalkar(anp178) U4A1
string = input("Provide a String: ").lower()
character = input("Provide a Character: ").lower()
count = 0
def counting(string,character):
  if string == "":
    return 0
  elif string[0] == character:
    return 1 + counting(string[1:], character)
  else:
    return counting(string[1:], character)

result = counting(string, character)
print(result)
