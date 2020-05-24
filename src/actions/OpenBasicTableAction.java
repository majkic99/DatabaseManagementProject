package actions;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class OpenBasicTableAction extends AbsDMAction {

    String imeTabele; // TODO

    public OpenBasicTableAction() {
        this.imeTabele = imeTabele;
//        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK));
//        putValue(SMALL_ICON, loadIcon(""));
        putValue(NAME, "Open");
        putValue(SHORT_DESCRIPTION, "Open Table");
    }

    // TODO
    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
