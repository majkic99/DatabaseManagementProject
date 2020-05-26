package actions;

import gui.MainFrame;
import resource.DBNode;
import resource.implementation.Attribute;
import resource.implementation.Entity;
import utils.Constraints;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

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

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(0,2,10,10));

        Entity entity = MainFrame.getInstance().getAppCore().getCurrentEntity();

        ArrayList<Attribute> primaryKeys = new ArrayList<>();
        for(DBNode attribute : entity.getChildren()) {
            if(((Attribute) attribute).isAttributePrimaryKey()) {
                primaryKeys.add((Attribute) attribute);
            }
        }

        Constraints c = new Constraints(0,0);

        panel.add(new JLabel("Izabrati primarne kljuceve za brisanje:"), c);
        c.x++;
        panel.add(new JLabel(), c);

        for(int i = 1; i < primaryKeys.size()+1; i++) {

            c.x = 0;
            c.y = i;

            JLabel label = new JLabel(primaryKeys.get(i-1).getName());
            panel.add(label,c);

            c.x++;

            JTextField txt = new JTextField();
            panel.add(txt, c);


        }
        if(JOptionPane.showConfirmDialog(null, panel,
                "Delete from table " + MainFrame.getInstance().getAppCore().getCurrentEntity().getName(),
                JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION) {


        }


    }
}
