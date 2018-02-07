package com.company;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class SimpleDB {
    String filename = "data.db";
    Map<String, String> hm = new HashMap<>();
    long placement = 0;


    public SimpleDB(){
        hm = loadDB();
    }

    public void insertDB(String index, String value){
        String ele = index + ";" + value;
        byte[] write = ele.getBytes();

        hm.put(index, placement + "," + write.length);
        //System.out.println(placement);
        placement += write.length + 1;
        System.out.println("len" + write.length);
        //System.out.println(ele.getBytes());
        write(ele);


    }

    public String getDB(String index){
        //System.out.println("get " + hm.get(index));
        int off = Integer.parseInt(hm.get(index).split(",")[0]);
        int len = Integer.parseInt(hm.get(index).split(",")[1]);
        System.out.println(off + " : " + len);
        try{
            FileInputStream fis = new FileInputStream(filename);
            //System.out.println(fis.available());
            //fis.skip(off);
            BufferedInputStream input = new BufferedInputStream(fis);
            //System.out.println("ava " + input.available());
            //System.out.println(input.skipBytes(0));
            byte[] read = new byte[len];
            input.skip(off);
            input.read(read, 0, len);
            String k = new String(read);
            //System.out.println("value: " + k);


            input.close();
            fis.close();

            return k;


        }catch(Exception e){
            e.printStackTrace();
        }

        return "nop";
    }

    private Map<String, String> loadDB(){
        Map<String, String> map = new HashMap<>();

        try {
            File f = new File(filename);
            FileInputStream in = new FileInputStream(filename);

            BufferedInputStream oi = new BufferedInputStream(in);
            byte[] b = new byte[(int) f.length()];
            oi.read(b);
            String everything = new String(b);
            String[] arr = everything.split("\n");
            for (String e : arr) {
                int leng = e.getBytes().length;
                System.out.println(leng);

                String index = e.split(";")[0];
                //System.out.println(placement + "," + leng);
                map.put(index, placement + "," + leng);
                placement = placement + e.getBytes().length + 1;
                //System.out.println(index);
                //System.out.println(hm.get("1"));
            }
            //oi.readFully(b);
            //System.out.println(new String(b));
            //System.out.println();

            //placement = in.getChannel().size()/8;

            in.close();
            return map;

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (EOFException ex1) {
            ex1.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        return map;
    }



    void write(String aInput){
        System.out.println("Writing to binary file...");
        try {
            BufferedOutputStream out = null;
            try {
                out = new BufferedOutputStream(new FileOutputStream(filename, true));
                //out.writeByte(aInput[0]);
                byte[] b = (aInput + "\n").getBytes();
                out.write(b);
                //out.writeUTF(aInput);
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
