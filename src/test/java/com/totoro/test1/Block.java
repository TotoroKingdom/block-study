package com.totoro.test1;

import java.util.Date;

/**
 * @author:totoro
 * @createDate:2022/11/14
 * @description: 定义区块链
 */
public class Block {

    //哈希
    public String hash;

    //前置区块链哈希
    public String previousHash;

    //数据
    private String data;

    //时间戳
    private long timeStamp;

    private int nonce;

    public Block(String data, String previousHash){
        this.data = data;
        this.previousHash = previousHash;
        this.timeStamp = new Date().getTime();
        this.hash = calculateHash();
    }

    //计算hash
    public String calculateHash(){
        String calculateHash = HashUtil.sha256(previousHash
                + Long.toString(timeStamp)
                + Integer.toString(nonce)
                + data);
        return calculateHash;
    }

    public void mineBlock(int difficulty){
        String target = new String(new char[difficulty]).replace('\0', '0');
        while (!hash.substring(0,difficulty).equals(target)){
            nonce ++;
            hash = calculateHash();
        }
        System.out.println("Block Mined: "+hash);
    }
}
