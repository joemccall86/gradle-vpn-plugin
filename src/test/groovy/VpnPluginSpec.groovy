import spock.lang.*
import org.gradle.api.*
import org.gradle.api.plugins.*
import org.gradle.testfixtures.ProjectBuilder
import com.github.VpnPlugin

class VpnPluginSpec extends Specification {

    Project project = ProjectBuilder.builder().build()

    def setup() {
        project.vpnBaseUrl = 'localhost'
    }

    def "the vpnUrl property is created"() {
        expect:
        !project.hasProperty('vpnUrl')

        when:
        project.apply plugin: VpnPlugin

        then:
        project.hasProperty('vpnUrl')
    }

    def "the vpnUrl points to the right host when vpnBaseUrl is reachable"() {
        setup:
        project.vpnBaseUrl     = 'localhost'
        project.vpnFallbackUrl = 'baseUrl.com'

        when:
        project.apply plugin: VpnPlugin
        
        then:
        project.vpnUrl == 'localhost'
    }

    def "the vpnUrl points to the right host when vpnBaseUrl is unreachable"() {
        setup:
        project.vpnBaseUrl     = '255.255.255.0'
        project.vpnFallbackUrl = 'baseUrl.com'

        when:
        project.apply plugin: VpnPlugin
        
        then:
        project.vpnUrl == 'baseUrl.com'
    }

    def "the project is in offline mode when vpnBaseUrl is unreachable"() {
        setup:
        project.vpnBaseUrl = '255.255.255.0'

        expect:
        !project.gradle.startParameter.offline

        when:
        project.apply plugin: VpnPlugin

        then:
        project.gradle.startParameter.offline
    }
}

