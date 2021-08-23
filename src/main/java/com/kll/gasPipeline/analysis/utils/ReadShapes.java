package com.kll.gasPipeline.analysis.utils;

import com.kll.gasPipeline.analysis.pojos.PipeLine;
import com.kll.gasPipeline.analysis.pojos.PipeNode;
import com.kll.gasPipeline.analysis.pojos.Point3D;
import com.kll.gasPipeline.geo.geotools.ShpTools;
import com.kll.gasPipeline.geo.pojos.ShpDatas;

import java.nio.charset.Charset;
import java.util.*;

public class ReadShapes {
    public static void main(String[] args) throws Exception {
        Set<PipeNode> nodesSet = new LinkedHashSet<>();
        List<PipeLine> lines = new ArrayList<>();
        List<String> paths = new ArrayList<>();
        paths.add("F:\\data\\shape\\天然气低压穿越.shp");
        paths.add("F:\\data\\shape\\天然气低压架空.shp");
        paths.add("F:\\data\\shape\\天然气低压桥管.shp");
        paths.add("F:\\data\\shape\\天然气低压直埋.shp");
        paths.add("F:\\data\\shape\\天然气中压B穿越.shp");
        paths.add("F:\\data\\shape\\天然气中压B架空.shp");
        paths.add("F:\\data\\shape\\天然气中压B桥管.shp");
        paths.add("F:\\data\\shape\\天然气中压B直埋.shp");
        for (String path : paths) {
            ShpDatas shpLines = ShpTools.readShpByPath(path, null, Charset.forName("GBK"));
            List<Map<String, Object>> props = shpLines.getProps();
            for (int i = 0; i < props.size(); i++) {
                PipeLine line = new PipeLine(props.get(i));
                List<PipeNode> nodes = line.getNodes();
                nodesSet.addAll(nodes);
                lines.add(line);
            }
        }
//        for (Point3D p : all) {
//            int i = point3DSet.indexOf(p);
//            if (i != -1) {
//                boolean contains = point3DSet.get(i).lines.contains(line);
//                if (!contains) {
//                    point3DSet.get(i).addLine(line);
//                }
//            } else {
//                p.addLine(line);
//                point3DSet.add(p);
//            }
//        }
//        ArrayList<Point3D> point3DS = new ArrayList<>(point3DSet);
//        point3DS.get(0).z = 111;
        List<Point3D> point3DS = new ArrayList<>();
        ShpDatas value = ShpTools.readShpByPath("F:\\data\\shape\\阀门点.shp", null, Charset.forName("GBK"));
        for (Map<String, Object> prop : value.getProps()) {
            PipeNode node = new PipeNode(prop);
            if (nodesSet.contains(node.getPoint())) {
//                point3DSet.add(node.getPoint());
            } else {
                point3DS.add(node.getPoint());
            }
        }
        System.out.println(123);
    }
}
