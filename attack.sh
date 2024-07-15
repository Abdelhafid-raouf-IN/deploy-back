#!/bin/bash

# Step 1: Run Vegeta attack
vegeta attack -rate=50 -duration=30s -targets=targets.txt > results.bin

# Step 2: Generate a text report from the binary results
vegeta report results.bin > results.txt

# Step 3: Plot the data using gnuplot
gnuplot <<EOF
set terminal png size 800,600
set output 'plot.png'
set title 'Vegeta Load Test'
set xlabel 'Time'
set ylabel 'Latency'
set datafile separator ","
plot 'results.txt' using 9:2 with lines title 'Latency'
EOF

# Step 4: Generate an HTML plot (optional)
vegeta plot results.bin > plot.html
