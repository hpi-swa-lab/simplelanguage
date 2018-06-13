#! /usr/bin/env python3

import sys
import numpy as np

times = []
runs = 0
avg_len = 0
time_sum = 0

def reject_outliers(data, m=1.5):
    return data[abs(data - np.mean(data)) < m * np.std(data)]

for line in sys.stdin:
  if ": " not in line:
    continue
  
  _, time_str = line.split(": ")
  times.append(int(time_str))

times_cleaned = reject_outliers(np.array(times))

print(np.round(np.mean(times_cleaned)).astype(int))
