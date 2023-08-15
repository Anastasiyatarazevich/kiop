package com.mygdx.game.utils.overlayShapes;

import java.util.ArrayList;

public class ShapesDescriptor {

    static String[] allDescriptions = {
            "(808, 353);15;broccoli.png\n" +
                    "(186, 199);4;cabbage.png\n" +
                    "(463, 92);81;cauliflower.png\n" +
                    "(672, 246);2;peas.png\n" +
                    "(185, 70);53;beet.png\n" +
                    "(94, 92);78;corn.png\n" +
                    "(669, 211);59;pumpkin.png\n" +
                    "(500, 226);57;eggplant.png\n"
    };

    private static int clearNumber(String inputStr) {
        String outputStr = "";
        for (int i = 0; i < inputStr.length(); i++) {
            if (Character.isDigit(inputStr.charAt(i)))
                outputStr += inputStr.charAt(i);
        }
        return Integer.parseInt(outputStr);
    }

    public static ArrayList<Shape> getSampleShapes(int sampleIdx) {
        ArrayList<Shape> shapesList = new ArrayList<>();

        String inputData = allDescriptions[sampleIdx];
        String[] lines = inputData.split("\n");
        for (String line : lines) {
            String[] splitLine = line.split(";");
            shapesList.add(new Shape(
                    clearNumber(splitLine[0].split(",")[0]),
                    clearNumber(splitLine[0].split(",")[1]),
                    Integer.parseInt(splitLine[1]),
                    splitLine[2]
            ));
        }

        return shapesList;
    }

}
