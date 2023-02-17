package base;

import helper.Configuration;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeMethod;


import static javax.swing.text.DefaultStyledDocument.ElementSpec.ContentType;

public class TestBase {

    public String baseUrl = Configuration.getBaseUrl();
    public String sellerDBAdmin = Configuration.getSellerDBAdmin();
    public String xTaxamoToken = Configuration.getXTaxamoToken();
    public String baseAdminEndpoint = "/admin/v1/marketplaces";
    public String registrationMPEndpoint = "/{mp_code}/registration";
    public RequestSpecification reqSpec;

    @BeforeMethod
    public void setup(){
        RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());

        RequestSpecBuilder reqBuilder = new RequestSpecBuilder();
        reqBuilder.addHeader("x-taxamo-token",Configuration.getXTaxamoToken());
        reqBuilder.setContentType(io.restassured.http.ContentType.JSON);

        reqSpec = reqBuilder.build();
    }

}
