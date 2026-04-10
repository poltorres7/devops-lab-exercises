# Exercise 03: Java Resource Starvation

## Scenario

The application is stable in code, but the Helm chart is undersized and causes the pod to fail under constrained memory.

## Candidate Goals

- inspect pod events
- identify OOMKilled or resource pressure
- update Helm values
- redeploy through GitOps

## Intended Fault

The app allocates memory on startup while the chart sets an unrealistically low memory limit.
