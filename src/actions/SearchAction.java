package actions;

import java.awt.event.ActionEvent;

public class SearchAction extends AbsDMAction {

    public SearchAction () {
//        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK));
//        putValue(SMALL_ICON, loadIcon(""));
        putValue(NAME, "Search");
        putValue(SHORT_DESCRIPTION, "Search");
    }

    // TODO
    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
