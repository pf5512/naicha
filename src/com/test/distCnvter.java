package com.test;
public class distCnvter {
    private final static double PI = 3.14159265358979323; // 圆周率
    private final static double R = 6371229; // 地球的半径

    public static double getDistance(double longt1, double lat1, double longt2,double lat2) {
        double x, y, distance;
        x = (longt2 - longt1) * PI * R
                * Math.cos(((lat1 + lat2) / 2) * PI / 180) / 180;
        y = (lat2 - lat1) * PI * R / 180;
        distance = Math.hypot(x, y);
        return distance;
    }
    public static void main(String[] args) {
    	//"116.3699110000","39.9936430000","113.93317937850907","22.537005849692"
    System.out.println(distCnvter.getDistance(116.3699110000, 39.9936430000, 113.93317937850907, 22.537005849692));
	}
}