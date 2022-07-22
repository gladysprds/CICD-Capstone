package starter.stepdefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import starter.SaveThread.PostSaveThread;

import java.io.IOException;

public class saveThread {
    PostSaveThread postSaveThread = new PostSaveThread();
    @Given("I set an endpoint for save a thread")
    public void iSetAnEndpointForSaveAThread() {
        postSaveThread.endpointSaveThread();
    }

    @When("I request POST for saving thread with {int} ID thread")
    public void iRequestPOSTForSavingThreadWithId_threadIDThread(int id_thread) throws IOException {
            postSaveThread.requestSaveThread(id_thread);
    }



    @And("I get the {string} with {int} ID User and ID Thread {int} after saving thread")
    public void iGetTheWithId_userIDUserAndIDThreadId_threadAfterSavingThread(String result,int id_user,int id_thread) {
    if(result.equals("success")){
        postSaveThread.validateDataAfterSavingThread(id_user,id_thread);
    }else{
        postSaveThread.errorMessage(result);
    }
    }
}
