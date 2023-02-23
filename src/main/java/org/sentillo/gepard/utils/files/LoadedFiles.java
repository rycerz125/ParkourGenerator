package org.sentillo.gepard.utils.files;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public class LoadedFiles {
    @Getter
    private List<LoadedFile> files;

    public LoadedFile findFileByName(String name){
        for(LoadedFile f : files){
            if(f.getFileName().equals(name))
                return f;
        }
        return null;
    }

    public LoadedFiles findFilesByName(String name){
        List<LoadedFile> found = new ArrayList<>();

        for(LoadedFile f : files){
            if(f.getFileName().equals(name))
                found.add(f);
        }

        return new LoadedFiles(found);
    }

    public LoadedFiles getAllFromFolder(String folder){
        List<LoadedFile> found = new ArrayList<>();

        for(LoadedFile f : files){
            if(f.getFilePath().startsWith(folder))
                found.add(f);
        }

        return new LoadedFiles(found);
    }
}
