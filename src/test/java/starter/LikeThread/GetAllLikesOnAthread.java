package starter.LikeThread;

import net.serenitybdd.rest.SerenityRest;
import org.apache.commons.io.FileUtils;
import org.json.simple.JSONObject;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import static org.hamcrest.Matchers.equalTo;

public class GetAllLikesOnAthread {
    private String token;
    String base_url = "http://44.206.244.111/v1/";

    public String endpointLikesThread(){
        return base_url + "likethread/like";
    }

    public void requestGetAllLikes(int id_thread) throws IOException {
        token = FileUtils.readFileToString(new File(System.getProperty("user.dir") + "//src//test//resources//filejson//tokens.json"), StandardCharsets.UTF_8);
        JSONObject requestData = new JSONObject();
        requestData.put("id",id_thread);
        SerenityRest.given().header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + token)
         .body(requestData.toJSONString());
        SerenityRest.when().get(endpointLikesThread());
    }

    public void validateDataDetailCountOnThread(){
        SerenityRest.then().body("message",equalTo("SUCCESS"));
        SerenityRest.then().body("data",equalTo(1));
    }

    public void errorMessage(){
        SerenityRest.then().body("message",equalTo("DATA_NOT_FOUND"));
    }




}
