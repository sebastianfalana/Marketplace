package base;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import helper.Configuration;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import java.security.interfaces.RSAPrivateKey;

public class MpSpecProvider {

    public static RequestSpecification getNewMPReqSpec() {
        RequestSpecBuilder reqBuilder = new RequestSpecBuilder();

        reqBuilder.addHeader("x-taxamo-token",Configuration.getXTaxamoToken());
        reqBuilder.addHeader("x-jws-signature",Configuration.getPrivateKey());
        reqBuilder.setContentType(ContentType.JSON);

        return reqBuilder.build();
    }

    public static ResponseSpecification getJWT() {
        try {
            Algorithm algorithm = Algorithm.RSA512(Configuration.getPrivateKey());
            String token = JWT.create()
                    .withIssuer("auth0")
                    .sign(algorithm);
        } catch (JWTCreationException exception){
            // Invalid Signing configuration / Couldn't convert Claims.
        }
    }

    public static ResponseSpecification getNewMPRespSpec() {
        ResponseSpecBuilder responseSpecBuilder = new ResponseSpecBuilder();
        responseSpecBuilder.expectStatusCode(200);

        return responseSpecBuilder.build();
    }
}
