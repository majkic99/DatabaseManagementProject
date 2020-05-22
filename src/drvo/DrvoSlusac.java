package drvo;

import resource.implementation.Entity;

import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.TreePath;

public class DrvoSlusac implements TreeSelectionListener {
    @Override
    public void valueChanged(TreeSelectionEvent e) {
        TreePath path = e.getPath();
        for(int i=0; i<path.getPathCount(); i++){
            if(path.getPathComponent(i) instanceof Entity){
                Entity d=(Entity)path.getPathComponent(i);
                System.out.println("Selektovana tabela:"+d);

                break;
            }
        }
    }
}
