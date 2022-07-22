package starter.Comments;

import net.serenitybdd.rest.SerenityRest;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import static org.hamcrest.Matchers.equalTo;

public class DeleteCommentOnAThread {

    private String token;

    String base_url = "http://44.206.244.111/v1/";

    public String endpointDeleteComment(int id_thread){
        return base_url + "comment/" + id_thread;
    }

    public void requestDeleteCommentById(int id_thread) throws IOException {
        token = FileUtils.readFileToString(new File(System.getProperty("user.dir") + "//src//test//resources//filejson//tokens.json"), StandardCharsets.UTF_8);
        SerenityRest.given().header("Authorization", "Bearer " + token);
        SerenityRest.when().delete(endpointDeleteComment(id_thread));
    }

    public void validateDataDetail(){
        SerenityRest.then().body("message", equalTo("SUCCESS"));
    }

    public void errorMessage(){
        SerenityRest.then().body("message", equalTo("DATA_NOT_FOUND"));
    }



}
