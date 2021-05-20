import MyFile.MyFile;

import java.io.File;

import static javafx.application.Platform.exit;

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
                }
                if (argv[i].equals("Base"))
                    inizio = true;
            }
            if(!inizio)
                if(MyFile.existFile(File.separator + "Map" + File.separator + "Base"))
                    action.inportHashmap("Base");
        }catch (ArrayIndexOutOfBoundsException ex){
            System.err.println("Errore Args" + ex.getMessage());
        }
    }
}
