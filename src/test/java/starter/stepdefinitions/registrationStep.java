package starter.stepdefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import net.thucydides.core.annotations.Steps;
import starter.Autentikasi.RegistrasiAdmin;

import java.io.IOException;

public class registrationStep {

    @Steps
    RegistrasiAdmin registrasiAdmin;

    @Given("I set an endpoint for new user")
    public void iSetAnEndpointForNewUser() {
        registrasiAdmin.endpointAdminRegister();
    }

    @When("I request POST user with {string} and {string} and {string} and this user is {string}")
    public void iRequestPOSTUserWithAndAndAndThisUserIs(String name, String email, String password, String adminStatus) throws IOException {
        registrasiAdmin.requestDataRegisterAdmin(name,email,password,adminStatus);
    }

    @Then("I validate the status code {int}")
    public void iValidateTheStatusCodeStatus_code(int status_code) {
        registrasiAdmin.verifyStatusCode(status_code);
    }

    @And("I validate the {string} detail after register")
    public void iValidateTheDetailAfterRegister(String message) {
        if (message.equals("success")){
            registrasiAdmin.validateDataDetail(message);
        }else if(message.equals("exist")){
            registrasiAdmin.errorMessageExistUser(message);
        }else if(message.equals("required")){
            registrasiAdmin.errorMessageRequired(message);
        }else{
            registrasiAdmin.errorMessageInvalid(message);
        }
    }


}
