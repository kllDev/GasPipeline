package com.kll.gasPipeline.analysis.pojos;

import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.io.WKTReader;

import java.util.*;

public class PipeLine {
    private Map<String, Object> prop;
    private List<PipeNode> nodes;
    private String wkt;
    private String id;
    public Set<String> fmIds;

    public PipeLine(Map<String, Object> prop) {
        this.prop = prop;
        wkt = (String) prop.get("the_geom");
        id = (String) prop.get("标识码");
        try {
            WKTReader reader = new WKTReader();
            Geometry geometry = reader.read(wkt);
            Coordinate[] coordinates = geometry.getCoordinates();
            nodes = new ArrayList<>();
            for (Coordinate coordinate : coordinates) {
                PipeNode node = new PipeNode(coordinate);
                int i = PipeTopo.nodes.indexOf(node);
                if (i != -1) {
                    PipeTopo.nodes.get(i).addLine(this);
                    nodes.add(PipeTopo.nodes.get(i));
                } else{
                    node.addLine(this);
                    PipeTopo.nodes.add(node);
                    nodes.add(node);
                }
            }
            PipeTopo.lines.add(this);
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
