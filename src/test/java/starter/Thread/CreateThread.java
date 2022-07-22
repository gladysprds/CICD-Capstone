package starter.Thread;


import net.serenitybdd.rest.SerenityRest;
import org.apache.commons.io.FileUtils;
import org.json.simple.JSONObject;


import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;


import static net.serenitybdd.rest.RestRequests.given;
import static org.hamcrest.Matchers.equalTo;

public class CreateThread {
    private String token;

    BodyThread bodyThread = new BodyThread();
    String base_url = "http://44.206.244.111/v1/";

    public String endpointPostThread(){
        return base_url + "thread/";
    }


    public void requestForThread() throws IOException {
        token = FileUtils.readFileToString(new File(System.getProperty("user.dir") + "//src//test//resources//filejson//tokens.json"), StandardCharsets.UTF_8);
        File file = new File("src/test/resources/image/contoh.png");
        given().header("Content-Type", "multipart/form-data")
                .header("Authorization", "Bearer " + token)
                .multiPart("json", bodyThread.dataForThread().toJSONString())
                .multiPart("file", file,"png")
                                        .log().body();
        SerenityRest.when().post(endpointPostThread());
    }


    public void requestForThreadWithoutImage() throws IOException {
        token = FileUtils.readFileToString(new File(System.getProperty("user.dir") + "//src//test//resources//filejson//tokens.json"), StandardCharsets.UTF_8);
        File file = new File("src/test/resources/image/contoh.png");
        given().header("Content-Type", "multipart/form-data")
                .header("Authorization", "Bearer " + token)
                .multiPart("json", bodyThread.dataForThread().toJSONString())
                .log().body();
        SerenityRest.when().post(endpointPostThread());
    }



    public void getDetailThread(){
        SerenityRest.then().body("data.content", equalTo("Krisis ekonomi adalah salah satu hal yang paling ditakuti oleh negara di seluruh dunia. Bagaimana tidak, jika hal tersebut terjadi, kerugian akan menimpa pemerintah dan masyarakat sekaligus."));
    }

    public void requestPostThreadInvalidData() throws Exception {
        token = FileUtils.readFileToString(new File(System.getProperty("user.dir") + "//src//test//resources//filejson//tokens.json"), StandardCharsets.UTF_8);
        File file = new File("src/test/resources/image/contoh.png");
        given().header("Content-Type", "multipart/form-data")
                .header("Authorization", "Bearer " + token)
                .multiPart("json", bodyThread.dataForInvalidThread().toJSONString())
                .multiPart("file", file,"png")
                .log().body();
        SerenityRest.when().post(endpointPostThread());
    }

    public void findErrorMessage(String data){
        if (data.equals("invalid")){
            SerenityRest.then().body("error",equalTo("Internal Server Error"));
        } else if(data.equals("null")){
            SerenityRest.then().body("message",equalTo("DATA_NOT_FOUND"));
        }
    }

    public void requestPostNullThread() throws Exception {
        token = FileUtils.readFileToString(new File(System.getProperty("user.dir") + "//src//test//resources//filejson//tokens.json"), StandardCharsets.UTF_8);
        File file = new File("src/test/resources/image/contoh.png");
        given().header("Content-Type", "multipart/form-data")
                .header("Authorization", "Bearer " + token)
                .multiPart("json", bodyThread.dataForNullThread().toJSONString())
                .multiPart("file", file,"png")
                .log().body();
        SerenityRest.when().post(endpointPostThread());
    }

    public void requestPostUnavailable() throws Exception {
        token = FileUtils.readFileToString(new File(System.getProperty("user.dir") + "//src//test//resources//filejson//tokens.json"), StandardCharsets.UTF_8);
        File file = new File("src/test/resources/image/contoh.png");
        given().header("Content-Type", "multipart/form-data")
                .header("Authorization", "Bearer " + token)
                .multiPart("json", bodyThread.dataForUnavailableTopic().toJSONString())
                .multiPart("file", file,"png")
                .log().body();
        SerenityRest.when().post(endpointPostThread());
    }




    public void requestThreadWithInvalidToken() throws Exception {
        this.token = FileUtils.readFileToString(new File(System.getProperty("user.dir") + "//src//test//resources//filejson//InvalidToken.json"), StandardCharsets.UTF_8);
        File file = new File("src/test/resources/image/contoh.png");
        given().header("Content-Type", "multipart/form-data")
                .header("Authorization", "Bearer " + token)
                .multiPart("json", bodyThread.dataForInvalidToken().toJSONString())
                .multiPart("file", file,"png")
                .log().body();
        SerenityRest.when().post(endpointPostThread());
    }



}
