package com.kll.gasPipeline.analysis.utils;

import com.kll.gasPipeline.analysis.pojos.Point3D;

public class Point3DUtil {
    public static double pointToLine(Point3D point1, Point3D point2, Point3D p) {
        double x1 = point1.x;
        double x2 = point2.x;
        double y1 = point1.y;
        double y2 = point2.y;
        double a = y1 - y2;
        double b = x2 - x1;
        double c = x1 * y2 - x2 * y1;
        double dist = Math.abs((a * p.x + b * p.y + c)/Math.sqrt(Math.pow(a,2)+Math.pow(b,2)));
        return dist;
    }

    /***
     * 计算点与点之间的距离
     * @param point1    点1
     * @param point2    点2
     * @return 距离
     */
    public double lineSpace(Point3D point1, Point3D point2) {
        double x1 = point1.x;
        double x2 = point2.x;
        double y1 = point1.y;
        double y2 = point2.y;
        double lineLength = 0;
        lineLength = Math.sqrt((x1 - x2) * (x1 - x2) + (y1 - y2)
                * (y1 - y2));
        return lineLength;
    }
}
