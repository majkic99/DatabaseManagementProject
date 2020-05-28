package actions;

import app.Main;
import gui.MainFrame;
import resource.DBNode;
import resource.implementation.Attribute;
import resource.implementation.Entity;
import utils.Constraints;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.lang.reflect.Array;
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
        int allGood = 1;
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
            txt.setName("Key");
            panel.add(txt, c);

        }
        String rez = "";
        ArrayList<String> uslovi = new ArrayList<String>();
        if(JOptionPane.showConfirmDialog(null, panel,
                "Delete from table " + MainFrame.getInstance().getAppCore().getCurrentEntity().getName(),
                JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION) {
            int keyCnt = 0;
            for(int i = 0; i < panel.getComponentCount(); i++) {
                if (panel.getComponent(i) instanceof JTextField && panel.getComponent(i).getName().equals("Key")){

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
                        uslovi.add(s);
                        //System.out.println(s);
                    }
                    keyCnt++;

                }
            }
        } else {
            return;
        }
        String upit = "";
        for (int k = 0; k < uslovi.size(); k++){
            if (k != 0){
             upit += " AND ";
            }
            upit += uslovi.get(k);
        }
        if (upit.equals("")) {
            allGood = 0;
            JOptionPane.showMessageDialog(null, "Nemate nikakav izbor primarnog kljuca");
        }

        System.out.println(upit);
        if (allGood == 1){
            MainFrame.getInstance().getAppCore().deleteFromTable(upit);
        }
    }
}
