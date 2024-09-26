
import java.security.*;
import java.util.ArrayList;

import src.StringUtil;



public class Transaction {
    public String transaction_id;
    public PublicKey sender_public_key;
    public PublicKey reciepent_public_key;    
    public float value;
    public byte[] signature;

    public ArrayList<TransactionInput> inputs = new ArrayList<>();
    public ArrayList<TransactionOutput> outputs = new ArrayList<>();

    private static int sequence = 0; // how many transactions have been generated

    public Transaction(PublicKey from, PublicKey to, float value, ArrayList<TransactionInput> inputs){
        this.sender_public_key = from;
        this.reciepent_public_key = to;
        this.value = value;
        this.inputs = inputs;
    }

    // Calculate transaction Hash, will be used as its id
    private String calculateHash(){
        sequence++; // to avoid 2 transactions having the same hash
        return StringUtil.applySha256(
                                        StringUtil.getStringFromKey(this.sender_public_key) +
                                        StringUtil.getStringFromKey(this.reciepent_public_key) +
                                        Float.toString(this.value) + this.sequence
                                    );       
    }
}
