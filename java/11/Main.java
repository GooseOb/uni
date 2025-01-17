import java.io.*;
import java.nio.file.*;

public class Main {
  public static void main(String[] args) throws IOException {
    createDirectories();

    Path file1 = Paths.get("Dir0/Dir1/Dir11/File1.txt");

    Files.copy(file1, Paths.get("Dir0/Dir3/Dir32/File1.txt"), StandardCopyOption.REPLACE_EXISTING);

    Files.write(
        Paths.get("Dir0/Dir1/Dir11/File2.txt"),
        new String(Files.readAllBytes(file1)).replace('a', '@').getBytes());

    Files.write(
        Paths.get("Dir0/Dir1/Dir11/Dir111/File3.txt"),
        new StringBuilder(new String(Files.readAllBytes(file1))).reverse().toString().getBytes());

    String file4Path = "Dir0/Dir2/File4.txt";
    Files.copy(file1, Paths.get(file4Path), StandardCopyOption.REPLACE_EXISTING);
    RandomAccessFile raf = new RandomAccessFile(file4Path, "rw");
    for (int i = 0; i <= 200 && i < raf.length(); i += 2) {
      raf.seek(i);
      raf.writeByte(' ');
    }
    raf.close();

    printDirectoryContents(new File("Dir0"));
  }

  private static void createDirectories() throws IOException {
    Files.createDirectories(Paths.get("Dir0/Dir1/Dir11/Dir111"));
    Files.createDirectories(Paths.get("Dir0/Dir2"));
    Files.createDirectories(Paths.get("Dir0/Dir3/Dir31"));
    Files.createDirectories(Paths.get("Dir0/Dir3/Dir32"));
  }

  private static void printDirectoryContents(File dir) {
    File[] files = dir.listFiles();
    if (files != null) {
      for (File file : files) {
        if (file.isDirectory()) {
          System.out.println("Directory: " + file.getAbsolutePath());
          printDirectoryContents(file);
        } else {
          System.out.println("File: " + file.getAbsolutePath() + " (" + file.length() + " bytes)");
        }
      }
    }
  }
}
