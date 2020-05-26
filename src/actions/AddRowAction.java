package actions;

import com.sun.xml.internal.ws.wsdl.parser.MemberSubmissionAddressingWSDLParserExtension;
import gui.MainFrame;
import jdk.nashorn.internal.scripts.JO;
import resource.DBNode;
import resource.implementation.Attribute;
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
        if(JOptionPane.showConfirmDialog(null, panel, "Insert into table" + MainFrame.getInstance().getAppCore().getCurrentEntity().getName(), JOptionPane.OK_OPTION) == JOptionPane.OK_OPTION) {
            String values = "";
            int txtCnt = 0;
            int allGood = 1;
            for(int i = 0; i < panel.getComponentCount(); i++) {
                if(panel.getComponent(i) instanceof JTextField) {
                    txtCnt++;
                    if (!(((Attribute)MainFrame.getInstance().getAppCore().getCurrentEntity().getChildren().get(txtCnt-1)).isNullable())){
                        if (((JTextField) panel.getComponent(i)).getText().equals("")) {
                            JOptionPane.showMessageDialog(null, "Nesto je NULL sto ne sme biti");
                            allGood = 0;

                        }

                    }

                    if (((JTextField) panel.getComponent(i)).getText().equals("")){
                        values += "NULL";
                        if(txtCnt < attributeNames.size()) values += ", ";
                        continue;
                    }


                    if (((Attribute)MainFrame.getInstance().getAppCore().getCurrentEntity().getChildren().get(txtCnt-1)).getAttributeType().toString().equals("VARCHAR")) {
                        values += "'" + ((JTextField) panel.getComponent(i)).getText() + "'";
                    }else{
                        values += ((JTextField) panel.getComponent(i)).getText();
                    }
                    if(txtCnt < attributeNames.size()) values += ", ";
                }
            }

            if (allGood == 1) {
                //System.out.println(values);
                MainFrame.getInstance().getAppCore().insert(values);
            }




        }

    }
}
