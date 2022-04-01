package com.epoint.f9plugin;

import com.epoint.f9plugin.componentized.ComponentResourceFile;
import com.epoint.f9plugin.componentized.IllegalComponentResourceFileException;
import com.epoint.f9plugin.util.F9Notifier;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.openapi.vfs.VirtualFile;

import java.io.IOException;

public class TestAction extends AnAction {

    @Override
    public void actionPerformed(AnActionEvent e) {
        VirtualFile data = e.getData(CommonDataKeys.VIRTUAL_FILE);
        try {
            ComponentResourceFile componentResourceFile = new ComponentResourceFile(data);
            componentResourceFile.copyToDirectory("D:/worksapce/jinshanzhgd/zhgd-jinshan-pro/src/main/resources/META-INF/resources/jinshanzhgd");
        } catch (IllegalComponentResourceFileException | IOException exception) {
            F9Notifier.notifyError(e.getProject(),exception.getMessage());
        }
    }
}
