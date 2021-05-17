package Models;

public class Account {
    private User user ;
    private String numero;
    private String email;

    public Account(User user, String numero, String email) {
        this.user = user;
        this.numero = numero;
        this.email = email;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
