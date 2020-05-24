package actions;

import java.awt.event.ActionEvent;

public class RemoveRowAction extends AbsDMAction {

    public RemoveRowAction () {
//        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK));
//        putValue(SMALL_ICON, loadIcon(""));
        putValue(NAME, "Remove");
        putValue(SHORT_DESCRIPTION, "Remove row");
    }

    // TODO
    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
