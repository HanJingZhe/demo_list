package com.qtgm.peng.t0801_system.utils;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.NumberFormat;
import java.util.HashMap;
import java.util.Map;

/**
 * @author peng_wang
 * @date 2019/8/1
 */
public class CPUUtil {

    /**
     * 通过获取cpu一行的数据，即可进行CPU占用率的计算。我们会用到的数据有:
     * - user(21441),从系统启动开始累计到当前时刻，处于用户态的运行时间，不包含nice值为负的进程。
     * - nice(3634),从系统启动开始累计到当前时刻，nice值为负的进程所占用的CPU时间。
     * - system(13602),从系统启动开始累计到当前时刻，处于核心态的运行时间。
     * - idle(818350),从系统启动开始累计到当前时刻，除IO等待时间以外的其它等待时间。
     * - iowait(3535),从系统启动开始累计到当前时刻，IO等待时间。
     * - irq(2),从系统启动开始累计到当前时刻，硬中断时间。
     * - softirq(99),从系统启动开始累计到当前时刻，软中断时间。
     * 总的CPU占用率的计算方法为：采样两个足够短的时间间隔的CPU快照，
     * CPU占用率 = 100*((totalTime2-totalTime1)-(idle2-idle1))/(totalTime2-totalTime1)。
     */

    //获取cpu使用率
    public static String getCpuRate() {

        if (android.os.Build.VERSION.SDK_INT >= 26) {//8.0以上没有权限读取
            return "0%";
        }else{
            //采样第一次cpu信息快照
            Map<String, String> map1 = getMap();
            //总的CPU时间totalTime = user+nice+system+idle+iowait+irq+softirq
            if(map1 == null || map1.size() < 1) return "0%";
            long totalTime1 = getTime(map1);
            System.out.println(totalTime1 + "...........................totalTime1.");
            //获取idleTime1
            long idleTime1 = Long.parseLong(map1.get("idle"));
            System.out.println(idleTime1 + "...................idleTime1");
            //间隔360ms
            try {
                Thread.sleep(360);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //采样第二次cpu信息快照
            Map<String, String> map2 = getMap();
            long totalTime2 = getTime(map2);
            System.out.println(totalTime2 + "............................totalTime2");
            //获取idleTime1
            long idleTime2 = Long.parseLong(map2.get("idle"));
            System.out.println(idleTime2 + "................idleTime2");

            //得到cpu的使用率
            double cpuRate = 100 * ((totalTime2 - totalTime1) - (idleTime2 - idleTime1)) / (totalTime2 - totalTime1);

            NumberFormat nf = NumberFormat.getPercentInstance();
            nf.setMaximumFractionDigits(2);
            double number = cpuRate / 100;
            return nf.format(number);
        }
    }

    //得到cpu信息
    public static long getTime(Map<String, String> map) {
        long totalTime = Long.parseLong(map.get("user")) + Long.parseLong(map.get("nice"))
                + Long.parseLong(map.get("system")) + Long.parseLong(map.get("idle"))
                + Long.parseLong(map.get("iowait")) + Long.parseLong(map.get("irq"))
                + Long.parseLong(map.get("softirq"));
        return totalTime;
    }

    //采样CPU信息快照的函数，返回Map类型
    public static Map<String, String> getMap() {
        String[] cpuInfos = null;
        //读取cpu信息文件
        BufferedReader br = null;
        try {
            br = new BufferedReader(new InputStreamReader(new FileInputStream("/proc/stat")));
            String load = br.readLine();
            br.close();
            cpuInfos = load.split(" ");
        } catch (FileNotFoundException e) {
            System.out.println("文件未找到");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("线程异常");
            e.printStackTrace();
        }
        Map<String, String> map = new HashMap<>();
        if(cpuInfos != null){
            map.put("user", cpuInfos[2]);
            map.put("nice", cpuInfos[3]);
            map.put("system", cpuInfos[4]);
            map.put("idle", cpuInfos[5]);
            map.put("iowait", cpuInfos[6]);
            map.put("irq", cpuInfos[7]);
            map.put("softirq", cpuInfos[8]);
        }
        return map;
    }

}
