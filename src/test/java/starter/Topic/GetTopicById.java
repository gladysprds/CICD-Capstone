package starter.Topic;

import net.serenitybdd.rest.SerenityRest;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import static org.hamcrest.Matchers.equalTo;

public class GetTopicById {
    private String token;
    String base_url = "http://44.206.244.111/v1/";

    public String endpointGetTopicById(String topic_id){
        return base_url + "topic/" + topic_id;
    }

    public void requestGetTopicById(String topic_id, String token) throws IOException {
        this.token = FileUtils.readFileToString(new File(System.getProperty("user.dir") + "//src//test//resources//filejson//tokens.json"), StandardCharsets.UTF_8);
        SerenityRest.given().header("Authorization", "Bearer " + this.token);
        SerenityRest.when().get(endpointGetTopicById(topic_id));
    }

    public void requestGetTopicByIdInvalidToken(String topic_id) throws IOException {
        token = FileUtils.readFileToString(new File(System.getProperty("user.dir") + "//src//test//resources//filejson//InvalidToken.json"), StandardCharsets.UTF_8);
        SerenityRest.given().header("Authorization", "Bearer " + token);
        SerenityRest.when().get(endpointGetTopicById(topic_id));
    }

    public void validateTopicDetail(){
        SerenityRest.then().body("data.id", equalTo(1));
    }

    public void errorMessageForGetTopicById(String result){
        if (result.equals("invalid token")){
            SerenityRest.then().body("message",equalTo("TOKEN_INVALID_OR_TOKEN_NULL"));
        } else if(result.equals("bad request")){
            SerenityRest.then().body("error",equalTo("Bad Request"));
        } else if(result.equals("data_not_found")){
            SerenityRest.then().body("message",equalTo("DATA_NOT_FOUND"));
        }
    }




}
