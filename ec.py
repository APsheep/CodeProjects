import matplotlib.pyplot as plt

def mainUS():
  with open("rands.txt", "r") as f:
    raw = f.read()
  raw = raw.strip()
  raw = raw.replace('"', "")
  raw_list = raw.split()
  data_list = []
  for x in raw_list:
    num = int(x)
    data_list.append(num)

  x = list(range(len(data_list)))
  y = data_list

  plt.plot(x, y, color='green')
  plt.title("Unsorted: Index vs Value")
  plt.xlabel("Index")
  plt.ylabel("Value")

  plt.show()

def mainS():
  with open("rands.txt", "r") as f:
    raw = f.read()
  raw = raw.strip()
  raw = raw.replace('"', "")
  raw_list = raw.split()
  data_list = []
  for x in raw_list:
    num = int(x)
    data_list.append(num)
  data_list.sort()

  x = list(range(len(data_list)))
  y = data_list

  plt.plot(x, y, color='blue')
  plt.title("Sorted: Index vs Value")
  plt.xlabel("Index")
  plt.ylabel("Value")

  plt.show()

mainUS()
mainS()

