package com.epoint.f9plugin.settings;

import com.epoint.f9plugin.util.F9ChooseFileUtil;
import com.intellij.openapi.util.EmptyRunnable;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.ui.FieldPanel;
import com.intellij.ui.TitledSeparator;
import com.intellij.util.ui.FormBuilder;

import javax.swing.*;

/**
 * Supports creating and managing a {@link JPanel} for the Settings Dialog.
 *
 * @author ztf
 */
public class F9SettingsComponent {

    private final JPanel myMainPanel;
    private FieldPanel customResourceFilePath = null;


    public F9SettingsComponent() {

        customResourceFilePath = new FieldPanel("", null, (actionEvent) -> {
            VirtualFile virtualFile = F9ChooseFileUtil.chooseDirectory();
            if (virtualFile != null) {
                customResourceFilePath.setText(virtualFile.getPresentableUrl());
            }
        }, EmptyRunnable.getInstance());
        myMainPanel = FormBuilder.createFormBuilder()
                .addComponent(new TitledSeparator("Application Setting"))
                .addLabeledComponent("Custom ResourceFile Path:", customResourceFilePath)
                .getPanel();
    }

    public JPanel getPanel() {
        return myMainPanel;
    }

    public JComponent getPreferredFocusedComponent() {
        return customResourceFilePath;
    }


    public void setCustomResourceFilePath(String path) {
        customResourceFilePath.setText(path);
    }

    public String getCustomResourceFilePath() {
        return customResourceFilePath.getText();
    }
}
