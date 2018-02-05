package com.company;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class SimpleDB {
    String filename = "data.db";
    Map<String, Integer> hm = new HashMap<>();
    long placement = 0;


    public SimpleDB(){
        hm = loadDB();
    }

    public void insertDB(String index, String value){
        String ele = index + ";" + value;
        byte[] write = ele.getBytes();

        hm.put(index, (int) placement);
        System.out.println(placement);
        placement += write.length+8;
        System.out.println("len" + write.length);
        //System.out.println(ele.getBytes());
        write(ele);


    }

    public String getDB(String index){
        int off = hm.get(index);

        try{
            FileInputStream fis = new FileInputStream(filename);
            System.out.println(fis.available());
            fis.skip(off);
            ObjectInputStream input = new ObjectInputStream(fis);
            System.out.println("ava " + input.available());
            System.out.println(input.skipBytes(0));
            String k = input.readUTF();
            System.out.println("value: " + k);


            input.close();
            fis.close();

            return k;


        }catch(Exception e){
            e.printStackTrace();
        }

        return "nop";
    }

    private Map<String, Integer> loadDB(){
        Map<String, Integer> map = new HashMap<>();

        try {
            FileInputStream in = new FileInputStream(filename);

            placement = in.getChannel().size()/8;

            in.close();

            return map;

        } catch (FileNotFoundException e) {
            //e.printStackTrace();
        } catch (EOFException ex1) {

        } catch (IOException e) {
            e.printStackTrace();
        }


        return map;
    }



    void write(String aInput){
        System.out.println("Writing to binary file...");
        try {
            ObjectOutputStream out = null;
            try {
                out = new ObjectOutputStream(new FileOutputStream(filename, true));
                //out.writeByte(aInput[0]);
                out.writeUTF(aInput);
                //System.out.printf("%02x ", aInput[0]);
                //System.out.println(Byte.parseByte("tes tytest"));
            }
            finally {
                out.flush();
                out.close();
            }
        }
        catch(FileNotFoundException ex){
            System.out.println("File not found.");
        }
        catch(IOException ex){
            System.out.println(ex);
        }
    }




}
