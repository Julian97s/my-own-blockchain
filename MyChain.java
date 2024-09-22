
import java.util.ArrayList;
import com.google.gson.*;

public class MyChain{

        public static ArrayList<Block> blockchain = new ArrayList<Block>();
    public static void main(String[] args) {
       blockchain.add(new Block("first block", "0"));
       blockchain.add(new Block("second block",blockchain.get(blockchain.size()-1).hash));
       blockchain.add(new Block("third block",blockchain.get(blockchain.size()-1).hash));

        String blockchainJson = new GsonBuilder().setPrettyPrinting().create().toJson(blockchain);
        System.out.println(blockchainJson);
    }
}

/*
 * now i have a mess of files, i dont know how to organize them and also,
 * https://medium.com/programmers-blockchain/importing-gson-into-eclipse-ec8cf678ad52
 * here is the library but i dodnt knwo how to install the library in my machine
 * 
 */