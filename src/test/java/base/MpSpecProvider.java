package base;

import helper.Configuration;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class MpSpecProvider {

    public static RequestSpecification getNewMPReqSpec() {
        RequestSpecBuilder reqBuilder = new RequestSpecBuilder();

        reqBuilder.addHeader("x-taxamo-token",Configuration.getXTaxamoToken());
        reqBuilder.addHeader("x-jws-signature",Configuration.getPrivateKey());
        reqBuilder.setContentType(ContentType.JSON);

        return reqBuilder.build();
    }

    public static ResponseSpecification getNewMPRespSpec() {
        ResponseSpecBuilder responseSpecBuilder = new ResponseSpecBuilder();
        responseSpecBuilder.expectStatusCode(200);

        return responseSpecBuilder.build();
    }
}
