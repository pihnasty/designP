package tests.core.StringBiulderTest;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Pihnastyi.O on 4/24/2017.
 */
public class StringBiulderT {
    public static void main(String[] args) {

    //    checkedUsers();
          statQueryText();
    }

    private static void checkedUsers() {
        List<String> checkedUsers = Arrays.asList("aaa","bbb","ccc","ddd");

        StringBuilder checkedUsersString = new StringBuilder("");

        checkedUsers.stream().map(user ->  checkedUsersString.append(","+user)).count();
        checkedUsersString.deleteCharAt(0);


        System.out.println(checkedUsersString.toString());
    }

    private static void statQueryText() {
        List<String> checkedUsers = Arrays.asList("aaa","bbb","ccc","ddd");

        String row_get_AttributeNames_STAT_QUERY_TEXT = "STAT_QUERY_TEXT";

        StringBuilder statQueryText = new StringBuilder();

        checkedUsers.stream().map(user -> {
            statQueryText.delete(0, statQueryText.length() ).append(row_get_AttributeNames_STAT_QUERY_TEXT);


            System.out.println(statQueryText.toString());
            return null;
        }).count();

    }

}
