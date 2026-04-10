# Exercise 01: Networking DB Connectivity

## Scenario

A Java app should connect to MySQL, but the deployed configuration points it at the wrong host. The app and the database live on different network endpoints, and the candidate must confirm connectivity, DNS, ports, and the correct target.

## Candidate Goals

- inspect pod environment and logs
- use `nc`, `dig`, `nslookup`, and `traceroute`
- identify the wrong database hostname
- update Helm values and redeploy

## Intended Fault

The chart ships with `db.host` set to a non-routable placeholder instead of the real MySQL endpoint.

## Remediation

Update `helm/values.yaml` with the correct database host and redeploy.
