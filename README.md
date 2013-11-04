# Play Quick Start Guide

This guide will walk you through deploying a Python application on Deis.

## Prerequisites

* A [User Account](http://docs.deis.io/en/latest/client/register/) on a [Deis Controller](http://docs.deis.io/en/latest/terms/controller/).
* A [Deis Formation](http://docs.deis.io/en/latest/gettingstarted/concepts/#formations) that is ready to host applications

If you do not yet have a controller or a Deis formation, please review the [Deis installation](http://docs.deis.io/en/latest/gettingstarted/installation/) instructions.

## Setup your workstation

* Install [RubyGems](http://rubygems.org/pages/download) to get the `gem` command on your workstation
* Install [Foreman](http://ddollar.github.com/foreman/) with `gem install foreman`
* Install [Play](http://www.playframework.com/documentation/2.2.x/Installing)

## Clone your Application

If you want to use an existing application, no problem.  You can also use the Deis sample application located at <https://github.com/bengrunfeld/example-play>.  Clone the example application to your local workstation:

    $ git clone https://github.com/bengrunfeld/example-play.git
    $ cd example-play

## Prepare your Application

To use a Play application with Deis, you will need to conform to 3 basic requirements:

 1. Use [Play Dependencies](http://www.playframework.com/documentation/1.2.1/dependency) to manage dependencies
 2. Use [Foreman](http://ddollar.github.com/foreman/) to manage processes
 3. Use [Environment Variables](https://help.ubuntu.com/community/EnvironmentVariables) to manage configuration inside your application

If you're deploying the example application, it already conforms to these requirements.

#### 1. Use Play Dependencies to manage dependencies

Play requires that you explicitly declare your dependencies using a [dependencies.yml](http://www.playframework.com/documentation/1.2.1/dependency) file. Here is a very basic example:

	# Application dependencies
	
	require:
	    - play 1.2.4

You can then install dependencies on your local workstation with `play dependencies`:

	[info] :: delivering :: exampleapp#exampleapp_2.10;1.0-SNAPSHOT :: 1.0-SNAPSHOT :: integration :: Mon Nov 04 13:21:33 MST 2013
	[info] 	delivering ivy file to /Users/bengrunfeld/Desktop/OpDemand/repos/example-play/exampleApp/target/scala-2.10/ivy-1.0-SNAPSHOT.xml
	
	Here are the resolved dependencies of your application:
	
	+-------------------------------------------------------------------+--------------------------------------------------------+------------------------------+
	| Module                                                            | Required by                                            | Note                         |
	+-------------------------------------------------------------------+--------------------------------------------------------+------------------------------+
	| com.typesafe.play:play-cache_2.10:2.2.0                           | exampleapp:exampleapp_2.10:1.0-SNAPSHOT                | As play-cache_2.10.jar       |
	+-------------------------------------------------------------------+--------------------------------------------------------+------------------------------+
	| net.sf.ehcache:ehcache-core:2.6.6                                 | com.typesafe.play:play-cache_2.10:2.2.0                | As ehcache-core.jar          |

Note: You can test locally using `play run`.

#### 2. Use Foreman to manage processes

Deis relies on a [Foreman](http://ddollar.github.com/foreman/) `Procfile` that lives in the root of your repository.  This is where you define the command(s) used to run your application.  Here is an example `Procfile`:

	web:    play run --http.port=$PORT $PLAY_OPTS

This tells Deis to run `web` workers using the command `play run --http.port=$PORT $PLAY_OPTS`. You can test this locally by running `foreman start`.

	13:27:11 web.1  | started with pid 41249
	13:27:14 web.1  | [info] Loading project definition from /Users/bengrunfeld/Desktop/OpDemand/repos/example-play/exampleApp/project
	13:27:15 web.1  | [info] Set current project to exampleApp (in build file:/Users/bengrunfeld/Desktop/OpDemand/repos/example-play/exampleApp/)
	13:27:15 web.1  | 
	13:27:16 web.1  | --- (Running the application from SBT, auto-reloading is enabled) ---
	13:27:16 web.1  | 
	13:27:16 web.1  | [info] play - Listening for HTTP on /0:0:0:0:0:0:0:0%0:9000

You should now be able to access your application locally at <http://localhost:9000>.

#### 3. Use Environment Variables to manage configuration

Deis uses environment variables to manage your application's configuration. For example, your application listener must use the value of the `PORT` environment variable. The following code snippet demonstrates how this can work inside your application:

    int port = System.getenv("PORT");
    if (port == null){ port = 5000;)}

## Create a new Application

Per the prerequisites, we assume you have access to an existing Deis formation. If not, please review the Deis [installation instuctions](http://docs.deis.io/en/latest/gettingstarted/installation/).

Use the following command to create an application on an existing Deis formation.

    $ deis create --formation=<formationName> --id=<appName>
	Creating application... done, created <appName>
	Git remote deis added
    
If an ID is not provided, one will be auto-generated for you.

## Deploy your Application

Use `git push deis master` to deploy your application.

	$ git push deis master
	<show code>
	
Once your application has been deployed, use `deis open` to view it in a browser. To find out more info about your application, use `deis info`.

## Scale your Application

To scale your application's [Docker](http://docker.io) containers, use `deis scale` and specify the number of containers for each process type defined in your application's `Procfile`. For example, `deis scale web=8`.

	$ deis scale web=8
	<show code>


## Configure your Application

Deis applications are configured using environment variables. The example application includes a special `POWERED_BY` variable to help demonstrate how you would provide application-level configuration. 

	$ curl -s http://yourapp.yourformation.com
	Powered by Deis
	$ deis config:set POWERED_BY=Play
	=== <appName>
	POWERED_BY: Play
	$ curl -s http://yourapp.yourformation.com
	Powered by Play

`deis config:set` is also how you connect your application to backing services like databases, queues and caches. You can use `deis run` to execute one-off commands against your application for things like database administration, initial application setup and inspecting your container environment.

	$ deis run ls -la
	<show code>
	
## Troubleshoot your Application

To view your application's log output, including any errors or stack traces, use `deis logs`.

    $ deis logs
    <show output>

## Additional Resources

* [Get Deis](http://deis.io/get-deis/)
* [GitHub Project](https://github.com/opdemand/deis)
* [Documentation](http://docs.deis.io/)
* [Blog](http://deis.io/blog/)
