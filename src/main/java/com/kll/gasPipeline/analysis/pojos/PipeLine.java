package com.kll.gasPipeline.analysis.pojos;

import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.io.WKTReader;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PipeLine {
    private Map<String, Object> prop;
    private List<PipeNode> nodes;
    private String wkt;

    public PipeLine(Map<String, Object> prop) {
        this.prop = prop;
        wkt = (String) prop.get("the_geom");
        try {
            WKTReader reader = new WKTReader();
            Geometry geometry = reader.read(wkt);
            Coordinate[] coordinates = geometry.getCoordinates();
            nodes = new ArrayList<>();
            for (Coordinate coordinate : coordinates) {
                PipeNode node = new PipeNode(coordinate);
                node.point.lines.add(this);
                nodes.add(node);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public List<PipeNode> getNodes() {
        return nodes;
    }

    public void setNodes(List<PipeNode> nodes) {
        this.nodes = nodes;
    }
}
