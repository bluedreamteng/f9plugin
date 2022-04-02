package com.epoint.f9plugin.componentized;

import com.intellij.openapi.vfs.VirtualFile;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.*;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

public class ComponentResourceFileTest {
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void constructor_fileIsNull_ThrowIllegalComponentResourceFileException() throws IllegalComponentResourceFileException {
        thrown.expect(IllegalComponentResourceFileException.class);
        thrown.expectMessage("virtualFile must not be null");
        new ComponentResourceFile(null);
    }

    @Test
    public void constructor_notvalidpath_IllegalComponentResourceFileException() throws IllegalComponentResourceFileException {
        VirtualFile virtualFile = mock(VirtualFile.class);
        String path = "E:/xmmavenrepositry/shanghaijinshan/com/epoint/frame/epoint-frame-sso/9.5.0-sp2/epoint-frame-sso-9.5.0-sp2.jar!/resources/adlogin.html";
        doReturn(false).when(virtualFile).isDirectory();
        doReturn(path).when(virtualFile).getPath();

        thrown.expect(IllegalComponentResourceFileException.class);
        thrown.expectMessage("virtualFile path is not a valid component resource file path");
        new ComponentResourceFile(virtualFile);

        String path2 = "E:/xmmavenrepositry/shanghaijinshan/com/epoint/frame/epoint-frame-sso/9.5.0-sp2/epoint-frame-sso-9.5.0-sp2.jar!/META-INF/resources";
        doReturn(path2).when(virtualFile).getPath();
        thrown.expect(IllegalComponentResourceFileException.class);
        thrown.expectMessage("virtualFile path is not a valid component resource file path");
        new ComponentResourceFile(virtualFile);
    }


    @Test
    public void constructor_validpath_IllegalComponentResourceFileException() throws IllegalComponentResourceFileException {
        VirtualFile virtualFile = mock(VirtualFile.class);
        doReturn(false).when(virtualFile).isDirectory();
        String path = "E:/xmmavenrepositry/shanghaijinshan/com/epoint/frame/epoint-frame-sso/9.5.0-sp2/epoint-frame-sso-9.5.0-sp2.jar!/META-INF/resources/adlogin.html";
        doReturn(path).when(virtualFile).getPath();

        ComponentResourceFile componentResourceFile = new ComponentResourceFile(virtualFile);

        assertEquals(virtualFile,componentResourceFile.getVirtualFile());
        assertEquals("adlogin.html",componentResourceFile.getRelativePath());
    }

    @Test
    public void getRelativeDir_rootFile_returnBlank() throws IllegalComponentResourceFileException {
        VirtualFile virtualFile = mock(VirtualFile.class);
        doReturn(false).when(virtualFile).isDirectory();
        String path = "E:/xmmavenrepositry/shanghaijinshan/com/epoint/frame/epoint-frame-sso/9.5.0-sp2/epoint-frame-sso-9.5.0-sp2.jar!/META-INF/resources/adlogin.html";
        doReturn(path).when(virtualFile).getPath();

        ComponentResourceFile componentResourceFile = new ComponentResourceFile(virtualFile);

        assertEquals("",componentResourceFile.getRelativeDir());
    }

    @Test
    public void getRelativeDir_dirFile_returnDirPath() throws IllegalComponentResourceFileException {
        VirtualFile virtualFile = mock(VirtualFile.class);
        doReturn(false).when(virtualFile).isDirectory();
        String path = "E:/xmmavenrepositry/shanghaijinshan/com/epoint/frame/epoint-frame-sso/9.5.0-sp2/epoint-frame-sso-9.5.0-sp2.jar!/META-INF/resources/a/b/adlogin.html";
        doReturn(path).when(virtualFile).getPath();

        ComponentResourceFile componentResourceFile = new ComponentResourceFile(virtualFile);

        assertEquals("a/b",componentResourceFile.getRelativeDir());
    }


}