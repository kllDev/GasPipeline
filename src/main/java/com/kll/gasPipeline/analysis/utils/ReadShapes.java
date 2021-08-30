package com.kll.gasPipeline.analysis.utils;


import com.kll.gasPipeline.analysis.pojos.PipeLine;
import com.kll.gasPipeline.analysis.pojos.PipeNode;
import com.kll.gasPipeline.analysis.pojos.PipeTopo;
import com.kll.gasPipeline.analysis.pojos.PipeValves;
import com.kll.gasPipeline.geo.geotools.ShpTools;
import com.kll.gasPipeline.geo.pojos.ShpDatas;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.nio.charset.Charset;
import java.util.*;

public class ReadShapes {
    public static void main(String[] args) throws Exception {
        PipeTopo topo = new PipeTopo();
        List<String> paths = new ArrayList<>();
//        paths.add("F:\\data\\shape\\天然气低压穿越.shp");
//        paths.add("F:\\data\\shape\\天然气低压架空.shp");
//        paths.add("F:\\data\\shape\\天然气低压桥管.shp");
//        paths.add("F:\\data\\shape\\天然气低压直埋.shp");
        paths.add("F:\\data\\shape\\天然气中压B穿越.shp");
        paths.add("F:\\data\\shape\\天然气中压B架空.shp");
        paths.add("F:\\data\\shape\\天然气中压B桥管.shp");
        paths.add("F:\\data\\shape\\天然气中压B直埋.shp");
        int size = 0;
        for (String path : paths) {
            ShpDatas shpLines = ShpTools.readShpByPath(path, null, Charset.forName("GBK"));
            List<Map<String, Object>> props = shpLines.getProps();
            size += props.size();
            for (int i = 0; i < props.size(); i++) {
                PipeLine line = new PipeLine(props.get(i));
                topo.addLine(line);
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
                topo.addNode(node);

//                nodes.add(node);
            }
        }
//        PipeLine line = new PipeLine("2a7f110c-40d2-4679-babc-1473c13cca19");
//        int i = topo.lines.indexOf(line);
//        PipeLine problemLine = topo.lines.get(i);
//        List<PipeNode> relateNodes = problemLine.getNodes();
//        PipeValves pipeValves = new PipeValves();
//        for (PipeNode relateNode : relateNodes) {
//            fmPoints(relateNode, pipeValves);
//        }
        //判断是否折线上的所有的点间距都在阈值之上
//        for (int i = 0; i < topo.lines.size(); i++) {
//            PipeLine line = topo.lines.get(i);
//            List<PipeNode> nodes = line.getNodes();
//            for (int j = 0; j < nodes.size() - 1; j++) {
//                PipeNode node1 = nodes.get(j);
//                PipeNode node2 = nodes.get(j + 1);
//                if (Math.abs(node1.point.x - node2.point.x) == 0 && Math.abs(node1.point.y - node2.point.y) == 0) {
//                    continue;
//                }
//                if (Math.abs(node1.point.x - node2.point.x) < 0.04 && Math.abs(node1.point.y - node2.point.y) < 0.04) {
//                    System.out.println(123);
//                }
//            }
//        }


//        for (int i = 0; i < topo.lines.size(); i++) {
//            PipeLine line = topo.lines.get(i);
//            int index = topo.lines.indexOf(line);
//            PipeLine problemLine = topo.lines.get(index);
//            PipeValves pipeValves = new PipeValves();
//            fmPoints(problemLine, pipeValves);
//            topo.lines.get(i).fmIds = pipeValves.valveIds;
//        }
//        int index = topo.lines.indexOf(new PipeLine("2a7f110c-40d2-4679-babc-1473c13cca19"));
//        PipeLine line = topo.lines.get(index);
//        try {
//            FileOutputStream fos = new FileOutputStream("../topo.txt");
//            ObjectOutputStream oos = new ObjectOutputStream(fos);
//            oos.writeObject(topo);
//            oos.flush();
//            oos.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        System.out.println(123);

        System.out.println(System.currentTimeMillis());
        String personJson = JacksonUtils.serialize(topo);
        System.out.println(System.currentTimeMillis());
        PipeTopo deserialize = JacksonUtils.deserialize(personJson, PipeTopo.class);
        System.out.println(System.currentTimeMillis());
        System.out.println(123);
    }

     public static void fmPoints(PipeLine line, PipeValves valves) {
        List<PipeNode> nodes = line.getNodes();
        valves.solvedLineIds.add(line.getId());
        for (PipeNode node : nodes) {
            if (node.getId() != null) {
                valves.valveIds.add(node.getId());
            } else {
                fmPoints(node, valves);
            }
        }
    }


    /**
     * @param node   节点
     * @param valves 数据结果
     */
    public static void fmPoints(PipeNode node, PipeValves valves) {
        Set<PipeLine> lineSet = node.getLines();
        for (PipeLine line : lineSet) {
            if (!valves.solvedLineIds.contains(line.getId())) {
                valves.solvedLineIds.add(line.getId());
                List<PipeNode> nodes = line.getNodes();
                //如果分支线段中的第一个点不是触发点,则由此点开始，向两端发射
                int index = nodes.indexOf(node);
                for (int i = index; i >= 0; i--) {
                    PipeNode pipeNode = nodes.get(i);
                    fmPoints(pipeNode, valves);
                    if (pipeNode.getId() != null) {
                        valves.valveIds.add(pipeNode.getId());
                        break;
                    }
                }
                for (int i = index; i < nodes.size(); i++) {
                    PipeNode pipeNode = nodes.get(i);
                    fmPoints(pipeNode, valves);
                    if (pipeNode.getId() != null) {
                        valves.valveIds.add(pipeNode.getId());
                        break;
                    }
                }
            }
        }


//        if (!valves.solvedNodeIds.contains(node.point)) {
//            if (node.getId() != null) {
//                valves.valveIds.add(node.getId());
//            } else {
//                Set<PipeLine> lines = node.getLines();
//                for (PipeLine line : lines) {
////                    List<PipeNode> nodes = line.getNodes();
////                    for (PipeNode pipeNode : nodes) {
////                        fmPoints(pipeNode, valves);
////                        valves.solvedNodeIds.add(node.point);
////                    }
//
//                    //如何确定点的顺序，
//                    if (!valves.solvedLineIds.contains(line.getId())) {
//                        valves.solvedLineIds.add(line.getId());
//                        List<PipeNode> nodes = line.getNodes();
//                        for (PipeNode pipeNode : nodes) {
//                            fmPoints(pipeNode, valves);
//                            valves.solvedNodeIds.add(node.point);
//                        }
//                    }
//                }
//            }
//        }
    }
}
