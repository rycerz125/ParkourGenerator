package org.sentillo.gepard.utils.files;

import lombok.Builder;
import lombok.Getter;

@Builder
public class LoadedFile {
   @Getter
   private String fileName;
   @Getter
   private String filePath;
   @Getter
   private String content;
}
