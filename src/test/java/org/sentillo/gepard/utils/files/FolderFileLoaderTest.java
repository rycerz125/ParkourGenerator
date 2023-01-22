package org.sentillo.gepard.utils.files;

import java.io.File;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class FolderFileLoaderTest {

    @Test
    void testLoadingTestFolder(){
        List<LoadedFile> loadedFiles = FolderFileLoader.loadFilesFromFolder("src" + File.separator + "test" + File.separator + "testfolder").getFiles();

        Assertions.assertEquals(
            "test.color",
            loadedFiles.get(1).getFileName()
        );
    }

    @Test
    void testLoadingFolderTree(){
        List<LoadedFile> loadedFiles = FolderFileLoader.loadFilesFromFolder("src" + File.separator + "test" + File.separator + "testfolder").getFiles();
        Assertions.assertEquals("blockConversion", loadedFiles.get(0).getFileName());
    }

    @Test
    void searchForFiles(){
        LoadedFiles loadedFiles = FolderFileLoader.loadFilesFromFolder("src" + File.separator + "test" + File.separator + "testfolder" + File.separator + "finder");
        LoadedFiles found = loadedFiles.findFilesByName("test");

        Assertions.assertEquals(2, found.getFiles().size());
    }

    @Test
    void getByFolderTest(){
        LoadedFiles loadedFiles = FolderFileLoader.loadFilesFromFolder("src" + File.separator + "test" + File.separator + "testfolder" + File.separator + "finder");
        LoadedFiles found = loadedFiles.findFilesByName("fuld");
        LoadedFiles newFolder = loadedFiles.getAllFromFolder(found.getFiles().get(0).getFilePath());
        Assertions.assertEquals(3, newFolder.getFiles().size());
    }
    
}
