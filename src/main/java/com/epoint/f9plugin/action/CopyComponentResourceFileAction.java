package com.epoint.f9plugin.action;

import com.epoint.f9plugin.componentized.ComponentResourceFile;
import com.epoint.f9plugin.componentized.IllegalComponentResourceFileException;
import com.epoint.f9plugin.settings.F9ProjectSettingsState;
import com.epoint.f9plugin.util.F9Notifier;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.openapi.fileEditor.FileEditorManager;
import com.intellij.openapi.vfs.VirtualFile;

import java.io.IOException;

public class CopyComponentResourceFileAction extends AnAction {

    @Override
    public void actionPerformed(AnActionEvent e) {
        VirtualFile data = e.getData(CommonDataKeys.VIRTUAL_FILE);
        try {
            ComponentResourceFile componentResourceFile = new ComponentResourceFile(data);
            VirtualFile virtualFile = componentResourceFile.copyToDirectory(F9ProjectSettingsState.getInstance().customResourceFilePath);
            if(e.getProject() == null) {
                return;
            }
            FileEditorManager.getInstance(e.getProject()).openFile(virtualFile, true);
        } catch (IllegalComponentResourceFileException | IOException exception) {
            F9Notifier.notifyError(e.getProject(),exception.getMessage());
        }
    }
}
