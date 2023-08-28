package com.mygdx.game.utils;


import com.sun.org.apache.bcel.internal.generic.PUSH;

public class ApplicationSettings {

    // ---- General settings

    public static int SCR_WIDTH = 1920;
    public static int SCR_HEIGHT = 1200;


    // ---- Schulte Table settings

    // ---- ---- Logic

    public static int COUNT_OF_SCHULTE_TABLES = 3;
    public static int SCHULTE_TABLE_SIZE = 5;

    // ---- ---- Ui

    public static int SCHULTE_TABLE_ITEMS_SIZE = 125;
    public static int SCHULTE_TABLE_ITEMS_PADDING = 20;


    // ---- Proof-Reading test settings

    // ---- ---- Ui

    public static int PROOF_READING_ROWS = 7;
    public static int PROOF_READING_COLUMNS = 16;
    public static int PROOF_READING_ITEMS_SIZE = 100;


    // ---- Overlay shapes settings

    public static String COLOR_MAP_DESCRIPTIONS_DIR = "overlayShapes/colorMap/";
    public static String OVERLAY_PICTURES_DIR = "overlayShapes/overlayPictures/";
    public static String COLOR_MAP_DIR = "overlayShapes/colorMap/";
    public static String COLORED_SHAPES_DIR = "overlayShapes/coloredShapes/";
    public static String BLACK_AND_WHITE_SHAPES_DIR = "overlayShapes/blackWhiteShapes/";
    public static String COLUMN_BLACK_AND_WHITE_SHAPES_DIR = "overlayShapes/columnsBlackWhiteShapes/";

    // TODO: remove it
    public static int COUNT_OF_FILES = 2;


    // ---- The extra fourth settings

    public static String FOURTH_CARD_DIR = "theExtraFourth/cardsImages/";
    public static int FOURTH_CARD_SIZE = 300;
    public static int FOURTH_CARD_PADDING = 50;


    // ---- Memo settings

    // ---- ---- Logic

    public static String CARDS_DIR = "memo/cardsImages/";
    public static int COUNT_OF_CARDS_TO_REMEMBER = 15;
    public static int COUNT_OF_ADDITION_CARDS = 15;
    public static int SECONDS_TO_REMEMBER = 3;

    // ---- ---- Ui

    public static int CARD_SIZE = 170;
    public static float CARD_MOVING_VELOCITY = 12;
    public static int CARD_PADDING = 20;
    public static int CARD_IMAGE_SIZE = 120;
    public static long CARD_MEMO_FADING_PERIOD = 800;


    // ---- Raven

    public static String RAVEN_CARD_IMAGES_DIR = "ravensatlas/ravenscards.atlas";
    public static int RAVEN_CARD_SIZE = 230;
    public static int RAVEN_CARD_PADDING = 30;
}
