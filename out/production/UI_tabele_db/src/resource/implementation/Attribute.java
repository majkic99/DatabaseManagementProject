package resource.implementation;


import resource.DBNode;
import resource.DBNodeComposite;
import resource.enums.AttributeType;
import resource.enums.ConstraintType;

import java.util.ArrayList;
import java.util.List;


public class Attribute extends DBNodeComposite {


    public AttributeType getAttributeType() {
        return attributeType;
    }

    public void setAttributeType(AttributeType attributeType) {
        this.attributeType = attributeType;
    }

    private AttributeType attributeType;

    public int getLength() {
        return length;
    }

    private int length;
    private List<Attribute> listaRelacija;

    public Attribute(String name, DBNode parent) {
        super(name, parent);
    }

    public Attribute(String name, DBNode parent, AttributeType attributeType, int length) {
        super(name, parent);
        this.attributeType = attributeType;
        this.length = length;
        listaRelacija = new ArrayList<Attribute>();
    }

    @Override
    public void addChild(DBNode child) {
        if (child != null && child instanceof AttributeConstraint){
            AttributeConstraint attributeConstraint = (AttributeConstraint) child;
            this.getChildren().add(attributeConstraint);
        }
    }

    public List<Attribute> getListaRelacija() {
        return listaRelacija;
    }

    public void setListaRelacija(List<Attribute> listaRelacija) {
        this.listaRelacija = listaRelacija;
    }

    public boolean isAttributeForeignKey(){
        for (DBNode dbNode : this.getChildren()){
            if (((AttributeConstraint)dbNode).getConstraintType().equals(ConstraintType.FOREIGN_KEY)){
                return true;

            }
        }
        return false;
    }

    public boolean isAttributePrimaryKey(){
        for (DBNode dbNode : this.getChildren()){
            if (((AttributeConstraint)dbNode).getConstraintType().equals(ConstraintType.PRIMARY_KEY)){
                return true;

            }
        }
        return false;
    }
    public boolean isNullable(){
        for (DBNode dbNode : this.getChildren()){
            if (((AttributeConstraint)dbNode).getConstraintType().equals(ConstraintType.NOT_NULL)) {
                return false;
            }
        }
        return true;
    }
}
