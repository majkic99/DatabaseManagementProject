package drvo;

import resource.implementation.Attribute;
import resource.implementation.AttributeConstraint;
import resource.implementation.Entity;
import resource.implementation.InformationResource;

import javax.swing.*;
import javax.swing.tree.DefaultTreeCellRenderer;
import java.awt.*;

public class TreeCellRenderer extends DefaultTreeCellRenderer {

    public TreeCellRenderer() {

    }

    @Override
    public Component getTreeCellRendererComponent(JTree tree, Object value, boolean sel, boolean expanded, boolean leaf, int row, boolean hasFocus) {

        super.getTreeCellRendererComponent(tree, value, sel, expanded, leaf, row, hasFocus);

        if (value instanceof InformationResource)
            setIcon(new ImageIcon("images/database.png"));
        else if (value instanceof Entity)
            setIcon(new ImageIcon("images/entity.png"));
        else if (value instanceof Attribute)
            setIcon(new ImageIcon("images/attribute.png"));
        else if (value instanceof AttributeConstraint)
            setIcon(new ImageIcon("images/constraints.png"));
        return this;
    }
}
