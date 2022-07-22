package starter.SaveThread;

import net.serenitybdd.rest.SerenityRest;
import org.apache.commons.io.FileUtils;
import org.json.simple.JSONObject;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.Matchers.equalTo;

public class PostSaveThread {
    private String token;
    String base_url = "http://44.206.244.111/v1/";
    public String endpointSaveThread(){
        return base_url + "savethread";
    }

    public void requestSaveThread(int id_thread) throws IOException {
        this.token = FileUtils.readFileToString(new File(System.getProperty("user.dir") + "//src//test//resources//filejson//tokensForReporting.json"), StandardCharsets.UTF_8);
        JSONObject requestData = new JSONObject();
        Map<String,Integer> threads = new HashMap<>();
        threads.put("id", id_thread);
        requestData.put("threads",threads);
        SerenityRest.given().header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + this.token)
                .body(requestData.toJSONString());
        SerenityRest.when().post(endpointSaveThread());
    }

    public void validateDataAfterSavingThread(int user_id,int id_thread){
        SerenityRest.then().body("message",equalTo("SUCCESS"));
        SerenityRest.then().body("data.user.id",equalTo(user_id));
        SerenityRest.then().body("data.threads.id",equalTo(id_thread));
    }

    public void errorMessage(String errorMessage){
        if(errorMessage.equals("YOU_HAVE_BEEN_SAVE_THIS_THREAD")){
        SerenityRest.then().body("message",equalTo("YOU_HAVE_BEEN_SAVE_THIS_THREAD"));
        }else{
            SerenityRest.then().body("message",equalTo("DATA_NOT_FOUND"));
        }
    }



}
