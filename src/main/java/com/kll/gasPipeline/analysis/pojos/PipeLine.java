package com.kll.gasPipeline.analysis.pojos;

import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.io.WKTReader;

import java.io.Serializable;
import java.util.*;

public class PipeLine implements Serializable {
    private static final long serialVersionUID = 4359709211352400088L;
    private Map<String, Object> prop;
    private List<PipeNode> nodes;
    private Coordinate[] coordinates;
    private String wkt;
    private String id;
    public Set<String> fmIds;

    public PipeLine(Map<String, Object> prop) {
        this.prop = prop;
        wkt = (String) prop.get("the_geom");
        id = (String) prop.get("标识码");
        nodes = new ArrayList<>();
        try {
            WKTReader reader = new WKTReader();
            Geometry geometry = reader.read(wkt);
            coordinates = geometry.getCoordinates();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public PipeLine(String id) {
        this.id = id;
    }


    public List<PipeNode> getNodes() {
        return nodes;
    }

    public void setNodes(List<PipeNode> nodes) {
        this.nodes = nodes;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Coordinate[] getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinate[] coordinates) {
        this.coordinates = coordinates;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PipeLine line = (PipeLine) o;
        return Objects.equals(id, line.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
