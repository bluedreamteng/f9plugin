package com.epoint.f9plugin.componentized;

import com.intellij.openapi.util.text.StringUtil;
import com.intellij.openapi.vfs.VirtualFile;
import com.sun.istack.NotNull;

import java.io.IOException;

public class ComponentResourceFile {
    public static final String COMPONENT_RESOURCE_PATH_FLAG = "jar!/META-INF/resources/";
    private final VirtualFile virtualFile;
    private final String relativePath;

    public ComponentResourceFile(VirtualFile virtualFile) throws IllegalComponentResourceFileException {
        if (virtualFile == null) {
            throw new IllegalComponentResourceFileException("virtualFile must not be null");
        }
        if (virtualFile.isDirectory()) {
            throw new IllegalComponentResourceFileException("virtualFile must be a file");
        }

        if (!virtualFile.getPath().contains(COMPONENT_RESOURCE_PATH_FLAG)) {
            throw new IllegalComponentResourceFileException("virtualFile path is not a valid component resource file path");
        }
        this.virtualFile = virtualFile;
        relativePath = virtualFile.getPath().split(COMPONENT_RESOURCE_PATH_FLAG)[1];
    }

    public void copyToDirectory(@NotNull String directoryPath) throws IOException {
        String containingFileDirPath;
        String relativeDir = getRelativeDir();
        if (StringUtil.isEmpty(relativeDir)) {
            containingFileDirPath = directoryPath;
        }
        else if (relativeDir.contains("/")) {

        }
    }

    public String getRelativeDir() {
        if (relativePath.contains("/")) {
            return relativePath.substring(0, relativePath.lastIndexOf("/"));
        }
        else {
            return "";
        }
    }


    public VirtualFile getVirtualFile() {
        return virtualFile;
    }

    public String getRelativePath() {
        return relativePath;
    }

}
