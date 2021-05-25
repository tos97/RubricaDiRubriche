import Models.Account;
import Models.User;
import Rubricae.MenuRubrica;
import org.junit.jupiter.api.*;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class TestCrudRubrica {

    private static Account pippo;
    private static Account pippo2;
    private static Account Ben;
    private static Account Gio;
    private static ArrayList<Account> rubrica;
    MenuRubrica menu;

    @BeforeAll
    static void beforeAll(){
        Ben = new Account(new User("Benedetto", "tosiani", "23"), "3312341300", "benedetto.tosiani@edu.unife.it");
        Gio = new Account(new User("Giorgio", "Vanni", "57"), "3339196342", "");
        pippo = new Account(new User("Pippo", "Vario", "64"), "3339196342", "");
        pippo2 = new Account(new User("Pippo2", "Vario2", "64"), "3339196342", "");
    }

    @BeforeEach
    void beforeEach(){
        rubrica = new ArrayList<>();
        menu = new MenuRubrica();
    }

    @Test
    @DisplayName("Add un elemento")
    void Test_001_Add(){
        menu.addAccount(Ben);
    }

    @Test
    @DisplayName("Search vuoto")
    void Test_002_Search(){
        rubrica.add(pippo);

        ArrayList<Account> risultato = new ArrayList<>();

        assertEquals(risultato, menu.search("pippo", rubrica));
    }

    @Test
    @DisplayName("Search un elemento")
    void Test_003_Search(){
        rubrica.add(pippo);
        rubrica.add(Ben);

        ArrayList<Account> risultato = new ArrayList<>();
        risultato.add(pippo);

        assertEquals(risultato, menu.search("ippo", rubrica));
    }

    @Test
    @DisplayName("Search più elementi")
    void Test_004_Search(){
        rubrica.add(pippo);
        rubrica.add(pippo2);

        ArrayList<Account> risultato = new ArrayList<>();
        risultato.add(pippo);
        risultato.add(pippo2);

        assertEquals(risultato, menu.search("ippo", rubrica));
    }

    @Test
    @DisplayName("Controlli su delete")
    @Disabled
    void Test_005_Delete(){
        rubrica.add(pippo);
        rubrica.add(pippo2);

        menu.setRubrica(rubrica);
        menu.canc("Vario2");
    }

    @AfterEach
    void afterEach(){}

    @AfterAll
    static void afterAll(){}
}
