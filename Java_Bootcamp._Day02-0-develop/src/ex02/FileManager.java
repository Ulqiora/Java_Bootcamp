package ex02;

import org.jetbrains.annotations.NotNull;

import java.nio.file.*;

public class FileManager {
    private static final FileManager INSTANCE = new FileManager();
    private static Path currentPath;
    private FileManager(){}
    public static FileManager getInstance(){
        return INSTANCE;
    }
    public void addToCurrentPath(String added){
        Path temp= currentPath.resolve(added).normalize();
        checkPath(temp);
        currentPath=temp;
    }
    public void setCurrentPath(String newCurPath){
        Path temp =Paths.get( newCurPath);
        checkPath(temp);
        currentPath=temp;
    }
    public static void checkPath(Path path){
        pathIsAbsolute(path);
        pathIsDirectory(path);
    }
    private static void pathIsAbsolute(Path path){
        if (!path.isAbsolute()) {
            throw new IllegalArgumentException("The current path isn't absolute");
        }
    }
    private static void pathIsDirectory(Path path){
        if (!Files.isDirectory(path)) {
            throw new IllegalArgumentException("The current path isn't absolute");
        }
    }
    public Path getCurrentPath(){
        return currentPath;
    }
    public void commandCD(String pathStr){
        try{
            addToCurrentPath(pathStr);
        } catch(Exception e){
            setCurrentPath(pathStr);
        }
    }
    public void commandLS(){
        try (DirectoryStream<Path> files = Files.newDirectoryStream(currentPath)) {
            for (Path tmp : files) {
                long size;

                if (Files.isDirectory(tmp)) {
                    size = directorySize(tmp);
                } else {
                    size = Files.size(tmp);
                }
                System.out.println(tmp.getFileName() + " " + getSize(size));
            }
        } catch(Exception e) {
            System.out.println("Exception in method: commandLS");
            System.out.println(e.getMessage());
        }
    }

    private static String getSize(long size) {
        return "" + (size / 1000) + " KB";
    }
    private static long directorySize(Path tmpPath) {
        long size = 0;
        try (DirectoryStream<Path> files = Files.newDirectoryStream(tmpPath)) {
            for (Path tmp : files) {
                if (Files.isDirectory(tmp)) {
                    size += directorySize(tmp);
                } else {
                    size += Files.size(tmp);
                }
            }
        } catch (Exception e) {
            System.err.println(e);
        }
        return size;
    }
    public static void commandMV(String before, String after){
        Path source = null;

        try (DirectoryStream<Path> files = Files.newDirectoryStream(currentPath)) {
            for (Path tmp : files) {
                if (tmp.getFileName().toString().equals(before) && Files.isRegularFile(tmp)) {
                    source = tmp;
                    break;
                }
            }
            if (source == null) {
                System.err.println("mv: no such file: " + before);
                files.close();
                return;
            }
            Path temp =currentPath.resolve(after).normalize();
            try {
                pathIsDirectory(temp);
                Files.move(source, temp.resolve(source.getFileName()), StandardCopyOption.REPLACE_EXISTING);
            } catch(Exception e){
                Files.move(source, source.resolveSibling(Paths.get(after)));
            }
        } catch(Exception e) {
            System.err.println("Exception in method: commandMV");
            System.err.println(e.getMessage());
        }
    }
}
