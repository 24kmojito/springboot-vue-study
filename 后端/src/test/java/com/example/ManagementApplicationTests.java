package com.example;

import com.example.common.lang.Result;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@SpringBootTest
class ManagementApplicationTests {

    @Test
    void forText() {
        Process proc;
        try {
            String[] args = new String[] { "python", "C:\\Users\\jty\\Desktop\\view1.py", "C:\\Users\\jty\\Desktop\\movies.xls" };

            proc = Runtime.getRuntime().exec(args);// 执行py文件
            //用输入输出流来截取结果
            BufferedReader in = new BufferedReader(new InputStreamReader(proc.getInputStream()));
            String line = null;
            line = in.readLine();
            System.out.println("line的值为"+line);
            if (line != null) {
//                System.out.println(line);
                System.out.println(Result.succ(line));
            }
//            return Result.succ(line);

            in.close();
            proc.waitFor();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(Result.fail("未成功获得可执行python文件生成的html文件"));
    }

//    @Test
//    void forText() {
//        System.out.println("执行....");
//        try {
//            Process process=Runtime.getRuntime().exec(para);
//
////接收子进程的I/O操作（py脚本执行的输入输出）
//　　　　　　　//process.getInputStream() 将子进程的i/o流重定向到父进程，由父进程控制子进程的i/o
//            BufferedReader reader=new BufferedReader(new InputStreamReader(process.getInputStream()，"GBK")); 　　//windows下编码GBK防止java读取乱码　　　　　　　　
//　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　
//            String line;
//            while((line=reader.readLine())!=null) {
//                System.out.println(line);
//            }
//            reader.close();
//　　　　　　　process.destory();   //结束子进程
//            //process.waitFor();  //使子进程等待，可能会引起阻塞-待分析（输入输出导致缓存满？）
//        } catch (IOException | InterruptedException e) {
//            e.printStackTrace();
//        }
//        System.out.println("结束....");
//
//
//    }
}
