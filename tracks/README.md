# Lab Exercises

Each exercise is self-contained in its own folder with:

- `app/`: the sample application source code
- `helm/`: Kubernetes deployment resources for the exercise
- `README.md`: the scenario, expected symptoms, and intended remediation path

All exercise workloads target the dedicated `workloads` node pool by default:

- `nodeSelector.workload=lab`
- toleration for `dedicated=lab:NoSchedule`

Exercises:

- `track-a`
- `track-b`
- `track-c`
- `track-d`
- `track-e`

## Deployment Model

Each exercise now includes:

- `argocd-application.yaml`: an Argo CD `Application` manifest for the track
- `app/`: Java source and Dockerfile
- `helm/`: runtime manifests and values

The Argo CD application files are configured to point at the GitHub repository URL used for this lab. Ensure Argo CD has repository access before applying them.

## Build and Push

The repository includes [`scripts/build_exercise_images.sh`](/Users/paultorresrivera/Code/github/devops-lab/scripts/build_exercise_images.sh) to build and push all exercise images to the DigitalOcean registry.

Example:

```bash
doctl registry login --context rocinante
IMAGE_TAG=latest /Users/paultorresrivera/Code/github/devops-lab/scripts/build_exercise_images.sh
```

Important track-specific note:

- Exercise 04 is expected to fail in CI because its test is intentionally broken. A normal image build for that scenario should fail until the candidate fixes the code or pipeline.

## Recommended Rollout Order

1. `track-e`
2. `track-a`
3. `track-b`
4. `track-c`
5. `track-d`

That order gives you:

- one deployable app with metrics first
- one networking investigation scenario
- one crash-loop scenario
- one Helm/resource tuning scenario
- one CI-only failure scenario
