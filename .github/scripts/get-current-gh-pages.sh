#!/bin/bash

echo "Fetching latest pages deployment..."

# Get the latest successful pages deployment
ARTIFACT_ID=$(gh api "/repos/${REPO}/actions/artifacts?name=github-pages" --jq '.artifacts[0].id')

if [ -n "$ARTIFACT_ID" ] && [ "$ARTIFACT_ID" != "null" ]; then
  echo "Found artifact ID: $ARTIFACT_ID"

  # Download the artifact
  gh api "/repos/${REPO}/actions/artifacts/${ARTIFACT_ID}/zip" > artifact.zip

  # Extract to build directory
  mkdir -p docs/build/mkdocs
  unzip -o artifact.zip -d /tmp/pages-artifact
  tar -xzf /tmp/pages-artifact/artifact.tar -C docs/build/mkdocs

  echo "Successfully extracted existing pages"
else
  echo "No existing artifact found, this may be the first deployment"
fi
