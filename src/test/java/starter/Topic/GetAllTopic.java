package starter.Topic;

import net.serenitybdd.rest.SerenityRest;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import static org.hamcrest.Matchers.equalTo;

public class GetAllTopic {
    private String token;
    String base_url = "http://44.206.244.111/v1/";

    public String endpointGetAllTopic(){
        return base_url + "topic/";
    }

    public void requestgetAllTopic() throws IOException {
        token = FileUtils.readFileToString(new File(System.getProperty("user.dir") + "//src//test//resources//filejson//tokens.json"), StandardCharsets.UTF_8);
        SerenityRest.given().header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + token);
        SerenityRest.when().get(endpointGetAllTopic());
    }

    public void validateDataDetai(){
        SerenityRest.then().body("message", equalTo("SUCCESS"));
    }


    public void requestgetAllTopicInvalidToken() throws IOException {
        token = FileUtils.readFileToString(new File(System.getProperty("user.dir") + "//src//test//resources//filejson//InvalidToken.json"), StandardCharsets.UTF_8);
        SerenityRest.given().header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + token);
        SerenityRest.when().get(endpointGetAllTopic());
    }
    public void errorMessageForGetAllTopic(){
        SerenityRest.then().body("message",equalTo("TOKEN_INVALID_OR_TOKEN_NULL"));
    }





}
