package com.api;

import java.nio.file.Path;
import java.nio.file.Paths;

public class MainTest {

    public static void main(String args[]){

        Path path = Paths.get(System.getProperty("user.dir"));

        Utils.executeValidation(path);

    }

}
