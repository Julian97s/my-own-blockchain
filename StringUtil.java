
import java.security.MessageDigest;

// this helper function will take a string and return a signature as a string after a SHA-256 is applied

public class StringUtil {
    public static String applySha256(String input){
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(input.getBytes("UTF-8"));
            StringBuffer hexString = new StringBuffer(); // will contain hash as hexidecimal

            /*
             * A String buffer is LIKE a string but CAN be modified
             */
            for (int i = 0 ; i < hash.length ; i++){
                String hex = Integer.toHexString(0xff & hash[i]);
                if(hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
