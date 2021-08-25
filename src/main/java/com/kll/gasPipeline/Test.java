package com.kll.gasPipeline;

import com.kll.gasPipeline.analysis.pojos.Point3D;
import org.locationtech.jts.geom.Coordinate;

import java.util.ArrayList;

public class Test {
    public static void main(String[] args) {
        ArrayList<Point3D> objects = new ArrayList<>();
        objects.add(new Point3D(new Coordinate(33.000, 333.000)));
        Point3D p = new Point3D(new Coordinate(33.001, 333.001));
        int i = objects.indexOf(p);
        System.out.println(123);
    }
}
