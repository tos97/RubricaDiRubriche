package MyFile;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class MyFile {

    public static String BASE_PATH = "C:\\Users\\rober\\Documents\\GitHub\\RubricaDiRubriche\\Rubriche\\src\\main";
    public static String RESOURCES_PATH = BASE_PATH + File.separator + "resources";
    public static String EXIT_JSON = ".json";


    public static String readFile(String fileNome){
        String bofyFile = "";
        FileReader reader = null;

        try{
            reader = new FileReader(RESOURCES_PATH + File.separator + fileNome + EXIT_JSON);
            int i = 0;
            while (i != -1){
                i = reader.read();
                if (i != -1)
                    bofyFile = bofyFile + (char) i;
            }
        } catch (IOException e){
            System.out.println("Errore di lettura " + e.getMessage());
        } finally {
            if (reader != null){
                try{
                    reader.close();
                } catch (IOException e){
                    System.out.println("Errore di lettura " + e.getMessage());
                }
            }
        }
        return bofyFile;
    }

    public static void writeFile(String fileNome, String body){
        String path = RESOURCES_PATH + File.separator + fileNome + EXIT_JSON;

        try{
            File file = new File(path);
            FileWriter fw = new FileWriter(file);
            fw.write(body);
            fw.flush();
            fw.close();
        } catch(IOException e){
            e.printStackTrace();
        }
    }

    /*public static void deleteFile(String fileNome){
        String path = RESOURCES_PATH + File.separator + fileNome + EXIT_JSON;
        File file = new File(path);
        if (file.delete())
            System.out.println("il file "+ path +" è stato cancellato");
        else{
            System.out.println("il file "+ path +" non può essere cancellato");
        }
    }*/

    public static void deleteFile(String nomePath, boolean all, String ext){
        if(all) {
            String path = RESOURCES_PATH + File.separator + nomePath;
            if(ext.length() == 0) {
                for (File filetmp : new File(path).listFiles()) {
                    if (filetmp.delete())
                        System.out.println("il file " + filetmp.getName() + " è stato cancellato");
                    else {
                        System.out.println("Errore nel cancellare i file in " + path);
                    }
                }
            }
            else{
                for (File filetmp: new File(path).listFiles()) {
                    if(filetmp.getName().contains(ext))
                        if (filetmp.delete())
                            System.out.println("il file " + filetmp.getName() + " è stato cancellato");
                        else {
                            System.out.println("Errore nel cancellare i file in " + path);
                        }
                }
            }
        }
        else{
            String path = RESOURCES_PATH + File.separator + nomePath + "." + ext;
            File file = new File(path);
            if (file.delete())
                System.out.println("il file "+ path +" è stato cancellato");
            else{
                System.out.println("il file "+ path +" non può essere cancellato");
            }
        }
    }

    /*public static void deleteFile(String dirPath, String ext){
        String path = RESOURCES_PATH + File.separator + dirPath;
        File file = new File(path);
        if(!file.exists())
            System.out.println("errore la dir "+ dirPath +" non esiste");
        else{
            for (File filetmp: new File(path).listFiles()) {
                if(filetmp.getName().contains(ext))
                    if (filetmp.delete())
                        System.out.println("il file " + filetmp.getName() + " è stato cancellato");
                    else {
                        System.out.println("Errore nel cancellare i file in " + path);
                    }
            }
        }

    }*/
}
