package starter.Thread;

import org.apache.commons.io.FileUtils;
import org.json.simple.JSONObject;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class BodyThread {
    private String token;
    public JSONObject dataForThread() throws IOException {
        token = FileUtils.readFileToString(new File(System.getProperty("user.dir") + "//src//test//resources//filejson//tokens.json"), StandardCharsets.UTF_8);
        JSONObject requestData = new JSONObject();
        Map<String,Integer> topic = new HashMap<>();
        topic.put("id", 1);
        requestData.put("topic", topic);
        requestData.put("title","Krisis Ekonomi");
        requestData.put("content","Krisis ekonomi adalah salah satu hal yang paling ditakuti oleh negara di seluruh dunia. Bagaimana tidak, jika hal tersebut terjadi, kerugian akan menimpa pemerintah dan masyarakat sekaligus.");

        return requestData;
    }


    public JSONObject dataForInvalidThread() throws IOException {
        token = FileUtils.readFileToString(new File(System.getProperty("user.dir") + "//src//test//resources//filejson//tokens.json"), StandardCharsets.UTF_8);
        JSONObject requestData = new JSONObject();
        Map<String,String> topic = new HashMap<>();
        topic.put("id", "thread1");
        requestData.put("topic", topic);
        requestData.put("title","Krisis Ekonomi");
        requestData.put("content","Krisis ekonomi adalah salah satu hal yang paling ditakuti oleh negara di seluruh dunia. Bagaimana tidak, jika hal tersebut terjadi, kerugian akan menimpa pemerintah dan masyarakat sekaligus.");

        return requestData;
    }


    public JSONObject dataForNullThread() throws IOException {
        token = FileUtils.readFileToString(new File(System.getProperty("user.dir") + "//src//test//resources//filejson//tokens.json"), StandardCharsets.UTF_8);
        JSONObject requestData = new JSONObject();
        Map<String,String> topic = new HashMap<>();
        topic.put("id", null);
        requestData.put("topic", topic);
        requestData.put("title","Krisis Ekonomi");
        requestData.put("content","Krisis ekonomi adalah salah satu hal yang paling ditakuti oleh negara di seluruh dunia. Bagaimana tidak, jika hal tersebut terjadi, kerugian akan menimpa pemerintah dan masyarakat sekaligus.");

        return requestData;
    }


    public JSONObject dataForUnavailableTopic() throws IOException {
        token = FileUtils.readFileToString(new File(System.getProperty("user.dir") + "//src//test//resources//filejson//tokens.json"), StandardCharsets.UTF_8);
        JSONObject requestData = new JSONObject();
        Map<String,Integer> topic = new HashMap<>();
        topic.put("id", 100);
        requestData.put("topic", topic);
        requestData.put("title","Krisis Ekonomi");
        requestData.put("content","Krisis ekonomi adalah salah satu hal yang paling ditakuti oleh negara di seluruh dunia. Bagaimana tidak, jika hal tersebut terjadi, kerugian akan menimpa pemerintah dan masyarakat sekaligus.");

        return requestData;
    }

    public JSONObject dataForInvalidToken(){
       JSONObject requestData = new JSONObject();
        Map<String,Integer> topic = new HashMap<>();
        topic.put("id", 10);
        requestData.put("topic", topic);
        requestData.put("title","Krisis Ekonomi");
        requestData.put("content","Krisis ekonomi adalah salah satu hal yang paling ditakuti oleh negara di seluruh dunia. Bagaimana tidak, jika hal tersebut terjadi, kerugian akan menimpa pemerintah dan masyarakat sekaligus.");

        return requestData;
    }


}
