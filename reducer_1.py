#!/usr/bin/env python
import sys
 
for line in sys.stdin:
 
    line = line.strip()
    category, page = line.split('\t', 1)

    print '%s'   % (category)