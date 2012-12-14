package com.github

import org.gradle.api.Plugin
import org.gradle.api.Project

class VpnPlugin implements Plugin<Project> {

    @Override
    void apply(Project project) {

        def vpnBaseUrl     = project.vpnBaseUrl
        def vpnPingTimeout = project.hasProperty('vpnPingTimeout')?project.vpnPingTimeout:3000

        if (!project.gradle.startParameter.offline) {
            if (InetAddress.getByName(vpnBaseUrl).isReachable(vpnPingTimeout)) {
                project.logger.info 'Found VPN Connection'
                project.vpnUrl = vpnBaseUrl

            } else {
                project.logger.info 'No VPN connection'
                if (project.hasProperty('vpnFallbackUrl')) {
                    project.logger.info "Setting vpnUrl to ${project.vpnFallbackUrl}"
                    project.vpnUrl = project.vpnFallbackUrl
                } else {
                    project.logger.info 'Defaulting to offline build'
                    project.vpnUrl = vpnBaseUrl
                    project.gradle.startParameter.offline = true
                }
            }
        }
    }
}

