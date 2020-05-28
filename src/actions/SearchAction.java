package actions;

import gui.MainFrame;
import org.w3c.dom.Attr;
import resource.DBNode;
import resource.implementation.Attribute;
import resource.implementation.Entity;
import utils.Constraints;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;

public class SearchAction extends AbsDMAction {

    private JComboBox<String> operators;
    private JPanel panel;
    private JTextField textField;
    private JButton and;
    private JButton or;
    private String upit;
    int brojcanik;
    public SearchAction () {
//        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK));
//        putValue(SMALL_ICON, loadIcon(""));
        putValue(NAME, "Search");
        putValue(SHORT_DESCRIPTION, "Search");
    }

    // TODO
    @Override
    public void actionPerformed(ActionEvent e) {
        upit = "";

        Entity entity = MainFrame.getInstance().getAppCore().getCurrentEntity();
        ArrayList<String> attributeNames = new ArrayList<>();
        for(DBNode attribute : entity.getChildren()) {
            if (((Attribute)attribute).getAttributeType().toString().equals("VARCHAR") || ((Attribute)attribute).getAttributeType().toString().equals("CHAR") ||
                    ((Attribute)attribute).getAttributeType().toString().equals("INTEGER") || ((Attribute)attribute).getAttributeType().toString().equals("NUMERIC")){
                attributeNames.add(attribute.getName());
            }

        }
        brojcanik = 0;
        panel = new JPanel();

        JComboBox<String> comboBox = new JComboBox<>();
        for(int i = 0; i < attributeNames.size(); i++) {
            comboBox.addItem(attributeNames.get(i));
        }

        comboBox.setSelectedItem(null);
        comboBox.addItemListener(new ItemChangeListener());
        comboBox.setName("combo");
        operators = new JComboBox<String>();
        operators.setName("operators");
        operators.addItemListener(new ItemChangeListenerTwo());
        panel.add(comboBox);

        panel.add(operators);
        operators.setSelectedItem(null);
        textField = new JTextField();
        textField.setName("polje");

        and = new JButton("AND");
        and.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                if (brojcanik!=0){
                    upit+= " AND ";
                }
                brojcanik++;
                upit += comboBox.getSelectedItem();
                String operatorcic = "";
                if (((String)operators.getSelectedItem()).equals("ends with")){
                    operatorcic = " like " + "'%" + textField.getText()+ "'";
                }
                if (((String)operators.getSelectedItem()).equals("starts with")){
                    operatorcic = " like " + "'" +textField.getText() + "%'" ;
                }
                if (((String)operators.getSelectedItem()).equals("contains")){
                    operatorcic = " like " + "'" + "%" + textField.getText() + "%"+ "'";
                }
                if (((String)operators.getSelectedItem()).equals("exact")){
                    operatorcic = " like " + "'"  + textField.getText() + "'";
                }
                if (((String)operators.getSelectedItem()).equals("<")){
                    operatorcic = " < " +  textField.getText() ;
                }
                if (((String)operators.getSelectedItem()).equals("=")){
                    operatorcic = " = " + textField.getText();
                }
                if (((String)operators.getSelectedItem()).equals(">")){
                    operatorcic = " > " + textField.getText() ;
                }
                upit += operatorcic;
                System.out.println(upit);
                textField.setText("");
            }
        });

        and.setName("AND");
        textField.setPreferredSize(new Dimension(100,50));
        or = new JButton("OR");
        or.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                if (brojcanik!=0){
                    upit+= " OR ";
                }
                brojcanik++;
                upit += comboBox.getSelectedItem();
                String operatorcic= "";

                if (((String)operators.getSelectedItem()).equals("ends with")){
                    operatorcic = " like " + "'%" + textField.getText()+ "'";
                }
                if (((String)operators.getSelectedItem()).equals("starts with")){
                    operatorcic = " like " + "'" +textField.getText() + "%'" ;
                }
                if (((String)operators.getSelectedItem()).equals("contains")){
                    operatorcic = " like " + "'" + "%" + textField.getText() + "%"+ "'";
                }
                if (((String)operators.getSelectedItem()).equals("exact")){
                operatorcic = " like " + "'"  + textField.getText() + "'";
                }
                if (((String)operators.getSelectedItem()).equals("<")){
                    operatorcic = " < " +  textField.getText() ;
                }
                if (((String)operators.getSelectedItem()).equals("=")){
                    operatorcic = " = " + textField.getText();
                }
                if (((String)operators.getSelectedItem()).equals(">")){
                    operatorcic = " > " + textField.getText() ;
                }
                upit += operatorcic;
                System.out.println(upit);
                textField.setText("");
            }
        });

        and.setName("OR");
        panel.setMinimumSize(new Dimension(500, 500));


        panel.setPreferredSize(new Dimension(500, 100));

        if(JOptionPane.showConfirmDialog(null, panel, "Filter&Sort", JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION) {
            System.out.println(upit);
            MainFrame.getInstance().getAppCore().search(upit);
        }


    }
    private String findType(String name){
        Entity entity = MainFrame.getInstance().getAppCore().getCurrentEntity();
        for(DBNode attribute : entity.getChildren()) {

            if (attribute.getName().equals(name)){
                return ((Attribute)attribute).getAttributeType().toString();
            }
        }
        return null;
    }
    private class ItemChangeListener implements ItemListener {
        @Override
        public void itemStateChanged(ItemEvent event) {

            if (event.getStateChange() == ItemEvent.SELECTED) {

                Object item = event.getItem();
                //operators.removeAllItems();
                if (findType((String)item).equals("VARCHAR") || findType((String)item).equals("CHAR")){
                    System.out.println("tekst");
                    operators.removeAllItems();

                    operators.addItem("starts with");
                    operators.addItem("ends with");
                    operators.addItem("contains");
                    operators.addItem("exact");


                }else{
                    System.out.println("broj");
                    operators.removeAllItems();
                    operators.addItem(">");
                    operators.addItem("=");
                    operators.addItem("<");
                }

                operators.setSelectedItem(null);
            }
        }
    }
    private class ItemChangeListenerTwo implements ItemListener {
        @Override
        public void itemStateChanged(ItemEvent event) {

            if (event.getStateChange() == ItemEvent.SELECTED) {

                Object item = event.getItem();
                //operators.removeAllItems();
                panel.add(textField);
                panel.add(and);
                panel.add(or);



            }
        }
    }
}
