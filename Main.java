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
//        GameProgress game1 = new GameProgress(30,10,12,5.5);
//        GameProgress game2 = new GameProgress(55,9,13,45.1);
//        GameProgress game3 = new GameProgress(9,17,14,4.8);
//
//        if(game1.saveGame("C:/testfiles/games/savegames/save1.dat")){
//            System.out.println("Сохранен успешно!");
//        }
//        if(game2.saveGame("C:/testfiles/games/savegames/save2.dat")){
//            System.out.println("Сохранен успешно!");
//        }
//        if(game3.saveGame("C:/testfiles/games/savegames/save3.dat")){
//            System.out.println("Сохранен успешно!");
//        }
//
//        List<String> savedfiles = Arrays.asList("C:/testfiles/games/savegames/save1.dat",
//                "C:/testfiles/games/savegames/save2.dat", "C:/testfiles/games/savegames/save3.dat");
//
//        try (ZipOutputStream zout = new ZipOutputStream(new FileOutputStream("C:/testfiles/games/savegames/zip.zip"))) {
//            for (String str: savedfiles) {
//                FileInputStream fis = new FileInputStream(str);
//                File file = new File(str);
//                zout.putNextEntry(new ZipEntry(file.getName()));
//                byte[] buffer = new byte[fis.available()];
//                fis.read(buffer);
//                zout.write(buffer);
//                zout.closeEntry();
//                fis.close();
//                if(file.delete())
//                    System.out.println("Удален успешно!");
//            }
//            System.out.println("Запакован успешно!");
//        } catch (Exception ex) {
//            System.out.println(ex.getMessage());
//        }


        if( openZip("C:/testfiles/games/savegames/", "C:/testfiles/games/savegames/zip.zip") )
            System.out.println("Распакован успешно!");

        GameProgress lastsave = openProgress("C:/testfiles/games/savegames/save3.dat");
        System.out.println(lastsave);


    }
}
