package actions;

import com.sun.xml.internal.ws.wsdl.parser.MemberSubmissionAddressingWSDLParserExtension;
import gui.MainFrame;
import jdk.nashorn.internal.scripts.JO;
import resource.DBNode;
import resource.implementation.Entity;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

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

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(0,4));

        Entity entity = MainFrame.getInstance().getAppCore().getCurrentEntity();

        ArrayList<String> attributeNames = new ArrayList<>();
        for(DBNode attribute : entity.getChildren()) {
            attributeNames.add(attribute.getName());
        }

        int x,y;
        x = y = 0;
        FilterAndSortAction.Constraints c = new FilterAndSortAction.Constraints(x,y);

        for(int i = 0; i < attributeNames.size(); i++) {

            // Lista atributa
            c.x = 0;
            c.y = i;
            JLabel label = new JLabel(attributeNames.get(i) + ":");
            panel.add(label, c);

            c.x++;

            JTextField txt = new JTextField();
            txt.setMargin(new Insets(5,5,5,5));
            panel.add(txt, c);


        }
        JOptionPane.showConfirmDialog(null, panel, "Add row", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
    }
}
