package cucumber.models;

import Models.Account;
import Models.User;

import java.util.ArrayList;

public class CrudRubrica {
    private Account pippo;
    private Account pippo2;
    private Account Ben;
    private Account Gio;
    private ArrayList<Account> rubrica;
    private String scelta;
    private Account account;

    public CrudRubrica() {
        Ben = new Account(new Models.User("Benedetto", "tosiani", "23"), "3312341300", "benedetto.tosiani@edu.unife.it");
        Gio = new Account(new Models.User("Giorgio", "Vanni", "57"), "3339196342", "");
        pippo = new Account(new Models.User("Pippo", "Vario", "64"), "3339196342", "");
        pippo2 = new Account(new User("Pippo2", "Vario2", "64"), "3339196342", "");
    }

    public void setRubricaZero() {
        rubrica = new ArrayList<>();
    }

    public void setRubrica(ArrayList<Account> rubrica) {
        this.rubrica.addAll(rubrica);
    }

    public Account getAccount(){
        return account;
    }

    public final String getScelta() {
        return scelta;
    }

    public ArrayList<Account> getRubrica() {
        return rubrica;
    }

    public void sceltaAccount(String name){
        scelta = name;
        if (name.equals("Ben"))
            account = Ben;
        else if (name.equals("pippo"))
            account = pippo;
        else if (name.equals("pippo2"))
            account = pippo2;
        else{
            account = Gio;
        }
    }
}
