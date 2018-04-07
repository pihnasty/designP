package tests.enumTest;

/**
 * Created by Pihnastyi.O on 3/13/2017.
 */
public class EnumTest {
    public static void main(String[] args) {

        System.out.println(ServerMessages.INVALID_SSL_HANDSHAKE.setMessage("dfdf"));
        System.out.println(ServerMessages.INVALID_SSL_HANDSHAKE.getMessage());


        ServerMessages enumS2 = ServerMessages.SUCCESS;
        System.out.println(enumS2.getMessage());
    }

    enum ServerMessages {
        SUCCESS, INVALID_CREDENTIALS, INVALID_CONNECTION_PROPS,
        INVALID_SSL_HANDSHAKE;

        public ServerMessages setMessage(String s) {
            sqlExceptionMessage = s;
            return this;
        }

        public String getMessage() {
            return sqlExceptionMessage;
        }

        private String sqlExceptionMessage="2";

    }
}

