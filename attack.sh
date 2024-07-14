#!/bin/bash

vegeta attack -rate=50 -duration=30s -targets=targets.txt > results.bin
vegeta report results.bin# Generate the HTML plot
vegeta plot results.bin > plot.html