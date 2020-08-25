# MvnRepo Backend

[Spring Boot](https://spring.io/projects/spring-boot) application written in [Kotlin](https://kotlinlang.org/) which provides REST-API backend.

## Prerequisites

* install oracle `java8`
* install `sdk`
* install `gradle`
* install `heroku` client
* install `rhc`

## Setup

**Setup environmental variables:**

* `MONGO_URL`: for `c9` (just set it as in Linux to `.profile` file)
* `PORT`: for `c9` is configured by default

## Build

Build jar of application.

```shell
./gradlew build
```

## Run

Run application

```shell
./gradlew bootRun
```

## Deploy

#### Deploy to Heroku

```shell
set heroku config:set MONGO_URL=mongodb://<login>:<pwd>@<space>.mlab.com:<port>/<db>
gradle build
gradle deployHeroku
```

Everything is configured during deployment (`Procfile` to run jar file, java8).

#### Deploy to OpenShift

* set environmental variable `MONGO_URL` (`rhc` command)
* commit generated `*.jar` file
  * to generate file use `gradle build`
  * there is `start` and `deploy` action_hooks
  * `start` and `deploy` files contains bash script to initialize environmental variables

*NOTE: `.openshift` directory is copied to bitbucket just as backup*

## Hints

**Useful commands:**

* `gradle build` and `gradle deployHeroku`
* `heroku logs -a mvnrepo-backend`
* `heroku run bash -a mvnrepo-backend`
* `heroku ps -a mvnrepo-backend`

#### Gradle problem on c9

**Consume too much memory -> need to specify max heap size**

Generate `gradlew` file:

```shell
gradle wrapper
```

Add to the `gradlew` file:

```shell
DEFAULT_JVM_OPTS=-Xmx128m
GRADLE_OPTS=-Xmx128m
```
