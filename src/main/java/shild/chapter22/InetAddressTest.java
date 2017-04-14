package shild.chapter22;

import java.net.InetAddress;
import java.net.UnknownHostException;

class InetAddressTest
{
    public static void main(String args[]) throws UnknownHostException {
        InetAddress Address = InetAddress.getLocalHost();
        System.out.println(Address);
        Address = InetAddress.getByName("www.HerbSchildt.com");
        System.out.println(Address);
        InetAddress SW[] = InetAddress.getAllByName("www.nba.com");
        for (int i=0; i<SW.length; i++)
            System.out.println(SW[i]);

        System.out.println("Address.getAddress().toString()="+Address.getAddress().toString());
        System.out.println("Address.getHostName()="+Address.getHostName());
        System.out.println("Address.getCanonicalHostName()="+Address.getCanonicalHostName());

        //InetAddress Address2 = Address.getAddress().toString();
        System.out.println("Address.getAddress()= "+(Address.getAddress()[0]+256)+" "+Address.getAddress()[1]+" "+Address.getAddress()[2]+" "+Address.getAddress()[3]);


    }
}
