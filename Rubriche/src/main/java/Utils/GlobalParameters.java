package Utils;

import java.io.File;
import java.net.PortUnreachableException;

public class GlobalParameters {

    public static String BASE_PATH = "C:\\Users\\rober\\Documents\\GitHub\\RubricaDiRubriche\\Rubriche";
    public static String MAIN_PATH = BASE_PATH + "\\src\\main";
    public static String RESOURCES_PATH = MAIN_PATH + File.separator + "resources";
    public static String PROPERTIES_PATH = RESOURCES_PATH + File.separator + "properties";
    public static String EXIT_JSON = ".json";
    public static String EXIT_PROPERTIES = ".properties";
    public static String MAP_PATH = File.separator + "Map";
    public static String RUB_PATH = File.separator + "Rub";
    public static String LOGS_PATH = BASE_PATH + "\\logs";
}
