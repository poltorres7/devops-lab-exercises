# Exercise 04: Java CI Failure

## Scenario

The application never reaches deployment because the CI pipeline fails during the build stage.

## Candidate Goals

- inspect Jenkins pipeline logs
- identify whether the failure is a test, dependency, or build-script issue
- fix the build and publish a successful image

## Intended Fault

The unit test is intentionally wrong and causes `mvn test` to fail.
