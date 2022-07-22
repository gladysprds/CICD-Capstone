package starter.stepdefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import starter.Autentikasi.RegistrasiAdmin;
import starter.Thread.CreateThread;
import starter.Thread.GetAllThread;
import starter.Thread.GetThread;

import java.io.IOException;


public class threadStep {
    CreateThread createThread = new CreateThread();
    GetThread getThread = new GetThread();
    GetAllThread getAllThread = new GetAllThread();

    @Given("I set an endpoint for make a thread")
    public void iSetAnEndpointForMakeAThread() {
        createThread.endpointPostThread();
    }



    @When("I request POST thread with or without {string}")
    public void iRequestPOSTThreadWithOrWithout(String image) throws IOException {
        if(image.equals("null image")){
            createThread.requestForThreadWithoutImage();
        }else{
            createThread.requestForThread();
        }

    }

    @And("I get the data detail")
    public void iGetTheDataDetail() {
        createThread.getDetailThread();
    }

    @When("I request POST thread with {string}")
    public void iRequestPOSTThreadWithAnd(String data) throws Exception {
        if (data.equals("invalid")){
            createThread.requestPostThreadInvalidData();
        }else if(data.equals("null")){
            createThread.requestPostNullThread();
        }else if(data.equals("invalid")){
            createThread.requestPostUnavailable();
        }else {
            createThread.requestThreadWithInvalidToken();
        }
    }


    @And("I get thread {string}")
    public void iGetThread(String errormessage) {
        createThread.findErrorMessage(errormessage);
    }

    @Given("I set an endpoint for get thread with {string}")
    public void iSetAnEndpointForGetThreadWith(String thread_id) {
        getThread.endpointGetThreadById(thread_id);
    }


    @Then("I validate the {int} for thread")
    public void iValidateTheForThread(int status_code) {
        getThread.validateStatusCode(status_code);
    }

    @And("I get the {string} for GET thread")
    public void iGetTheForGETThread(String detail_thread) {
       if(detail_thread.equals("success")){
           getThread.validateDataDetail();
       }else{
           getThread.errorMessageThread(detail_thread);
       }

    }

    @When("I request GET thread with {string} and {string}")
    public void iRequestGETThreadWithAnd(String thread_id, String token) throws IOException {
        if(token.equals("valid")){
            getThread.requestGetThreadById(thread_id,token);
        }else {
            getThread.requestGetThreadByIdInvalidToken(thread_id);
        }
    }

    @Given("I set an endpoint for get all thread")
    public void iSetAnEndpointForGetAllThread() {
        getAllThread.endpointGetAllThread();
    }

    @When("I request GET all thread with {string}")
    public void iRequestGETAllThreadWithToken(String token) throws IOException {
        if(token.equals("valid")){
            getAllThread.requestGetAllThread(token);
        }else {
            getAllThread.requestGetAllThreadInvalidToken();
        }
    }

    @And("I get the {string} of get all thread")
    public void iGetTheOfGetAllThread(String result) {
        if(result.equals("success")){
            getAllThread.validateDataDetail();
        }else{
            getAllThread.errorMessageThread();
        }

    }



}
