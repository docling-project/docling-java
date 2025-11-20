#!/bin/bash

# Reclaim disk space, otherwise we have too little free space at the start of a job
time sudo docker image prune --all --force || true
time sudo rm -rf /usr/share/dotnet || true
time sudo rm -rf /usr/share/swift || true
time sudo rm -rf /usr/local/lib/android || true
time sudo rm -rf /opt/ghc || true
time sudo rm -rf /usr/local/.ghcup || true
time sudo rm -rf /opt/pipx || true
time sudo rm -rf /usr/share/rust || true
time sudo rm -rf /usr/local/go || true
time sudo rm -rf /usr/share/miniconda || true
time sudo rm -rf /usr/local/share/powershell || true
time sudo rm -rf /usr/lib/google-cloud-sdk || true

# Remove infrastructure things that are unused and take a lot of space
time sudo rm -rf /opt/hostedtoolcache/CodeQL || true
time sudo rm -rf /imagegeneration/installers/go-* || true
time sudo rm -rf /imagegeneration/installers/node-* || true
time sudo rm -rf /imagegeneration/installers/python-* || true
