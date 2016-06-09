# Play Quick Start Guide

This guide will walk you through deploying a Play application on [Deis Workflow][].

## Usage

```console
$ git clone https://github.com/deis/example-play.git
$ cd example-play
$ deis create
Creating Application... done, created rental-cutpurse
Git remote deis added
remote available at ssh://git@deis-builder.deis.rocks:2222/rental-cutpurse.git
$ git push deis master
Counting objects: 68, done.
Delta compression using up to 4 threads.
Compressing objects: 100% (44/44), done.
Writing objects: 100% (68/68), 46.93 KiB | 0 bytes/s, done.
Total 68 (delta 14), reused 68 (delta 14)
Starting build... but first, coffee!
-----> Play! app detected
-----> Installing OpenJDK 1.6... done
-----> Installing Play! 1.3.0.....
       Done installing Play!
-----> Building Play! application...
       ~        _            _
       ~  _ __ | | __ _ _  _| |
       ~ | '_ \| |/ _' | || |_|
       ~ |  __/|_|\____|\__ (_)
       ~ |_|            |__/
       ~
       ~ play! 1.3.0, https://www.playframework.com
       ~
       1.3.0
       Building Play! application at directory ./
       Resolving dependencies: .play/play dependencies ./ --forProd --forceCopy --silent -Duser.home=/tmp/build 2>&1
       ~ Resolving dependencies using /tmp/build/conf/dependencies.yml,
       ~
       ~
       ~ No dependencies to install
       ~
       ~ Done!
       ~
       Precompiling: .play/play precompile ./ --silent 2>&1
       Listening for transport dt_socket at address: 8000
       19:30:57,841 INFO  ~ Starting /tmp/build
       :: loading settings :: url = jar:file:/tmp/build/.play/framework/lib/ivy-2.3.0.jar!/org/apache/ivy/core/settings/ivysettings.xml
       19:30:58,254 INFO  ~ Precompiling ...
       Jun 9, 2016 7:30:59 PM org.codehaus.groovy.runtime.m12n.MetaInfExtensionModule newModule
       WARNING: Module [groovy-all] - Unable to load extension class [org.codehaus.groovy.runtime.NioGroovyMethods]
       19:31:02,284 INFO  ~ Done.
       ~ using java version "1.6.0_27"
-----> Discovering process types
       Procfile declares types -> web
-----> Compiled slug size is 84M
Build complete.
Launching App...
Done, rental-cutpurse:v2 deployed to Deis

Use 'deis open' to view this application in your browser

To learn more, use 'deis help' or visit https://deis.com/

To ssh://git@deis-builder.deis.rocks:2222/rental-cutpurse.git
 * [new branch]      master -> master
$ curl http://rental-cutpurse.deis.rocks
Powered by Deis
```

## Additional Resources

* [GitHub Project](https://github.com/deis/workflow)
* [Documentation](https://deis.com/docs/workflow/)
* [Blog](https://deis.com/blog/)

[Deis Workflow]: https://github.com/deis/workflow#readme
