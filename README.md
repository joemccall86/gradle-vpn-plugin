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

apply plugin: VpnPlugin
vpn.baseHost = '192.168.40.21'
vpn.pingTimeout = 3000

repositories {
    maven { url "http://${vpn.baseHost}:8081/artifactory/repo" }
}

</code></pre>

