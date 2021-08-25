package com.kll.gasPipeline.analysis.pojos;

import org.locationtech.jts.geom.Coordinate;

import java.util.Objects;

public class Point3D extends Coordinate {

    public Point3D(Coordinate c) {
        super(c);
    }

    public Point3D(Coordinate c, int t) {
        this.x = Math.ceil(c.x * 10000)/10000;
        this.y = Math.ceil(c.y * 10000)/10000;
        this.z = 0;
    }


    @Override
    public boolean equals(Object other) {
        if (!(other instanceof Coordinate)) {
            return false;
        }
        //判断两个点误差是否在一定范围内
        //      点判断相同的条件（不考虑同xy，不同z存在两个点）
        Point3D otherPoint = (Point3D) other;
        if (Math.abs(x - otherPoint.x) > 0.001) {
            return false;
        }
        if (Math.abs(y - otherPoint.y) > 0.001) {
            return false;
        }
//        if (z - other.z > 0.001) {
//            return false;
//        }
        return true;
    }

    @Override
    public int hashCode() {
        //Algorithm from Effective Java by Joshua Bloch [Jon Aquino]
//        int result = 17;
//        result = 37 * result + hashCode(Math.round(x));
//        result = 37 * result + hashCode(Math.round(y));
//        return result;

        return Objects.hash(Math.round(x), Math.round(y));
    }
}
