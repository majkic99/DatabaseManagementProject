package actions;


import app.Main;
import gui.MainFrame;
import resource.DBNode;
import resource.implementation.Attribute;
import resource.implementation.Entity;

import java.awt.event.ActionEvent;
import java.util.Vector;

public class RelationsAction extends AbsDMAction {

    public RelationsAction () {
//        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK));
//        putValue(SMALL_ICON, loadIcon(""));
        putValue(NAME, "Relations");
        putValue(SHORT_DESCRIPTION, "Relations");
    }

    // TODO
    @Override
    public void actionPerformed(ActionEvent e) {
        Vector<Object> vector = (Vector<Object>) MainFrame.getInstance().getAppCore().getMainTableModel().getDataVector().elementAt(MainFrame.getInstance().getCurrentTV().getTable().getSelectedRow());
        System.out.println(MainFrame.getInstance().getAppCore().getMainTableModel().getDataVector().elementAt(MainFrame.getInstance().getCurrentTV().getTable().getSelectedRow()));
        //System.out.println(MainFrame.getInstance().getAppCore().getMainTableModel().getColumnName(1));

        Entity currentMainEntity = MainFrame.getInstance().getAppCore().getCurrentEntity();
        String currentRelationName = MainFrame.getInstance().getAppCore().getCurrentRelationName();
        Entity currentRelationEntity;
        for (DBNode dbnode : MainFrame.getInstance().getAppCore().getIrRoot().getChildren()){
            if (dbnode.getName().equals(currentRelationName)){
                currentRelationEntity = (Entity)dbnode;
            }
        }
        int i = 0;

        for (i = 0; i < currentMainEntity.getRelacije().size(); i++){
            if (currentMainEntity.getRelacije().get(i).equals(currentRelationName)){
                break;
            }
        }

        int j = 0;
        String s = currentMainEntity.getFkNameinThis().get(i);
        String pk = currentMainEntity.getPkNameinThat().get(i);

        for (j = 0; j < MainFrame.getInstance().getAppCore().getMainTableModel().getDataVector().size(); j++){
           String objekt = MainFrame.getInstance().getAppCore().getMainTableModel().getColumnName(j);
            if (objekt.equals(s)){
                break;
            }


        }
        String type = "";
        String kljuc = vector.get(j).toString();
        System.out.println(kljuc.toString());
        for (DBNode dbNode : currentMainEntity.getChildren()){
            if (dbNode.getName().equals(pk)){
                type = (((Attribute)dbNode).getAttributeType().toString());
            }
        }
        System.out.println(type);
        String upit = "";
        if (type.equals("CHAR") || type.equals("VARCHAR") || type.equals("TEXT")){
            upit = pk + " like " + "'" + kljuc + "'";
        }else {
            upit = pk + " = " + kljuc;
        }


        MainFrame.getInstance().getAppCore().updateRelationTable(upit, currentRelationName);



    }
}
