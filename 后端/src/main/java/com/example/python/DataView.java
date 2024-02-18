package com.example.python;


import com.example.common.lang.Result;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class DataView {

    public static Result excView(String url) {

        Process proc;
        try {
            String[] args = new String[] { "D:\\python\\python.exe", "C:\\Users\\jty\\Desktop\\中南杯\\fmanagement\\src\\main\\java\\com\\example\\python\\view1.py", url };

            proc = Runtime.getRuntime().exec(args);// 执行py文件
            //用输入输出流来截取结果
            System.out.println(proc.exitValue());
            System.out.println(proc.waitFor());
            BufferedReader in = new BufferedReader(new InputStreamReader(proc.getInputStream()));
            String line = null;
            line = in.readLine();
            System.out.println("line的值为"+line);
            if (line != null) {
//                System.out.println(line);
                return Result.succ(line);
            }
//            return Result.succ(line);

            in.close();
            proc.waitFor();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return Result.fail("未成功获得可执行python文件生成的html文件");


    }// main

}
