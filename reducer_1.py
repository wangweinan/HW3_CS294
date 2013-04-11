#!/usr/bin/env python
import sys
 
for line in sys.stdin:
 
    line = line.strip()
    category, text = line.split('\t', 1)

    print '%s\t%s'   %   ( category, text )