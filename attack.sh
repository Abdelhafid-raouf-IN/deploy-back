#!/bin/bash

# Run vegeta attack
vegeta attack -rate=100 -duration=20s -targets=targets.txt > results.bin

# Generate vegeta report
vegeta report results.bin

# Generate vegeta plot
vegeta plot results.bin > plot.html

# Use jplot to visualize results
jplot --url http://localhost:8080/debug/vars Threads

# Example usage of jaggr (aggregating data, the exact usage will depend on your specific needs)
# Assuming you have a JSON file `metrics.json` from vegeta or another source:
# jaggr usage here is a placeholder; replace with the actual command you need
# jaggr -config jaggr_config.json metrics.json > aggregated_results.json

# Note: Add the necessary commands to fetch and prepare data for jplot and jaggr.
# This script assumes jplot and jaggr are installed and accessible in the PATH.
