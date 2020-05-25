package resource.implementation;


import resource.DBNode;
import resource.DBNodeComposite;

import java.util.ArrayList;
import java.util.List;


public class Entity extends DBNodeComposite {

    private static int count = 0;
    private int ID;
    private List<String> relacije;
    private List<Entity> relacijeEntiteta;





    public Entity(String name, DBNode parent) {
        super(name, parent);
        this.ID = count++;
        relacije = new ArrayList<String>();
    }

    @Override
    public void addChild(DBNode child) {
        if (child != null && child instanceof Attribute){
            Attribute attribute = (Attribute) child;
            this.getChildren().add(attribute);
        }

    }

    public void transformStringsToEntities(){
        relacijeEntiteta = new ArrayList<Entity>();
        for (DBNode dbNode : ((InformationResource)this.getParent()).getChildren()){
            for (String string : this.getRelacije()){
                if (dbNode.getName().equals(string)){
                    relacijeEntiteta.add((Entity)dbNode);

                }

            }

        }
    }



    public int getID() {
        return ID;
    }

    public List<String> getRelacije() {
        return relacije;
    }

    public void setRelacije(List<String> relacije) {
        this.relacije = relacije;
    }
}
