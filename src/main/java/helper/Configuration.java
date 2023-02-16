package helper;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import java.util.Properties;

import static jdk.internal.net.http.common.Utils.getProperty;


public class Configuration {

    public static String getBaseUrl() {
        return getProperty("baseUrl");
    }

    public static String getPrivateKey() {
        return getProperty("privateKey");
    }
}
