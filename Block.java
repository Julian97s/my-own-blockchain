
import java.util.Date;

public class Block {
    public String hash;
    public String previousHash;
    private String data;
    private long timeStamp;

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
                                                        this.data
                                                        );
        return calculated_hash; // shouldn't the parameters be this. ? in the website i got this it was not this.
    }
}


