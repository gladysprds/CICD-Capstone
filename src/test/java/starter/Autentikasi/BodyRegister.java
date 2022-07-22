package starter.Autentikasi;

import org.apache.commons.io.FileUtils;
import org.json.simple.JSONObject;
import utils.General;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;


public class BodyRegister {

    private String token;
    String email;
    String name;
    String password;
    General general = new General();
    public JSONObject dataForRegister(String name, String email ,String password, String adminStatus) throws IOException {
        token = FileUtils.readFileToString(new File(System.getProperty("user.dir") + "//src//test//resources//filejson//tokens.json"), StandardCharsets.UTF_8);

        Boolean.parseBoolean("true");

        if (name.equals("same")){
            this.name = "aduhaihsan";
        }else{
            this.name = name;
        }

        if (email.equals("same")){
            this.email = "real_akun@gmail.com";
        } else if(email.equals("adminfound@gmail.com")){
            this.email = general.randomEmail();
            try (FileWriter file = new FileWriter("src//test//resources//filejson//email.json")) {
                file.write(this.email);
                file.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else if(email.equals("adminfound")){
            this.email = email;
        }else{
            this.email = email;
        }

        if (password.equals("same")) {
            this.password = "aduhaIhsan1511";
        }else {
            this.password=password;
        }

        JSONObject requestData = new JSONObject();
        requestData.put("name", this.name);
        requestData.put("email", this.email);
        requestData.put("password", this.password);
        requestData.put("adminStatus",adminStatus);

        return requestData;
    }
}
