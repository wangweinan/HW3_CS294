#!/usr/bin/env python
import sys
for line in sys.stdin:
 	line = line.strip()
 	cnt, iD = line.split("\t",1)
	print '[[%s]]\t[[%.0f]]'   % (cnt,int(iD[2:-2]))