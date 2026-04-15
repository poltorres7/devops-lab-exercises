# Exercise 05: Java Metrics Gap

## Scenario

The app already exposes basic actuator metrics, but HTTP request metrics are absent from the dashboard. The candidate must add the right instrumentation and complete the Grafana view.

## Candidate Goals

- inspect `/actuator/prometheus`
- add missing HTTP metrics instrumentation
- confirm Prometheus scraping
- update the dashboard

## Intended Fault

The app exposes a custom Prometheus metric and a `/status` endpoint, but it does not instrument HTTP request metrics yet.
