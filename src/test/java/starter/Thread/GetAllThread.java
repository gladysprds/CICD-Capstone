package starter.Thread;

import net.serenitybdd.rest.SerenityRest;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import static org.hamcrest.Matchers.equalTo;

public class GetAllThread {

    private String token;

    String base_url = "http://44.206.244.111/v1/";

    public String endpointGetAllThread(){
        return base_url + "thread/";
    }

    public void requestGetAllThread(String token) throws IOException {
        token = FileUtils.readFileToString(new File(System.getProperty("user.dir") + "//src//test//resources//filejson//tokens.json"), StandardCharsets.UTF_8);
        SerenityRest.given().header("Authorization", "Bearer " + token);
        SerenityRest.when().get(endpointGetAllThread());
    }

    public void requestGetAllThreadInvalidToken() throws IOException {
        this.token = FileUtils.readFileToString(new File(System.getProperty("user.dir") + "//src//test//resources//filejson//InvalidToken.json"), StandardCharsets.UTF_8);
        SerenityRest.given().header("Authorization", "Bearer " + this.token);
        SerenityRest.when().get(endpointGetAllThread());
    }

    public void validateDataDetail(){
        SerenityRest.then().body("message", equalTo("SUCCESS"));
    }

    public void errorMessageThread(){
            SerenityRest.then().body("message", equalTo("TOKEN_INVALID_OR_TOKEN_NULL"));

    }


}
