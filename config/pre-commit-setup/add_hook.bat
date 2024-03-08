@echo off
SETLOCAL EnableDelayedExpansion

REM Define relative path to .git/hooks
SET "hooksDir=../../.git/hooks"

REM Check if .git/hooks directory exists, if not create it
IF NOT EXIST "!hooksDir!" (
    mkdir "!hooksDir!"
    echo Created directory: !hooksDir!
)

REM Check if pre-commit hook already exists
IF EXIST "!hooksDir!/pre-commit" (
    REM Backup existing pre-commit hook
    copy /Y "!hooksDir!/pre-commit" "!hooksDir!/pre-commit.bak"
    echo Existing pre-commit hook backed up to: !hooksDir!/pre-commit.bak
)

REM Copy the new pre-commit hook
copy /Y "pre-commit" "!hooksDir!/pre-commit"

echo New pre-commit hook setup complete.
