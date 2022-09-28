package at.htlhl;

import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;

import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class Input implements NativeKeyListener {

    public void nativeKeyPressed(NativeKeyEvent e) {
        if (e.getKeyCode() == NativeKeyEvent.VC_W || e.getKeyCode() == NativeKeyEvent.VC_UP) {
            if (Direction.checkDirection(Direction.Up) || Main.game.snake.getSnakeSize() == 0) {
                Snake.direction = Direction.Up;
            }
        }
        if (e.getKeyCode() == NativeKeyEvent.VC_A || e.getKeyCode() == NativeKeyEvent.VC_LEFT) {
            if (Direction.checkDirection(Direction.Left)  || Main.game.snake.getSnakeSize() == 0) {
                Snake.direction = Direction.Left;
            }
        }
        if (e.getKeyCode() == NativeKeyEvent.VC_S || e.getKeyCode() == NativeKeyEvent.VC_DOWN) {
            if (Direction.checkDirection(Direction.Down) || Main.game.snake.getSnakeSize()== 0) {
                Snake.direction = Direction.Down;
            }
        }
        if (e.getKeyCode() == NativeKeyEvent.VC_D || e.getKeyCode() == NativeKeyEvent.VC_RIGHT) {
            if (Direction.checkDirection(Direction.Right) || Main.game.snake.getSnakeSize() == 0) {
                Snake.direction = Direction.Right;
            }
        }
    }

    public void nativeKeyReleased(NativeKeyEvent e) {

    }

    public void nativeKeyTyped(NativeKeyEvent e) {

    }

    public static void startInputListener() {
        LogManager.getLogManager().reset();
        Logger logger = Logger.getLogger(GlobalScreen.class.getPackage().getName());
        logger.setLevel(Level.OFF);

        try {
            GlobalScreen.registerNativeHook();
        } catch (NativeHookException ex) {
            System.err.println(ex.getMessage());
            System.exit(1);
        }
        GlobalScreen.addNativeKeyListener(new Input());
    }
}