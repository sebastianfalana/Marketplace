import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;

public class CreateMP extends TestBase{

    public void createMP() {
        Response response =
        given()

        when()
                .post(sellerDBAdmin)
    }

}
