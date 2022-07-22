package starter.stepdefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import starter.LikeThread.GetAllLikesOnAthread;
import starter.LikeThread.GetDislikeThread;
import starter.LikeThread.PostLikesOnAThread;

import java.io.IOException;

public class likeThread {
    PostLikesOnAThread postLikesOnAThread = new PostLikesOnAThread();
    GetAllLikesOnAthread getAllLikesOnAthread = new GetAllLikesOnAthread();
    GetDislikeThread getDislikeThread = new GetDislikeThread();

    @Given("I set an endpoint for liking on a thread")
    public void iSetAnEndpointForLikingOnAThread() {
        postLikesOnAThread.endpointLikeThread();

    }

    @When("I request like on a thread by ID thread {int}")
    public void iRequestLikeOnAThreadByIDThreadId_thread(int id_thread) throws IOException {
        postLikesOnAThread.requestPostLikeThread(id_thread);
    }

    @And("I get the {string} result that i like the thread {int}")
    public void iGetTheResultThatILikeTheThreadId_thread(String result,int id_thread) {
        if(result.equals("data not found")){
            postLikesOnAThread.errorMessage();
        }else{
            postLikesOnAThread.validateDataDetailLikeThread(id_thread);
        }
    }

    //Get all like thread
    @Given("I set an endpoint count like on a thread")
    public void iSetAnEndpointCountLikeOnAThread() {
        getAllLikesOnAthread.endpointLikesThread();
    }

    @When("I request count like on a thread by ID thread {int}")
    public void iRequestCountLikeOnAThreadByIDThreadId_thread(int id_thread) throws IOException {
        getAllLikesOnAthread.requestGetAllLikes(id_thread);
    }

    @And("I get the {string} result that i get number of like on the thread")
    public void iGetTheResultThatIGetNumberOfLikeOnTheThread(String result) {
        if(result.equals("success")){
            getAllLikesOnAthread.validateDataDetailCountOnThread();
        }else {
            getAllLikesOnAthread.errorMessage();
        }
    }

    //Get All Dislike Thread
    @Given("I set an endpoint for dislike thread")
    public void iSetAnEndpointForDislikeThread() {
        getDislikeThread.endpointDistLikeLikesThread();
    }

    @When("I set an endpoint for dislike thread with ID Thread {int}")
    public void iSetAnEndpointForDislikeThreadWithIDThreadId_thread(int id_thread) throws IOException {
        getDislikeThread.requestGetDislike(id_thread);
    }

    @And("I get the {string} result that i get number of dislike on the thread")
    public void iGetTheResultThatIGetNumberOfDislikeOnTheThread(String result) {
        if(result.equals("success")){
            getDislikeThread.validateDataDetailCountDislikeOnThread();
        }else{
            getDislikeThread.errorMessage();
        }
    }



}
