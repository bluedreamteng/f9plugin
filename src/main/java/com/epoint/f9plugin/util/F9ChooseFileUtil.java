package com.epoint.f9plugin.util;

import com.intellij.openapi.fileChooser.FileChooser;
import com.intellij.openapi.fileChooser.FileChooserDescriptor;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;

public class F9ChooseFileUtil {
    public static VirtualFile chooseDirectory() {
        FileChooserDescriptor fileChooserDescriptor = new FileChooserDescriptor(false, true, false, false, false, false);
        return FileChooser.chooseFile(fileChooserDescriptor,null, null);
    }

    public static VirtualFile chooseFile() {
        FileChooserDescriptor fileChooserDescriptor = new FileChooserDescriptor(true, false, false, false, false, false);
        return FileChooser.chooseFile(fileChooserDescriptor, null, null);
    }
}
