package pattern.mvvm.passiv;

public class Model {
    private String password;

    public Model() {
        password = "password"; //just set a default password.
    }

    public void setPassword(String pass) {
        password = pass;
    }

    public String getPassword() {
        return password;
    }
}
