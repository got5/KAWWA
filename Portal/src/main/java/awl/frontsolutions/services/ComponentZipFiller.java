package awl.frontsolutions.services;

import java.io.IOException;
import java.util.zip.ZipOutputStream;

import awl.frontsolutions.internal.DownloadDocType;



public interface ComponentZipFiller {

   public void fillWithThemeCSS(ZipOutputStream zos, String themeName)throws IOException;
   public void fillWithThemeJs(ZipOutputStream zos, String themeName)throws IOException;
   public void fillWithThemeImg(ZipOutputStream zos, String themeName)throws IOException;
   public void fillWithThemeTemplate(ZipOutputStream zos, String themeName, DownloadDocType doctype)throws IOException;
   public void fillWithComponent(ZipOutputStream zos,String urlTag, DownloadDocType dlType, boolean includeExamples, String themeName, String themeDir, boolean tapestry) throws IOException;
    
}
