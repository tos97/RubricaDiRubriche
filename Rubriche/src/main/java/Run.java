import Utils.Utils;

import java.io.File;
import java.util.Properties;

import static Utils.GlobalParameters.*;
import static Utils.Utils.*;

public class Run {

    static ActionMap action = new ActionMap();

    public static void main(String [] argv) {

        int n = 0;
        if (argv.length > 0) {
            gestioneProperties(argv);
        }
        decodeArgs();
        do {
            action.opzioni();
            do {
                n = action.controllo();
            } while (n == -1);
            action.scelta(n);
            if (n == 0)
                break;
        } while (true);
    }

    public static void gestioneProperties(String [] argv){
        String body = "";
        String path = PROPERTIES_PATH + File.separator + "Arguments" + EXIT_PROPERTIES;
        for(int i = 0;i < argv.length;i++){
            try {
                if (!body.contains(argv[i]))
                    if (argv[i].contains("-")) {
                        if (i == (argv.length -1))
                            body = body + argv[i];
                        if (argv[i + 1].contains("-"))
                            body = body + argv[i] + "\n";
                        else {
                            body = body + argv[i] + " = " + argv[i+1] + "\n";
                        }
                    }
            }catch (ArrayIndexOutOfBoundsException ex){}
        }
        writeFileProp(path,body);
    }

    public static void decodeArgs(){
        Properties prop = loadProp("Arguments");
        boolean inizio = false;
        for (String key: prop.stringPropertyNames()){
            switch (key){
                case "-import":
                    if (prop.getProperty(key).length() > 0)
                        action.importHashmap(prop.getProperty(key));
                    break;
                case "-export":
                    if (prop.getProperty(key).length() > 0)
                        action.exportHashmap(prop.getProperty(key));
                    break;
                case "-h":
                    System.out.println("\nHELP:\nBenvenuto in questa rubrica ecco alcune info:");
                    System.out.println("Questa rubrica serve per salvare i contatti di vari utenti diversi");
                    System.out.println("Qui troverai già alcuni esempi gia salvati nell'utente Admin c'è anche la possibilità di inportare tramite json backup di rubriche");
                    System.out.println("sia passando il loro nome come argomento sia passandolo tramite tastiera durante l'esecuzione");
                    break;
            }
            if (prop.getProperty(key).equals("Base"))
                inizio = true;
        }
        if(!inizio)
            if(Utils.existFile((RESOURCES_PATH + MAP_PATH + File.separator + "Base"), "json"))
                action.importHashmap("Base");
    }

    /*public static void decodeArgs(String [] argv){
        String backupIniziale = "Base";
        Properties prop = loadProp("Arguments");
        try{
            boolean inizio = false;
            for(int i = 0;i< prop.size();i++){

                switch (argv[i]){
                    case "-import":
                        action.importHashmap(argv[i+1]);
                        break;
                    case "-export":
                        action.exportHashmap(argv[i+1]);
                        break;
                    case "-h":
                        System.out.println("\nHELP:\nBenvenuto in questa rubrica ecco alcune info:");
                        System.out.println("Questa rubrica serve per salvare i contatti di vari utenti diversi");
                        System.out.println("Qui troverai già alcuni esempi gia salvati nell'utente Admin c'è anche la possibilità di inportare tramite json backup di rubriche");
                        System.out.println("sia passando il loro nome come argomento sia passandolo tramite tastiera durante l'esecuzione");
                        break;
                }

                if (argv[i].equals(backupIniziale))
                    inizio = true;
            }
            if(!inizio)
                if(Utils.existFile(MAP_PATH + File.separator + backupIniziale))
                    action.importHashmap(backupIniziale);
        }catch (ArrayIndexOutOfBoundsException ex){
            System.err.println("Errore Args" + ex.getMessage());
        }
    }*/
}
