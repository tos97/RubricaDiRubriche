import static javafx.application.Platform.exit;

public class Run {
    public static void main(String [] argv){
        ActionMap action = new ActionMap();
        int n = 0;
        do{
            action.opzioni();
            do {
                n = action.controllo();
            } while (n == -1);
            action.scelta(n);
            if (n == 0)
                break;
        } while (true);
    }
}
