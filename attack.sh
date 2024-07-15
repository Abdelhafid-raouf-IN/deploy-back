#!/bin/bash

vegeta attack -rate=50 -duration=30s -targets=targets.txt > results.bin

vegeta report results.bin > results.txt

vegeta plot results.bin > plot.html

# Generate a plot with gnuplot
gnuplot <<EOF
set terminal png size 800,600
set output 'plot.png'
set title 'Vegeta Load Test'
set xlabel 'Time'
set ylabel 'Latency'
plot 'results.txt' using 9:2 with lines title 'Latency'
EOF
