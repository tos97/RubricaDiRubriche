import Utils.Utils;

import java.io.File;
import java.util.Properties;

import static Utils.GlobalParameters.*;
import static Utils.Utils.*;

public class Run {

    static ActionMap action = new ActionMap();

    public static void main(String [] argv) {

        int n = 0;
        if (argv.length >= 0) {
            decodeArgs(argv);
        }
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

    public static void decodeArgs(String [] argv){
        Properties prop = loadProp("settings");
        String backupIniziale = prop.getProperty("backup.nome");
        System.out.println(backupIniziale);
        try{
            boolean inizio = false;
            for(int i = 0;i<argv.length;i++){
                switch (argv[i]){
                    case "-inport":
                        action.inportHashmap(argv[i+1]);
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
                    action.inportHashmap(backupIniziale);
        }catch (ArrayIndexOutOfBoundsException ex){
            System.err.println("Errore Args" + ex.getMessage());
        }
    }
}
