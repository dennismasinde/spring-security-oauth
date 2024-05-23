# CI/CD Pipeline for Docker Images with Semantic Versioning

This repository contains a continuous integration (CI) and continuous delivery (CD) pipeline implemented using GitHub Actions. The pipeline automates the building and deployment of Docker images while incorporating semantic versioning for version management.

## Overview

The CI/CD pipeline automates the following processes:

- **Continuous Integration (CI)**:
  - Maven-based Java project is built whenever changes are pushed to the main branch.
  - Docker image is built with the project artifacts.

- **Continuous Delivery (CD)**:
  - Whenever changes are pushed to the main branch, the CI workflow triggers the CD pipeline.
  - The CD pipeline dynamically determines the latest semantic version tag from the Docker registry.
  - The Docker image corresponding to the latest semantic version is pulled and deployed.

## Prerequisites

Before setting up the CI/CD pipeline, ensure you have the following:

- Docker installed on the CI/CD environment.
- Maven installed on the CI/CD environment.
- Access to a Docker registry (e.g., Docker Hub) where your Docker images will be stored.

## Getting Started

To use this CI/CD pipeline for your project, follow these steps:

1. **Configure Secrets**:
   Add the following secrets to your GitHub repository settings:
   - `DOCKER_USERNAME`: Docker Hub username.
   - `DOCKER_PASSWORD`: Docker Hub access token or password.

2. **Update Workflow Files**:
   - Modify the `ci.yml` file under `.github/workflows` to replace placeholders with actual values (e.g., Docker image name).
   - Modify the `cd.yml` file under `.github/workflows` if necessary, depending on your deployment requirements.

3. **Push Changes**:
   Push the updated workflow files and any other changes to your main branch.

4. **Test CI/CD Pipeline**:
   Make a change to your project and push it to the main branch. Monitor the GitHub Actions tab in your repository to ensure the CI/CD pipeline runs successfully.

## Usage

- To trigger the CI pipeline: Push changes to the main branch.
- To trigger the CD pipeline: The CD pipeline is automatically triggered by the CI pipeline whenever changes are pushed to the main branch.

## License

This project is licensed under the [MIT License](LICENSE).

