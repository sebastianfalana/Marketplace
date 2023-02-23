package helper;


import com.auth0.jwt.interfaces.RSAKeyProvider;

import java.io.FileInputStream;
import java.io.IOException;
import java.security.interfaces.RSAPrivateKey;
import java.util.Properties;

public class Configuration {



    public static String getSellerDBAdmin() {
        return getProperty("sellerDBAdmin");
    }

    public static String getBaseUrl() {
        return getProperty("baseUrl");
    }
    public static String getXTaxamoToken() {
        return getProperty("xTaxamoToken");
    }

    public static RSAKeyProvider getPrivateKey() {
        return getProperty("privateKey");
    }public static String getAdminEndpoint() {
        return getProperty("adminEndpoint");
    }

    private static String getProperty(String propertyName) {
        Properties properties = new Properties();
        try {
            properties.load(new FileInputStream("src/main/resources/config.properties"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return properties.getProperty(propertyName);
    }
}
