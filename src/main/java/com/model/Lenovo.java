package com.model;

public class Lenovo implements SaleComputer {
    //真实类要实现接口

    public String sale(double money) {//定义了含参一个方法

        System.out.println("花了" + money + "元买了一台联想电脑...");
        return "联想电脑";
    }

    public void show() {//定义了一个无参方法
        System.out.println("展示电脑....");
    }
}
