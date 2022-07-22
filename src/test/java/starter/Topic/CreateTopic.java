package starter.Topic;

import net.serenitybdd.rest.SerenityRest;
import org.apache.commons.io.FileUtils;
import org.json.simple.JSONObject;
import utils.General;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;


import static org.hamcrest.Matchers.equalTo;

public class CreateTopic {
    private String token;
    String topicName;
    General general = new General();
    String base_url = "http://44.206.244.111/v1/";

    public String endpointPostTopic(){
        return base_url + "topic/";
    }

    public void requestPostTopic(String topicName) throws IOException {
        this.token = FileUtils.readFileToString(new File(System.getProperty("user.dir") + "//src//test//resources//filejson//tokens.json"), StandardCharsets.UTF_8);
        JSONObject requestData = new JSONObject();

         if(topicName.equals("Manga")){
             this.topicName = general.randomTopic();
         }else{
             this.topicName = topicName;
         }

        requestData.put("topicName", this.topicName);
        SerenityRest.given().header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + this.token)
                .body(requestData.toJSONString());
        SerenityRest.when().post(endpointPostTopic());
    }


    public void requestPostTopicInvalidToken(String topicName) throws IOException {
        token = FileUtils.readFileToString(new File(System.getProperty("user.dir") + "//src//test//resources//filejson//InvalidToken.json"), StandardCharsets.UTF_8);
        JSONObject requestData = new JSONObject();

        if(topicName.equals("Manga")){
            this.topicName = general.randomTopic();
        }else{
            this.topicName = topicName;
        }

        requestData.put("topicName", this.topicName);
        SerenityRest.given().header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + token)
                .body(requestData.toJSONString());
        SerenityRest.when().post(endpointPostTopic());
    }


    public void getDetailTopic(){
        SerenityRest.then().body("data.topicName", equalTo(this.topicName));
    }


    public void errorMessageTopic(String detail_topic){
        if (detail_topic.equals("duplicate")){
            SerenityRest.then().body("message",equalTo("data duplicate"));
        } else if(detail_topic.equals("null")){
            SerenityRest.then().body("message",equalTo("DATA_NOT_FOUND"));
        }
    }


}
