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

Note that this plugin is still in early development and does not yet work. If
you're interested in contributing feel free to fork and make pull requests.

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

