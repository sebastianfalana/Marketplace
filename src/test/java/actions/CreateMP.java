package actions;

import base.MpSpecProvider;
import base.TestBase;
import helper.Configuration;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;

public class CreateMP extends TestBase {

    @Test
    public void createMP() {

            given()
                    .spec(MpSpecProvider.getNewMPReqSpec())
                    .body(body).
            when()
                    .post(Configuration.getSellerDBAdmin()).
            then()
                    .spec(MpSpecProvider.getNewMPRespSpec());
    }

}
