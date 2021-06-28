package cucumber.steps;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import cucumber.models.User;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class UserSteps {
    private User user = new User();

    @Given("^that this (.*) is given to clear (.*) certification exam$")
    public void certificationName(String name,String certification){
        user.setName(name);
        user.setCertification(certification);
    }

    @When("^(.*) got (\\d+) marks in exam$")
    public void gotMarks(String name, int marks){
        user.setName(name);
        user.setMarks(marks);
    }

    @Then("^(.*) is know as (.*) certified$")
    public void certifiedYes(String name, String certification){
        assertThat(name,is(user.getName()));
        assertThat(user.getCertification(),equalTo(certification));
        assertThat(user.getResult(),is(true));
    }

    @Then("^(.*) is not as (.*) certified$")
    public void certifiedNo(String name, String certification){
        assertThat(name,is(user.getName()));
        assertThat(user.getCertification(),equalTo(certification));
        assertThat(user.getResult(),is(false));
    }
}
