package starter.stepdefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import starter.Following.CreateFollowing;
import starter.Following.GetAllFollowers;
import starter.Following.GetFollowingOnAccount;

import java.io.IOException;

public class followingSteps {
    CreateFollowing createFollowing = new CreateFollowing();
    GetAllFollowers getAllFollowers = new GetAllFollowers();
    GetFollowingOnAccount getFollowingOnAccount = new GetFollowingOnAccount();
    @Given("I set an endpoint for following")
    public void iSetAnEndpointForFollowing() {
        createFollowing.endpointPostFollowing();
    }


    @When("I request POST for following with {string} and follow {int}")
    public void iRequestPOSTForFollowingWithAndFollowUser_follow(String token, int user_follow) throws IOException {
        if(token.equals("valid")){
        createFollowing.requestPostFollowing(user_follow);
        }else{
        createFollowing.requestPostFollowingInvalidToken(user_follow);
        }
    }

    @And("I get the {string} with {int} and {int} after following")
    public void iGetTheId_userAndUser_followAfterFollowing(String result, int id_user, int user_follow) {
        if(result.equals("success")){
            createFollowing.validateDataDetail(id_user,user_follow);
        }else if(result.equals("two times following")){
            createFollowing.validateDataDetail(id_user,user_follow);
        }else{
            createFollowing.errorMessage(result);
        }

    }


    @Given("I set an endpoint for GET following with {int}")
    public void iSetAnEndpointForGETFollowingWithId_following(int id_user) {
        getAllFollowers.endpointGetFollowers(id_user);
    }

    @When("I request GET for following with {int} and {string} token")
    public void iRequestGETForFollowingWithId_followingAndToken(int user_follow,String token) throws IOException {
        if(token.equals("valid")){
            getAllFollowers.requestGetFollowers(user_follow);
        }else{
            getAllFollowers.requestGetFollowersInvalidToken(user_follow);
        }
    }

    @And("I get the {string} with id_user {int} and id for following user {int}")
    public void iGetTheWithId_userId_userAndIdForFollowingUserUser_follow(String result,int id_user,int user_follow) {
        if(result.equals("success")){
            getAllFollowers.validateGetDetail(id_user,user_follow);
        }else{
            getAllFollowers.errorMessage(result);
        }
    }

    @Given("I set an endpoint for GET following for an account")
    public void iSetAnEndpointForGETFollowingForAnAccount() {
            getFollowingOnAccount.endpointGetFollowersOnAccount();
    }

    @When("I request GET following on an account with token {string}")
    public void iRequestGETFollowingOnAnAccountWithToken(String token) throws IOException {
        if(token.equals("token with following user")) {
            getFollowingOnAccount.requestGetFollowersOnAccount();
        }else if(token.equals("token without following user")){
            getFollowingOnAccount.requestGetFollowersOnAccountWithNoFollowers();
        }
    }

    @And("I get the {string} with id user {int} and id user followed {int}")
    public void iGetTheWithIdUserId_userAndIdUserFollowedUser_follow(String result,int id_user,int user_follow) {
        if (result.equals("success")){
            getFollowingOnAccount.validateGetDetail(id_user,user_follow);
        }else if(result.equals("data not found")){
            getFollowingOnAccount.errorMessage(result);
        }


    }


}
