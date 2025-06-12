package com.mefiddzy.lmod.util.classVariables;

public class Interval {
    private int startPoint, endPoint;

    public boolean between(int number) {
        return (number <= this.endPoint && number >= this.startPoint);
    }

    public int getStartPoint() {
        return startPoint;
    }

    public int getEndPoint() {
        return endPoint;
    }

    public void print() {
        System.out.println('{' + this.startPoint + "->" + this.endPoint + '}');
    }

    public void setStartPoint(int startPoint) {
        this.startPoint = startPoint;
    }

    public void setEndPoint(int endPoint) {
        this.endPoint = endPoint;
    }

    public Interval(int startPoint, int endPoint) {
        this.endPoint = endPoint;
        this.startPoint = startPoint;
    }
}
