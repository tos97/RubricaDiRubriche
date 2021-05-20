package Models;

import java.util.ArrayList;

public class MapModel {
    private Role ruolo;
    private ArrayList<Account> rubrica;

    public MapModel() { }

    public MapModel(Role ruolo, ArrayList<Account> rubrica) {
        this.ruolo = ruolo;
        this.rubrica = rubrica;
    }

    public Role getRuolo() {
        return ruolo;
    }

    public void setRuolo(Role ruolo) {
        this.ruolo = ruolo;
    }

    public ArrayList<Account> getRubrica() {
        return rubrica;
    }

    public void setRubrica(ArrayList<Account> rubrica) {
        this.rubrica = rubrica;
    }
}
