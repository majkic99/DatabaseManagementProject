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
import java.util.List;

public class ReportAction extends AbsDMAction {

    public ReportAction() {
//        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK));
//        putValue(SMALL_ICON, loadIcon(""));
        putValue(NAME, "Report");
        putValue(SHORT_DESCRIPTION, "Report");
    }

    // TODO
    @Override
    public void actionPerformed(ActionEvent e) {

        JPanel finalPanel = new JPanel();
        finalPanel.setLayout(new GridBagLayout());
        Constraints c = new Constraints(0,0);

        JPanel operations = new JPanel();
        JPanel chooseCol = new JPanel();
        JPanel groupBy = new JPanel();

        // Operations

        operations.setLayout(new GridLayout(1, 2, 20, 20));
        JRadioButton r1 = new JRadioButton("COUNT");
        JRadioButton r2 = new JRadioButton("AVERAGE");
        ButtonGroup bg = new ButtonGroup();
        bg.add(r1);
        bg.add(r2);
        operations.add(new JLabel("Izaberi operaciju: "));
        operations.add(r1);
        operations.add(r2);

        // Odabir kolone za Count/Avg

        chooseCol.setLayout(new GridLayout(0,2,5,5));
        chooseCol.setAlignmentX(10);

        Entity currentEntity = MainFrame.getInstance().getAppCore().getCurrentEntity();
        List<DBNode> attributeList = currentEntity.getChildren();
        chooseCol.add(new JLabel("Izaberi kolonu: "));
        chooseCol.add(new JLabel(" "));
        ButtonGroup group = new ButtonGroup();
        JRadioButton button = new JRadioButton("All");
        group.add(button);
        chooseCol.add(button);
        for(int i = 0; i < attributeList.size(); i++) {
            button = new JRadioButton(attributeList.get(i).getName());
            group.add(button);
            chooseCol.add(button);
        }

        // Group By Seletion

        groupBy.setLayout(new GridLayout(0,2,5,5));
        groupBy.setAlignmentX(10);

        groupBy.add(new JLabel("Grupiraj po: "));
        groupBy.add(new JLabel(" "));
        for(int i = 0; i < attributeList.size(); i++) {
            JCheckBox checkBox = new JCheckBox(attributeList.get(i).getName());
            groupBy.add(checkBox);
        }


        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(20,20,20,20);
        constraints.gridy = 0;
        finalPanel.add(operations, constraints);
        constraints.gridy = 1;
        finalPanel.add(chooseCol, constraints);
        constraints.gridy = 2;
        finalPanel.add(groupBy, constraints);


        String operation = "";
        String col = "";
        String groups = "";
        if (JOptionPane.showConfirmDialog(null, finalPanel,
                "Reports for " + MainFrame.getInstance().getAppCore().getCurrentEntity().getName(),
                JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION) {


            // Odabir operacije
            for(int i = 0; i < operations.getComponentCount(); i++)
                if(operations.getComponent(i) instanceof JRadioButton)
                    if(((JRadioButton) operations.getComponent(i)).isSelected())
                        operation = ((JRadioButton) operations.getComponent(i)).getText();




            // Odabir kolone
            for(int i = 0; i < chooseCol.getComponentCount(); i++)
                if(chooseCol.getComponent(i) instanceof JRadioButton)
                    if(((JRadioButton) chooseCol.getComponent(i)).isSelected())
                        col = ((JRadioButton) chooseCol.getComponent(i)).getText();


            // Grupe
            for(int i = 0; i < groupBy.getComponentCount(); i++)
                if(groupBy.getComponent(i) instanceof JCheckBox)
                    if(((JCheckBox) groupBy.getComponent(i)).isSelected())
                        groups += ((JCheckBox) groupBy.getComponent(i)).getText() + ", ";

            if(groups.endsWith(", ")) {
                groups = groups.substring(0, groups.length() - 2);
                groups += " ";
            }

            // Kreiranje upita
            if(col.equals("All"))
                col = "*";

            MainFrame.getInstance().getAppCore().report(operation, col, groups);


        }
    }
}
