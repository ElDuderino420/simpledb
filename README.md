# simpledb
JAVA based

insertDB method adds index and value to database.

getDB method gets value from binary file from index.

runs loadDB in constructor which loads the indexes into the hashmap.

```Java
        SimpleDB sdb = new SimpleDB();
        sdb.insertDB("1","value1");
        sdb.insertDB("2","value2efgdfhgj");
        sdb.insertDB("3","value3sdfgdhjfshklhghdgrfhdghkj,llfkhjgjfhfgdf");
        System.out.println(sdb.getDB("1"));
        System.out.println(sdb.getDB("2"));
        System.out.println(sdb.getDB("3"));

```
