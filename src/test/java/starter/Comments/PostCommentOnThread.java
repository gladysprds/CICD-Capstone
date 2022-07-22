package starter.Comments;

import net.serenitybdd.rest.SerenityRest;
import org.apache.commons.io.FileUtils;
import org.json.simple.JSONObject;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.Matchers.equalTo;

public class PostCommentOnThread {
    private String token;
    String base_url = "http://44.206.244.111/v1/";
    public String endpointForPostComment(){
        return base_url + "comment/";
    }

    public void requestCommentOnThread(int id_thread) throws IOException {
        token = FileUtils.readFileToString(new File(System.getProperty("user.dir") + "//src//test//resources//filejson//tokens.json"), StandardCharsets.UTF_8);
        JSONObject requestData = new JSONObject();
        Map<String,Integer> thread = new HashMap<>();
        thread.put("id", id_thread);
        requestData.put("thread",thread);
        requestData.put("comment","setuju banget");
        SerenityRest.given().header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + token)
                .body(requestData.toJSONString());
        SerenityRest.when().post(endpointForPostComment());
    }


    public void validateComment(){
        SerenityRest.then().body("message",equalTo("SUCCESS"));
    }

    public void validateErrorMessage(){
            SerenityRest.then().body("message",equalTo("DATA_NOT_FOUND"));
    }

}
