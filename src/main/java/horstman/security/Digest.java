package horstman.security;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by Pihnastyi.O on 2/23/2017.
 */
public class Digest {
    public static void main(String[] args) throws NoSuchAlgorithmException {
        MessageDigest alg = MessageDigest.getInstance("SHA-1");
        byte [] input = {'O','l','e','h'};
        byte [] hash = alg.digest(input);
        String d ="";
        for (int i=0; i< hash.length; i++) {
            int v = hash[i]&0xFF;
            d+= Integer.toString(v,16).toLowerCase()+" ";
        }

        System.out.println(0xFF);
        System.out.println(d);

    }

}
