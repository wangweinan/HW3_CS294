#!/usr/bin/env python
# mapper_2.py
import sys
import nltk
import re
from nltk.corpus import stopwords
from nltk.stem import PorterStemmer
from collections import Counter
stem = PorterStemmer()
list = []
inPage = False
inText = False
True_iD = True
iD = []
pattern = re.compile(r'[,\[\{\}\/\=\*\]\-\|\'\.\?\"\!:;\)\(\&\@\>\<]')
for line in sys.stdin:
    line = line.strip()
    if inPage:
        list.append(line)
    if line.find( "<page>" ) != -1:
        inPage = True
        True_iD = True
        iD = []
        continue
    if line.find( "</page>" ) != -1:
        inPage = False
        True_iD = True
        continue
    if inPage and line.find( "<text" ) != -1:
        inText = True
        continue
    if inPage and True_iD and line.find("<id>") != -1:
        iD.append(line[len("<id>") : -len("</id>")])
        True_iD = False

    if inPage and line.find( "/text" ) != -1:
        inText = False
        text = ' '.join(list)
        #Tokenizing Text For Each XML
        temp = text.decode("utf-8","ignore")
        temp = temp.replace(u'\ufeff',' ')
        temp_1 = re.sub(pattern," ",temp)
        temp_1 = temp_1.lower()
        res=[]
        for x in temp_1.split():
        	if x not in stopwords.words('english'):
        		res.append(x)
        clean_text = " ".join(stem.stem_word(word) for word in res)
        tokens = nltk.word_tokenize(clean_text)
        cnt = Counter(tokens)
        print("[[%s]]\t[[%.0f]]") % (dict(cnt),int(iD[0]))
        list = []
        continue
    


