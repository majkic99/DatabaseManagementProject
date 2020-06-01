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
            txt.setName("PKValue");
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
            txt.setName("UpdatedValue");
            panel.add(txt, c);
        }
        ArrayList<String> vrednostiKljuceva = new ArrayList<String>();
        ArrayList<String> izmene = new ArrayList<String>();
        int allGood = 1;
        String values = "";
        int txtCnt = 0;
        int fieldCnt = 0;
        if(JOptionPane.showConfirmDialog(null, panel,
                "Edit " + MainFrame.getInstance().getAppCore().getCurrentEntity().getName(),
                JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION) {
                int keyCnt = 0;

                for(int i = 0; i < panel.getComponentCount(); i++) {
                    if (panel.getComponent(i) instanceof JTextField) {
                        if (panel.getComponent(i).getName().equals("PKValue")) {

                            if (!(((JTextField) panel.getComponent(i)).getText().equals(""))){

                                String s = primaryKeys.get(keyCnt).getName();
                                String testString = primaryKeys.get(keyCnt).getAttributeType().toString();
                                if (testString.equals("DECIMAL") || testString.equals("FLOAT") || testString.equals("BIGINT") || testString.equals("INT") ||
                                        testString.equals("NUMERIC") || testString.equals("INT") || testString.equals("SMALLINT")){
                                    try {
                                        Float.parseFloat(((JTextField) panel.getComponent(i)).getText());
                                        s+= " = " + ((JTextField) panel.getComponent(i)).getText();
                                    }
                                    catch(Exception er) {
                                        JOptionPane.showMessageDialog(null, s + " - Ovo je broj upisali ste string");
                                        allGood = 0;
                                    }
                                }else {
                                    s += " = " + "'"+ ((JTextField) panel.getComponent(i)).getText()+ "'";
                                }
                                vrednostiKljuceva.add(s);
                                //System.out.println(s);
                            }
                            keyCnt++;
                        }

                        if (panel.getComponent(i).getName().equals("UpdatedValue")) {

                            txtCnt++;

                            if (((JTextField) panel.getComponent(i)).getText().equals("")){

                                continue;
                            }
                            fieldCnt++;
                            System.out.println(txtCnt);
                            if(fieldCnt!= 1) values += ", ";
                            String testString = ((Attribute)MainFrame.getInstance().getAppCore().getCurrentEntity().getChildren().get(txtCnt-1)).getAttributeType().toString();
                            if (testString.equals("DECIMAL") || testString.equals("FLOAT") || testString.equals("BIGINT") || testString.equals("INT") ||
                                    testString.equals("NUMERIC") || testString.equals("INT") || testString.equals("SMALLINT")){
                                try {
                                    Float.parseFloat(((JTextField) panel.getComponent(i)).getText());
                                }
                                catch(Exception er) {
                                    JOptionPane.showMessageDialog(null, (((Attribute)MainFrame.getInstance().getAppCore().getCurrentEntity().getChildren().get(txtCnt-1)).getName() + " - Ovo je broj upisali ste string"));
                                    allGood = 0;
                                }
                            }



                            values += ((Attribute)MainFrame.getInstance().getAppCore().getCurrentEntity().getChildren().get(txtCnt-1)).getName();
                            values += " = ";
                            if ( testString.equals("VARCHAR") || testString.equals("TEXT")|| testString.equals("NVARCHAR")|| testString.equals("DATETIME")|| testString.equals("CHAR")) {
                                int length = ((Attribute)MainFrame.getInstance().getAppCore().getCurrentEntity().getChildren().get(txtCnt-1)).getLength();
                                String s = ((JTextField) panel.getComponent(i)).getText();
                                if (length < s.length()){
                                    //System.out.println(s + ((Attribute)MainFrame.getInstance().getAppCore().getCurrentEntity().getChildren().get(txtCnt-1)).getName());
                                    JOptionPane.showMessageDialog(null, (((Attribute)MainFrame.getInstance().getAppCore().getCurrentEntity().getChildren().get(txtCnt-1)).getName() + " - Upisali ste previse karaktera"));
                                    allGood = 0;
                                }
                                values += "'" + s + "'";
                            }else{
                                values += ((JTextField) panel.getComponent(i)).getText();
                            }

                        }

                    }
                }


        } else {
            return;
        }
        String upit = "";
        for (int k = 0; k < vrednostiKljuceva.size(); k++){
            if (k != 0){
                upit += " AND ";
            }
            upit += vrednostiKljuceva.get(k);
        }
        System.out.println(upit);
        System.out.println(values);
        if (upit.equals("")) {
            allGood = 0;
            JOptionPane.showMessageDialog(null, "Nemate nikakav izbor primarnog kljuca");
        }
        if (values.equals("")) {
            allGood = 0;
            JOptionPane.showMessageDialog(null, "Nemate nikakve promene");
        }
        if (allGood == 1){
            MainFrame.getInstance().getAppCore().updateInTable(values, upit);
        }

    }
}
