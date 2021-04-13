import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class GameProgress implements Serializable {
    private static final long serialVersionUID = 1L;
    private String name;
    private int health;
    private int weapons;
    private int lvl;
    private double distance;

    public GameProgress(String name, int health, int weapons, int lvl, double distance) {
        this.health = health;
        this.weapons = weapons;
        this.lvl = lvl;
        this.distance = distance;
        this.name = name;
    }

    public String saveGame(String saveGamesPath) {
        String savingPath = saveGamesPath + this.name + ".dat";
        try (FileOutputStream stOneFOS = new FileOutputStream(savingPath);
                ObjectOutputStream stOneOOS = new ObjectOutputStream(stOneFOS)) {
            stOneOOS.writeObject(this);
            stOneFOS.flush();
            //System.out.println(String.format("Игра \"%s\" успешно сохранена", this.name););
            return savingPath;
        } catch (Exception e) {
            //System.out.println(String.format("Не удалось сохранить игру \"%s\": %s", this.name, e.getMessage()));
            return null;
        }
    }

    @Override
    public String toString() {
        return " Game \"" + name +
                "\": GameProgress{" +
                "health=" + health +
                ", weapons=" + weapons +
                ", lvl=" + lvl +
                ", distance=" + distance +
                '}';
    }
}