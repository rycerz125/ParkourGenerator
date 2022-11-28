package org.sentillo.gepard.utils.files;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class FolderFileLoaderTest {

    @Test
    public void testLoadingTestFolder(){
        List<LoadedFile> loadedFiles = FolderFileLoader.loadFilesFromFolder("./testfolder");

        Assertions.assertEquals(
            "test.txt", 
            loadedFiles.get(1).getFileName()
        );
        Assertions.assertEquals(
            "test\ntest",
            loadedFiles.get(1).getContent()
        );
    }

    @Test
    public void testLoadingFolderTree(){
        List<LoadedFile> loadedFiles = FolderFileLoader.loadFilesFromFolder("./testfolder");
        Assertions.assertEquals("test2.txt", loadedFiles.get(0).getFileName());
    }
    
}