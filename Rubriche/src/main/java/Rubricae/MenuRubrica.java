package Rubricae;

import Models.Account;
import Models.User;
import Utils.Utils;
import com.google.gson.Gson;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import static Utils.GlobalParameters.*;

public class MenuRubrica {

    ArrayList<Account> rubrica = new ArrayList<Account>();

    /*public Rubricae.MenuRubrica(ArrayList<Account> rubrica) {
        this.rubrica = rubrica;
    }*/

    public MenuRubrica() {}

    public ArrayList<Account> getRubrica() {
        return rubrica;
    }

    public void setRubrica(ArrayList<Account> rubrica) {
        this.rubrica = rubrica;
    }

    public int getSize(ArrayList<Account> rub) {
        return rub.size();
    }

    public void opzioniRubrica(String nomeRubrica){
        System.out.println("\nMenu Rubrica "+ nomeRubrica +"\n " +
                "0) Menu Rubriche(Map)\n 1) Add\n 2) Delete\n " +
                "3) Update\n 4) Search\n 5) Print All\n " +
                "6) Delete All\n 7) Salva tramite json\n " +
                "8) print All in Json\n " +
                "9) Import Json da File\n 10) Export in file");
    }

    public ArrayList<Account> scelta(int n,String nomeRubrica){
        switch (n) {
            case 0:
                System.out.println("Arrivederci " + nomeRubrica);
                return rubrica;
            case 1: aggiungi();break;
            case 2:
                Scanner scan = new Scanner(System.in);
                System.out.println("Scrivi quello che vuoi cancellare:");
                String s = scan.nextLine();
                canc(s);
                break;
            case 3:
                Scanner sc = new Scanner(System.in);
                System.out.println("Scrivi quello che vuoi cancellare:");
                String str = sc.nextLine();
                modifica(str);
                break;
            case 4:
                Scanner scanner = new Scanner(System.in);
                System.out.print("Scrivi cosa cercare: ");
                ArrayList<Account> temp = new ArrayList<Account>();
                temp.addAll(search(scanner.next(), rubrica));
                if(getSize(temp) == 0)
                    System.err.println("Non ci sono riferimenti alla ricerca effettuata");
                else{
                    System.out.println("Trovato:");
                    printArray(temp);
                }
                break;
            case 5: printArray(rubrica);break;
            case 6: clear();break;
            case 7: importJson(); break; //importJson();
            case 8: exportJson(); break; //exportJson();
            case 9: importJsonFile(); break; //importJsonFile();
            case 10: exportJsonFile(nomeRubrica); break; //exportJsonFile();
            default: System.out.println("ERRORE inserire un numero tra 0 a 10");
        }
        return rubrica;
    }

    public void aggiungi(){
        String id = "",nom = "",cog = "",eta = "",email = "",tel = "";
        Scanner scan = new Scanner(System.in);
        do{
            System.out.print("Scrivi Nome (obbligatorio): ");
            nom = scan.nextLine();
            if (nom.length() <= 0)
                System.out.println("ERRORE\nci deve essere il nome obbligatorio");
        } while(nom.length() <= 0);
        System.out.print("Scrivi cognome: ");
        cog = scan.nextLine();
        System.out.print("Scrivi Eta: ");
        eta = scan.nextLine();
        do {
            System.out.print("Scrivi Email: ");
            email = scan.nextLine();
            System.out.print("Scrivi Numero di Telefono: ");
            tel = scan.nextLine();
            if (email.length() <= 0 && tel.length() <= 0)
                System.out.println("ERRORE\nci deve essere almeno il numero o l'email");
        } while(email.length() <= 0 && tel.length() <= 0);
        Account account = new Account(new User(nom,cog,eta),tel,email);
        rubrica.addAll(addAccount(account));
    }

    public ArrayList<Account> addAccount(Account account) {
        ArrayList<Account> app = new ArrayList<>();
        app.add(account);
        return app;
    }

    public void canc(String s){
        Scanner scan = new Scanner(System.in);
        ArrayList<Account> temp;
        temp = search(s,rubrica);
        if (getSize(temp) == 0){
            System.out.println("Nessun elemento trovato");
            System.out.println("controlla tra questi elementi salvati quello da cancellare");
            printArray(rubrica);
        }
        if (getSize(temp) > 1) {
            System.out.println("Troppi elementi trovati a questa ricerca si più specifico");
            System.out.println("controlla tra questi elementi trovati ed inserisci un elemento univoco del contatto come l'uid dell'elemento da  cancellare");
            printArray(temp);
            s = scan.next();
            canc(s);
        }
        if (getSize(temp) == 1) {
            //System.out.println("vuoi cancellare questo elemento (y o n):");
            //printArray(temp);
            //char r = scan.next().charAt(0);
            //if(r == 'y')
                for(int i = 0;i < getSize(rubrica);i++) {
                    if (findIndex(rubrica, s, i))
                        rubrica.remove(i);
                }
        }
    }

    public void modifica(String s) {
        Scanner scan = new Scanner(System.in);
        ArrayList<Account> temp;
        temp = search(s,rubrica);
        if (getSize(temp) == 0)
            System.err.println("ATTENZIONE\nNessun contatto con questo nome trovato");
        if (getSize(temp) > 1) {
            System.out.println("Troppi elementi trovati a questa ricerca si più specifico");
            System.out.println("controlla tra questi elementi trovati ed inserisci un elemento univoco del contatto come l'uid dell'elemento da modificare");
            printArray(temp);
            s = scan.next();
            modifica(s);
        }
        if (getSize(temp) == 1) {
            System.out.println("\nContatto trovato:");
            printArray(temp);
            System.out.println("\nModifica da effettuare:");
            s = scan.nextLine();
            System.out.println("\n 1) Nome\n 2) Cognome\n 3) età\n 4) Numero di telefono\n 5) Email");
            int r = scan.nextInt();
            int i;
            for(i = 0;i < getSize(rubrica);i++) {
                if (findIndex(rubrica, s, i))
                    break;
            }
            switch (r) {
                case 1:
                    rubrica.get(i).getUser().setNome(s);
                    break;
                case 2:
                    rubrica.get(i).getUser().setSurname(s);
                    break;
                case 3:
                    rubrica.get(i).getUser().setAge(s);
                    break;
                case 4:
                    rubrica.get(i).setNumero(s);
                    break;
                case 5:
                    rubrica.get(i).setEmail(s);
                    break;
                default:
                    System.out.println("Fuori range di selezione");
            }
        }
    }

    public ArrayList<Account> search(String s, ArrayList<Account> app) {
        ArrayList<Account> temp = new ArrayList<>();
        for(int i = 0;i < getSize(app);i++) {
            if (findIndex(app, s, i)) {
                temp.add(app.get(i));
            }
        }
        return temp;
    }

    public boolean findIndex(ArrayList<Account> app, String s, int i) {
        boolean n,c,a,u,t,e;
        n = app.get(i).getUser().getNome().contains(s);
        c = app.get(i).getUser().getSurname().contains(s);
        a = app.get(i).getUser().getAge().contains(s);
        u = app.get(i).getUser().getId().contains(s);
        t = app.get(i).getNumero().contains(s);
        e = app.get(i).getEmail().contains(s);
        if(n == true || c == true || a == true || u == true || t == true || e == true)
            return true;
        return false;
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

    public void clear() {
        rubrica.clear();
    }

    public void importJson(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Inserisci il json da salvare:");
        String json = scanner.next();
        rubrica.addAll(Arrays.asList(importJ(json)));
    }

    public Account[] importJ(String jj){
        return new Gson().fromJson(jj, Account[].class);
    }

    public void exportJson(){
        System.out.println(new Gson().toJson(rubrica));
    }

    public String exportJson(String nome){
        return new Gson().toJson(rubrica);
    }

    public void importJsonFile() {
        Scanner scan = new Scanner(System.in);
        System.out.println("scrivi il nome del file dai cui prelevare la rubrica");
        boolean bol;
        do {
            String nome = scan.next();
            bol = Utils.existFile(RESOURCES_PATH + RUB_PATH + File.separator + nome, "json");
            if (!bol)
                System.out.println("Il file " + nome + " non esiste.\nControlla meglio!\n");
            else {
                rubrica.addAll(Arrays.asList(new Gson().fromJson(Utils.readFile((File.separator + "Rub" + File.separator + nome)), Account[].class)));
                System.out.println("importato correttamente");
            }
        } while (!bol);
    }

    public void exportJsonFile(String nomeRubrica){
        String nome = "backup" + nomeRubrica;
        System.out.println("Il nome del file su cui salvare la rubrica è "+nome);
        Utils.writeFile((RUB_PATH + File.separator + nome),exportJson(nome));
    }
}
