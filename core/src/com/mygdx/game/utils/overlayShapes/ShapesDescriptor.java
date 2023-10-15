package com.mygdx.game.utils.overlayShapes;

import java.util.ArrayList;

public class ShapesDescriptor {

    static String[] allDescriptions = {
            "(282, 177);69;pepper.png\n" +
                    "(606, 91);62;cauliflower.png\n" +
                    "(409, -17);21;eggplant.png\n" +
                    "(186, 292);46;broccoli.png\n" +
                    "(818, 243);86;corn.png\n" +
                    "(4, 50);54;peas.png\n" +
                    "(694, 309);83;beet.png\n" +
                    "(454, 264);43;carrot.png\n",
            "(767, 6);46;pumpkin.png\n" +
                    "(302, 92);17;eggplant.png\n" +
                    "(458, 354);76;carrot.png\n" +
                    "(634, 226);32;beet.png\n" +
                    "(46, 60);57;cabbage.png\n" +
                    "(684, 161);51;peas.png\n" +
                    "(414, 186);30;broccoli.png\n" +
                    "(114, 161);27;corn.png\n",
            "(311, 111);1;pumpkin.png\n" +
                    "(128, 306);0;eggplant.png\n" +
                    "(609, 201);47;broccoli.png\n" +
                    "(716, 279);61;cabbage.png\n" +
                    "(312, 49);58;carrot.png\n" +
                    "(126, 192);64;beet.png\n" +
                    "(475, -46);50;peas.png\n" +
                    "(804, 182);70;cauliflower.png\n",
            "(676, 89);32;peas.png\n" +
                    "(451, 303);5;carrot.png\n" +
                    "(317, 252);78;broccoli.png\n" +
                    "(139, 209);30;corn.png\n" +
                    "(783, 135);79;beet.png\n" +
                    "(99, 156);81;cabbage.png\n" +
                    "(584, 15);7;eggplant.png\n" +
                    "(549, 168);44;pumpkin.png\n",
            "(533, 110);83;broccoli.png\n" +
                    "(744, 258);43;corn.png\n" +
                    "(91, 182);22;pepper.png\n" +
                    "(387, 241);74;pumpkin.png\n" +
                    "(695, 34);30;carrot.png\n" +
                    "(639, 210);84;cauliflower.png\n" +
                    "(248, 96);88;cabbage.png\n" +
                    "(268, 160);70;eggplant.png\n"
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
