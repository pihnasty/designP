package brus.brus_850_Annotation;

import java.util.List;

/**
 * Created by Pihnastyi.O on 7/27/2016.
 */
public class b851_PasswordsUtils {
    @b851_UseCase (id=47, description = "Password must contain at least one numberic")
    public boolean validatePassword(String password) {
        return password.matches("\\W*");
    }
    @b851_UseCase (id=48)
    public String encryptPassword (String password) {
        return new StringBuilder(password).reverse().toString();
    }
    @b851_UseCase (id=49, description = "New passord can't equals")
    public  boolean  checkForNewPassword ( List<String> prePassword, String password) {
        return !prePassword.contains(password);
    }
}
