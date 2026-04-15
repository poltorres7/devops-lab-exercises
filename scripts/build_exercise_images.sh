#!/usr/bin/env bash
set -euo pipefail

ROOT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")/.." && pwd)"
REGISTRY="${REGISTRY:-registry.digitalocean.com/devopslabregistry}"
IMAGE_TAG="${IMAGE_TAG:-latest}"

declare -a TRACKS=(
  "track-a:network-db-connectivity"
  "track-b:java-restart-loop"
  "track-c:java-resource-starvation"
  "track-d:java-ci-failure"
  "track-e:java-metrics-gap"
)

for entry in "${TRACKS[@]}"; do
  track_dir="${entry%%:*}"
  image_name="${entry##*:}"

  echo "==> Building ${image_name}:${IMAGE_TAG}"
  docker build \
    -t "${REGISTRY}/${image_name}:${IMAGE_TAG}" \
    "${ROOT_DIR}/tracks/${track_dir}/app"

  echo "==> Pushing ${image_name}:${IMAGE_TAG}"
  docker push "${REGISTRY}/${image_name}:${IMAGE_TAG}"
done
