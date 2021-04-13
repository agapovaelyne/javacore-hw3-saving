import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipEntry;

public class Loading {

    public static void unZip(String zipToUnzip, String dirToUnzip) {
        try (ZipInputStream zis = new ZipInputStream(new FileInputStream(zipToUnzip))) {
            ZipEntry entry;
            String fileFromZipName;

            while ((entry = zis.getNextEntry()) != null) {
                fileFromZipName = entry.getName();
                FileOutputStream fileFromZipFOUT = new FileOutputStream(dirToUnzip + fileFromZipName);
                for (int c = zis.read(); c != -1; c = zis.read()) {
                    fileFromZipFOUT.write(c);
                }
                fileFromZipFOUT.flush();
                zis.closeEntry();
                fileFromZipFOUT.close();
                System.out.println("Распакован файл " + fileFromZipName);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static GameProgress openProgress(String progressFilePath) {
        GameProgress gameProgress = null;
        try (FileInputStream progressFIS = new FileInputStream(progressFilePath);
             ObjectInputStream progressOIS = new ObjectInputStream(progressFIS)) {
            gameProgress = (GameProgress) progressOIS.readObject();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return gameProgress;
    }
}