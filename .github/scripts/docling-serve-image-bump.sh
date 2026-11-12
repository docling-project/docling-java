#!/usr/bin/env bash
#
# Bumps DoclingServeContainerConfig.DOCLING_IMAGE_VERSION to the newest docling-serve tag
# that passed the version tests (within the current major version).
#
# Usage: docling-serve-image-bump.sh <path-to-results.md>
#
# Environment:
#   DRY_RUN      If "true", report what would change without modifying any file.
#   GITHUB_OUTPUT  If set, `current`, `new`, `updated` and `skipped` are appended to it.
#
set -euo pipefail

RESULTS_FILE="${1:-}"
DRY_RUN="${DRY_RUN:-false}"

SCRIPT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"
CONFIG_FILE="${CONFIG_FILE:-${SCRIPT_DIR}/../../docling-testcontainers/src/main/java/ai/docling/testcontainers/serve/config/DoclingServeContainerConfig.java}"

if [[ -z "${RESULTS_FILE}" || ! -f "${RESULTS_FILE}" ]]; then
  echo "Usage: $0 <path-to-results.md>" >&2
  exit 1
fi

if [[ ! -f "${CONFIG_FILE}" ]]; then
  echo "Config file not found: ${CONFIG_FILE}" >&2
  exit 1
fi

CURRENT="$(sed -nE 's/.*String DOCLING_IMAGE_VERSION = "(v[0-9]+\.[0-9]+\.[0-9]+)".*/\1/p' "${CONFIG_FILE}" | head -n 1)"
if [[ -z "${CURRENT}" ]]; then
  echo "Could not determine the current DOCLING_IMAGE_VERSION from ${CONFIG_FILE}" >&2
  exit 1
fi

MAJOR="${CURRENT%%.*}"
MAJOR="${MAJOR#v}"

# Stable tags within the current major, by result: "SUCCESS" or anything else
tags_with_result() {
  local pattern="$1"
  sed -nE "s/^\| (v${MAJOR}\.[0-9]+\.[0-9]+) \| ${pattern} \|.*/\1/p" "${RESULTS_FILE}" | sort -V
}

NEW="$( (tags_with_result '✅ SUCCESS' || true) | tail -n 1)"
NEW="${NEW:-${CURRENT}}"

# Never downgrade
if [[ "$(printf '%s\n%s\n' "${CURRENT}" "${NEW}" | sort -V | tail -n 1)" == "${CURRENT}" ]]; then
  NEW="${CURRENT}"
fi

# Newer tags (within the major) that did not succeed
SKIPPED=""
while IFS= read -r tag; do
  [[ -z "${tag}" ]] && continue
  if [[ "$(printf '%s\n%s\n' "${CURRENT}" "${tag}" | sort -V | tail -n 1)" == "${tag}" && "${tag}" != "${CURRENT}" ]]; then
    SKIPPED="${SKIPPED:+${SKIPPED},}${tag}"
  fi
done < <(sed -nE "s/^\| (v${MAJOR}\.[0-9]+\.[0-9]+) \| ❌.*/\1/p" "${RESULTS_FILE}" | sort -V)

UPDATED="false"
if [[ "${NEW}" != "${CURRENT}" ]]; then
  UPDATED="true"
  if [[ "${DRY_RUN}" == "true" ]]; then
    echo "[dry run] Would bump DOCLING_IMAGE_VERSION ${CURRENT} -> ${NEW}"
  else
    TMP_FILE="$(mktemp)"
    sed -E "s/(String DOCLING_IMAGE_VERSION = \")${CURRENT//./\\.}(\")/\1${NEW}\2/" "${CONFIG_FILE}" > "${TMP_FILE}"
    cat "${TMP_FILE}" > "${CONFIG_FILE}"
    rm -f "${TMP_FILE}"
    echo "Bumped DOCLING_IMAGE_VERSION ${CURRENT} -> ${NEW}"
  fi
else
  echo "DOCLING_IMAGE_VERSION ${CURRENT} is already up to date"
fi

[[ -n "${SKIPPED}" ]] && echo "Newer tags that did not pass: ${SKIPPED}"

if [[ -n "${GITHUB_OUTPUT:-}" ]]; then
  {
    echo "current=${CURRENT}"
    echo "new=${NEW}"
    echo "updated=${UPDATED}"
    echo "skipped=${SKIPPED}"
  } >> "${GITHUB_OUTPUT}"
fi
