import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Ghost {
    private BufferedImage appearance;
    private String ghostPath = "resources/ghosts/";

    Ghost(String color) {
        try {
            if (color.equals("red"))
                this.appearance = ImageIO.read(new File(ghostPath + "redGhost.png"));
            else if(color.equals("yellow"))
                this.appearance = ImageIO.read(new File(ghostPath + "yellowGhost.png"));
            else
                this.appearance = ImageIO.read(new File(ghostPath + "blueGhost.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public BufferedImage getAppearance() {
        return appearance;
    }
}


