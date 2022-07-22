package starter.stepdefinitions;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import starter.Comments.DeleteCommentOnAThread;
import starter.Comments.GetCommentOnAThread;
import starter.Comments.PostCommentOnThread;

import java.io.IOException;

public class commentStep {
    PostCommentOnThread postCommentOnThread = new PostCommentOnThread();
    GetCommentOnAThread getCommentOnAThread = new GetCommentOnAThread();
    DeleteCommentOnAThread deleteCommentOnAThread = new DeleteCommentOnAThread();
    @Given("I set an endpoint for make a thread on a comment")
    public void iSetAnEndpointForMakeAThreadOnAComment() {
        postCommentOnThread.endpointForPostComment();

    }

    @When("I request comment on a thread by ID thread {int}")
    public void iRequestCommentOnAThreadByIDThreadId_thread(int id_thread) throws IOException {
        postCommentOnThread.requestCommentOnThread(id_thread);
    }


    @And("I get the {string} comment")
    public void iGetTheComment(String result) {
        if(result.equals("success")){
            postCommentOnThread.validateComment();
        }else{
            postCommentOnThread.validateErrorMessage();
        }

    }


    @Given("I set an endpoint for GET comment by ID thread {int}")
    public void iSetAnEndpointForGETCommentByIDThreadId_thread(int id_thread) {
            getCommentOnAThread.endpointGetComment(id_thread);
    }

    @When("I request GET comment by ID thread {int}")
    public void iRequestGETCommentByIDThreadId_thread(int id_thread) throws IOException {
        getCommentOnAThread.requestGetComment(id_thread);
    }

    @And("I get the {string} based on id thread {int}")
    public void iGetTheBasedOnIdThreadId_thread(String result, int id_thread) {
        if(result.equals("success")){
            getCommentOnAThread.validateDataDetail(id_thread);
        }else {
            getCommentOnAThread.errorMessage();
        }
    }








    @Given("I set an endpoint for delete comments by ID Comment {int}")
    public void iSetAnEndpointForDeleteCommentsByIDCommentId_comment(int id_comment) {
        deleteCommentOnAThread.endpointDeleteComment(id_comment);
    }

    @When("I request DELETE Comment by ID Comment {int}")
    public void iRequestDELETECommentByIDCommentId_comment(int id_comment) throws IOException {
        deleteCommentOnAThread.requestDeleteCommentById(id_comment);
    }

    @And("I get the result for comment")
    public void iGetTheResultForComment(){
        deleteCommentOnAThread.validateDataDetail();
    }

    @And("I get the {string} for comment")
    public void iGetTheForComment(String result) {
        if(result.equals("success")){
            deleteCommentOnAThread.validateDataDetail();
        }else{
            deleteCommentOnAThread.errorMessage();
        }
    }


}
