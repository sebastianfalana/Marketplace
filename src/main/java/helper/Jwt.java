package helper;

import java.io.*;
import java.security.MessageDigest;
import java.security.KeyPair;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.security.interfaces.ECPrivateKey;
import java.security.spec.InvalidKeySpecException;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.UUID;
import java.math.BigInteger;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JOSEObjectType;
import com.nimbusds.jose.crypto.ECDSASigner;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;

import helper.Configuration;
import org.bouncycastle.openssl.PEMParser;
import org.bouncycastle.openssl.PEMKeyPair;
import org.bouncycastle.openssl.jcajce.JcaPEMKeyConverter;

public class Jwt {
    public static void main(String[] args)
            throws Exception {
        /*
         ***** SIGNING VARIABLES *****
         * JWT sign values and claims are defined:
         * - jti : UUID V4 (jwtID)
         * - sub : Method
         * - aud : Endpoint
         * - iss : App ID
         * - kid : Key ID
         * - iat : Current date and time
         * - exp : Expiry date and time
         * - payload : JSON payload (empty string if there are no payload)
         * - hashPayload : Hash the payload with SHA-256
         */
        String jti = UUID.randomUUID().toString();
        String sub = "POST";
        String aud = Configuration.getSellerDBAdmin()+Configuration.getAdminEndpoint();
        String iss = "64858388-5b76-496a-95b1-82b3bf4b4683";
        String kid = "apex-example";
        Date iat = new Date();
        Date exp = new Date(iat.getTime() + 180 * 1000);

        String jsonstring = "{\"payload\":\"data\"}";
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        byte[] digest = md.digest(jsonstring.getBytes("UTF-8"));
        String hashPayload = (new BigInteger(1, digest)).toString(16);

        /*
         ***** READ PRIVATE KEY *****
         * ecPrivateKey : Private key to sign JWT
         */

        PEMParser pemParser = new PEMParser(convertStringToX509Cert(Configuration.getPrivateKey()));
        Object object = pemParser.readObject();
        JcaPEMKeyConverter converter = new JcaPEMKeyConverter();
        KeyPair kp = converter.getKeyPair(((PEMKeyPair) object));
        ECPrivateKey ecPrivateKey = (ECPrivateKey) kp.getPrivate();

        /*
         ***** CREATE JWT *****
         * signedJWT.serialize() : JWT to append in header (x-apex-jwt).
         */
        JWSHeader jwsHeader = new JWSHeader.Builder(JWSAlgorithm.RS512)
                .keyID(kid)
                .type(JOSEObjectType.JWT)
                .build();
        JWTClaimsSet claimSet = new JWTClaimsSet.Builder()
                .issuer(iss)
                .subject(sub)
                .issueTime(iat)
                .jwtID(jti)
                .expirationTime(exp)
                .claim("data", hashPayload)
                .claim("aud", aud)
                .build();
        SignedJWT signedJWT = new SignedJWT(jwsHeader, claimSet);
        signedJWT.sign(new ECDSASigner(ecPrivateKey));
        System.out.println(signedJWT.serialize());
    }

    private static X509Certificate convertStringToX509Cert(String certificate) throws Exception{
        InputStream targetStream = new ByteArrayInputStream(certificate.getBytes());
        return (X509Certificate) CertificateFactory
                .getInstance("X509")
                .generateCertificate(targetStream);
    }
}