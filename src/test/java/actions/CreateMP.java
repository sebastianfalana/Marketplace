package actions;

import base.MpSpecProvider;
import base.TestBase;
import helper.Configuration;
import org.testng.annotations.Test;

import java.io.File;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;

public class CreateMP extends TestBase {

    @Test
    public void createMP() {

            given()
                    .spec(MpSpecProvider.getNewMPReqSpec())
                    .body(new File("src/main/resources/createMP.json")).
            when()
                    .post(Configuration.getSellerDBAdmin()+Configuration.getAdminEndpoint()).
            then()
                    .statusCode(200)
                    .spec(MpSpecProvider.getNewMPRespSpec());
    }

}
