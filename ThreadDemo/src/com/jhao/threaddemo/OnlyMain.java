package com.jhao.threaddemo;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;

/**
 * @author JiangHao
 * @date 2020/4/12
 * @describe java的多线程无处不在
 */
public class OnlyMain {

    public static void main(String[] args) {
        //虚拟机线程管理的接口
        ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();
        //获取线程信息
        for (ThreadInfo threadInfo:threadMXBean.dumpAllThreads(false,false)){
            System.out.println("["+threadInfo.getThreadId()+"]"+" "
                    +threadInfo.getThreadName());
        }

    }
}
