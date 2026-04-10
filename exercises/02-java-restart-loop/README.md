# Exercise 02: Java Restart Loop

## Scenario

The app crashes immediately after startup because of an intentional code bug.

## Candidate Goals

- inspect pod restart counts
- read stack traces
- identify the code defect
- rebuild and redeploy the fixed image

## Intended Fault

The application throws an exception from a startup runner.
