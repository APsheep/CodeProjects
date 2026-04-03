#Akshata Padalkar(anp178) U4A2
from mySearches import bsearch, lsearch

def main():
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


  targets = [78700, 3333, 1118]

  for x in targets:
    b_index, b_count = bsearch(x, data_list)
    l_index, l_count = lsearch(x, data_list)

    print(f"Searching for {x}:")

    if b_index != -1:
        print(f"  Binary Search: Found at index {b_index} in {b_count} lookups")
    else:
        print(f"  Binary Search: Not found in {b_count} lookups")

    if l_index != -1:
        print(f"  Linear Search: Found at index {l_index} in {l_count} lookups")
    else:
        print(f"  Linear Search: Not found in {l_count} lookups")

    print() 

# run main
if __name__ == "__main__":
    main()
