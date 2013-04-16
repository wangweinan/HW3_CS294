#!/usr/bin/env python
import sys
import pickle
import nltk
import re
from nltk.corpus import stopwords
from porterstemmer import Stemmer
from collections import Counter
stem = Stemmer()

Dict = dict()
Data = sys.stdin.readlines()
Data = Data[0][len("Counter("): -len("))")]
Data = eval(Data)
Dict.update(Data)
print(Dict)
