package com.company;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class SimpleDB {
    String filename = "data.db";
    Map<String, String> hm = new HashMap<>();

    public SimpleDB(){
        hm = loadDB();
    }

    public void insertDB(String index, String value){
        hm.put(index, value);

        try {
            FileOutputStream out = new FileOutputStream(filename);
            ObjectOutputStream os = new ObjectOutputStream(out);
            //os.writeUTF(index);
            //os.writeUTF(value);
            os.writeObject(hm);

            os.close();
            out.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getDB(String index){
        return hm.get(index);
    }

    private Map<String, String> loadDB(){
        Map<String, String> map = new HashMap<>();

        try {
            FileInputStream in = new FileInputStream(filename);
            ObjectInputStream is = new ObjectInputStream(in);
            /*while (is.available() != 0){
                String index = is.readUTF();
                String value = is.readUTF();
                System.out.println("putting " + index + " and " + value);
                map.put(index, value);
            }*/
            return (Map<String,String>) is.readObject();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (EOFException ex1) {

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


        return map;
    }


}
