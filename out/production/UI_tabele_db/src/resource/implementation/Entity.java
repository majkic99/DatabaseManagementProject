package resource.implementation;


import resource.DBNode;
import resource.DBNodeComposite;


public class Entity extends DBNodeComposite {

    private static int count = 0;
    private int ID;

    public Entity(String name, DBNode parent) {
        super(name, parent);
        this.ID = count++;
    }

    @Override
    public void addChild(DBNode child) {
        if (child != null && child instanceof Attribute){
            Attribute attribute = (Attribute) child;
            this.getChildren().add(attribute);
        }

    }

    public int getID() {
        return ID;
    }
}
