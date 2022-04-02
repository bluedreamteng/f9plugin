package com.epoint.f9plugin.action;

import com.epoint.f9plugin.componentized.ComponentResourceFile;
import com.epoint.f9plugin.componentized.IllegalComponentResourceFileException;
import com.epoint.f9plugin.settings.F9ProjectSettingsState;
import com.epoint.f9plugin.util.F9Notifier;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.openapi.vfs.VirtualFile;

import java.io.IOException;

public class CopyComponentResourceFileAction extends AnAction {

    @Override
    public void actionPerformed(AnActionEvent e) {
        VirtualFile data = e.getData(CommonDataKeys.VIRTUAL_FILE);
        try {
            ComponentResourceFile componentResourceFile = new ComponentResourceFile(data);
            componentResourceFile.copyToDirectory(F9ProjectSettingsState.getInstance().customResourceFilePath);
        } catch (IllegalComponentResourceFileException | IOException exception) {
            F9Notifier.notifyError(e.getProject(),exception.getMessage());
        }
    }
}
