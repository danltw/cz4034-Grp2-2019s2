import csv
import re
import os

directory = os.path.dirname(os.path.abspath(__file__))

words= []
with open(r'C:\TwitterCrawl\finalOutput_remove_id.csv', 'r', encoding="utf8") as csvfile:
    reader = csv.reader(csvfile)
    next(reader)
    for row in reader:
         csv_words = row[1].split(" ")
         for i in csv_words:
              words.append(i)
    print("Total number of words:",len(words))

uniqueWords = [] 
for i in words:
      if not i in uniqueWords:
        uniqueWords.append(i)

print("Number of unique words:",len(uniqueWords))
print("finish count!")
