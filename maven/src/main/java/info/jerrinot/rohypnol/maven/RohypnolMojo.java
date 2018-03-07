package info.jerrinot.rohypnol.maven;

import info.jerrinot.rohypnol.Config;
import org.apache.maven.artifact.Artifact;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.plugins.annotations.ResolutionScope;
import org.apache.maven.project.MavenProject;

import java.io.File;
import java.util.Map;
import java.util.Properties;

@Mojo(name = "prepare-agent", defaultPhase = LifecyclePhase.INITIALIZE,
        requiresDependencyResolution = ResolutionScope.RUNTIME,
        threadSafe = true)
public class RohypnolMojo extends AbstractMojo {

    private static final String AGENT_ARTIFACT_NAME = "info.jerrinot:rohypnol-core";

    @Parameter(property = "plugin.artifactMap", required = true, readonly = true)
    private Map<String, Artifact> pluginArtifactMap;

    @Parameter(property = "project", readonly = true)
    private MavenProject project;

    @Parameter(property = "rohypnol.classPrefix")
    private String classPrefix;

    @Parameter(property = "rohypnol.minIntervalNanos", defaultValue = "-1")
    private long minIntervalNanos;

    @Parameter(property = "rohypnol.maxPauseNanos", defaultValue = "-1")
    private long maxPauseNanos;

    private static final String SUREFIRE_ARG_LINE_KEY = "argLine";

    @Override
    public void execute() {
        getLog().debug("Running Rohypnol Plugin");
        if (classPrefix == null || classPrefix.isEmpty()) {
            throw new IllegalArgumentException("Class prefix is not configured. " +
                    "Use plugin configuration 'classPrefix' to select which classes to instrument.");
        }

        Properties properties = project.getProperties();

        String oldArgLine = properties.getProperty(SUREFIRE_ARG_LINE_KEY);
        if (oldArgLine == null) {
            oldArgLine = "";
        }
        getLog().debug("Old arg line: " + oldArgLine);
        String newValue = createNewArgLine(oldArgLine);
        getLog().debug("Setting argline as " + newValue);
        properties.setProperty(SUREFIRE_ARG_LINE_KEY, newValue);
    }

    private String createNewArgLine(String oldValue) {
        File agentJarFile = getAgentJarFile();
        String newValue = oldValue + " -javaagent:"+agentJarFile.getAbsolutePath() + " ";
        newValue += "-D" + Config.CLASS_PREFIX_KEY + "=" + classPrefix + " ";
        if (minIntervalNanos != -1) {
            newValue += "-D" + Config.MINIMUM_INTERVAL_BETWEEN_PAUSES_KEY + "=" + minIntervalNanos + " ";
        }
        if (maxPauseNanos != -1) {
            newValue += "-D" + Config.MAXIMUM_PAUSE_NANOS_KEY + "=" + maxPauseNanos + " ";
        }
        return newValue;
    }

    private File getAgentJarFile() {
        final Artifact artifact = pluginArtifactMap.get(AGENT_ARTIFACT_NAME);
        return artifact.getFile();
    }
}
