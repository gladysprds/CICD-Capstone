package starter.stepdefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import starter.Users.GetAllUsers;
import starter.Users.GetUsersById;
import starter.Users.UpdateUsers;

import java.io.IOException;

public class userStep {
    GetAllUsers getAllUsers = new GetAllUsers();
    GetUsersById getUsersById = new GetUsersById();
    UpdateUsers updateUsers = new UpdateUsers();

    @Given("I set an endpoint for get all user")
    public void iSetAnEndpointForGetAllUser() {
        getAllUsers.endpointGetAllUser();
    }

    @When("I request GET all user")
    public void iRequestGETAllUser() throws IOException {
        getAllUsers.requestGetAllUsers();
    }

    @And("I get a success message")
    public void iGetASuccessMessage() {
        getAllUsers.validateMessage();

    }

    @When("I request GET all user without Token")
    public void iRequestGETAllUserWithoutToken() {
        getAllUsers.requestGetAllUserNoToken();
    }

    @And("I get error message")
    public void iGetErrorMessage() {
        getAllUsers.validateErrorMessage();
    }



    @Given("I set an endpoint for get detail user with {string}")
    public void iSetAnEndpointForGetDetailUserWith(String user_id) {
        getUsersById.endpointGetUserById(user_id);
    }

    @When("I request GET Detail User with {string}")
    public void iRequestGETDetailUserWithId_request(String user_id) throws IOException {
        getUsersById.requestGetUserById(user_id);
    }


    @And("I get the {string}")
    public void iGetThe(String result) {
        if(result.equals("authorized")){
            getUsersById.validateDataDetail();
        }else{
        getUsersById.validateErrorMessage(result);
        }

    }


    @Given("I set an endpoint for get detail a user")
    public void iSetAnEndpointForGetDetailAUser() {
        getUsersById.endpointGetUserById("1");
    }

    @When("I request GET Detail user")
    public void iRequestGETDetailUser(){
        getUsersById.requestGetUserByIdwWithNoToken("1");
    }


    @When("I request POST detail user")
    public void iRequestPOSTDetailUser() throws IOException {
        getUsersById.requestUserWithPostMethod("1");
    }

    @And("I get the method not allowed error")
    public void iGetTheMethodNotAllowedError() {
        getUsersById.validateMethodNotAllowed();
    }

    @Given("I set an endpoint for update user with {string}")
    public void iSetAnEndpointForUpdateUserWith(String id_user) {
        updateUsers.endpointUpdateUser(id_user);

    }

    @When("I request UPDATE user with {string} and {string} and {string}")
    public void iRequestUPDATEUserWithAndAnd(String data, String id_user, String token) throws IOException {
        if(token.equals("valid")){
            updateUsers.requestUpdateUser(id_user,data);
        }else{
            updateUsers.requestUpdateUserInvalidToken(id_user,data);
        }

    }

    @And("I get the {string} update user same with {string}")
    public void iGetTheUpdateUserSameWith(String result,String data) {
        if(result.equals("success")){
            updateUsers.validateDataDetail(data);
        }else{
            updateUsers.errorMessage();
        }
    }


}
