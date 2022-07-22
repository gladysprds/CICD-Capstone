package starter.ReportThread;

import net.serenitybdd.rest.SerenityRest;
import org.apache.commons.io.FileUtils;
import org.json.simple.JSONObject;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.Matchers.equalTo;

public class PostReportThread {
    private String token;
    String base_url = "http://44.206.244.111/v1/";
    public String endpointReportThread(){
        return base_url + "reportthread/";
    }

    public void requestReportThread(int id_thread) throws IOException {
        this.token = FileUtils.readFileToString(new File(System.getProperty("user.dir") + "//src//test//resources//filejson//tokensForReporting.json"), StandardCharsets.UTF_8);
        JSONObject requestData = new JSONObject();
        Map<String,Integer> thread = new HashMap<>();
        thread.put("id", id_thread);
        requestData.put("thread",thread);
        requestData.put("report_type","This_thread_contains_inappropriate_and_Fraud_elements");
        SerenityRest.given().header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + this.token)
                .body(requestData.toJSONString());
        SerenityRest.when().post(endpointReportThread());
    }

    public void validateDataDetailAfterReport(String report_type, int id_thread,int user_id){
        SerenityRest.then().body("message",equalTo("SUCCESS"));
        SerenityRest.then().body("data.user.id",equalTo(id_thread));
        SerenityRest.then().body("data.thread.users.id",equalTo(user_id));
        SerenityRest.then().body("data.report_type",equalTo(report_type));
    }

    public void errorMessage(){
        SerenityRest.then().body("message",equalTo("DATA_NOT_FOUND"));
    }


}
