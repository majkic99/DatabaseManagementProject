package gui;

import app.Main;

import javax.swing.*;
import java.awt.*;

public class Toolbar extends JToolBar {

    public Toolbar() {

        addSeparator();
        add(MainFrame.getInstance().getActionManager().getOpenBasicTableAction());
        addSeparator();
        add(MainFrame.getInstance().getActionManager().getAddRowAction());
        addSeparator();
        add(MainFrame.getInstance().getActionManager().getRemoveRowAction());
        addSeparator();
        add(MainFrame.getInstance().getActionManager().getRefreshAction());
        addSeparator();
        add(MainFrame.getInstance().getActionManager().getFilterAction());
        addSeparator();
        add(MainFrame.getInstance().getActionManager().getRelationsAction());
        addSeparator();
        add(MainFrame.getInstance().getActionManager().getReportAction());
        addSeparator();
        add(MainFrame.getInstance().getActionManager().getSearchAction());
        addSeparator();

        setFloatable(false);
        setBackground(new Color(230,255,255));
    }

}
