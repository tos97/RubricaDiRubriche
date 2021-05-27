import Utils.Utils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.TestFactory;

import java.io.File;
import java.util.Arrays;
import java.util.Collection;

import static Utils.GlobalParameters.LOGS_PATH;
import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.DynamicTest.dynamicTest;
import org.junit.jupiter.api.DynamicTest;

public class ExempleDynamicTest {
    String testo = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. \nUt enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. \nDuis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. \nExcepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.";

    @TestFactory
    @DisplayName("Test Write")
    Collection<DynamicTest> dynamicTestCollection(){
        return Arrays.asList(
                dynamicTest("1 Test Read", () -> Utils.writeFileProp(LOGS_PATH + File.separator + "Gennaro.log", testo)),
                dynamicTest("2 Test Exist", () -> assertEquals(true, Utils.existFile(LOGS_PATH + File.separator + "Gennaro", "log"))),
                dynamicTest("3 Test Read", () -> assertEquals(testo, Utils.readFile(LOGS_PATH + File.separator + "Gennaro", "log"))),
                dynamicTest("4 Test Delete", ()-> Utils.deleteFile(LOGS_PATH + File.separator + "Gennaro", false,"log"))
        );
    }
}
