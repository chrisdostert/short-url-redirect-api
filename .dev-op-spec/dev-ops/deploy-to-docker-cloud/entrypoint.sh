#!/bin/sh
SERVICE_VERSION=$(cat target/VERSION)

updateDockerCloudService () {

docker-cloud service set --sync --image ${DOCKER_REPO_NAME}:${SERVICE_VERSION} redirect-api.${DOCKERCLOUD_STACK_NAME}

}

redeployDockerCloudService () {

docker-cloud service redeploy --sync redirect-api.${DOCKERCLOUD_STACK_NAME}

}

updateDockerCloudService && redeployDockerCloudService
