package src;

import java.util.ArrayList;
import com.google.gson.*;

public class MyChain{

    public static ArrayList<Block> blockchain = new ArrayList<Block>();
    public static int difficulty = 5;
    public static void main(String[] args) {
       blockchain.add(new Block("first block", "0"));
       System.out.println("Trying to Mine block 1...");
       blockchain.get(0).mineBlock(difficulty);

       blockchain.add(new Block("second block",blockchain.get(blockchain.size()-1).hash));
       System.out.println("Trying to Mine block 2...");
       blockchain.get(1).mineBlock(difficulty);

       blockchain.add(new Block("third block",blockchain.get(blockchain.size()-1).hash));
       System.out.println("Trying to Mine block 3...");
       blockchain.get(2).mineBlock(difficulty);

       System.out.println("\nBlockchain is Valid: "+ isChainValid());

       String blockchainJson = new GsonBuilder().setPrettyPrinting().create().toJson(blockchain);
       System.out.println("\nThe Block chain: ");
       System.out.println(blockchainJson);
    }

    private static boolean isChainValid(){
        Block currentBlock;
        Block previousBlock;
        String hashTarget = new String(new char[difficulty]).replace('\0','0');

        // loop through blockchain to check hashes
        for (int i = 1; i < blockchain.size(); i++){ // int 1 must be equal to 1 because im pulling out of a list, not an array.
            currentBlock = blockchain.get(i);
            previousBlock = blockchain.get(i-1);
            // compare registered has and calculated hash:
            if(!currentBlock.hash.equals(currentBlock.calculateHash())){
                System.out.println("Current hashes not equal");
                return false;
            }
            // compare previous hash and registered previous hash
            if (!previousBlock.hash.equals(currentBlock.previousHash)){
                System.out.println("Previous Hashes not equal");
                return false;    
            }
            // check if hash is not solved
            if(!currentBlock.hash.substring(0,difficulty).equals(hashTarget)){
                System.out.println("This block hasnt been mined");
                return false;
            }
        }
        return true;
    }
}

/*
 * now i have a mess of files, i dont know how to organize them and also,
 * https://medium.com/programmers-blockchain/importing-gson-into-eclipse-ec8cf678ad52
 * here is the library but i dodnt knwo how to install the library in my machine
 * 
 * how can i use thelibrary that i have already downloaded so i can avoid going with gradle?
 * 
 * how can i move th files and have a folder structure
 * 
 */