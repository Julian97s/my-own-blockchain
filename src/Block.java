package src;

import java.util.Date;

public class Block {
    public String hash;
    public String previousHash;
    private String data;
    private long timeStamp;
    private int nonce; //"created for one special ocation"

    public Block (String data, String previousHash){
        this.data = data;
        this.previousHash = previousHash;
        this.timeStamp = new Date().getTime();
        this.hash = calculateHash(); // has to be after the other parameters are assigned
    }

        /* calculateHash will help scramble all the data */
    public String calculateHash(){
        String calculated_hash = StringUtil.applySha256(
                                                        this.previousHash +
                                                        Long.toString(this.timeStamp) + 
                                                        Integer.toString(this.nonce) +
                                                        this.data
                                                        );
        return calculated_hash; // shouldn't the parameters be this. ? in the website i got this it was not this.
    }

    public void mineBlock (int difficulty) {
        // Is there any resource where i can see this graphically?
        String target = new String(new char[difficulty]).replace("\0","0"); // create a string with difficulty * 0
        while (!this.hash.substring(0,difficulty).equals(target)) {
            nonce++;
            hash = calculateHash();
        }
        System.out.println("Block mined!: " + this.hash);
    }

}


