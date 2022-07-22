package starter.Users;

import net.serenitybdd.rest.SerenityRest;
import org.apache.commons.io.FileUtils;
import org.json.simple.JSONObject;
import utils.General;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import static net.serenitybdd.rest.RestRequests.given;
import static org.hamcrest.Matchers.equalTo;

public class UpdateUsers {
    General general = new General();
    private String token;
    String base_url = "http://44.206.244.111/v1/";
    String data;

    public String endpointUpdateUser(String id_user){
        return base_url + "user/update/" + id_user ;
    }

    public void requestUpdateUser(String id_user,String data) throws IOException {
        token = FileUtils.readFileToString(new File(System.getProperty("user.dir") + "//src//test//resources//filejson//tokens.json"), StandardCharsets.UTF_8);
        JSONObject requestData = new JSONObject();
        requestData.put("name", data);
        SerenityRest.given().header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + token)
                .body(requestData.toJSONString());
        SerenityRest.when().put(endpointUpdateUser(id_user));
    }


    public void requestUpdateUserInvalidToken(String id_user,String data) throws IOException {
        token = FileUtils.readFileToString(new File(System.getProperty("user.dir") + "//src//test//resources//filejson//InvalidToken.json"), StandardCharsets.UTF_8);
        JSONObject requestData = new JSONObject();

        if(data.equals("ihsangtg")){
            this.data = general.randomName();
        }else{
            this.data = data;
        }

        requestData.put("name", data);
        SerenityRest.given().header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + token)
                .body(requestData.toJSONString());
        SerenityRest.when().post(endpointUpdateUser(id_user));
    }

    public void validateDataDetail(String data){
        SerenityRest.then().body("data.name", equalTo(data));
    }

    public void errorMessage(){
        SerenityRest.then().body("message", equalTo("TOKEN_INVALID_OR_TOKEN_NULL"));
    }







}
