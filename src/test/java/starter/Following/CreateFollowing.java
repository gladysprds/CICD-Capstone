package starter.Following;

import net.serenitybdd.rest.SerenityRest;
import org.apache.commons.io.FileUtils;
import org.json.simple.JSONObject;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.Matchers.equalTo;

public class CreateFollowing {
    private String token;
    String base_url = "http://44.206.244.111/v1/";
    public String endpointPostFollowing(){
        return base_url + "following/";
    }

    public void requestPostFollowing(int follow) throws IOException {
        token = FileUtils.readFileToString(new File(System.getProperty("user.dir") + "//src//test//resources//filejson//tokens.json"), StandardCharsets.UTF_8);
        JSONObject requestData = new JSONObject();
        Map<String,Integer> user_follow = new HashMap<>();
        user_follow.put("id", follow);
        requestData.put("user_follow",user_follow);
        requestData.put("type","FOLLOW");
        SerenityRest.given().header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + token)
                .body(requestData.toJSONString());
        SerenityRest.when().post(endpointPostFollowing());
    }

    public void requestPostFollowingInvalidToken(int follow) throws IOException {
        this.token = FileUtils.readFileToString(new File(System.getProperty("user.dir") + "//src//test//resources//filejson//InvalidToken.json"), StandardCharsets.UTF_8);
        JSONObject requestData = new JSONObject();
        Map<String,Integer> user_follow = new HashMap<>();
        user_follow.put("id", follow);
        requestData.put("user_follow",user_follow);
        requestData.put("type","FOLLOW");
        SerenityRest.given().header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + this.token)
                .body(requestData.toJSONString());
        SerenityRest.when().post(endpointPostFollowing());
    }


    public void validateDataDetail(int user_id, int user_follow){
        SerenityRest.then().body("message",equalTo("SUCCESS"));
        SerenityRest.then().body("data.user.id",equalTo(user_id));
        SerenityRest.then().body("data.user_follow.id",equalTo(user_follow));

    }

    public void errorMessage(String errorMessage){
        if(errorMessage.equals("data_not_found")){
            SerenityRest.then().body("message",equalTo("DATA_NOT_FOUND"));
        }else if(errorMessage.equals("invalid token")){
            SerenityRest.then().body("message",equalTo("TOKEN_INVALID_OR_TOKEN_NULL"));
        }else if(errorMessage.equals("CAN'T_FOLLOW_YOURSELF")){
            SerenityRest.then().body("message",equalTo("CAN'T_FOLLOW_YOURSELF"));
        }

    }





}
