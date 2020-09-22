package com.yaorange.Controller.job;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @ClassNameBackupMySQLJob
 * @Descripiotn //TODO
 * @Author luokun
 * @Date 2020/8/19 22:37
 * @Version 1.0
 **/
public class BackupMySQLJob {
    private final String DB_NAME = "crm";
    private final String BACKUP_PATH = "E:\\CRM_DB_BACKUP\\";

    public void backup() {
        System.out.println(new Date().toLocaleString() + ":开始备份......");
        long start = System.currentTimeMillis();
        //准备命令，注意空格
        String cmd = "mysqldump -uroot -proot " + DB_NAME;
        try {
            //创建Runtime
            Runtime runtime = Runtime.getRuntime();
            //调用方法获取,(相当于执行cmd命令)
            Process process = runtime.exec(cmd);
            //调用方法获取生成的sql文件输入流
            InputStream inputStream = process.getInputStream();
            //转换字节流为字符流
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            //准备定义输出文件名
            //定义目录名
            String dirName = new SimpleDateFormat("yyyy-MM").format(new Date());
            //定义文件后缀名
            String fileName = new SimpleDateFormat("yyyy-MM-dd-HHmmss").format(new Date());
            fileName = DB_NAME + "-" + fileName + ".sql";
            //创建File对象
            File file = new File(BACKUP_PATH + dirName, fileName);
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
            //数据输出
            //准备输出流
            PrintWriter writer = new PrintWriter(file);
            //通过输入流循环读取数据，使用输出流输出
            String lineData = null;
            while ((lineData = bufferedReader.readLine()) != null) {
                //换行输出
                writer.println(lineData);
            }
            writer.flush();
            writer.close();

            bufferedReader.close();
            inputStreamReader.close();
            inputStream.close();

            //TODO 后期基础日志框架后，应该将备份信息记录日志
            //日志记录：备份耗时，备份文件大小
            long end = System.currentTimeMillis();
            long times = end - start;
            long fileSize = file.length();
            System.out.println(new Date().toLocaleString() + ":备份结束......");
            System.out.println("文件名：" + fileName);
            System.out.println("文件大小：" + (fileSize / 1024.0));
            System.out.println("总共耗时：" + (times / 1000) + "秒");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
