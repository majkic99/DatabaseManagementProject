package actions;

import gui.MainFrame;
import resource.DBNode;
import resource.implementation.Attribute;
import resource.implementation.Entity;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

public class FilterAndSortAction extends AbsDMAction {

    public FilterAndSortAction() {
//        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK));
//        putValue(SMALL_ICON, loadIcon(""));
        putValue(NAME, "Filter&Sort");
        putValue(SHORT_DESCRIPTION, "Filter and sort");
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
        Constraints c = new Constraints(x,y);

        for(int i = 0; i < attributeNames.size(); i++) {

            // Lista atributa
            c.x = 0;
            c.y = i;
            JLabel label = new JLabel(attributeNames.get(i) + ":");
            panel.add(label, c);

            c.x++;

            // CheckBox za izbor atributa za filter
            JCheckBox box = new JCheckBox();
            box.setSelected(true);
            box.setName(attributeNames.get(i));
            panel.add(box, c);

            c.x++;

            // ComboBox za izbor asc/desc
            JComboBox<String> cmb1 = new JComboBox<>();
            cmb1.addItem("None");
            cmb1.addItem("ASC");
            cmb1.addItem("DESC");
            cmb1.setSelectedIndex(0);
            cmb1.setName("order");
            panel.add(cmb1, c);

            c.x++;

            // Izbor prioriteta za sort
            JComboBox<Integer> cmb2 = new JComboBox<>();

            for(int j = 1; j < attributeNames.size()+1; j++) {
                cmb2.addItem(j);
            }
            cmb2.setName("priority");
            panel.add(cmb2, c);

        }
        if(JOptionPane.showConfirmDialog(null, panel, "Filter&Sort", JOptionPane.OK_OPTION) == JOptionPane.OK_OPTION) {
            String upit = "";
            String checkedCols = "";
            int cboxCount = 0;
            int orderCount = 0;
            int priorityCount = 0;
            ArrayList<String> columnOrders = new ArrayList<>();

            for(int i = 0; i < panel.getComponentCount(); i++) {
                if(panel.getComponent(i) instanceof JCheckBox) {
                    cboxCount++;
                    if (((JCheckBox) panel.getComponent(i)).isSelected()) {
                        checkedCols += panel.getComponent(i).getName();
                        if(cboxCount + 1 < attributeNames.size()) checkedCols += ", ";
                    }
                }
                else if(panel.getComponent(i) instanceof JComboBox && panel.getComponent(i).getName().equals("order")) {
                    orderCount++;
                    if(!(((JComboBox) panel.getComponent(i)).getSelectedItem().equals("None"))) {
                        String s = attributeNames.get(orderCount-1) + " " + ((JComboBox) panel.getComponent(i)).getSelectedItem();
                        columnOrders.add(s);
                        System.out.println(s);
                    }
                }
                else if(panel.getComponent(i) instanceof JComboBox && panel.getComponent(i).getName().equals("priority")) {
                    priorityCount++;

                }
            }

        }

    }

    private class Constraints {

        int x;
        int y;

        Constraints (int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}

