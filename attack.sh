#!/bin/bash

# Run the attack using Vegeta
vegeta attack -rate=100 -duration=20s -targets=targets.txt > results.bin

# Generate a textual report
vegeta report results.bin

# Generate the HTML plot using Vegeta
vegeta plot results.bin > plot.html

