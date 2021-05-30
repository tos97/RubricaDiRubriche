package Utils;

import org.junit.Test;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.junit.Assert.*;

import java.io.File;

import static Utils.GlobalParameters.*;
import static org.junit.Assert.assertEquals;

@TestMethodOrder(MethodOrderer.MethodName.class)
public class TestCrudUtils {

    String testo = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. \nUt enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. \nDuis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. \nExcepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.";

    @BeforeAll
    static void beforeAll(){ }

    @BeforeEach
    void beforeEach(){ }

    @ParameterizedTest(name = "File: {0}")
    @DisplayName("Write File")
    @CsvSource({"Gennaro", "Michele"})
    @Tag("File")
    void Test_001_Write(String arg){
        Utils.writeFileProp(LOGS_PATH + File.separator + arg + ".log", testo);
    }

    @ParameterizedTest(name = "File: {0}")
    @DisplayName("File Exist")
    @CsvSource({"Gennaro", "Michele"})
    @Tag("File")
    void Test_002_Esiste(String arg){
        assertEquals(true, Utils.existFile(LOGS_PATH + File.separator + arg, "log"));
    }

    @ParameterizedTest(name = "File: {0}")
    @DisplayName("Read File")
    @CsvSource({"Gennaro", "Michele"})
    @Tag("File")
    void Test_003_Leggi(String arg){
        assertEquals(testo, Utils.readFile(LOGS_PATH + File.separator + arg, "log"));
    }

    @ParameterizedTest(name = "File: {0}")
    @DisplayName("Cancella File")
    @CsvSource({"Gennaro", "Michele"})
    @Tag("File")
    void Test_004_Delete(String arg){
        Utils.deleteFile(LOGS_PATH + File.separator + arg, false,"log");
    }

    @AfterEach
    void afterEach(){}

    @AfterAll
    static void afterAll(){}
}
