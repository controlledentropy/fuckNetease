package com.fucknetease;

import java.io.*;

/*
*@description
*@author Communist_CM
*@date 6/9/2021
*@version 
*@Modified by ?
*/
public class NCnc2mp3 {

    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("请输入netease cloud music缓存文件夹绝对路径");
            return;
        }

        DataInputStream dis = null;
        DataOutputStream dos = null;

        String inNcDirPath = args[0];
        File file = new File(inNcDirPath);
        if (!file.exists()) {
            System.out.println("输入路径错误或路径不存在");
            return;
        }
        File[] ucList = file.listFiles();
        for(File f : ucList) {
            if(f.getName().contains("uc!")) {
                convert(f);
            }
        }
        return;

    }
    private static void convert(File ucfile) {
            String outfileName = String.format("%s\\convert\\%s.mp3", ucfile.getParent(), ucfile.getName());
            File inFile = ucfile;
            File outFile = new File(outfileName);
            outFile.mkdirs();
            DataInputStream inStream = null;
            DataOutputStream outStream = null;
        try {
            inStream = new DataInputStream(new FileInputStream(inFile));
            outStream = new DataOutputStream(new FileOutputStream(outFile));
            byte[] b = new byte[1024];
            int len;
            while ((len = inStream.read(b)) != -1) {
                for (int i = 0; i < len; i++) {
                    b[i] ^= 0xa3;
                }
                outStream.write(b, 0, len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (outStream != null) {
                try {
                    outStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (inStream != null) {
                try {
                    inStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}