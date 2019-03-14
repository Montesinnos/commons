#!/usr/bin/env bash

echo "Building and compiling project"
./gradlew clean
./gradlew --refresh-dependencies
./gradlew build
echo "Publishing"
./gradlew publish
./gradlew uploadArchives