import Models.Account;
import Models.MapModel;
import Models.Role;
import Models.User;
import MyFile.MyFile;
import Rubricae.MenuRubrica;
import com.google.gson.Gson;
import com.sun.org.omg.CORBA.StructMemberSeqHelper;

import java.io.File;
import java.util.*;

public class ActionMap {
    Map<Role,ArrayList> mapp = new HashMap();
    //Role ruolo = new Role();
    Rubricae.MenuRubrica menu = new Rubricae.MenuRubrica();

    public ActionMap() {
        ArrayList<Account> rubrica =  new ArrayList<Account>();
        rubrica.add(new Account(new User("Bendetto", "tosiani", "23"), "3312341300", "benedetto.tosiani@edu.unife.it"));
        rubrica.add(new Account(new User("Giorgio", "Vanni", "57"), "3339196342", ""));
        mapp.put(new Role("Other", "Rubrica prova per test, precaricatata uguale alla prima"), new ArrayList<Account>());
        mapp.put(new Role("Admin", "Rubrica dell'amministratore, quella principale precaricatata"), rubrica);
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
        System.out.println(" 8) import json mappa rubrica da file");
        System.out.println(" 9) export json mappa rubrica da file");
        System.out.println(" 10) Delete file");
    }

    public void scelta(int n){
        switch (n){
            case 0:
                System.out.println("Fine");
                break;
            case 1:
                print(mapp);
                System.out.println("Scegli su quale rubrica lavorare (inserire la posizione della rubrica voluta):");
                int contr;
                do {
                    contr = controllo();
                    if (contr <= mapp.size() && contr >= 0)
                        break;
                    else{
                        System.out.println("inserisci un valore tra 1 e " + mapp.size() + "se non ci sono rubriche 0 per escape");
                        contr = -1;
                    }
                } while (contr == -1);

                int c = 1;
                for (Role i : mapp.keySet()) {
                    if (c == contr){
                        int valore = 0;
                        menu.setRubrica(mapp.get(i));
                        do {
                            menu.opzioniRubrica(i.getType());
                            do {
                                valore = controllo();
                            } while (valore == -1);
                            ArrayList<Account> array = new ArrayList<Account>();
                            array.addAll(menu.scelta(valore,i.getType()));
                            if (valore == 0) {
                                mapp.put(i,array);
                                break;
                            }
                        } while (true);
                    }
                    c++;
                }
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
            case 8: inportHashmap("");
                break;
            case 9: exportHashmap("");
                break;
            case 10:
                Scanner scann = new Scanner(System.in);
                Scanner scInt = new Scanner(System.in);
                boolean bol = false;
                String path = "Map";
                System.out.print("scegli la cartella in cui entrare (1-Map, 2-Rub): ");
                do {
                    switch (scInt.nextInt()) {
                        case 1:
                            path = "Map";
                            bol = true;
                            break;
                        case 2:
                            path = "Rub";
                            bol = true;
                            break;
                        default:
                            System.out.println("Sei fuori range");
                    }
                }while (!bol);
                System.out.println("Voi cancellare:\n 1) Un file specifico\n 2) Tutti i file\n 3) File con una estensione");
                bol = false;
                do {
                    switch (scInt.nextInt()) {
                        case 1:
                            System.out.print("scrivi il nome del file da cancellare: ");
                            MyFile.deleteFile((File.separator + path + File.separator + scann.next()), false, "json");
                            bol = true;
                            break;
                        case 2:
                            MyFile.deleteFile((File.separator + path), true, "");
                            bol = true;
                            break;
                        case 3:
                            System.out.print("Inserisci estensione da cancellare: ");
                            MyFile.deleteFile((File.separator + path), true, scann.next());
                            bol = true;
                            break;
                        default:
                            System.out.println("Sei fuori range");
                    }
                }while (!bol);
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
        int pos = 1;
        for(Role i: st.keySet()){
            System.out.println(" "+ pos +") Nome: " + i.getType());
            System.out.println(" Uid: " + i.getUid());
            System.out.println(" Descrizione: " + i.getDescription());
            pos++;
            System.out.println();
        }
    }

    public void printRubriche(){
        for(Role i: mapp.keySet()) {
            System.out.println();
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

    public void inportHashmap(String nome){
        if (!MyFile.existFile(File.separator + "Map" + File.separator + nome))
            System.out.println("Il file "+ nome +" non esiste.\nControlla meglio!\n");
        else {
            if (nome.length() > 0) {
                inport(nome);
            } else {
                System.out.println("scrivi il nome del file su cui salvare le rubriche");
                inport(new Scanner(System.in).next());
                System.out.println("Inportato correttamente " + nome);
            }
        }
    }

    public void inport(String nome){
        String json = MyFile.readFile(File.separator + "Map" + File.separator + nome);
        ArrayList<MapModel> mapList = new ArrayList<>(Arrays.asList(new Gson().fromJson(json, MapModel[].class)));
        for(MapModel i: mapList){
            mapp.put(i.getRuolo(),i.getRubrica());
        }
    }

    public void exportHashmap(String nome){
        if (nome.length() > 0) {
            inport(nome);
        } else {
            System.out.println("scrivi il nome del file su cui salvare le rubriche");
            export(new Scanner(System.in).next());
            System.out.println("Exportato correttamente " + nome);
        }
    }

    public void export(String nome){
        ArrayList<MapModel> mapList = new ArrayList<>();
        int n = 0;
        for(Role i: mapp.keySet()){
            ArrayList<Account> arr =  new ArrayList<>();
            MapModel mapMod = new MapModel();
            Role role = new Role();

            arr.addAll(mapp.get(i));

            role.setType(i.getType());
            role.setDescription(i.getDescription());
            role.setId(i.getUid());

            mapMod.setRuolo(role);
            mapMod.setRubrica(arr);

            mapList.add(n, mapMod);
            n++;
        }
        MyFile.writeFile((File.separator + "Map" + File.separator + nome), new Gson().toJson(mapList));
    }
}
