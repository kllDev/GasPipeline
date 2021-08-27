package com.kll.gasPipeline.analysis.pojos;

import com.kll.gasPipeline.analysis.utils.Point3DUtil;
import org.locationtech.jts.geom.Coordinate;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PipeTopo implements Serializable {
    private static final long serialVersionUID = 4359709211352400087L;

    public  Map<Integer, List<PipeNode>> nodesMap;
    public  List<PipeNode> nodes;
    public  List<PipeLine> lines;

    public PipeTopo() {
        this.nodesMap = new HashMap<>();
        this.nodes = new ArrayList<>();
        this.lines = new ArrayList<>();
    }

    public void addLine(PipeLine line) {
        Coordinate[] coordinates = line.getCoordinates();
        for (Coordinate coordinate : coordinates) {
            PipeNode node = new PipeNode(coordinate);
            int i = nodes.indexOf(node);
            if (i != -1) {
                line.getNodes().add(nodes.get(i));
                nodes.get(i).addLine(line);
            } else{
                node.addLine(line);
                line.getNodes().add(node);
                nodes.add(node);
            }
        }
        lines.add(line);
    }

    public void addNode(PipeNode node) {
        int i = nodes.indexOf(node);
        if (i != -1) {
            nodes.get(i).setProp(node.getProp());
            nodes.get(i).setId(node.getId());
        }else{
            for (PipeLine line : lines) {
                Point3D point1 = line.getNodes().get(0).point;
                Point3D point2 = line.getNodes().get(line.getNodes().size() - 1).point;
                if (Point3DUtil.pointToLine(point1, point2, node.point) < 0.05) {
                    boolean xIn = node.point.x < Math.max(point1.x, point2.x)&&node.point.x > Math.min(point1.x, point2.x);
                    boolean yIn = node.point.y < Math.max(point1.y, point2.y)&&node.point.y > Math.min(point1.y, point2.y);
                    if (xIn && yIn) {
                        System.out.println(node.point.x);
                        System.out.println(node.point.y);
                        System.out.println(line.getId());
                        System.out.println("=============================点在线段上========================");
                        break;
                    }
                }
            }
            System.out.println(123);
        }
    }
}
