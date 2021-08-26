package com.kll.gasPipeline.analysis.pojos;

import java.util.HashSet;
import java.util.Set;

public class PipeValves {
    public Set<String> valveIds;
    public Set<Point3D> solvedNodeIds;
    public Set<String> solvedLineIds;

    public PipeValves() {
        valveIds = new HashSet<>();
        solvedNodeIds = new HashSet<>();
        solvedLineIds = new HashSet<>();
    }

}
