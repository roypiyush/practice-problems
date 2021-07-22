package com.topcoder.bruteforce;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class SurveillanceSystem {

    public static void main(String[] args) {

        String containers = "-X-XXX-X-XXXX--XX-XX-X---XX----XX---X-XX--X-X-X-";
        int[] reports = {11, 9};
        int L = 18;

        System.out.println(new SurveillanceSystem().getContainerInfo(
                containers, reports, L));

    }

    public String getContainerInfo(String containers, int[] reports, int L) {

        StringBuilder sectorInfo = new StringBuilder();


        for (int i = 0; i < containers.length(); i++) {
            sectorInfo.insert(0, '-');
        }

        int numberOfSegments = containers.length() - L + 1;

        int[] containersInSegment = new int[numberOfSegments];
        for (int i = 0; i < containersInSegment.length; i++) {
            for (int j = 0; j < L; j++) {
                if (containers.charAt(i + j) == 'X')
                    containersInSegment[i]++;
            }
        }

        // Key is count of containers
        // Value is list of sectors where 'key' number containers are present
        Map<Integer, List<Integer>> containerTosegments = new HashMap<>();
        for (int i = 0; i < containersInSegment.length; i++) {

            List<Integer> segmentNumber = containerTosegments.get(containersInSegment[i]);
            if (segmentNumber == null) {
                segmentNumber = new ArrayList<>();
            }
            segmentNumber.add(i);
            containerTosegments.put(containersInSegment[i], segmentNumber);

        }

        // Key count of containers
        // Value is count of sectors required
        Map<Integer, Integer> requiredContainersToSegmentMap = new HashMap<>();
        for (int i : reports) {
            requiredContainersToSegmentMap.put(i, requiredContainersToSegmentMap.get(i) == null ? 1 : (requiredContainersToSegmentMap.get(i) + 1));
        }

        System.out.println(containerTosegments);
        System.out.println(requiredContainersToSegmentMap);


        for (Entry<Integer, Integer> reportCount : requiredContainersToSegmentMap.entrySet()) {

            Map<Integer, Integer> sectors = new HashMap<>();
            List<Integer> list = containerTosegments.get(reportCount.getKey());
            int max = 1;
            for (int i : list) {

                for (int j = 0; j < L; j++) {

                    Integer sector = i + j;
                    Integer sectorRepitition = sectors.get(sector);
                    if (sectorRepitition == null) {
                        sectors.put(sector, 1);
                    } else {
                        Integer value = sectors.get(sector);
                        value = value + 1;
                        if (value > max)
                            max = value;
                        sectors.put(sector, value);
                    }
                }


            }

            for (Entry<Integer, Integer> entry : sectors.entrySet()) {

                if (sectorInfo.charAt(entry.getKey()) != '+') {
                    int remaining = containerTosegments.get(reportCount.getKey()).size() - reportCount.getValue();
                    if (remaining == 0) {
                        sectorInfo.setCharAt(entry.getKey(), '+');
                    } else if (remaining > 0) {
                        sectorInfo.setCharAt(entry.getKey(), sectors.get(entry.getKey())> remaining ? '+' : '?');
                    }

                }

            }


        }


        return sectorInfo.toString();
    }

}
