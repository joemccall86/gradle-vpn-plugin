Gradle VPN Plugin
=================

A plugin that assists in building in an environment where the shared repo is 
behind a VPN.

This plugin is very simple. It will try to ping a server before any artifacts 
are checked. If that server cannot be pinged, gradle.startParameter.offline will
be set to true. The following options are available for configuring:

* vpnBaseUrl - the URL to try and ping to determine whether or not the host is
  connected to the VPN
* vpnFallbackUrl - if present, the public repository to use when the host is not
  connected to the VPN. if not present, gradle.startParameter.offline will be 
  set.