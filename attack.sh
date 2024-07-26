#!/bin/bash

vegeta attack -rate=100 -duration=20s -targets=targets.txt > results.bin

vegeta report results.bin

vegeta plot results.bin > plot.html
