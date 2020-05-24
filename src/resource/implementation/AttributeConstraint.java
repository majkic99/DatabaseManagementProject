package resource.implementation;

import resource.DBNode;
import resource.enums.ConstraintType;


public class AttributeConstraint extends DBNode {

    public ConstraintType getConstraintType() {
        return constraintType;
    }

    private ConstraintType constraintType;

    public AttributeConstraint(String name, DBNode parent, ConstraintType constraintType) {
        super(name, parent);
        this.constraintType = constraintType;
    }
}
