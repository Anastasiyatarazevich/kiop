package com.mygdx.game.utils.overlayShapes;

import java.util.ArrayList;

public class ShapesDescriptor {

    static String[] allDescriptions = {
            "(707, 282);11;cauliflower.png\n" +
                    "(816, 81);3;peas.png\n" +
                    "(242, 242);68;beet.png\n" +
                    "(151, 51);14;pumpkin.png\n" +
                    "(662, 268);85;cabbage.png\n" +
                    "(458, 157);73;eggplant.png\n" +
                    "(399, 318);65;pepper.png\n" +
                    "(-10, 153);68;carrot.png\n",
            "(-5, 131);30;peas.png\n" +
                    "(751, 170);44;corn.png\n" +
                    "(520, 303);81;cauliflower.png\n" +
                    "(667, 207);8;broccoli.png\n" +
                    "(213, 109);60;carrot.png\n" +
                    "(206, 268);41;cabbage.png\n" +
                    "(680, 44);52;beet.png\n" +
                    "(374, 127);81;eggplant.png\n"
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
