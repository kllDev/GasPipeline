package com.kll.gasPipeline;

import com.kll.gasPipeline.analysis.pojos.Point3D;
import org.locationtech.jts.geom.Coordinate;

public class Test {
    public static void main(String[] args) {
//        Point3D point1 = new Point3D(new Coordinate(5126.554, 38573.878));
        Point3D point1 = new Point3D(new Coordinate(5068.735, 38589.907));
        Point3D point2 = new Point3D(new Coordinate(5054.556, 38593.984));
//        Point3D point = new Point3D(new Coordinate(5063.845, 38591.349));
//        Point3D point = new Point3D(new Coordinate(5064.158, 38591.246));
        Point3D point = new Point3D(new Coordinate(5063.955, 38591.088));
//        Point3D point = new Point3D(new Coordinate(5064.614, 38591.110));
        double v = (point.x - point1.getX()) * (point1.getY() - point2.getY()) - (point1.getX() - point2.getX())
                * (point.y - point1.getY());
        System.out.println(123);
    }
}
