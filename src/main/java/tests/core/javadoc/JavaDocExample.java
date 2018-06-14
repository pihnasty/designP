package tests.core.javadoc;

/**
 * The {@code GrantedRolesNodePrinter} class is a mechanism for generating the grant/revoke statement
 , which makes it possible provide privileges from one Role for another Role.
 * If you grant a role to another role, then the database adds the privilege domain of the granted role to
 * the privilege domain of the grantee role. Users who have been granted the grantee
 * role can enable it and exercise the privileges in the granted role's
 * privilege domain.
 * @see <a href="https://docs.oracle.com/cd/B19306_01/server.102/b14200/statements_9013.htm#i2125988">Grant</a>
 * @see <a href="https://docs.oracle.com/cd/B19306_01/server.102/b14200/statements_9020.htm#i2100253">Revoke</a>
 */

public class JavaDocExample {
    // {@JavaDocExample GrantedRolesNodePrinter}
}
