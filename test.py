#!/usr/bin/env python
import sys
import re
import networkx as nx
parent=[]
child=[]
for line in sys.stdin:
    words=line.decode("utf8","ignore")
    words = words.split(",")
    pad = words[1].replace('_',' ')
    pad0=re.sub('[^A-Za-z0-9]+',"",pad)
    parent.append(pad0.upper())
    if words[2].find('\\n') == -1:
        child.append(re.sub('[^A-Za-z0-9]+',"",words[2]))
    else:
        current = words[2].split('\\n')
        child.append(re.sub('[^A-Za-z0-9]+',"",current[-1]))
#print(parent)

category = set(parent) | set(child)
catIndex = dict(zip(category,range(len(category))))
parent_num = []
child_num = []
[parent_num.append(catIndex.get(x)) for x in parent]
[child_num.append(catIndex.get(y)) for y in child]
parentDic = dict(zip(parent,parent_num))
childDic = dict(zip(child,child_num))
DG = nx.DiGraph()

Tuple = [(y,x) for x in parent_num for y in child_num]
print(Tuple[0:10])