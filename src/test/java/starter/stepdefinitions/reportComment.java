package starter.stepdefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import starter.ReportComment.PostReportComment;

import java.io.IOException;

public class reportComment {
    PostReportComment postReportComment = new PostReportComment();
    @Given("I set an endpoint for report comment")
    public void iSetAnEndpointForReportComment() {
        postReportComment.endpointReportComment();
    }

    @When("I request POST for report with ID Comment {int}")
    public void iRequestPOSTForReportWithIDCommentId_comment(int id_comment) throws IOException {
        postReportComment.requestReportComment(id_comment);
    }

    @And("I get the report type with {string} and {string} after reporting with {int} ID Comment and user ID {int}")
    public void iGetTheReportTypeWithAndAfterReportingWithId_commentIDCommentAndUserIDUser_id(String result, String report_type, int id_comment, int user_id) {
       if(result.equals("success")){
           postReportComment.validateDataDetailAfterReport(report_type,id_comment,user_id);
       }else {
           postReportComment.errorMessage();
       }
    }
}
