package hwCore32;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.util.Arrays;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

public class Main {

    static boolean openZip(String path, String zipfile){
        try (ZipInputStream zin = new ZipInputStream(new FileInputStream(zipfile))) {
            ZipEntry entry;
            String name;
            while ((entry = zin.getNextEntry()) != null) {
                name = entry.getName();
                FileOutputStream fout = new FileOutputStream(path+name);
                for (int c = zin.read(); c != -1; c = zin.read()) {
                    fout.write(c);
                }
                fout.flush();
                zin.closeEntry();
                fout.close();
            }
            return true;
        } catch (Exception ex) {
            return false;
            //System.out.println(ex.getMessage());
        }
    }

    static GameProgress openProgress(String savedat){
        GameProgress gameProgress = null;
        try (FileInputStream  fis = new FileInputStream(savedat);
             ObjectInputStream ois = new ObjectInputStream(fis)) {
            gameProgress = (GameProgress) ois.readObject();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return gameProgress;
    }

    public static void main(String[] args) {

        if( openZip("C:/testfiles/games/savegames/", "C:/testfiles/games/savegames/zip.zip") )
            System.out.println("Распакован успешно!");

        GameProgress lastsave = openProgress("C:/testfiles/games/savegames/save3.dat");
        System.out.println(lastsave);

    }
}
