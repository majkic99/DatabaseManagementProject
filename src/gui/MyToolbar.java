package gui;

import javax.swing.*;
import java.awt.*;

public class MyToolbar extends JToolBar {

    public MyToolbar() {

//        addSeparator();
//        add(MainFrame.getInstance().getActionManager().getOpenBasicTableAction());
        addSeparator();
        add(MainFrame.getInstance().getActionManager().getAddRowAction());
        addSeparator();
        add(MainFrame.getInstance().getActionManager().getRemoveRowAction());
        addSeparator();
        add(MainFrame.getInstance().getActionManager().getEditAction());
        addSeparator();
        add(MainFrame.getInstance().getActionManager().getFilterAndSortAction());
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
