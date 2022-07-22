package starter.stepdefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import starter.Autentikasi.Login;


public class loginSteps {
    Login login = new Login();

    @Given("I set an endpoint for logged into website")
    public void iSetAnEndpointForLoggedIntoWebsite() {
        login.endpointLogin();
    }

    @When("I request POST user with {string} and {string}")
    public void iRequestPOSTUserWithAnd(String email, String password) {
        login.requestLogin(email,password);
    }



    @And("I validate the {string} detail after login")
    public void iValidateTheDetailAfterLogin(String result) {
        if(result.equals("success")){
            login.validateDetailMessage();
            login.getToken();
        }else if(result.equals("email required")){
            login.errorMessageNullEmail();
        }else if(result.equals("password required")){
            login.errorMessageNullPass();
        }else{
            login.errorMessageInvalidData();
        }

    }




}
