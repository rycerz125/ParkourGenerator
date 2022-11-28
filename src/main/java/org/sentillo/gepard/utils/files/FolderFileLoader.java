package org.sentillo.gepard.utils.files;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

public class FolderFileLoader {
   public static List<LoadedFile> loadFilesFromFolder(String path){
        List<LoadedFile> loadedFiles = new ArrayList<>();
        List<File> directoriesToRun = new ArrayList<>();

        File folderPath = new File(path);
        String[] fileList = folderPath.list();

        for(String fileName : fileList){

            File file = new File(folderPath.getPath()
            + File.separator 
            + fileName);

            if(file.isDirectory()) {
                directoriesToRun.add(file);
                continue;
            }

            LoadedFile.LoadedFileBuilder fileBuilder = LoadedFile.builder();
            fileBuilder.fileName(fileName);
            fileBuilder.filePath(folderPath.getPath());

            try{
                FileInputStream fileStream = new FileInputStream(file);
                String content = new String(fileStream.readAllBytes());
                fileStream.close();

                fileBuilder.content(content);
            }
            catch(Exception e){}

            for(File f : directoriesToRun){
                for(LoadedFile lfile : loadFilesFromFolder(f.getAbsolutePath())){
                    loadedFiles.add(lfile);
                }
            }

            loadedFiles.add(fileBuilder.build());
        }
        
        return loadedFiles;
   } 
}
