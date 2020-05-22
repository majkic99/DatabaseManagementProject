package drvo;

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
