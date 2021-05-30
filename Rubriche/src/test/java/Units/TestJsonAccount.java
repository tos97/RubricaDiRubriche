package Units;

import Models.Account;
import Models.User;
import Rubricae.MenuRubrica;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;

public class TestJsonAccount {
    MenuRubrica rub = new MenuRubrica();
    ArrayList<Account> rubrica;
    private static Account account_1;
    private static Account account_2;
    private static final String json1 = "[{\"user\":{\"nome\":\"francesco\",\"surname\":\"fusco\",\"age\":\"21\",\"uid\":\"7b7949f2-e97f-4ce1-b268-966ebb108ae4\"},\"numero\":\"1234567890\",\"email\":\"fusco.com\"}]";
    private static final String json2 = "[{\"user\":{\"nome\":\"michele\",\"surname\":\"rubino\",\"age\":\"\",\"uid\":\"30a32ef7-c5e2-44b5-a403-51385b5e8928\"},\"numero\":\"0987654321\",\"email\":\"\"}]\"";

    //"[{\"user\":{\"nome\":\"michele\",\"surname\":\"rubino\",\"age\":\"\",\"uid\":\"30a32ef7-c5e2-44b5-a403-51385b5e8928\"},\"numero\":\"0987654321\",\"email\":\"\"}]\""

    @BeforeAll
    static void beforeAll(){
        account_1 = new Account(new User("francesco","fusco","21","7b7949f2-e97f-4ce1-b268-966ebb108ae4"),"1234567890","fusco.com");
        account_2 = new Account(new User("michele","rubino","","30a32ef7-c5e2-44b5-a403-51385b5e8928"),"0987654321","");
    }

    @BeforeEach
    void beforeEach(){
        rubrica = new ArrayList<>();
    }

    //@ParameterizedTest(name = "Test {0}")
    //@ValueSource (strings = {json1, json2})
    @Test
    @DisplayName("Salva Json")
    void Test_001_Salva(){
        rubrica.addAll(Arrays.asList(rub.importJ(json1)));
        ArrayList<Account> temp = new ArrayList<>();
        temp.add(account_1);
        temp.add(account_2);
        int c = 0;
        for (Account contenuto1: rubrica){
            int i = 0;
            for (Account contenuto2: temp){
                if(contenuto1.getUser().getNome().equals(contenuto2.getUser().getNome()))
                    i++;
                if(contenuto1.getUser().getSurname().equals(contenuto2.getUser().getSurname()))
                    i++;
                if(contenuto1.getUser().getAge().equals(contenuto2.getUser().getAge()))
                    i++;
                if(contenuto1.getUser().getId().equals(contenuto2.getUser().getId()))
                    i++;
                if(contenuto1.getEmail().equals(contenuto2.getEmail()))
                    i++;
                if(contenuto1.getNumero().equals(contenuto2.getNumero()))
                    i++;
            }
            if (i == 6)
                c++;
        }
        assertEquals(c, rubrica.size());
    }

    @Test
    @DisplayName("Export Json")
    void Test_002_Export(){
        rubrica.add(account_1);
        rub.setRubrica(rubrica);
        assertEquals(json1,rub.exportJson(""));
    }

    @AfterEach
    void afterEach(){}

    @AfterAll
    static void afterAll(){}
}
