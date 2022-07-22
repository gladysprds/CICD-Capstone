package utils;

import java.util.Random;

public class General {
    Random rand = new Random();
    public String randomEmail(){
        return "forumGroup" + + rand.nextInt(1000) + "@gmail.com";
    }

    public String randomTopic(){return "testing" + + rand.nextInt(100);}

    public String randomName(){
        return  "gladyspardosi" + + rand.nextInt(1000);
    }

}
