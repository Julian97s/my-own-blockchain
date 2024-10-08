package src;

import java.security.MessageDigest;
import java.security.PublicKey;
import java.security.Signature;
import java.util.Base64;

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

    public static byte[] applyACDSASig(PrivateKey privateKey, String input) {
        Signature dsa;
        byte[] output = new byte[0];
        try {
            dsa = Signature.getInstance("ECDSA","BC");
            dsa.initSign(privateKey);
            byte[] strByte = input.getBytes();
            dsa.update(strByte);
            byte[] realSign = dsa.sign();
            output = realSign;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return output;
    }

    //Verify s String signature 
    public static boolean verifyECDSASign(PublicKey publicKey, String data, byte[] signature){
        try {
            Signature ecdsaVerify = Signature.getInstance("ECDSA","BC");
            ecdsaVerify.initVerify(publicKey);
            ecdsaVerify.update(data.getBytes());
            return ecdsaVerify.verify(signature);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static String getStringFromKey(Key key){
        return Base64.getEncoder().encodeToString(key.getEncoded());
    }

}
