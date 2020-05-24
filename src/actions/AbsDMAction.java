package actions;

import javax.swing.AbstractAction;
import javax.swing.Icon;
import javax.swing.ImageIcon;

@SuppressWarnings("serial")
public abstract class AbsDMAction extends AbstractAction {

    public Icon loadIcon(String name) {
        return new ImageIcon(name);
    }

}