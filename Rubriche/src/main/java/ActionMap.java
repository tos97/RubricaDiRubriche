import Models.Account;
import Models.Role;
import Models.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ActionMap {
    Map<Role,ArrayList> mapp = new HashMap();
    Role ruolo = new Role();
    //MenuRubrica menu = new MenuRubrica();

    public ActionMap() {
        ArrayList<Account> rubrica =  new ArrayList<Account>();
        rubrica.add(new Account(new User("Bendetto", "tosiani", "23"), "3312341300", "benedetto.tosiani@edu.unife.it"));
        rubrica.add(new Account(new User("Giorgio", "Vanni", "57"), "3339196342", ""));
        mapp.put(new Role("Admin", "Rubrica dell'amministratore, quella principale precaricatata"), rubrica);
        mapp.put(new Role("Other", "Rubrica prova per test, precaricatata uguale alla prima"), rubrica);
    }

    public void opzioni(){
        System.out.println();
        System.out.println("Seleziona l'opzione desiderata:");
        System.out.println(" 0) Uscita");
        System.out.println(" 1) Apri una rubrica esistente");
        System.out.println(" 2) Crea una nuova rubrica");
        System.out.println(" 3) Elimina una rubrica");
        System.out.println(" 4) Modifica ruolo");
        System.out.println(" 5) Stampa tutte le rubriche");
        System.out.println(" 6) Cancella tutte le rubriche");
        System.out.println(" 7) Stampa il contenuto di tutte le rubriche");
    }

    public void scelta(int n){
        switch (n){
            case 0:
                System.out.println("Fine Esercizio");
                break;
            case 1:
                break;
            case 2:
                nuovaRubrica();
                break;
            case 3:
                Scanner sc = new Scanner(System.in);
                System.out.print("Scrivi il nome della rubrica da cancellare: ");
                if(!cancella(sc.next()))
                    System.out.println("Nessun Elemento a quel nome trovato");
                break;
            case 4:
                Scanner scanner = new Scanner(System.in);
                System.out.print("Quale Rubrica vuoi modificare: ");
                if(!modificaRole(scanner.next()))
                    System.out.println("Nessun Elemento a quel nome trovato");
                break;
            case 5:
                print(mapp);
                break;
            case 6:
                System.out.println("Eliminate tutte le rubriche");
                svuotaTutto();
                break;
            case 7:
                System.out.println("Contatti:");
                printRubriche();
                break;
            default:
                System.out.println("Scelta non valida");;
        }
    }

    public int controllo(){
        Scanner scan = new Scanner(System.in);
        int n = -1;
        try{
            n = scan.nextInt();
            System.out.println();
        } catch (Exception e){
            System.out.println("\nAttenzione devi inserire un numero");
        }
        return n;
    }

    public void nuovaRubrica(){
        Scanner scan = new Scanner(System.in);
        String type, description;
        do{
            System.out.print("Scrivi Nome (obbligatorio): ");
            type = scan.nextLine();
            if (type.length() <= 0)
                System.out.println("ERRORE\nci deve essere il nome obbligatorio");
        } while(type.length() <= 0);
        System.out.print("Scrivi una descrizione: ");
        description = scan.nextLine();
        mapp.put(new Role(type,description),new ArrayList<Account>());
    }

    public boolean modificaRole(String s){
        for(Role i: mapp.keySet()) {
            if (i.getType().equals(s)) {
                System.out.println("Cosa vuoi modificare:");
                System.out.println(" 1) nome");
                System.out.println(" 2) descrizione");
                System.out.println(" 3) entrambi");
                Scanner sc = new Scanner(System.in);
                Scanner scan = new Scanner(System.in);
                do {
                    switch (controllo()) {
                        case 1:
                            System.out.print("Nome: ");
                            i.setType(sc.next());
                            return true;
                        case 2:
                            System.out.print("Descrizione: ");
                            i.setDescription(sc.nextLine());
                            return true;
                        case 3:
                            System.out.print("Nome: ");
                            i.setType(sc.next());
                            System.out.print("Descrizione: ");
                            i.setDescription(scan.nextLine());
                            return true;
                        default:
                            System.out.println("inserisci un valore valido");
                    }
                } while (true);
            }
        }
        return false;
    }

    public void print(Map<Role,ArrayList> st){
        for(Role i: st.keySet()){
            System.out.println(" Nome: " + i.getType());
            System.out.println(" Uid: " + i.getUid());
            System.out.println(" Descrizione: " + i.getDescription());
            System.out.println();
        }
    }

    public void printRubriche(){
        MenuRubrica menu = new MenuRubrica();
        for(Role i: mapp.keySet()) {
            menu.printArray(mapp.get(i));
        }
    }

    public boolean cancella(String nome){
        for(Role i: mapp.keySet()) {
            if (i.getType().equals(nome)){
                System.out.println("Eliminato");
                mapp.remove(i);
                return true;
            }
        }
        return false;
    }

    public void svuotaTutto(){
        mapp.clear();
    }
}
