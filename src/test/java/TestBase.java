import helper.Configuration;

public class TestBase {

    public String baseUrl = Configuration.getBaseUrl();
    public String sellerDBAdmin = Configuration.getSellerDBAdmin();
    public String xTaxamoToken = Configuration.getXTaxamoToken();
    public String baseAdminEndpoint = "/admin/v1/marketplaces";
    public String registrationMPEndpoint = "/{mp_code}/registration";

}
