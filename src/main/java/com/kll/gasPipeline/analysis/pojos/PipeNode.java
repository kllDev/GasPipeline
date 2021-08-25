package com.kll.gasPipeline.analysis.pojos;

import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.io.WKTReader;

import java.util.*;


/**
 * 重写了hashcode 以及equals方法，保证当point相同时，node即为相同（x,y）
 */
public class PipeNode {
    public Point3D point;
    private Map<String, Object> prop;
    private Set<PipeLine> lines;
    private String id;

    public PipeNode(String id) {
        this.id = id;
    }

    public PipeNode(Coordinate coordinate) {
        this.point = new Point3D(coordinate);
        this.lines = new LinkedHashSet<>();
    }

    public PipeNode(Map<String, Object> prop) {
        try {
            WKTReader reader = new WKTReader();
            String wkt = (String) prop.get("the_geom");
            Geometry geometry = reader.read(wkt);
            Coordinate[] coordinates = geometry.getCoordinates();
            point = new Point3D(coordinates[0], 2);
            id = (String) prop.get("标识码");
            int i = PipeTopo.nodes.indexOf(this);
            if (i != -1) {
                PipeTopo.nodes.get(i).prop = prop;
                PipeTopo.nodes.get(i).id = id;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 添加关联直线
     *
     * @param pipeLine 管线
     */
    public void addLine(PipeLine pipeLine) {
        lines.add(pipeLine);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Point3D getPoint() {
        return point;
    }

    public void setPoint(Point3D point) {
        this.point = point;
    }

    public Map<String, Object> getProp() {
        return prop;
    }

    public void setProp(Map<String, Object> prop) {
        this.prop = prop;
    }


    public Set<PipeLine> getLines() {
        return lines;
    }

    public void setLines(Set<PipeLine> lines) {
        this.lines = lines;
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

    /**
     * 使用点的hashcode来判断node对象是否相同
     */
    @Override
    public int hashCode() {
        return point.hashCode();
    }
}
