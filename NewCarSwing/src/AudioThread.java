import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

public class AudioThread implements Runnable {
    @Override
    public void run() {
        try {
            /*Player p = new Player(new FileInputStream(getClass()
                    .getClassLoader().getResource("res/GetLow.mp3").getFile()));*/

            Player p = new Player(getClass().getClassLoader().getResourceAsStream("res/GetLow.mp3"));
            p.play();
        } catch (JavaLayerException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
