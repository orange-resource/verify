package com.orange.verify.builder.config;

import java.util.LinkedList;
import java.util.List;

public class TableNameList {

    private String projectName;
    private static final List<String> tableNameList = new LinkedList<>();

    public static void add(String tableName) {
        tableNameList.add(tableName);
    }

    public static List<String> getList() {
        return tableNameList;
    }

}
