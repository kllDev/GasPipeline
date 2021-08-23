package com.kll.gasPipeline;


import com.kll.gasPipeline.geo.geotools.ShpTools;
import com.kll.gasPipeline.geo.pojos.ShpDatas;

import java.nio.charset.Charset;

public class Main {
    public static void main(String[] args) {
        try {
            ShpDatas shpDatas = ShpTools.readShpByPath("F:\\data\\shape\\天然气低压直埋.shp", null, Charset.forName("GBK"));
            System.out.println(123);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
