package drvo;

import resource.DBNodeComposite;
import resource.implementation.AttributeConstraint;
import resource.implementation.InformationResource;

import javax.swing.event.TreeModelListener;
import javax.swing.tree.TreePath;

public class TreeModel implements javax.swing.tree.TreeModel {
    InformationResource ir;

    public TreeModel(InformationResource ir) {
        this.ir = ir;
    }

    @Override
    public Object getRoot() {

        return ir;
    }

    @Override
    public Object getChild(Object parent, int index) {
        if (parent instanceof AttributeConstraint){
            return null;
        }else{
            return ((DBNodeComposite)parent).getChildren().get(index);
        }
    }

    @Override
    public int getChildCount(Object parent) {
        if (parent instanceof AttributeConstraint){
            return 0;
        }else{
            return ((DBNodeComposite)parent).getChildren().size();
        }
    }

    @Override
    public boolean isLeaf(Object node) {
        return node instanceof AttributeConstraint;
    }

    @Override
    public void valueForPathChanged(TreePath path, Object newValue) {

    }

    @Override
    public int getIndexOfChild(Object parent, Object child) {
        if (parent instanceof AttributeConstraint){
            return -1;
        }else{
            return ((DBNodeComposite)parent).getChildren().indexOf(child);
        }
    }

    @Override
    public void addTreeModelListener(TreeModelListener l) {

    }

    @Override
    public void removeTreeModelListener(TreeModelListener l) {

    }
}
