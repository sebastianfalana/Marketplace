package actions;

import base.MpSpecProvider;
import base.TestBase;
import helper.Configuration;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;

public class CreateMP extends TestBase {

    public static String createMP() {
        return
            given()
                    .spec(MpSpecProvider.getNewMPReqSpec()).
            when()
                    .post(Configuration.getSellerDBAdmin()).
            then()
                    .spec(MpSpecProvider.getNewMPRespSpec())
                    .extract().response().jsonPath().get("marketplace_code");
    }

}
