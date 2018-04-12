#
# Copyright 2017, TOTVS S.A.
# All rights reserved.
#

#
# Build Specification Reference for Makefile
#
# The main phases are defined by:
#
# "pre_build":
#   In this phase we should execute commands that
#   should precede "build" phase (ex. build some
#   dependencies, install a missing package, ...).
#
# "build":
#   In this phase we should execute commands that
#   should build some package or generate some
#   artifact (ex. mvn commands, npm commands, ...).
#
# "post_build":
#   In this phase we should execute commands that
#   should come after the "build" phase, like a
#   deployment spec that CI/CD tool will execute.
#
# Our CI/CD tool will read/execute this file in the
# stages below as the following order:
#
# "Build":
#   $ make pre_build
#   $ make build
#   $ make post_build
#

#
# Your custom rules/targets
#

# Rule "maven:dependencies"
.PHONY: maven\:dependencies
.SILENT: maven\:dependencies
maven\:dependencies:
	# produce a failure return code if any command return error \
	set -eo pipefail;

# Rule "maven:build"
.PHONY: maven\:build
.SILENT: maven\:build
maven\:build:
	# produce a failure return code if any command return error \
	set -eo pipefail; \
	# get current branch \
	localBranch=$(shell git rev-parse --abbrev-ref HEAD); \
	# maven goals for custom branchs \
	declare -a branchs=( \
		"master:deploy" \
	); \
	for b in $${branchs[@]}; do \
		if [ $$localBranch = $${b%:*} ]; then mvnGoal=$${b#*:}; break; else mvnGoal="install"; fi; \
	done; \
	mvn clean $$mvnGoal -f pom.xml;

#
# Default rules/targets (please don't change their names)
# Environment variables are also defined here
#

# defaul shell
SHELL = /bin/bash

# Rule "pre_build"
.PHONY: pre_build
.SILENT: pre_build
pre_build:
	# produce a failure return code if any command return error \
	set -eo pipefail; \
	make maven:dependencies

# Rule "build"
.PHONY: build
.SILENT: build
build:
	# produce a failure return code if any command return error \
	set -eo pipefail; \
	make maven:build;

# Rule "post_build"
.PHONY: post_build
.SILENT: post_build
post_build:
	# produce a failure return code if any command return error \
	set -eo pipefail;
