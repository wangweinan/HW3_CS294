#!/usr/bin/env python
# mapper.py
# D. Thiebaut
# takes a complete xml file, extract the title and 
# text part of the file, and outputs a tuple
# <category \t text> a shorter version of text
 
import sys
category = []
inCategory = False
list = []
title = "Unknown"
inText = False
for line in sys.stdin:
    line = line.strip()
    if line.find( "[[Category:" )!= -1:
        category.append(line[ len( "[[Category:" ) : -len( "]]" ) ])
    if line.find( "<text>" ) != -1:
        inText = True
        continue
    if line.find( "</text>" ) != -1:
        inText = False
        continue
    if inText:
        list.append( line )
 
text = ' '.join(list)
text = text[0:10] + "..." + text[-10:]
category = ','.join(map(str,category))
print '[[%s]]\t[[%s]]' % (category, text)