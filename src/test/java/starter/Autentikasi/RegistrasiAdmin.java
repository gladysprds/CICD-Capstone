package starter.Autentikasi;

import net.serenitybdd.rest.SerenityRest;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import static net.serenitybdd.rest.RestRequests.given;
import static net.serenitybdd.rest.SerenityRest.then;
import static org.hamcrest.Matchers.equalTo;

public class RegistrasiAdmin{
    private String token;

    BodyRegister bodyRegister = new BodyRegister();
    String base_url = "http://44.206.244.111/v1/";

    public String endpointAdminRegister(){
        return base_url + "auth/register";
    }

    public void requestDataRegisterAdmin(String name, String email ,String password, String adminStatus) throws IOException {
        token = FileUtils.readFileToString(new File(System.getProperty("user.dir") + "//src//test//resources//filejson//tokens.json"), StandardCharsets.UTF_8);
        File file = new File("src/test/resources/image/contoh.png");
        given().header("Content-Type", "multipart/form-data")
                .header("Authorization", "Bearer " + token)
                .multiPart("json", bodyRegister.dataForRegister(name, email, password, adminStatus).toJSONString())
                .multiPart("file",file,"png")
                .log().body();
        SerenityRest.when().post(endpointAdminRegister());
    }

    public void verifyStatusCode(int status_code){
        then().statusCode(equalTo(status_code));
    }

    public void validateDataDetail(String response){
        if (response.equals("success")){
            SerenityRest.then().body("message", equalTo("SUCCESS"));
        }
    }

    public void errorMessageRequired(String errorRequired){
        if(errorRequired.equals("required")){
            then().body("message", equalTo("COLUMN_NOT_FILLED"));
        }
    }

    public void errorMessageExistUser(String errorExist){
        if(errorExist.equals("exist")){
            then().body("message", equalTo("USER_ALREADY_EXISTS"));
        }
    }

    public void errorMessageInvalid(String errorInvalid){
        if(errorInvalid.equals("invalid name")){
            then().body("message", equalTo("CHARACTER_LESS_THAN_4"));
        }
        else if(errorInvalid.equals("invalid email")){
            then().body("message", equalTo("EMAIL_NOT_VALID"));
        }
        else if(errorInvalid.equals("invalid password")){
            then().body("message", equalTo("CHARACTER_LESS_THAN_8"));
        }else{
            then().body("message", equalTo("FORMAT_EMAIL_WRONG"));
        }
    }







}
