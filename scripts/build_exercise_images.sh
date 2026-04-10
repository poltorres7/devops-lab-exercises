#!/usr/bin/env bash
set -euo pipefail

ROOT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")/.." && pwd)"
REGISTRY="${REGISTRY:-registry.digitalocean.com/devopslabregistry}"
IMAGE_TAG="${IMAGE_TAG:-latest}"

declare -a EXERCISES=(
  "01-network-db-connectivity:network-db-connectivity"
  "02-java-restart-loop:java-restart-loop"
  "03-java-resource-starvation:java-resource-starvation"
  "04-java-ci-failure:java-ci-failure"
  "05-java-metrics-gap:java-metrics-gap"
)

for entry in "${EXERCISES[@]}"; do
  exercise_dir="${entry%%:*}"
  image_name="${entry##*:}"

  echo "==> Building ${image_name}:${IMAGE_TAG}"
  docker build \
    -t "${REGISTRY}/${image_name}:${IMAGE_TAG}" \
    "${ROOT_DIR}/exercises/${exercise_dir}/app"

  echo "==> Pushing ${image_name}:${IMAGE_TAG}"
  docker push "${REGISTRY}/${image_name}:${IMAGE_TAG}"
done

