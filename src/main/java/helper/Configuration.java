package helper;


import static jdk.internal.net.http.common.Utils.getProperty;

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

    public static String getPrivateKey() {
        return getProperty("privateKey");
    }
}
