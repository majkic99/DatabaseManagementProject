package actions;

import com.sun.xml.internal.ws.wsdl.parser.MemberSubmissionAddressingWSDLParserExtension;

import java.awt.event.ActionEvent;

public class AddRowAction extends AbsDMAction {

    public AddRowAction() {
//        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK));
//        putValue(SMALL_ICON, loadIcon(""));
        putValue(NAME, "Add");
        putValue(SHORT_DESCRIPTION, "Add row");
    }

    // TODO
    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
