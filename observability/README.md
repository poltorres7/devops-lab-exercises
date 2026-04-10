# Observability dashboards

This directory contains the public reference copies of the base Grafana dashboards used in the lab.

## What you get in Grafana

When the lab is provisioned, Grafana already includes:

- 5 base dashboards, one per exercise application
- separate platform dashboards
- Prometheus-backed Kubernetes metrics
- Loki-backed application logs

## Candidate workflow

The dashboards provisioned in the live lab are the default starting point.

Recommended workflow:

1. Open the base dashboard for the exercise you are troubleshooting.
2. Use the built-in metrics and logs to investigate the issue.
3. If you want to customize the dashboard, use **Save As** in Grafana.
4. Keep your own copy without changing the base dashboard.

## Base exercise dashboards

- `01-network-db-connectivity.base.json`
- `02-java-restart-loop.base.json`
- `03-java-resource-starvation.base.json`
- `04-java-ci-failure.base.json`
- `05-java-metrics-gap.base.json`

## Included panels

Each exercise dashboard includes:

- desired replicas
- available replicas
- running pods
- container restarts
- CPU usage
- memory working set
- readiness state
- waiting reason
- application logs from Loki

These files are public reference exports. The private source of truth and provisioning manifests live in the `devops-lab` repository.
