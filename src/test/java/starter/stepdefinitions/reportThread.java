package starter.stepdefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import starter.ReportThread.GetAllReport;
import starter.ReportThread.PostReportThread;

import java.io.IOException;

public class reportThread {
    PostReportThread postReportThread = new PostReportThread();
    GetAllReport getAllReport = new GetAllReport();
    @Given("I set an endpoint for report")
    public void iSetAnEndpointForReport() {
        postReportThread.endpointReportThread();
    }

    @When("I request POST for report with {int}")
    public void iRequestPOSTForReportWithId_thread(int id_thread) throws IOException {
        postReportThread.requestReportThread(id_thread);
    }

    @And("I get the report type with {string} and {string} after reporting with {int} ID Thread and user ID {int}")
    public void iGetTheReportTypeWithAndAfterReportingWithId_threadIDThreadAndUserIDUser_id(String result, String report_type, int user_id,int id_thread) {
        if(result.equals("success")) {
            postReportThread.validateDataDetailAfterReport(report_type,user_id,id_thread);
        }else{
            postReportThread.errorMessage();
        }
    }

    @Given("I set an endpoint for GET all report")
    public void iSetAnEndpointForGETAllReport() {
        getAllReport.endpointReportThread();
    }

    @When("I request GET for showing list report")
    public void iRequestGETForShowingListReport() throws IOException {
        getAllReport.requestGetAllThread();
    }

    @And("I get the message")
    public void iGetTheMessage() {
        getAllReport.validateMessage();
    }
}
