import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Set;

public class ConvertVideos {
    private static final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in, StandardCharsets.UTF_8));
    private static final Set<String> videoExtensions = Set.of("mp4", "avi", "divx", "mov", "mpg", "wmv", "mkv");


    public static void main(String[] args) throws Exception{
        System.out.println("Path to folder with videos (include subfolders):");
        String stringPath = reader.readLine();
        File f = new File(stringPath);
        recursive(f);
    }

    // способы открыть программу из-под Java
    private static void testOpen(){
        try {
            Desktop.getDesktop().open(new File(""));

            Runtime.getRuntime().exec("C:\\Program Files\\Mozilla Firefox\\firefox.exe");

            ProcessBuilder b = new ProcessBuilder("C:\\troyan.exe","-arg1", "-arg2").inheritIO();
            b.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void recursive(File f) {
        if (f.isDirectory()) {
            for (File ff : f.listFiles()) recursive(ff);
        } else if (f.isFile()) {
            String name = f.getName();
            int ptIdx = name.lastIndexOf('.');
            if (ptIdx!=-1 && videoExtensions.contains(name.substring(ptIdx+1))){
                String fullPath = f.getAbsolutePath();
                long lastModified = f.lastModified();
                String newPath = fullPath.substring(0,fullPath.length()-(name.length()-ptIdx)) + " (2).mp4";
                //String command = String.format("ffmpeg.exe -i \"%s\" -c:v h264 -c:a aac \"%s\"", fullPath, newPath);
                String[] command = {"ffmpeg.exe", "-i", fullPath, "-c:v", "h264", "-c:a", "aac", newPath};
                try {
                    ProcessBuilder ffmpeg = new ProcessBuilder(command).inheritIO();
                    Process process = ffmpeg.start();
                    process.waitFor();
                    File newFile = new File(newPath);
                    newFile.setLastModified(lastModified);
                } catch (IOException | InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
