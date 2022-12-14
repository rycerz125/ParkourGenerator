package org.sentillo.gepard.utils.files;

import java.io.File;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class FolderFileLoaderTest {

    @Test
    public void testLoadingTestFolder(){
        List<LoadedFile> loadedFiles = FolderFileLoader.loadFilesFromFolder("src" + File.separator + "test" + File.separator + "testfolder").getLoadedFiles();

        Assertions.assertEquals(
            "test.txt", 
            loadedFiles.get(1).getFileName()
        );
        Assertions.assertEquals(
            "testtest",
            loadedFiles.get(1).getContent().trim()
        );
    }

    @Test
    public void testLoadingFolderTree(){
        List<LoadedFile> loadedFiles = FolderFileLoader.loadFilesFromFolder("src" + File.separator + "test" + File.separator + "testfolder").getLoadedFiles();
        Assertions.assertEquals("test2.txt", loadedFiles.get(0).getFileName());
    }

    @Test
    public void searchForFiles(){
        LoadedFiles loadedFiles = FolderFileLoader.loadFilesFromFolder("src" + File.separator + "test" + File.separator + "testfolder" + File.separator + "finder");
        LoadedFiles found = loadedFiles.findFilesByName("test");

        Assertions.assertEquals(2, found.getLoadedFiles().size());
    }

    @Test
    public void getByFolderTest(){
        LoadedFiles loadedFiles = FolderFileLoader.loadFilesFromFolder("src" + File.separator + "test" + File.separator + "testfolder" + File.separator + "finder");
        LoadedFiles found = loadedFiles.findFilesByName("fuld");
        LoadedFiles newFolder = loadedFiles.getAllFromFolder(found.getLoadedFiles().get(0).getFilePath());
        Assertions.assertEquals(3, newFolder.getLoadedFiles().size());
    }
    
}
