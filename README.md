# Commit message checker

[![Build Status](https://travis-ci.org/jUnG3/commit-message-checker.svg?branch=master)](https://travis-ci.org/jUnG3/commit-message-checker) [![codecov](https://codecov.io/gh/jUnG3/commit-message-checker/branch/master/graph/badge.svg)](https://codecov.io/gh/jUnG3/commit-message-checker)

## How to start

1. Clone the repository
2. Configure `default.properties` file in the resources directory
3. Launch `./gradlew clean build`
4. The compiled `.jar` is in the `build/libs` directory
5. Copy the builded .jar in youre target repo
6. Enable the commit-msg hook in you're repo by coping the example file `cp .git/hooks/commig-msg.example .git/hooks/commit-msg`
7. In the `example_commit_message_edition.sh.version` file is an example of how the application can be used
