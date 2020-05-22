package resource;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


public abstract class DBNode {

    private String name;

    private DBNode parent;

    public String toString(){
        return name;
    }

    public DBNode(String name, DBNode parent) {
        this.name = name;
        this.parent = parent;
    }

    public DBNode() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public DBNode getParent() {
        return parent;
    }

    public void setParent(DBNode parent) {
        this.parent = parent;
    }
}
