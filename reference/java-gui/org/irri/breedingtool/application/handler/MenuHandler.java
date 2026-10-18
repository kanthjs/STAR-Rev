/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.inject.Inject
 *  org.eclipse.e4.core.di.annotations.Execute
 *  org.eclipse.e4.ui.model.application.MApplication
 *  org.eclipse.e4.ui.model.application.ui.basic.MWindow
 *  org.eclipse.e4.ui.model.application.ui.menu.MDirectMenuItem
 *  org.eclipse.e4.ui.model.application.ui.menu.MMenu
 *  org.eclipse.e4.ui.model.application.ui.menu.MMenuElement
 *  org.eclipse.e4.ui.workbench.modeling.EModelService
 *  org.eclipse.e4.ui.workbench.modeling.EPartService
 */
package org.irri.breedingtool.application.handler;

import java.util.Arrays;
import javax.inject.Inject;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.ui.model.application.MApplication;
import org.eclipse.e4.ui.model.application.ui.basic.MWindow;
import org.eclipse.e4.ui.model.application.ui.menu.MDirectMenuItem;
import org.eclipse.e4.ui.model.application.ui.menu.MMenu;
import org.eclipse.e4.ui.model.application.ui.menu.MMenuElement;
import org.eclipse.e4.ui.workbench.modeling.EModelService;
import org.eclipse.e4.ui.workbench.modeling.EPartService;

public class MenuHandler {
    @Inject
    private static EPartService service;
    @Inject
    private static MApplication application;
    @Inject
    private static EModelService modelService;
    public static String PackageName;

    static {
        PackageName = "Star";
    }

    @Execute
    public static void execute() {
    }

    @Execute
    public static void sssetDesignMenuEnabled(boolean value) {
        MMenu designMenu = (MMenu)((MWindow)application.getChildren().get(0)).getMainMenu().getChildren().get(1);
        MMenu cfr_ignored_0 = (MMenu)((MWindow)application.getChildren().get(0)).getMainMenu().getChildren().get(1);
        designMenu.setEnabled(value);
        int i = 0;
        while (i < designMenu.getChildren().size()) {
            if (designMenu.getChildren().get(i) instanceof MDirectMenuItem) {
                MDirectMenuItem directMenuTest = (MDirectMenuItem)designMenu.getChildren().get(i);
                directMenuTest.setEnabled(value);
            } else if (designMenu.getChildren().get(i) instanceof MMenu) {
                MMenu subMenuList = (MMenu)designMenu.getChildren().get(i);
                int x = 0;
                while (x < subMenuList.getChildren().size()) {
                    MDirectMenuItem directMenu = (MDirectMenuItem)subMenuList.getChildren().get(x);
                    directMenu.setEnabled(value);
                    ++x;
                }
                subMenuList.setEnabled(value);
            }
            ++i;
        }
    }

    public static void setDataDependentMenuVisible(boolean value) {
        String[] stringArray = new String[]{"Data", "Analysis", "Analyze"};
    }

    public static void setOnlyVisibleMenu(String[] menus, boolean value) {
        int x = 0;
        while (x < ((MWindow)application.getChildren().get(0)).getMainMenu().getChildren().size()) {
            MMenu menu = (MMenu)((MWindow)application.getChildren().get(0)).getMainMenu().getChildren().get(x);
            int i = 0;
            while (i < menu.getChildren().size()) {
                if (Arrays.asList(menus).contains(menu.getLabel().toString())) {
                    if (menu.getChildren().get(i) instanceof MDirectMenuItem) {
                        ((MDirectMenuItem)menu.getChildren().get(i)).setVisible(value);
                    } else if (menu.getChildren().get(i) instanceof MMenu) {
                        MenuHandler.setOnlyVisibleMenu((MMenu)menu.getChildren().get(i), value);
                        ((MMenuElement)menu.getChildren().get(i)).setVisible(value);
                    }
                }
                ++i;
            }
            ++x;
        }
    }

    public static void deleteMenu(String menuLabel) {
        int x = 0;
        while (x < ((MWindow)application.getChildren().get(0)).getMainMenu().getChildren().size()) {
            MMenu menu = (MMenu)((MWindow)application.getChildren().get(0)).getMainMenu().getChildren().get(x);
            if (menu.getLabel().toString().equals(menuLabel)) {
                menu.setVisible(false);
            }
            ++x;
        }
    }

    public static void setOnlyVisibleMenu(MMenu menu, boolean value) {
        int i = 0;
        while (i < menu.getChildren().size()) {
            if (menu.getChildren().get(i) instanceof MDirectMenuItem) {
                ((MDirectMenuItem)menu.getChildren().get(i)).setVisible(value);
            } else if (menu.getChildren().get(i) instanceof MMenu) {
                MenuHandler.setOnlyVisibleMenu((MMenu)menu.getChildren().get(i), value);
                ((MMenuElement)menu.getChildren().get(i)).setVisible(value);
            }
            ++i;
        }
    }

    public static void setOnsslyVisibleDataMenu(String menuType) {
        MMenu dataMenu = (MMenu)((MWindow)application.getChildren().get(0)).getMainMenu().getChildren().get(2);
        int i = 0;
        while (i < dataMenu.getChildren().size()) {
            if (dataMenu.getChildren().get(i) instanceof MDirectMenuItem) {
                MDirectMenuItem directDataMenu = (MDirectMenuItem)dataMenu.getChildren().get(i);
                if (directDataMenu.getAccessibilityPhrase().toString().equals(menuType)) {
                    directDataMenu.setVisible(true);
                } else {
                    directDataMenu.setVisible(false);
                }
            } else if (dataMenu.getChildren().get(i) instanceof MMenu) {
                MMenu subMenuList = (MMenu)dataMenu.getChildren().get(i);
                if (subMenuList.getAccessibilityPhrase().toString().equals(menuType)) {
                    subMenuList.setVisible(true);
                } else {
                    subMenuList.setVisible(false);
                }
                int x = 0;
                while (x < subMenuList.getChildren().size()) {
                    if (dataMenu.getChildren().get(i) instanceof MDirectMenuItem) {
                        MDirectMenuItem directDataMenu = (MDirectMenuItem)dataMenu.getChildren().get(i);
                        if (directDataMenu.getAccessibilityPhrase().toString().equals(menuType)) {
                            directDataMenu.setVisible(true);
                        } else {
                            directDataMenu.setVisible(false);
                        }
                    }
                    ++x;
                }
            }
            ++i;
        }
    }
}

