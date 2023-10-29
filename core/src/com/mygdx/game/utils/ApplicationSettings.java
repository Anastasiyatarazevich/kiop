package com.mygdx.game.utils;


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
    public static String OVERLAY_COLOR_MAP_DIR = "overlayShapes/colorMap/";
    public static String COLORED_SHAPES_DIR = "overlayShapes/coloredShapes/";
    public static String BLACK_AND_WHITE_SHAPES_DIR = "overlayShapes/blackWhiteShapes/";
    public static String COLUMN_BLACK_AND_WHITE_SHAPES_DIR = "overlayShapes/columnsBlackWhiteShapes/";

    // TODO: remove it
    public static int COUNT_OF_FILES = 5;


    // ---- The extra fourth settings

    public static String FOURTH_CARD_DIR = "theExtraFourth/cardsImages/";
    public static int FOURTH_CARD_SIZE = 300;
    public static int FOURTH_CARD_PADDING = 50;


// ---- Memo settings

    // ---- ---- Logic

    public static String CARDS_DIR = "memo/cardsImages/";
    public static int COUNT_OF_CARDS_TO_REMEMBER = 7;
    public static int COUNT_OF_ADDITION_CARDS = 8;
    public static int SECONDS_TO_REMEMBER = 2;

    // ---- ---- Ui

    public static float MEMO_CARD_MOVING_VELOCITY = 9;
    public static long MEMO_CARD_FADING_PERIOD = 800;
    public static int MEMO_CARD_SIZE = 170;
    public static int MEMO_CARD_PADDING = 20;
    public static int MEMO_CARD_IMAGE_SIZE = 120;


// ---- Raven

    public static String RAVEN_CARD_IMAGES_DIR = "ravensatlas/ravenscards.atlas";
    public static int RAVEN_CARD_SIZE = 230;
    public static int RAVEN_CARD_PADDING = 30;


// ---- Nonsense settings

    public static int NONSENSE_COUNT_OF_SAMPLES = 3;
    public static String NONSENSE_PICTURES_DIR = "nonsense/pictures/";
    public static String NONSENSE_COLOR_MAP_DIR = "nonsense/colormaps/";


// ---- Sequences settings


    public static int SEQUENCES_COUNT = 5;
    public static int SEQUENCES_COUNT_CARDS = 4;
    public static int SEQUENCES_TIME_IN_SECONDS = 5 * 60;

}
