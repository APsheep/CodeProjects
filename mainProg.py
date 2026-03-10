
from myLib import isHarshad, isSiete, Hodges

harsh_sum = 0
hodges_count = 0

input_file = open("Rumbers.txt", "r")
output_file = open("HarshOut.txt", "w")

for line in input_file:
    numbers = line.strip().split('\t')

    for n in numbers:
        num = int(n)

        if isHarshad(num):
            harsh_sum += num

            if isSiete(num):
                output_file.write(str(num) + "\n")

                if num % Hodges == 0:
                    print(num)
                    hodges_count += 1

input_file.close()
output_file.close()

print("Sum of Harshad numbers:", harsh_sum)
print("Count divisible by Hodges:", hodges_count)
