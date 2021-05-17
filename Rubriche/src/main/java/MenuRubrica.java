import Models.Account;

import java.util.ArrayList;

public class MenuRubrica {

    ArrayList<Account> rubrica;

    public MenuRubrica(ArrayList<Account> rubrica) {
        this.rubrica = rubrica;
    }

    public MenuRubrica() {
    }

    public void printArray(ArrayList<Account> acc) {
        for(Account persona: acc){
            System.out.println("ID: " + persona.getUser().getId());
            System.out.println("Nome: " + persona.getUser().getNome());
            System.out.println("Cognome: " + persona.getUser().getSurname());
            System.out.println("Età: " + persona.getUser().getAge());
            System.out.println("Telefono: " + persona.getNumero());
            System.out.println("Email: " + persona.getEmail());
            System.out.println();
        }
    }
}
