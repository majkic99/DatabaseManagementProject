package app;

import gui.MainFrame;
import gui.table.TableModel;
import resource.DBNode;
import resource.DBNodeComposite;
import resource.data.Row;

import javax.swing.*;
import java.util.List;

public class Main {


    public static void main(String[] args) {
        AppCore appCore = new AppCore();
        appCore.loadResource();

        MainFrame mainFrame = MainFrame.getInstance();
        mainFrame.setAppCore(appCore);
        mainFrame.maximize();    // My preferable choice

        //TableModel tm = new TableModel();


        //appCore.readDataFromTable("EMPLOYEES");
        /*
        for (DBNode db: appCore.getIrRoot().getChildren()
             ) {
            System.out.println(db);
            for (DBNode db1: ((DBNodeComposite)db).getChildren()
            ) {
                System.out.println("--" + db1);
                for (DBNode db2: ((DBNodeComposite)db1).getChildren()
                ) {
                    System.out.println("----" + db2);
                }
            }
        }

         */

    }

}
