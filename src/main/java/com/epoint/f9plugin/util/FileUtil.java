package com.epoint.f9plugin.util;

import com.intellij.openapi.vfs.VfsUtil;
import com.intellij.openapi.vfs.VirtualFile;

import java.io.IOException;

/**
 * 文件操作相关工具类
 *
 * @author ztf
 */
public class FileUtil {

    public static VirtualFile copyFileToTargetDirectory(String containingFileDirPath, VirtualFile virtualFile) throws IOException {
        VirtualFile containingDir = VfsUtil.createDirectoryIfMissing(containingFileDirPath);
        assert containingDir != null;
        if (virtualFile.isDirectory()) {
            String newDirPath = containingFileDirPath + "/" + virtualFile.getName();
            VirtualFile newDir = VfsUtil.createDirectoryIfMissing(newDirPath);
            assert newDir != null;
            VfsUtil.copyDirectory(FileUtil.class, virtualFile, newDir, null);
            return containingDir;
        } else {
            return VfsUtil.copy(FileUtil.class, virtualFile, containingDir);
        }
    }
}