package starter.ReportThread;

import net.serenitybdd.rest.SerenityRest;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import static org.hamcrest.Matchers.equalTo;

public class GetAllReport {
    private String token;
    String base_url = "http://44.206.244.111/v1/";
    public String endpointReportThread(){
        return base_url + "reportthread/";
    }

    public void requestGetAllThread() throws IOException {
        token = FileUtils.readFileToString(new File(System.getProperty("user.dir") + "//src//test//resources//filejson//tokens.json"), StandardCharsets.UTF_8);
        SerenityRest.given().header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + token);
        SerenityRest.when().get(endpointReportThread());
    }

    public void validateMessage(){
        SerenityRest.then().body("message",equalTo("SUCCESS"));
    }
}
