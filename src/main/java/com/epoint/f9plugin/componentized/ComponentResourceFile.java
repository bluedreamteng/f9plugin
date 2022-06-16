package com.epoint.f9plugin.componentized;

import com.epoint.f9plugin.util.FileUtil;
import com.intellij.openapi.ui.MessageDialogBuilder;
import com.intellij.openapi.util.text.StringUtil;
import com.intellij.openapi.vfs.VirtualFile;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

public class ComponentResourceFile {
    public static final String COMPONENT_RESOURCE_PATH_FLAG = "jar!/META-INF/resources/";
    private final VirtualFile virtualFile;
    private final String relativePath;

    public ComponentResourceFile(VirtualFile virtualFile) throws IllegalComponentResourceFileException {
        if (virtualFile == null) {
            throw new IllegalComponentResourceFileException("virtualFile must not be null");
        }
        if (!virtualFile.getPath().contains(COMPONENT_RESOURCE_PATH_FLAG)) {
            throw new IllegalComponentResourceFileException("virtualFile path is not a valid component resource file path");
        }
        this.virtualFile = virtualFile;
        relativePath = virtualFile.getPath().split(COMPONENT_RESOURCE_PATH_FLAG)[1];
    }

    public VirtualFile copyToDirectory(@NotNull String directoryPath) throws IOException {
        String relativeDir = getRelativeDir();
        if (StringUtil.isEmpty(relativeDir)) {
            relativeDir = "";
        } else if (relativeDir.contains("/")) {
            String root = relativeDir.substring(0, relativeDir.indexOf("/"));
            MessageDialogBuilder.YesNo yesNo = MessageDialogBuilder.yesNo("根目录", "检测到根目录：" + root + "，" + "是否去除？");
            if (yesNo.isYes()) {
                relativeDir = relativeDir.substring(relativeDir.indexOf("/") + 1);
            }
        } else {
            String root = relativeDir;
            MessageDialogBuilder.YesNo yesNo = MessageDialogBuilder.yesNo("根目录", "检测到根目录：" + root + "，" + "是否去除？");
            if (yesNo.isYes()) {
                relativeDir = "";
            }
        }
        String containingFileDirPath = directoryPath + "/" + relativeDir;
        return FileUtil.copyFileToTargetDirectory(containingFileDirPath,virtualFile);
    }

    public String getRelativeDir() {
        if (relativePath.contains("/")) {
            return relativePath.substring(0, relativePath.lastIndexOf("/"));
        } else {
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
