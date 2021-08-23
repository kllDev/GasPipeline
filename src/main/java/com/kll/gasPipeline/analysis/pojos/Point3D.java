package com.kll.gasPipeline.analysis.pojos;

import org.locationtech.jts.geom.Coordinate;

import java.util.*;

public class Point3D extends Coordinate {
    public Set<PipeLine> lines;

    public Point3D(Coordinate c) {
        super(c);
        lines = new LinkedHashSet<>();
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof Coordinate)) {
            return false;
        }
        return equals3D((Point3D) other);
    }


    public boolean equals3D(Point3D other) {
        if (x - other.x > 0.001) {
            return false;
        }
        if (y - other.y > 0.001) {
            return false;
        }
        if (z - other.z > 0.001) {
            return false;
        }
        lines.addAll(other.lines);
        other.lines = lines;
        return true;
    }

    public Set<PipeLine> getLines() {
        return lines;
    }

    public void setLines(Set<PipeLine> lines) {
        this.lines = lines;
    }
}
