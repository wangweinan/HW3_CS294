#!/usr/bin/env python
import sys
import pickle
import nltk
import re
from nltk.corpus import stopwords
from nltk.stem import PorterStemmer
from collections import Counter
stem = PorterStemmer()

Dict = Counter()
for line in sys.stdin:
	line = line.strip()
	Data = eval(line)
	Data = Counter(Data)
	Dict.update(Data)
	
print(Dict)
