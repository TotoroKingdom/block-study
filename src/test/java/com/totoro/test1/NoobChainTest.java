package com.totoro.test1;

import java.util.ArrayList;
import com.google.gson.GsonBuilder;

/**
 * @author:totoro
 * @createDate:2022/11/14
 * @description:
 */
public class NoobChainTest {

    public static ArrayList<Block> blocks = new ArrayList<>();
    public static int difficulty = 5;

    public static void main(String[] args) {

        Block firstBlock = new Block("First Block", "0");
        blocks.add(firstBlock);
        System.out.println("firstBlock:  "+firstBlock.hash);
        blocks.get(0).mineBlock(difficulty);


        Block secondBlock = new Block("Second Block", firstBlock.hash);
        blocks.add(secondBlock);
        System.out.println("secondBlock:  "+secondBlock.hash);
        blocks.get(1).mineBlock(difficulty);

        Block threeBlock = new Block("Third Block", secondBlock.hash);
        blocks.add(threeBlock);
        System.out.println("thirdBlock:  "+threeBlock.hash);
        blocks.get(2).mineBlock(difficulty);







        String blockJson = new GsonBuilder().setPrettyPrinting().create().toJson(blocks);
        System.out.println(blockJson);

        isChainValid();
        blocks.set(2,new Block("123",firstBlock.hash));
        isChainValid();

    }

    public static Boolean isChainValid(){

        Block currentBlock;
        Block previousBlock;
        String hashTarget = new String(new char[difficulty]).replace('\0', (char) 0);

        for (int i = 1; i < blocks.size(); i++) {
            currentBlock = blocks.get(i);
            previousBlock = blocks.get(i - 1);

            if (!currentBlock.hash.equals(currentBlock.calculateHash())){
                System.out.println("当前哈希不相等");
                return  false;
            }

            if (!previousBlock.hash.equals(currentBlock.previousHash)){
                System.out.println("前置哈希不相等");
                return false;
            }


            if (!currentBlock.hash.substring(0, difficulty).equals(hashTarget)){
                System.out.println("挖矿失败");
                return false;
            }

        }
        System.out.println("校验通过");
        return true;
    }
}
