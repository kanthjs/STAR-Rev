/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.inject.Inject
 *  org.eclipse.e4.core.di.annotations.Optional
 *  org.eclipse.e4.core.services.events.IEventBroker
 *  org.eclipse.e4.ui.di.UIEventTopic
 *  org.eclipse.e4.ui.model.application.MApplication
 *  org.eclipse.e4.ui.model.application.ui.MUIElement
 *  org.eclipse.e4.ui.model.application.ui.MUILabel
 *  org.eclipse.e4.ui.model.application.ui.basic.MPart
 *  org.eclipse.e4.ui.model.application.ui.basic.MPartStack
 *  org.eclipse.e4.ui.model.application.ui.basic.MWindow
 *  org.eclipse.e4.ui.workbench.modeling.EModelService
 *  org.eclipse.e4.ui.workbench.modeling.EPartService
 *  org.eclipse.e4.ui.workbench.modeling.EPartService$PartState
 *  org.eclipse.swt.widgets.Display
 *  org.eclipse.swt.widgets.MessageBox
 *  org.eclipse.swt.widgets.Table
 *  org.osgi.service.event.Event
 *  org.osgi.service.event.EventHandler
 */
package org.irri.breedingtool.application.handler;

import java.io.File;
import javax.annotation.PostConstruct;
import javax.inject.Inject;
import org.eclipse.e4.core.di.annotations.Optional;
import org.eclipse.e4.core.services.events.IEventBroker;
import org.eclipse.e4.ui.di.UIEventTopic;
import org.eclipse.e4.ui.model.application.MApplication;
import org.eclipse.e4.ui.model.application.ui.MUIElement;
import org.eclipse.e4.ui.model.application.ui.MUILabel;
import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.e4.ui.model.application.ui.basic.MPartStack;
import org.eclipse.e4.ui.model.application.ui.basic.MWindow;
import org.eclipse.e4.ui.workbench.modeling.EModelService;
import org.eclipse.e4.ui.workbench.modeling.EPartService;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Table;
import org.irri.breedingtool.application.handler.MenuHandler;
import org.irri.breedingtool.application.handler.PartStackHandler;
import org.irri.breedingtool.manager.impl.DataManipulationManager;
import org.irri.breedingtool.manager.impl.ProjectExplorerManager;
import org.irri.breedingtool.projectexplorer.view.ProjectExplorerView;
import org.irri.breedingtool.projectexplorer.view.RJavaManagerInitializer;
import org.irri.breedingtool.utility.WindowDialogControlUtil;
import org.osgi.service.event.Event;
import org.osgi.service.event.EventHandler;

public class EventBrokerHandler {
    private EventHandler testHandler;
    public String lastPartActiveID = "null";
    ProjectExplorerManager projMan = new ProjectExplorerManager();
    DataManipulationManager dataManipulationManager = new DataManipulationManager();
    @Inject
    private static EPartService service;
    @Inject
    private static MApplication application;
    @Inject
    private static EModelService modelService;
    @Inject
    private EPartService partService;

    public static void activate() {
        System.out.println("ACTIVATED");
    }

    @PostConstruct
    public void pc(IEventBroker eventBroker) {
        this.testHandler = new EventHandler(){

            public void handleEvent(Event event) {
                final Object part = event.getProperty("ChangedElement");
                boolean tbr = (Boolean)event.getProperty("NewValue");
                if (part instanceof MPart) {
                    if (((MPart)part).getElementId().equals("retainer")) {
                        return;
                    }
                    service.activate((MPart)part);
                    service.showPart((MPart)part, EPartService.PartState.ACTIVATE);
                    if (!tbr) {
                        String elemId = ((MPart)part).getElementId();
                        if (((MUILabel)part).getLabel().toString().startsWith("*")) {
                            int style = 456;
                            MessageBox messageBox = new MessageBox(Display.getCurrent().getActiveShell(), style);
                            messageBox.setMessage("The file " + ((MUILabel)part).getLabel().replace("*", "") + " is modified. Would you like to save the file?");
                            int rc = messageBox.open();
                            switch (rc) {
                                case 256: {
                                    service.activate((MPart)part);
                                    service.activate((MPart)part);
                                    service.activate((MPart)part);
                                    ((MPart)part).setVisible(true);
                                    service.showPart((MPart)part, EPartService.PartState.ACTIVATE);
                                    Runnable timer = new Runnable(){

                                        @Override
                                        public void run() {
                                            service.activate((MPart)part);
                                            ((MPart)part).setVisible(true);
                                            service.showPart((MPart)part, EPartService.PartState.ACTIVATE);
                                            System.out.println("Timer Activated");
                                        }
                                    };
                                    Display.getCurrent().getActiveShell().getDisplay().timerExec(350, timer);
                                    break;
                                }
                                case 64: {
                                    try {
                                        Table table = DataManipulationManager.getActiveTable(elemId);
                                        DataManipulationManager.removeTable(table);
                                    }
                                    catch (Exception exception) {}
                                    WindowDialogControlUtil.closeAllWindowDialogOfFile(((MPart)part).getElementId());
                                    String originalTmpFilepath = ((MPart)part).getElementId().replaceAll(".csv", RJavaManagerInitializer.VARINFO_ORIGINALFILE_EXTENSION);
                                    EventBrokerHandler.this.projMan.deleteFile(originalTmpFilepath);
                                    ((MPart)part).getParent().getChildren().remove(part);
                                    break;
                                }
                                case 128: {
                                    EventBrokerHandler.this.dataManipulationManager.keepOriginalFile(new File(elemId));
                                    WindowDialogControlUtil.closeAllWindowDialogOfFile(((MPart)part).getElementId());
                                    try {
                                        Table table2 = DataManipulationManager.getActiveTable(elemId);
                                        DataManipulationManager.removeTable(table2);
                                    }
                                    catch (Exception exception) {}
                                    ((MPart)part).getParent().getChildren().remove(part);
                                }
                            }
                        } else if (!((MPart)part).getElementId().equals("retainer")) {
                            ((MPart)part).getParent().getChildren().remove(part);
                        }
                    }
                }
            }
        };
    }

    @Inject
    @Optional
    public void partActivation(@UIEventTopic(value="org/eclipse/e4/ui/LifeCycle/activate") Event event) {
        MWindow mainWindow = (MWindow)modelService.find("MainWindow", (MUIElement)application);
        MPartStack ViewerStack = (MPartStack)modelService.find("ViewerStack", (MUIElement)application);
        if (PartStackHandler.getActiveElementID() == null) {
            return;
        }
        ProjectExplorerManager projectMan = new ProjectExplorerManager();
        MPart activePart = (MPart)event.getProperty("ChangedElement");
        if (ProjectExplorerView.rootTree.getItemCount() > 0) {
            mainWindow.setLabel(String.valueOf(RJavaManagerInitializer.APPLICATION_TITLE) + projectMan.getWorkspacePath());
            if (PartStackHandler.getActiveElementID() == null || ViewerStack.getChildren().isEmpty()) {
                return;
            }
            if (PartStackHandler.getActiveElementID().toString().endsWith(".csv")) {
                mainWindow.setLabel(String.valueOf(RJavaManagerInitializer.APPLICATION_TITLE) + projectMan.getWorkspacePath());
                MenuHandler.setDataDependentMenuVisible(true);
                if (!this.lastPartActiveID.equals(PartStackHandler.getActiveElementID())) {
                    this.lastPartActiveID = PartStackHandler.getActiveElementID();
                }
            } else {
                MenuHandler.setDataDependentMenuVisible(false);
            }
            System.out.println(PartStackHandler.getActiveElementID());
            System.out.println(projectMan.getProjectPath());
            String newWindowTitle = String.valueOf(RJavaManagerInitializer.APPLICATION_TITLE) + PartStackHandler.getActiveElementID().replace("retainer", "");
            mainWindow.setLabel(newWindowTitle);
        }
        if (!activePart.getElementId().toString().equals("ProjectExplorer")) {
            activePart.getElementId().toString().length();
        }
    }
}

