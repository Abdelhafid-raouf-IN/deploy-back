#!/bin/bash

# Execute the load test
cat targets.txt | vegeta attack -duration=30s -rate=50 | tee results.bin | vegeta report

# Generate the JSON report
vegeta report -inputs=results.bin -reporter=json > results.json

# Generate the HTML plot
vegeta report -inputs=results.bin -reporter=plot > plot.html
