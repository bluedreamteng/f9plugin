package com.epoint.f9plugin;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.openapi.vfs.VirtualFile;

public class TestAction extends AnAction {

    @Override
    public void actionPerformed(AnActionEvent e) {
        VirtualFile data = e.getData(CommonDataKeys.VIRTUAL_FILE);
        System.out.println(data.getPath());
        System.out.println(data.getCanonicalPath());
    }
}
