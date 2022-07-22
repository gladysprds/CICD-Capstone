package starter.Thread;

import net.serenitybdd.rest.SerenityRest;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import static net.serenitybdd.rest.SerenityRest.then;
import static org.hamcrest.Matchers.equalTo;

public class GetThread {
    private String token;

    String base_url = "http://44.206.244.111/v1/";

    public String endpointGetThreadById(String thread_id){
        return base_url + "thread/" + thread_id;
    }

    public void requestGetThreadById(String user_id,String token) throws IOException {
        token = FileUtils.readFileToString(new File(System.getProperty("user.dir") + "//src//test//resources//filejson//tokens.json"), StandardCharsets.UTF_8);
        SerenityRest.given().header("Authorization", "Bearer " + token);
        SerenityRest.when().get(endpointGetThreadById(user_id));

    }

    public void requestGetThreadByIdInvalidToken(String user_id) throws IOException {
        token = FileUtils.readFileToString(new File(System.getProperty("user.dir") + "//src//test//resources//filejson//InvalidToken.json"), StandardCharsets.UTF_8);
        SerenityRest.given().header("Authorization", "Bearer " + this.token);
        SerenityRest.when().get(endpointGetThreadById(user_id));

    }
    public void validateStatusCode(int status_code){
    then().statusCode(equalTo(status_code));
    }

    public void validateDataDetail(){
        SerenityRest.then().body("data.id", equalTo(27));
    }

    public void errorMessageThread(String detail_thread){
        if (detail_thread.equals("bad_request")){
            SerenityRest.then().body("error",equalTo("Bad Request"));
        } else if(detail_thread.equals("data_not_found")){
            SerenityRest.then().body("message",equalTo("DATA_NOT_FOUND"));
        }
    }

}
