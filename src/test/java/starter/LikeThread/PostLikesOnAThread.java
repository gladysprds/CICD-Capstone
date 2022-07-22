package starter.LikeThread;

import net.serenitybdd.rest.SerenityRest;
import org.apache.commons.io.FileUtils;
import org.json.simple.JSONObject;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.Matchers.equalTo;

public class PostLikesOnAThread {
    private String token;
    String base_url = "http://44.206.244.111/v1/";
    public String endpointLikeThread(){
        return base_url + "likethread/";
    }

     public void requestPostLikeThread(int id_thread) throws IOException {
         token = FileUtils.readFileToString(new File(System.getProperty("user.dir") + "//src//test//resources//filejson//tokens.json"), StandardCharsets.UTF_8);
         JSONObject requestData = new JSONObject();
         Map<String,Integer> thread_like = new HashMap<>();
         thread_like.put("id", id_thread);
         requestData.put("thread_like",thread_like);
         requestData.put("is_like",true);
         requestData.put("is_dislike",false);
         SerenityRest.given().header("Content-Type", "application/json")
                 .header("Authorization", "Bearer " + token)
                 .body(requestData.toJSONString());
         SerenityRest.when().post(endpointLikeThread());
     }

     public void validateDataDetailLikeThread(int id_thread){
         SerenityRest.then().body("message",equalTo("SUCCESS"));
         SerenityRest.then().body("data.thread_like.id",equalTo(id_thread));
     }

     public void errorMessage(){
         SerenityRest.then().body("message",equalTo("DATA_NOT_FOUND"));
     }
}
