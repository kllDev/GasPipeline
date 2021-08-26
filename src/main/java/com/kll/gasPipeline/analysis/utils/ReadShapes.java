package com.kll.gasPipeline.analysis.utils;


import com.kll.gasPipeline.analysis.pojos.PipeLine;
import com.kll.gasPipeline.analysis.pojos.PipeNode;
import com.kll.gasPipeline.analysis.pojos.PipeTopo;
import com.kll.gasPipeline.analysis.pojos.PipeValves;
import com.kll.gasPipeline.geo.geotools.ShpTools;
import com.kll.gasPipeline.geo.pojos.ShpDatas;

import java.nio.charset.Charset;
import java.util.*;

public class ReadShapes {
    public static void main(String[] args) throws Exception {
        List<String> paths = new ArrayList<>();
//        paths.add("F:\\data\\shape\\天然气低压穿越.shp");
//        paths.add("F:\\data\\shape\\天然气低压架空.shp");
//        paths.add("F:\\data\\shape\\天然气低压桥管.shp");
//        paths.add("F:\\data\\shape\\天然气低压直埋.shp");
        paths.add("F:\\data\\shape\\天然气中压B穿越.shp");
        paths.add("F:\\data\\shape\\天然气中压B架空.shp");
        paths.add("F:\\data\\shape\\天然气中压B桥管.shp");
        paths.add("F:\\data\\shape\\天然气中压B直埋.shp");
        for (String path : paths) {
            ShpDatas shpLines = ShpTools.readShpByPath(path, null, Charset.forName("GBK"));
            List<Map<String, Object>> props = shpLines.getProps();
            for (int i = 0; i < props.size(); i++) {
                PipeLine line = new PipeLine(props.get(i));
//                List<PipeNode> lineNodes = line.getNodes();
//                nodes.addAll(nodes);
//                lines.add(line);
            }
        }
        List<String> pointPath = new ArrayList<>();
//        pointPath.add("F:\\data\\shape\\补偿器点.shp");
//        pointPath.add("F:\\data\\shape\\调压器点.shp");
        pointPath.add("F:\\data\\shape\\阀门点.shp");
//        pointPath.add("F:\\data\\shape\\法兰点.shp");
//        pointPath.add("F:\\data\\shape\\三通点.shp");
//        pointPath.add("F:\\data\\shape\\水井点.shp");
//        pointPath.add("F:\\data\\shape\\立管点.shp");
        for (String path : pointPath) {
            ShpDatas points = ShpTools.readShpByPath(path, null, Charset.forName("GBK"));
            for (Map<String, Object> prop : points.getProps()) {
                PipeNode node = new PipeNode(prop);
//                nodes.add(node);
            }
        }
//        PipeLine line = new PipeLine("9fe708e8-e035-4db5-a126-cef5026e8956");
//        int i = PipeTopo.lines.indexOf(line);
//        PipeLine problemLine = PipeTopo.lines.get(i);
//        List<PipeNode> relateNodes = problemLine.getNodes();
//        PipeValves pipeValves = new PipeValves();
//        for (PipeNode relateNode : relateNodes) {
//            fmPoints(relateNode, pipeValves);
//        }
        for (int i = 0; i < PipeTopo.lines.size(); i++) {
            PipeLine line = PipeTopo.lines.get(i);
            int index = PipeTopo.lines.indexOf(line);
            PipeLine problemLine = PipeTopo.lines.get(index);
            List<PipeNode> relateNodes = problemLine.getNodes();
            PipeValves pipeValves = new PipeValves();
            for (PipeNode relateNode : relateNodes) {
                fmPoints(relateNode,pipeValves);
            }
            if (pipeValves.valveIds.size() < 2) {
                System.out.println(123);
            }
            PipeTopo.lines.get(i).fmIds = pipeValves.valveIds;
        }
        System.out.println(123);
    }

    public static void fmPoints(PipeNode node, PipeValves valves) {
        if (!valves.solvedNodeIds.contains(node.point)) {
            if (node.getId() != null) {
                valves.valveIds.add(node.getId());
            } else {
                Set<PipeLine> lines = node.getLines();
                for (PipeLine line : lines) {
                    if (!valves.solvedLineIds.contains(line.getId())) {
                        valves.solvedLineIds.add(line.getId());
                        List<PipeNode> nodes = line.getNodes();
                        for (PipeNode pipeNode : nodes) {
                            fmPoints(pipeNode, valves);
                            valves.solvedNodeIds.add(node.point);
                        }
                    }
                }
            }
        }
    }
}
