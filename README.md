#Bitbucket

project `mvnrepa-backend`

##Pipeline
just build (maybe in the future test) and send artifact jar file to bitbucket repository to possible download

#C9

project `common`: https://ide.c9.io/frido/common

Used Ubuntu template.

**Setup project:**
* install oracle `java8`
* install `sdk` (for gradle)
* install `gradle` (via sdk)
* `git` is already installed as part of Ubuntu template
* clone from `bitbucket`
* set env MONGO_URL for c9 (just set it as in linux to `.profile` file)
* env PORT is setted by default

**Setup heroku**
* install `heroku` client

**Setup openshift client**
* install `ruby`
* install `rubygems`
* install `rhc`

**Usefull commands:**
* `gradle build`
* `gradle bootRun`

#Heroku

project `mvnrepo-backend`: https://mvnrepo-backend.herokuapp.com/test/

**Setup**
* run `set heroku config:set MONGO_URL=mongodb://<login>:<pwd>@<space>.mlab.com:<port>/<db>`
* run `gradle build`
* run `gradle deployHeroku`
    * everything is setup during deployment (`Procfile` to run jar file, java8)
    
**usefull commands:**
* `gradle build` and `gradle deployHeroku`
* `heroku logs -a mvnrepo-backend`
* `heroku run bash -a mvnrepo-backend`
* `heroku ps -a mvnrepo-backend`

#Openshift

project `mvnrepo`: http://mvnrepo-frido.rhcloud.com/

Used DIY (Do It Yourself) template.

**Setup**
* set env MONGO_URL (rhc command)
* commit generated `*.jar` file
    * to generate file use `gradle build`
    * there is `start` and `deploy` action_hooks
    * `start` and `deploy` files contains bash script to init env
    
*NOTE: `.openshift` directory is copied to bitbucket just as backup*

#LogEntries

as `Heroku` Add-ons

account: *mvnrepo-backend* 

#Wakatime

**Setup**
* "of" plugin according tutorial

#NOTES

## gradle problem on c9
**Consume too much memory -> need to specify max heap size**
* generate `gradlew` via `gradle wrapper`
* add to `gradlew` file:
    * DEFAULT_JVM_OPTS=-Xmx128m
    * GRADLE_OPTS=-Xmx128m

#API
GET methods for everything.

**search**
* query: regex search in group, artifact and version (maybe in description)
    * return `List<Lib>`
* group: exac match in group field
    * return `List<Lib>`
* artifact: exac match in artifact field
    * return `List<Lib>`
* version: exac match in version field
    * return `List<Lib>`
* artifact: exac match in artifact field
    * return `List<Lib>`
* psize: page size
* pnumber: number of page

**objectId**
* id(path): exac match in id field
    * return `Lib|Pom` according 'style'
* style: lib|pom|all (default all)

**id**
* group+artifact+version: exac match in fields
    * return `Lib|Pom` according 'style'
* style: lib|pom|all (default all)

**developer**
* query: regex search in name, nick, organization
    * return `Lib|Pom` according 'style'
* style: lib|pom|all (default all)