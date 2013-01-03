package net.ll86.plugins

import org.gradle.api.Plugin
import org.gradle.api.Project

class VpnPlugin implements Plugin<Project> {

    private vpnStatusDetermined = false

    @Override
    void apply(Project project) {

        project.extensions.create('vpn', VpnPluginExtension)

        if (project.gradle.startParameter.offline) {
            vpnStatusDetermined = true
        }

        project.configurations.each {
            it.incoming.beforeResolve {
                if (!vpnStatusDetermined) {
                    def baseHost = project.vpn.baseHost
                    def pingTimeout = project.vpn.pingTimeout
                    if (InetAddress.getByName(baseHost).isReachable(pingTimeout)) {
                        project.logger.info 'Found VPN connection'
                    } else {
                        project.gradle.startParameter.offline = true
                        project.logger.info 'VPN connection not found, defaulting to offline build'
                    }

                    vpnStatusDetermined = true
                }
            }
        }
    }
}

