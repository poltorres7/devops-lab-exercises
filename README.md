# devops-lab-exercices
Public exercises for candidates, troubleshooting scenarios.

## Jenkins Kaniko Build

Use [ci/kaniko-build.Jenkinsfile](/Users/paultorresrivera/Code/github/devops-lab-exercices/ci/kaniko-build.Jenkinsfile) as the pipeline definition for building exercise images inside the Kubernetes cluster.

Expected Jenkins job shape:

- Pipeline job
- Pipeline definition: `Pipeline script from SCM`
- SCM: `Git`
- Repository URL: `https://github.com/poltorres7/devops-lab-exercices.git`
- Script Path: `ci/kaniko-build.Jenkinsfile`

Required Jenkins namespace secret:

- `devopslabregistry`

The Kaniko agent mounts that secret at `/kaniko/.docker/config.json` and pushes to:

- `registry.digitalocean.com/devopslabregistry`

Useful build parameters:

- `EXERCISE_DIR`
- `GIT_REF`
- `IMAGE_TAG`
- `PUSH_LATEST`
- `UPDATE_GITOPS`
- `GIT_PUSH_CREDENTIALS_ID`

Tagging behavior:

- if `IMAGE_TAG` is empty, the pipeline uses the Git short commit hash
- this is the recommended immutable tag format for Argo CD deployments
- if `UPDATE_GITOPS=true`, the pipeline updates `exercises/<name>/helm/values.yaml` with that tag and pushes the change back to GitHub
- `UPDATE_GITOPS=true` requires a Jenkins username/password credential that can push to the repo
