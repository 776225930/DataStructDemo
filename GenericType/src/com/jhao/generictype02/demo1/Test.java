package com.jhao.generictype02.demo1;

import com.sun.org.apache.regexp.internal.RE;

/**
 * @author JiangHao
 * @date 2020/4/25
 * @describe
 */
public class Test {


    public static void main(String[] args) {
        scene01();
    }


    public static void scene01() {
        XiaoLi xiaoLi = new XiaoLi();
        XiaoMing xiaoMing = new XiaoMing();
        XiaoMingMa xiaoMingMa = new XiaoMingMa();

        //小明给了他妈一个盘子RawPlate,什么水果都能放
        RawPlate rawPlate = xiaoMing.getPlate();
        //小明妈去装水果
        xiaoMingMa.addFruit(rawPlate);
        //小丽吃香蕉
        xiaoLi.eat((Banana) rawPlate.get());

    }
}
