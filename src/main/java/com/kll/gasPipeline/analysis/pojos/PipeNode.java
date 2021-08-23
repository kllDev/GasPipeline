package com.kll.gasPipeline.analysis.pojos;

import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.io.WKTReader;

import java.util.Map;

public class PipeNode {
    public Point3D point;
    private Map<String, Object> prop;
    private String wkt;

    public PipeNode(Map<String, Object> prop) {
        this.prop = prop;
        wkt = (String) prop.get("the_geom");
        try {
            WKTReader reader = new WKTReader();
            Geometry geometry = reader.read(wkt);
            Coordinate[] coordinates = geometry.getCoordinates();
            point = new Point3D(coordinates[0]);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public PipeNode(Coordinate coordinate) {
        this.point = new Point3D(coordinate);
    }

    public Point3D getPoint() {
        return point;
    }

    @Override
    public boolean equals(Object obj) {
        PipeNode node = (PipeNode) obj;
        if (point.equals(node.point)) {
            return true;
        } else {
            return false;
        }
    }
}
