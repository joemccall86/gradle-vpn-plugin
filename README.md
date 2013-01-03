Gradle VPN Plugin [![Build Status](https://buildhive.cloudbees.com/job/joemccall86/job/gradle-vpn-plugin/badge/icon)](https://buildhive.cloudbees.com/job/joemccall86/job/gradle-vpn-plugin/)
=================

A plugin that assists in building in an environment where the shared repo is 
behind a VPN.

This plugin is very simple. It will try to ping a server before any artifacts 
are checked. If that server cannot be pinged, gradle.startParameter.offline will
be set to true.

The plugin can be configured with the following parameters:

* vpn.baseUrl - the URL to to ping when configurations incoming dependencies
  are to be resolved.
* vpn.pingTimeout - the timeout in msec to wait for a ICMP reply

Example:
<pre><code>
// build.gradle

buildscript {
    dependencies {
        classpath 'net.ll86.plugins:gradle-vpn-plugin:0.1-SNAPSHOT'
    }
}

apply plugin: 'vpn'
vpn.baseHost = '192.168.40.21'
vpn.pingTimeout = 3000

repositories {
    maven { url "http://${vpn.baseHost}:8081/artifactory/repo" }
}

</code></pre>

When the above code is run, it will behave the following way:
* If vpn.baseHost is pingable, gradle will behave as default
* If vpn.baseHost is not pingable, gradle will operate in offline mode

Note that due to a chicken/egg problem, the VPN plugin can't really help you 
with repositories in the buildscript section of your project. It can only help
with compile dependencies.

TODO
---
There could possibly be vpn.fallbackUrl, but I'm not sure how to set that up 
because it seems like the repositories get resolved *after* the ping command 
could be run.
