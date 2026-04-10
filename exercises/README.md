# Lab Exercises

Each exercise is self-contained in its own folder with:

- `app/`: the sample application source code
- `helm/`: Kubernetes deployment resources for the exercise
- `README.md`: the scenario, expected symptoms, and intended remediation path

All exercise workloads target the dedicated `workloads` node pool by default:

- `nodeSelector.workload=lab`
- toleration for `dedicated=lab:NoSchedule`

Exercises:

- `01-network-db-connectivity`
- `02-java-restart-loop`
- `03-java-resource-starvation`
- `04-java-ci-failure`
- `05-java-metrics-gap`

## Deployment Model

Each exercise now includes:

- `argocd-application.yaml`: an Argo CD `Application` manifest for the scenario
- `app/`: Java source and Dockerfile
- `helm/`: runtime manifests and values

The Argo CD application files intentionally use `REPLACE_WITH_GIT_REPO_URL` because this workspace does not currently have a Git remote configured. Before applying them, update `spec.source.repoURL` to the real repository URL that Argo CD can reach.

## Build and Push

The repository includes [`scripts/build_exercise_images.sh`](/Users/paultorresrivera/Code/github/devops-lab/scripts/build_exercise_images.sh) to build and push all exercise images to the DigitalOcean registry.

Example:

```bash
doctl registry login --context rocinante
IMAGE_TAG=latest /Users/paultorresrivera/Code/github/devops-lab/scripts/build_exercise_images.sh
```

Important exercise-specific note:

- Exercise 04 is expected to fail in CI because its test is intentionally broken. A normal image build for that scenario should fail until the candidate fixes the code or pipeline.

## Recommended Rollout Order

1. `05-java-metrics-gap`
2. `01-network-db-connectivity`
3. `02-java-restart-loop`
4. `03-java-resource-starvation`
5. `04-java-ci-failure`

That order gives you:

- one deployable app with metrics first
- one networking investigation scenario
- one crash-loop scenario
- one Helm/resource tuning scenario
- one CI-only failure scenario
