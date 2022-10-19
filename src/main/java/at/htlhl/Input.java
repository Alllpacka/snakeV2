package at.htlhl;

import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;

import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class Input implements NativeKeyListener {

    private static Snake snake;

    /**
     * @param e
     * is triggered, when a key is pressed
     */
    public void nativeKeyPressed(NativeKeyEvent e) {
        snake.putKeyIn(e.getKeyCode());
    }

    public void nativeKeyReleased(NativeKeyEvent e) {

    }

    public void nativeKeyTyped(NativeKeyEvent e) {

    }

    public static void updateSnake(Snake snake ) {
        Input.snake = snake;
    }

    /**
     * starts the key listener and deactivates log warnings
     */
    public static void startInputListener(Snake snake ) {
        LogManager.getLogManager().reset();
        Logger logger = Logger.getLogger(GlobalScreen.class.getPackage().getName());
        logger.setLevel(Level.OFF);

        Input.snake = snake;

        try {
            GlobalScreen.registerNativeHook();
        } catch (NativeHookException ex) {
            System.err.println(ex.getMessage());
            System.exit(1);
        }
        GlobalScreen.addNativeKeyListener(new Input());
    }
}