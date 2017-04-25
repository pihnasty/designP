package tests.core.StringBiulderTest;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Pihnastyi.O on 4/24/2017.
 */
public class StringBiulderT {
    public static void main(String[] args) {

        List<String> checkedUsers = Arrays.asList("aaa","bbb","ccc","ddd");

        StringBuilder checkedUsersString = new StringBuilder("");

        checkedUsers.stream().map(user ->  checkedUsersString.append(","+user)).count();
        checkedUsersString.deleteCharAt(0);


        System.out.println(checkedUsersString.toString());
    }
}
