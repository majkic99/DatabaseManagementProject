package drvo;

import gui.MainFrame;
import gui.table.TableView;
import resource.implementation.Attribute;
import resource.implementation.AttributeConstraint;
import resource.implementation.Entity;
import resource.implementation.InformationResource;

import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.TreePath;


public class TreeController implements TreeSelectionListener {

    @Override
    public void valueChanged(TreeSelectionEvent e) {
        TreePath selectedNode = e.getNewLeadSelectionPath();
        if (selectedNode == null)
            return;

        Object node = selectedNode.getLastPathComponent();
        if (node == null)
            return;

        TreePath path = e.getPath();
        for(int i=0; i<path.getPathCount(); i++){
            if(path.getPathComponent(i) instanceof Entity){
                Entity entity = (Entity) path.getPathComponent(i);

                MainFrame.getInstance().getAppCore().readDataFromTable(entity.getName());

                // ID povezuje entity sa TableView komponentom, da bi znali koje smo vec ucitali,
                // i te vec ucitane samo stavimo u fokus

                boolean has = false;

                if(MainFrame.getInstance().getUsed().contains(entity.getID())) {
                    has = true;
                    MainFrame.getInstance().setCurrentTV(MainFrame.getInstance().getTVByID(entity.getID()));
                }


                // Ako do sad nije otvarana odabrana tabela
                if(!has) {
                    MainFrame.getInstance().addUsed(entity.getID());
                    TableView tv = new TableView(entity.getID());
                    tv.setName(entity.getName());
                    tv.getTable().setModel(MainFrame.getInstance().getAppCore().getTableModel());
                    MainFrame.getInstance().getTopTab().add(tv);
                    MainFrame.getInstance().setCurrentTV(tv);
                   }


                break;
            }
        }

        if (node instanceof InformationResource) {

            InformationResource ir = (InformationResource) node;
            System.out.println("Trenutni node: " + ir + "(Information Resource)");

        } else if(node instanceof Entity) {

            Entity en = (Entity) node;
            System.out.println("Trenutni node: " + en + "(Entity)");

        } else if(node instanceof Attribute) {

            Attribute at = (Attribute) node;
            System.out.println("Trenutni node: " + at + "(Attribute)");

        } else if(node instanceof AttributeConstraint) {

            AttributeConstraint ac = (AttributeConstraint) node;
            System.out.println("Trenutni node: " + ac + "(Attribute Constraint)");

        }

    }
}
