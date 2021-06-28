package cucumber.steps;

import Rubricae.MenuRubrica;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import cucumber.models.CrudRubrica;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class CrudSteps {
    private CrudRubrica crudRubrica = new CrudRubrica();
    private MenuRubrica menu = new MenuRubrica();

    @Given("Creation of a new address book")
    public void certificationName(){
        crudRubrica.setRubricaZero();
    }

    @When("^choose which user you want to use between Ben,Gio,Pippo,Pippo2: (.*)$")
    public void gotMarks(String name){
        crudRubrica.sceltaAccount(name);
        crudRubrica.setRubrica(menu.addAccount(crudRubrica.getAccount()));
    }

    @Then("added to the list")
    public void certifiedYes(){
        assertThat(crudRubrica.getRubrica().get(0).getUser().getNome().contains(crudRubrica.getScelta()),is(true));
    }
}
