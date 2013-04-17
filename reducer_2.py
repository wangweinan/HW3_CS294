#!/usr/bin/env python
import sys
import pickle
import nltk
import re
from nltk.corpus import stopwords
from nltk.stem import PorterStemmer
import Counter
stem = PorterStemmer()

Dict = dict()
for line in sys.stdin:
	line = line.strip()
	Data = eval(line)
	Dict.update(Data)
	
print(Dict)
