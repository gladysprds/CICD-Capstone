package starter.Users;
import net.serenitybdd.rest.SerenityRest;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import static org.hamcrest.Matchers.equalTo;

public class GetAllUsers {
    private String token;
    String base_url = "http://44.206.244.111/v1/";

    public String endpointGetAllUser(){
        return base_url + "user";
    }

    public void requestGetAllUsers() throws IOException {
        token = FileUtils.readFileToString(new File(System.getProperty("user.dir") + "//src//test//resources//filejson//tokens.json"), StandardCharsets.UTF_8);
        SerenityRest.given().header("Authorization", "Bearer " + token);
        SerenityRest.when().get(endpointGetAllUser());

    }

    public void validateMessage(){
        SerenityRest.then().body("message", equalTo("SUCCESS"));
    }

    public void requestGetAllUserNoToken(){
        token = null;
        SerenityRest.given().header("Authorization", "Bearer " + token);
        SerenityRest.when().get(endpointGetAllUser());
    }

    public void validateErrorMessage(){
        SerenityRest.then().body("message", equalTo("TOKEN_INVALID_OR_TOKEN_NULL"));
    }



}
