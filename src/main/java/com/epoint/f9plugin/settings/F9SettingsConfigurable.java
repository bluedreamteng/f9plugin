package com.epoint.f9plugin.settings;

import com.intellij.openapi.options.Configurable;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

/**
 * Provides controller functionality for project settings.
 *
 * @author ztf
 */
public class F9SettingsConfigurable implements Configurable {

    private F9SettingsComponent f9SettingsComponent;

    @Nls(capitalization = Nls.Capitalization.Title)
    @Override
    public String getDisplayName() {
        return "F9 Plugin";
    }

    @Override
    public JComponent getPreferredFocusedComponent() {
        return f9SettingsComponent.getPreferredFocusedComponent();
    }

    @Nullable
    @Override
    public JComponent createComponent() {
        f9SettingsComponent = new F9SettingsComponent();
        return f9SettingsComponent.getPanel();
    }

    @Override
    public boolean isModified() {
        F9ProjectSettingsState projectSettingsState = F9ProjectSettingsState.getInstance();
        return (!f9SettingsComponent.getCustomResourceFilePath().equals(projectSettingsState.customResourceFilePath));
    }

    @Override
    public void apply() {
        F9ProjectSettingsState projectSettings = F9ProjectSettingsState.getInstance();
        projectSettings.customResourceFilePath = f9SettingsComponent.getCustomResourceFilePath();
    }

    @Override
    public void reset() {
        F9ProjectSettingsState settings = F9ProjectSettingsState.getInstance();
        f9SettingsComponent.setCustomResourceFilePath(settings.customResourceFilePath);
    }

    @Override
    public void disposeUIResources() {
        f9SettingsComponent = null;
    }

}
