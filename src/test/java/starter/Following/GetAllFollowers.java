package starter.Following;

import net.serenitybdd.rest.SerenityRest;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import static org.hamcrest.Matchers.equalTo;

public class GetAllFollowers {
    private String token;
    String base_url = "http://44.206.244.111/v1/";

    public String endpointGetFollowers(int id_user){
        return base_url + "following/" + id_user;
    }

    public void requestGetFollowers(int id_user) throws IOException {
        token = FileUtils.readFileToString(new File(System.getProperty("user.dir") + "//src//test//resources//filejson//tokens.json"), StandardCharsets.UTF_8);
        SerenityRest.given().header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + token);
        SerenityRest.when().get(endpointGetFollowers(id_user));
    }

    public void requestGetFollowersInvalidToken(int id_user) throws IOException {
        this.token = FileUtils.readFileToString(new File(System.getProperty("user.dir") + "//src//test//resources//filejson//InvalidToken.json"), StandardCharsets.UTF_8);
        SerenityRest.given().header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + this.token);
        SerenityRest.when().get(endpointGetFollowers(id_user));
    }

    public void validateGetDetail(int user_id,int user_follow){
        SerenityRest.then().body("message",equalTo("SUCCESS"));
        SerenityRest.then().body("data.user.id",equalTo(user_id));
        SerenityRest.then().body("data.user_follow.id",equalTo(user_follow));
    }

    public void errorMessage(String errorMessage){
        if(errorMessage.equals("data not found")){
            SerenityRest.then().body("message",equalTo("DATA_NOT_FOUND"));
        }else if(errorMessage.equals("invalid token")){
            SerenityRest.then().body("message",equalTo("TOKEN_INVALID_OR_TOKEN_NULL"));
        }

    }

}
