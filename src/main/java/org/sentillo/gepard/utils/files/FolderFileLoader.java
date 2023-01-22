package org.sentillo.gepard.utils.files;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

public class FolderFileLoader {

    private FolderFileLoader(){}
   public static LoadedFiles loadFilesFromFolder(String path){
        List<LoadedFile> loadedFiles = new ArrayList<>();

        File folderPath = new File(path);
        String[] fileList = folderPath.list();

        for(String fileName : fileList){

            File file = new File(folderPath.getPath()
            + File.separator 
            + fileName);

            if(file.isDirectory()) {
                for(LoadedFile lfile : loadFilesFromFolder(file.getAbsolutePath()).getFiles()){
                    loadedFiles.add(lfile);
                }
                continue;
            }

            LoadedFile.LoadedFileBuilder fileBuilder = LoadedFile.builder();
            fileBuilder.fileName(fileName);
            fileBuilder.filePath(folderPath.getPath());

            try(FileInputStream fileStream = new FileInputStream(file)){
                String content = new String(fileStream.readAllBytes());
                fileBuilder.content(content);
            }
            catch(Exception e){

            }

            loadedFiles.add(fileBuilder.build());
        }
        
        return new LoadedFiles(loadedFiles);
   } 
}
