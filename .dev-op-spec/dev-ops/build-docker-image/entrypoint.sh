#!/bin/sh
docker build -t ${DOCKER_REPO_NAME}:$(cat target/VERSION) target/docker/stage