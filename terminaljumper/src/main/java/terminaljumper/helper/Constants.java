package terminaljumper.helper;

/**
 * <h1>Constants</h1>
 * The Constants class stores constants needed by other classes in the game,
 * such as file paths.
 */
public class Constants {
        public static final float PPM = 32.0f;

        /**
         * The Images class stores file paths for image files used by the game.
         */
        public static class Images {
                public static final String NUMBERS = "sprites/numbers.png";
                public static final String STANDING_LEFT = "sprites/player/left_standing.png";
                public static final String STANDING_RIGHT = "sprites/player/right_standing.png";
                public static final String RUN_LEFT = "sprites/player/left_running.png";
                public static final String RUN_RIGHT = "sprites/player/right_running.png";
                public static final String JUMP_LEFT = "sprites/player/left_jump.png";
                public static final String JUMP_RIGHT = "sprites/player/right_jump.png";
                public static final String NUMBERS_RED = "sprites/numbers_RED.png";
                public static final String NUMBERS_GREEN = "sprites/numbers_GREEN.png";
                public static final String TERMINALJUMPER = "ui/terminaljumper.png";
                public static final String WINDOW = "ui/window.png";
                public static final String START = "ui/start.png";
                public static final String QUIT = "ui/quit.png";
                public static final String DESKTOP = "ui/desktop.png";
                public static final String BOMB = "sprites/bomb.png";
                public static final String SETTINGS = "ui/setting.png";
                public static final String HOWTOPLAY = "ui/howtoplay.png";
                public static final String BACK = "ui/back.png";
                public static final String LEGENDARY = "ui/EXPERT.png";
                public static final String CLASSIC = "ui/CLASSIC.png";
                public static final String EXPLANATION = "ui/howtoplayex.png";
                public static final String STAR = "sprites/star.png";
                public static final String RETRY = "ui/retry.png";
                public static final String MAINMENU = "ui/mainmenu.png";
                public static final String DOUBLE_JUMP = "sprites/DoubleJump.png";
                public static final String DOUBLE_JUMP_TEXT = "sprites/doublejump_text.png";
                public static final String MOUSE = "sprites/Mouse.png";
        }

        /**
         * Names of the collision objects (has to be the same names as in the .tmx
         * files)
         */
        public static class CollisionObject {
                public static final String PLAYER = "player";
                public static final String PLATFORM = "platform";
                public static final String WALL = "wall";
                public static final String BOMB = "bomb";
                public static final String STAR = "star";
                public static final String DOUBLE_JUMP = "doublejump";
                public static final String MOUSE = "mouse";
        }

        /**
         * The Audio class stores file paths for audio files used by the game.
         */
        public static class Audio {
                public static final String GAMESCREEN_BACKGROUND = "music/Wallpaper.mp3";
                public static final String SPACE_JAZZ = "music/Space-Jazz.mp3";
                public static final String GAME_OVER = "sounds/mixkit-arcade-retro-game-over-213.wav";
                public static final String JUMP = "sounds/button-11.wav";
                public static final String POWER_UP = "sounds/button-3.wav";
                public static final String POWER_DOWN = "sounds/button-5.wav";
                public static final String DOUBLE_JUMP_UPGRADE = "sounds/mixkit-extra-bonus-in-a-video-game-2045.wav";

        }
}
