import spock.lang.*
import org.gradle.api.*
import org.gradle.api.plugins.*
import org.gradle.testfixtures.ProjectBuilder
import net.ll86.plugins.VpnPlugin
import net.ll86.plugins.VpnPluginExtension

class VpnPluginSpec extends Specification {

    Project project = ProjectBuilder.builder().build()

    def "the project extension is properly applied"() {
        expect:
        !project.extensions.findByType(VpnPluginExtension.class)

        when:
        project.apply plugin: VpnPlugin

        then:
        project.extensions.findByType(VpnPluginExtension.class)
    }

    // We can't really do any further testing at this point because the
    // project cannot be evaluated inside of a unit testing environment. We can
    // make sure that the project extension has the right value for the
    // resolvedUri however. We just can't ensure that it's ready properly by
    // the repositories.

    // If you're here looking for usage see the examples directory in the root
    // project.
}

