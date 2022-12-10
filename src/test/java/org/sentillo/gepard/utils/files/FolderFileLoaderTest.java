package org.sentillo.gepard.utils.files;

import java.io.File;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class FolderFileLoaderTest {

    @Test
    public void testLoadingTestFolder(){
        List<LoadedFile> loadedFiles = FolderFileLoader.loadFilesFromFolder("src" + File.separator + "test" + File.separator + "testfolder");

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
        List<LoadedFile> loadedFiles = FolderFileLoader.loadFilesFromFolder("src" + File.separator + "test" + File.separator + "testfolder");
        Assertions.assertEquals("test2.txt", loadedFiles.get(0).getFileName());
    }
    
}
