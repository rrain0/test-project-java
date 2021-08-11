import java.io.File;
import java.time.LocalDateTime;
import java.time.ZoneId;

public class RestoreFileModifiedTime {
    public static void main(String[] args) {
        String[] paths = {"I:\\"};

        for (String p : paths) subFiles(new File(p));
    }

    private static void subFiles(File f){
        if (f.isDirectory()){
            for (File subf : f.listFiles()) subFiles(subf);
        } else if (f.isFile()){
            fileOperation(f);
        }
    }

    private static void fileOperation(File f){
        if (f.getName().matches("V_\\d{8}_\\d{6}.*\\.mp4")){
            int[] dateTime = {
                    Integer.parseInt(f.getName().substring(8,10)), //day
                    Integer.parseInt(f.getName().substring(6,8)), //month
                    Integer.parseInt(f.getName().substring(2,6)), //year
                    Integer.parseInt(f.getName().substring(11,13)), //hour
                    Integer.parseInt(f.getName().substring(13,15)), //minute
                    Integer.parseInt(f.getName().substring(15,17)), //second
            };
            LocalDateTime time = LocalDateTime.of(dateTime[2], dateTime[1], dateTime[0], dateTime[3], dateTime[4], dateTime[5]);
            long timeMills = time.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
            f.setLastModified(timeMills);
        }
    }
}
