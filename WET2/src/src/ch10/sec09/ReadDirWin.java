package src.ch10.sec09;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.Scanner;

public class ReadDirWin {
    public static void main(String[] args) throws IOException, InterruptedException {
       // Process p = new ProcessBuilder("/bin/ls", "-l").directory(Paths.get("/tmp").toFile()).start();
     Process p = new ProcessBuilder("cmd").directory(Paths.get(".").toFile()).start();//Windows
        try (Scanner in = new Scanner(p.getInputStream())) {
            while (in.hasNextLine())
                System.out.println(in.nextLine());
        }
        int result = p.waitFor();
        System.out.println("Exit value: " + result);
    }
}
