package com.qtgm.peng.t0801_system.utils;

import android.util.Log;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileFilter;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author peng_wang
 * @date 2019/8/1
 */
public class CPU2 {


    private static long cpu0_totalJiffies[] = new long[2];
    public static long cpu1_totalJiffies[] = new long[2];
    private static long cpu2_totalJiffies[] = new long[2];
    private static long cpu3_totalJiffies[] = new long[2];
    private static long cpu0_totalIdle[] = new long[2];
    private static long cpu1_totalIdle[] = new long[2];
    private static long cpu2_totalIdle[] = new long[2];
    private static long cpu3_totalIdle[] = new long[2];

    private static double cpu0_rate;
    private static double cpu1_rate;
    private static double cpu2_rate;
    private static double cpu3_rate;

    public static void getCPURateDesc() {
        String path = "/proc/stat";// 系统CPU信息文件
        //int firstCPUNum=0;//设置这个参数，这要是防止两次读取文件获知的CPU数量不同，导致不能计算。这里统一以第一次的CPU数量为基准
        FileReader fileReader = null;
        BufferedReader bufferedReader = null;
        Pattern pattern = Pattern.compile(" [0-9]+");
        for (int i = 0; i < 2; i++) {
            cpu0_totalJiffies[i] = 0;
            cpu1_totalJiffies[i] = 0;
            cpu2_totalJiffies[i] = 0;
            cpu3_totalJiffies[i] = 0;

            cpu0_totalIdle[i] = 0;
            cpu1_totalIdle[i] = 0;
            cpu2_totalIdle[i] = 0;
            cpu3_totalIdle[i] = 0;
            try {
                fileReader = new FileReader(path);
                bufferedReader = new BufferedReader(fileReader, 8192);
                String str;
                while ((str = bufferedReader.readLine()) != null) {
                    if (str.toLowerCase().startsWith("cpu0")) {
                        int index = 0;
                        Matcher matcher = pattern.matcher(str);
                        while (matcher.find()) {
                            try {
                                long tempJiffies = Long.parseLong(matcher.group(0).trim());
                                cpu0_totalJiffies[i] += tempJiffies;
                                if (index == 3) {//空闲时间为该行第4条栏目
                                    cpu0_totalIdle[i] += tempJiffies;
                                }
                                index++;
                            } catch (NumberFormatException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                    if (str.toLowerCase().startsWith("cpu1")) {
                        int index = 0;
                        Matcher matcher = pattern.matcher(str);
                        while (matcher.find()) {
                            try {
                                long tempJiffies = Long.parseLong(matcher.group(0).trim());
                                cpu1_totalJiffies[i] += tempJiffies;
                                if (index == 3) {//空闲时间为该行第4条栏目
                                    cpu1_totalIdle[i] += tempJiffies;
                                }
                                index++;
                            } catch (NumberFormatException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                    if (str.toLowerCase().startsWith("cpu2")) {
                        int index = 0;
                        Matcher matcher = pattern.matcher(str);
                        while (matcher.find()) {
                            try {
                                long tempJiffies = Long.parseLong(matcher.group(0).trim());
                                cpu2_totalJiffies[i] += tempJiffies;
                                if (index == 3) {//空闲时间为该行第4条栏目
                                    cpu2_totalIdle[i] += tempJiffies;
                                }
                                index++;
                            } catch (NumberFormatException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                    if (str.toLowerCase().startsWith("cpu3")) {
                        int index = 0;
                        Matcher matcher = pattern.matcher(str);
                        while (matcher.find()) {
                            try {
                                long tempJiffies = Long.parseLong(matcher.group(0).trim());
                                cpu3_totalJiffies[i] += tempJiffies;
                                if (index == 3) {//空闲时间为该行第4条栏目
                                    cpu3_totalIdle[i] += tempJiffies;
                                }
                                index++;
                            } catch (NumberFormatException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                    if (i == 0) {
                        try {//暂停50毫秒，等待系统更新信息。
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } finally {
                if (bufferedReader != null) {
                    try {
                        bufferedReader.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        if (cpu0_totalJiffies[0] > 0 && cpu0_totalJiffies[1] > 0 && cpu0_totalJiffies[0] != cpu0_totalJiffies[1]) {
            cpu0_rate = 1.0 * ((cpu0_totalJiffies[1] - cpu0_totalIdle[1]) - (cpu0_totalJiffies[0] - cpu0_totalIdle[0])) / (cpu0_totalJiffies[1] - cpu0_totalJiffies[0]);
        }
        if (cpu1_totalJiffies[0] > 0 && cpu1_totalJiffies[1] > 0 && cpu1_totalJiffies[0] != cpu1_totalJiffies[1]) {
            cpu1_rate = 1.0 * ((cpu1_totalJiffies[1] - cpu1_totalIdle[1]) - (cpu1_totalJiffies[0] - cpu1_totalIdle[0])) / (cpu1_totalJiffies[1] - cpu1_totalJiffies[0]);
        }
        if (cpu2_totalJiffies[0] > 0 && cpu2_totalJiffies[1] > 0 && cpu2_totalJiffies[0] != cpu2_totalJiffies[1]) {
            cpu2_rate = 1.0 * ((cpu2_totalJiffies[1] - cpu2_totalIdle[1]) - (cpu2_totalJiffies[0] - cpu2_totalIdle[0])) / (cpu2_totalJiffies[1] - cpu2_totalJiffies[0]);
        }
        if (cpu3_totalJiffies[0] > 0 && cpu3_totalJiffies[1] > 0 && cpu3_totalJiffies[0] != cpu3_totalJiffies[1]) {
            cpu3_rate = 1.0 * ((cpu3_totalJiffies[1] - cpu3_totalIdle[1]) - (cpu3_totalJiffies[0] - cpu3_totalIdle[0])) / (cpu3_totalJiffies[1] - cpu3_totalJiffies[0]);
        }

        //  return String.format("cpu:%.2f",rate);
        Log.d("CpuUtils", "zrx---- cpu0_rate:" + cpu0_rate + ", cpu1_rate:" + cpu1_rate + ", cpu2_rate:" + cpu2_rate + ", cpu3_rate:" + cpu3_rate);
    }

    //计算总的cpu使用率
    public static String getCPURateDesc_All() {
        String path = "/proc/stat";// 系统CPU信息文件
        long totalJiffies[] = new long[2];
        long totalIdle[] = new long[2];
        int firstCPUNum = 0;//设置这个参数，这要是防止两次读取文件获知的CPU数量不同，导致不能计算。这里统一以第一次的CPU数量为基准
        FileReader fileReader = null;
        BufferedReader bufferedReader = null;
        Pattern pattern = Pattern.compile(" [0-9]+");
        for (int i = 0; i < 2; i++) {
            totalJiffies[i] = 0;
            totalIdle[i] = 0;
            try {
                fileReader = new FileReader(path);
                bufferedReader = new BufferedReader(fileReader, 8192);
                int currentCPUNum = 0;
                String str;
                while ((str = bufferedReader.readLine()) != null && (i == 0 || currentCPUNum < firstCPUNum)) {
                    if (str.toLowerCase().startsWith("cpu")) {
                        currentCPUNum++;
                        int index = 0;
                        Matcher matcher = pattern.matcher(str);
                        while (matcher.find()) {
                            try {
                                long tempJiffies = Long.parseLong(matcher.group(0).trim());
                                totalJiffies[i] += tempJiffies;
                                if (index == 3) {//空闲时间为该行第4条栏目
                                    totalIdle[i] += tempJiffies;
                                }
                                index++;
                            } catch (NumberFormatException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                    if (i == 0) {
                        firstCPUNum = currentCPUNum;
                        try {//暂停50毫秒，等待系统更新信息。
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } finally {
                if (bufferedReader != null) {
                    try {
                        bufferedReader.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        double rate = -1;
        if (totalJiffies[0] > 0 && totalJiffies[1] > 0 && totalJiffies[0] != totalJiffies[1]) {
            rate = 1.0 * ((totalJiffies[1] - totalIdle[1]) - (totalJiffies[0] - totalIdle[0])) / (totalJiffies[1] - totalJiffies[0]);
        }

        Log.d("CpuUtils", "zrx---- cpu_rate:" + rate);
        return String.format("cpu:%.2f", rate);
    }


    //获取核心数
    public static int getNumCores() {
        //Private Class to display only CPU devices in the directory listing
        class CpuFilter implements FileFilter {
            @Override
            public boolean accept(File pathname) {
                //Check if filename is "cpu", followed by a single digit number
                if (Pattern.matches("cpu[0-9]", pathname.getName())) {
                    return true;
                }
                return false;
            }
        }

        try {
            //Get directory containing CPU info
            File dir = new File("/sys/devices/system/cpu/");
            //Filter to only list the devices we care about
            File[] files = dir.listFiles(new CpuFilter());
            Log.d("王鹏", "CPU Count: " + files.length);
            //Return the number of cores (virtual CPU devices)
            return files.length;
        } catch (Exception e) {
            //Print exception
            Log.d("王鹏", "CPU Count: Failed.");
            e.printStackTrace();
            //Default to return 1 core
            return 1;
        }
    }


}
