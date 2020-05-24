package actions;

import java.awt.event.ActionEvent;

public class RefreshAction extends  AbsDMAction {

    public RefreshAction () {
//        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK));
//        putValue(SMALL_ICON, loadIcon(""));
        putValue(NAME, "Refresh");
        putValue(SHORT_DESCRIPTION, "Refresh");
    }

    // TODO
    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
