import helper.Configuration;

public class TestBase {

    public String baseUrl = Configuration.getBaseUrl();
    public String sellerDB_Admin = Configuration.getSellerDB_Admin();
    public String baseEndpoint = "/admin/v1/marketplaces";
    public String registrationMP = "/{mp_code}/registration";

}
