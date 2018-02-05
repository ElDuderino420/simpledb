package com.company;

public class Main {

    public static void main(String[] args) {
	// write your code here


        SimpleDB sdb = new SimpleDB();
        sdb.insertDB("1","value1");
        sdb.insertDB("2","value2efgdfhgj");
        sdb.insertDB("3","value3sdfgdhjfshklhghdgrfhdghkj,llfkhjgjfhfgdf");
        System.out.println(sdb.getDB("1"));
        System.out.println(sdb.getDB("2"));
        System.out.println(sdb.getDB("3"));
        //System.out.println(sdb.getDB("4"));
        //System.out.println(sdb.getDB("5"));
        //System.out.println(sdb.getDB("6"));
        //System.out.println(sdb.getDB("7"));
        //System.out.println(sdb.getDB("8"));
    }
}
