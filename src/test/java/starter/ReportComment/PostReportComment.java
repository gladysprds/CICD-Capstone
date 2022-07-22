package starter.ReportComment;

import net.serenitybdd.rest.SerenityRest;
import org.apache.commons.io.FileUtils;
import org.json.simple.JSONObject;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.Matchers.equalTo;

public class PostReportComment {
    private String token;
    String base_url = "http://44.206.244.111/v1/";

    public String endpointReportComment(){
        return base_url + "reportcomment/";
    }

    public void requestReportComment(int id_comment) throws IOException {
        this.token = FileUtils.readFileToString(new File(System.getProperty("user.dir") + "//src//test//resources//filejson//tokensForReporting.json"), StandardCharsets.UTF_8);
        JSONObject requestData = new JSONObject();
        Map<String,Integer> comment = new HashMap<>();
        comment.put("id", id_comment);
        requestData.put("comment",comment);
        requestData.put("report_type","This_thread_contains_inappropriate_and_Fraud_elements");
        SerenityRest.given().header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + this.token)
                .body(requestData.toJSONString());
        SerenityRest.when().post(endpointReportComment());
    }
    public void validateDataDetailAfterReport(String report_type, int id_thread,int user_id){
        SerenityRest.then().body("message",equalTo("SUCCESS"));
        SerenityRest.then().body("data.comment.id",equalTo(id_thread));
        SerenityRest.then().body("data.user.id",equalTo(user_id));
        SerenityRest.then().body("data.report_type",equalTo(report_type));
    }
    public void errorMessage(){
        SerenityRest.then().body("message",equalTo("DATA_NOT_FOUND"));
    }







}
