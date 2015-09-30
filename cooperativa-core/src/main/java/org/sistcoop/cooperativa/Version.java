package org.sistcoop.cooperativa;

import java.io.IOException;
import java.io.InputStream;
import java.util.Calendar;
import java.util.Properties;

import org.codehaus.jackson.annotate.JsonProperty;

/**
 * @author <a href="mailto:bill@burkecentral.com">Bill Burke</a>
 * @version $Revision: 1 $
 */
public class Version {
    public static String VERSION;
    public static String RESOURCES_VERSION;
    public static String BUILD_TIME;
    public static final String UNKNOWN = "UNKNOWN";
    public static final Version SINGLETON;

    private final String version = VERSION;
    private final String buildTime = BUILD_TIME;

    static {
        Properties props = new Properties();
        InputStream is = Version.class.getResourceAsStream("/persona-version.properties");
        try {
            props.load(is);
            VERSION = props.getProperty("version");
            BUILD_TIME = props.getProperty("build-time");
            RESOURCES_VERSION = VERSION.toLowerCase();
            if (RESOURCES_VERSION.endsWith("-snapshot")) {
                RESOURCES_VERSION = RESOURCES_VERSION.replace("-snapshot", "-"
                        + Calendar.getInstance().getTime().toString());
            }
        } catch (IOException e) {
            VERSION = UNKNOWN;
            BUILD_TIME = UNKNOWN;
        }

        SINGLETON = new Version();
    }

    @JsonProperty("version")
    public String getVersion() {
        return version;
    }

    @JsonProperty("build-time")
    public String getBuildTime() {
        return buildTime;
    }

}
