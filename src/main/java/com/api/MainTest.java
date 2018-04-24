package com.api;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class MainTest {

    public static void main(String args[]){

        ArrayList<String> ignoreList = new ArrayList<>();

        ignoreList.add("RULE0001");

        Path path = Paths.get(System.getProperty("user.dir"));

        Utils.executeValidation(path, ignoreList);

    }

}
