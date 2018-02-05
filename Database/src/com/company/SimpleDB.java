package com.company;

import java.io.*;
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
        String ele = index + "#" + value;

        try {
            FileOutputStream out = new FileOutputStream(filename, true);

            byte[] write = ele.getBytes("US-ASCII");

            StringBuilder binary = new StringBuilder();
            for (byte b : write) {
                int val = b;
                for (int i = 0; i < 8; i++){
                    binary.append((val & 128) == 0 ? 0 : 1);
                    val <<= 1;
                }
            }

            System.out.println(binary);
            int tmp = write.length + (int) placement;
            hm.put(index, tmp);
            placement = tmp;

            out.write(binary.toString().getBytes("US-ASCII"));

            //ObjectOutputStream os = new ObjectOutputStream(out);
            //os.writeUTF(index);
            //os.writeUTF(value);
            //os.writeObject(hm);

            //os.close();
            out.close();

        } catch (FileNotFoundException e) {

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Integer getDB(String index){

        int hello = 1;
        try {
            FileInputStream in = new FileInputStream(filename);

            in.skip(hm.get(index));
            System.out.println(hm.get(index));
            hello = in.read();
            in.close();



        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (EOFException ex1) {

        } catch (IOException e) {
            e.printStackTrace();
        }

        return hello;
    }

    private Map<String, Integer> loadDB(){
        Map<String, Integer> map = new HashMap<>();

        try {
            FileInputStream in = new FileInputStream(filename);

            placement = in.getChannel().size()/8;

            in.close();

            return map;

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (EOFException ex1) {

        } catch (IOException e) {
            e.printStackTrace();
        }


        return map;
    }


}
