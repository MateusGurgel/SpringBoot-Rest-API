package com.gurgel.apigateway.Utils;

public class BasicMath {

    public static double sum(double num, double num2){
        return num + num2;
    }
    public static double mul(double num, double num2){
        return num * num2;
    }
    public static double sub(double num, double num2){
        return num - num2;
    }

    public static double div(double num, double num2){
        return num / num2;
    }

    public static double mean(double num, double num2){
        return (num + num2) / 2;
    }

    public static double sqrt(double num){
        return Math.sqrt(num);
    }
}
