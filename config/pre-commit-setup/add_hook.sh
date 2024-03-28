#!/bin/bash

# Define relative path to .git/hooks
hooksDir="../../.git/hooks"

# Check if .git/hooks directory exists, if not create it
if [ ! -d "$hooksDir" ]; then
    mkdir -p "$hooksDir"
    echo "Created directory: $hooksDir"
fi

# Check if pre-commit hook already exists
if [ -f "$hooksDir/pre-commit" ]; then
    # Backup existing pre-commit hook
    cp "$hooksDir/pre-commit" "$hooksDir/pre-commit.bak"
    echo "Existing pre-commit hook backed up to: $hooksDir/pre-commit.bak"
fi

# Copy the new pre-commit hook
cp "pre-commit" "$hooksDir/pre-commit"

echo "New pre-commit hook setup complete."
