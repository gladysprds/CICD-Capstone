package starter.Autentikasi;

import io.restassured.response.Response;
import net.serenitybdd.rest.SerenityRest;
import org.json.simple.JSONObject;

import java.io.FileWriter;
import java.io.IOException;

import static org.hamcrest.Matchers.equalTo;

public class Login {
    String base_url = "http://44.206.244.111/v1/";

    public String endpointLogin(){
        return base_url + "auth/login";
    }

    public void requestLogin(String email ,String password){
        JSONObject requestData = new JSONObject();

        requestData.put("email", email);
        requestData.put("password",password);
        SerenityRest.given().header("Content-Type", "application/json")
                .body(requestData.toJSONString());
        SerenityRest.when().post(endpointLogin());
    }

    public void validateDetailMessage(){
        SerenityRest.then().body("message", equalTo("SUCCESS"));
    }

    public void getToken(){
        Response response = SerenityRest.lastResponse();
        String token = response.body().path("data.token");
        try (FileWriter file = new FileWriter("src//test//resources//filejson//tokens.json")) {
            file.write(token);
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void errorMessageInvalidData(){
        SerenityRest.then().body("message", equalTo("DATA_NOT_FOUND"));
    }

    public void errorMessageNullEmail(){
        SerenityRest.then().body("message",equalTo("EMAIL_REQUIRED"));
    }

    public void errorMessageNullPass(){
        SerenityRest.then().body("message",equalTo("PASSWORD_REQUIRED"));
    }







}
