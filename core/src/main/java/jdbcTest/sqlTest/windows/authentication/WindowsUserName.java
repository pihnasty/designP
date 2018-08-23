package jdbcTest.sqlTest.windows.authentication;

public class WindowsUserName {
    public static void main(String[] args) {
        String name = System.getProperty("user.name");
   //      name = System.getProperty("user.home");
   //     name = System.getProperty("domain");
        String domain = System.getenv("USERDOMAIN_ROAMINGPROFILE");
        System.out.println( domain+"\\"+name);

        System.out.println("OC="+System.getProperty("os.name").toLowerCase());

        getTypeOS();
    }

    public static Boolean getTypeOS() {
        String os = System.getProperty("os.name").toLowerCase();

        return  os.contains("win");
    }

}
