import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Keys implements KeyListener {

    private final Map<Integer, Boolean> keyStates = new ConcurrentHashMap<Integer, Boolean>();

    public Keys() {
        keyStates.put(KeyEvent.VK_LEFT, Boolean.FALSE);
        keyStates.put(KeyEvent.VK_RIGHT, Boolean.FALSE);
        keyStates.put(KeyEvent.VK_UP, Boolean.FALSE);
        keyStates.put(KeyEvent.VK_SPACE, Boolean.FALSE);
        keyStates.put(KeyEvent.VK_ENTER, Boolean.FALSE);
    }

    public boolean leftKey() {
        return keyDown(KeyEvent.VK_LEFT);
    }

    public boolean rightKey() {
        return keyDown(KeyEvent.VK_RIGHT);
    }

    public boolean drop() {
        return keyDown(KeyEvent.VK_SPACE);
    }

    public boolean rotateKey() {
        return keyDown(KeyEvent.VK_UP);
    }

    public boolean newGame() {
        return keyDown(KeyEvent.VK_ENTER);
    }

    private boolean keyDown(int keyCode) {
        return keyStates.put(keyCode, Boolean.FALSE);
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent keyEvent) {
        if (keyStates.containsKey(keyEvent.getKeyCode())) {
            keyStates.put(keyEvent.getKeyCode(), Boolean.TRUE);
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }
}
