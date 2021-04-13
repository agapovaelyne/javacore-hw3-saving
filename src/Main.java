import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Main {

    public static String saveGamesPath = "/Users/elina.agapova/NTLG/Games/savegames/";
    public static String zippedSavingsPath = saveGamesPath + "allStations.zip";

    public static String zipSavings(String[] savings) {
        try (ZipOutputStream zout = new ZipOutputStream(new FileOutputStream(zippedSavingsPath))) {
            for (String saving: savings) {
                FileInputStream savingFIS = new FileInputStream(saving);
                ZipEntry savingEntry = new ZipEntry(saving.substring(saving.lastIndexOf("/")+1));
                zout.putNextEntry(savingEntry);
                byte[] buffer = new byte[savingFIS.available()];
                savingFIS.read(buffer);
                zout.write(buffer);
                zout.closeEntry();
                savingFIS.close();
            }
            zout.flush();
            return "Файлы сохранений успешно заархивированы";
        } catch (Exception e) {
            return String.format("Возникла ошибка при архивации файлов: %s", e.getMessage());
        }
    }

    public static void removeUnZippedSavings(String[] savings) {
        for (String saving: savings) {
            File savingFile = new File(saving);
            if (savingFile.delete()) {
                System.out.printf("Файл \"%s\" успешно удален\n", saving.substring(saving.lastIndexOf("/")+1));
            }
        }
    }

    public static void main(String[] args) {

        //Создать три экземпляра класса GameProgress
        GameProgress stationOne = new GameProgress("station 1",10, 3, 1, 4.39);
        GameProgress stationTwo = new GameProgress("station 2",7, 4, 2, 8.03);
        GameProgress stationThree = new GameProgress("station 3",9, 7, 4, 10);

        //Сохранить сериализованные объекты GameProgress в папку savegames из предыдущей задачи
        String [] savings = new String [] {
                                        stationOne.saveGame(saveGamesPath),
                                        stationTwo.saveGame(saveGamesPath),
                                        stationThree.saveGame(saveGamesPath)
                                        };

        //Созданные файлы сохранений из папки savegames запаковать в архив zip.
       System.out.println(zipSavings(savings));

        //Удалить файлы сохранений, лежащие вне архива.
       removeUnZippedSavings(savings);

       //Задача 3: Произвести распаковку архива в папке savegames.
        Loading.unZip(zippedSavingsPath, saveGamesPath);

        //Задача 3:Произвести считывание и десериализацию одного из разархивированных файлов save.dat
        // и вывести в консоль состояние сохранненой игры.
        System.out.println(Loading.openProgress(savings[0]));
    }
}
