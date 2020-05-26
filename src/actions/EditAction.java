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

public class EditAction extends  AbsDMAction {

    public EditAction() {
//        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK));
//        putValue(SMALL_ICON, loadIcon(""));
        putValue(NAME, "Edit");
        putValue(SHORT_DESCRIPTION, "Update table");
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

        panel.add(new JLabel("Izabrati primarni kljuc/kljuceve za izmenu:"), c);
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

        c.x = 0;
        c.y = primaryKeys.size() + 1;

        // Prazan red za estetiku
        panel.add(new JLabel(), c);
        c.x++;
        panel.add(new JLabel(), c);

        c.y++;
        c.x = 0;

        panel.add(new JLabel("Unesite izmene u sledeca polja:"),c);
        c.x++;
        panel.add(new JLabel(),c);

        c.y++;

        ArrayList<String> attributeNames = new ArrayList<>();
        for(DBNode attribute : entity.getChildren()) {
            attributeNames.add(attribute.getName());
        }

        int tmp = c.y;
        for(int i = tmp; i < attributeNames.size() + tmp; i++) {
            c.x = 0;
            c.y = i;
            JLabel label = new JLabel(attributeNames.get(i - tmp) + ":");
            panel.add(label, c);

            c.x++;


            JTextField txt = new JTextField();
//            txt.setBorder(BorderFactory.createEmptyBorder());
            panel.add(txt, c);
        }

        if(JOptionPane.showConfirmDialog(null, panel,
                "Edit " + MainFrame.getInstance().getAppCore().getCurrentEntity().getName(),
                JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION) {


        }

    }
}
