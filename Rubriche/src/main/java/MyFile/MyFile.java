package MyFile;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class MyFile {

    public static String BASE_PATH = "C:\\Users\\rober\\Documents\\GitHub\\Rubrica\\RubricaArrayList\\src\\main";
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
}
