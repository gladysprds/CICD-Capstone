package starter.Topic;

import net.serenitybdd.rest.SerenityRest;
import org.apache.commons.io.FileUtils;
import org.json.simple.JSONObject;
import utils.General;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import static org.hamcrest.Matchers.equalTo;

public class PutTopic {
    private String token;
    String topicName;
    String base_url = "http://44.206.244.111/v1/";


    public String endpointPutTopic(int topic_id){
        return base_url + "topic/" + topic_id;
    }

    public void requestPutTopic(int topic_id, String data) throws IOException {
        token = FileUtils.readFileToString(new File(System.getProperty("user.dir") + "//src//test//resources//filejson//tokens.json"), StandardCharsets.UTF_8);
        JSONObject requestData = new JSONObject();
        requestData.put("topicName", data);
        SerenityRest.given().header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + token)
                .body(requestData.toJSONString());
        SerenityRest.when().put(endpointPutTopic(topic_id));
    }



    public void validateDataDetail(String data){
        SerenityRest.then().body("data.topicName", equalTo(data));
    }

    public void errorMessage(){
        SerenityRest.then().body("message", equalTo("DATA_NOT_FOUND"));
    }
}
