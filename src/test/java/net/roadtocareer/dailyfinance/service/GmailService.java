package net.roadtocareer.dailyfinance.service;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import net.roadtocareer.dailyfinance.setup.Setup;
import org.apache.commons.configuration.ConfigurationException;

import java.io.IOException;

/*
 ** 2025, January 11, Saturday, 10:53 AM
 */
public class GmailService {
    public static Response readLatestMail() throws IOException, ConfigurationException, InterruptedException {
        Thread.sleep(9000);
        RestAssured.baseURI = "https://gmail.googleapis.com";

        Response gmailList = RestAssured
                .given()
                .contentType("application/json")
                .header("Authorization", "Bearer " + Setup.configFile().getProperty("access_token"))
                .when()
                .get("/gmail/v1/users/me/messages");

        String messageId = gmailList.jsonPath().get("messages[0].id").toString();

        Response gmail = RestAssured
                .given()
                .contentType("application/json")
                .header("Authorization", "Bearer " + Setup.configFile().getProperty("access_token"))
                .when()
                .get("/gmail/v1/users/me/messages/"+ messageId);

        return gmail;
    }
}
