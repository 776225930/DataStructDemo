package com.jhao.generictype02.demo1;

import com.sun.org.apache.regexp.internal.RE;

/**
 * @author JiangHao
 * @date 2020/4/25
 * @describe
 */
public class Test {


    public static void main(String[] args) {
//        scene01();
        scene02();
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
        xiaoLi.eat((Banana) rawPlate.get());//报错Apple cannot be cast to Banana
    }

    public static void scene02() {
        XiaoLi xiaoLi = new XiaoLi();
        XiaoMing xiaoMing = new XiaoMing();
        XiaoMingMa xiaoMingMa = new XiaoMingMa();

        //假如这个盘子是智能的，能主动检测你往里面装什么
        //小明给的这个盘子只能装香蕉,方法泛型类型推断
        AIPlate<Banana> aiPlate = xiaoMing.<Banana>getAIPlate();
        //小明妈就不会装错了
        xiaoMingMa.addFruitToAI(aiPlate);
        //小丽吃香蕉
        xiaoLi.eat(aiPlate.get());//bingo
    }
}
