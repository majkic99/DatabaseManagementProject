package gui;

import app.AppCore;
import app.Main;
import lombok.Data;
import observer.Notification;
import observer.Subscriber;
import observer.enums.NotificationCode;
import resource.implementation.InformationResource;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.util.Vector;


public class MainFrame extends JFrame implements Subscriber {

    private static MainFrame instance = null;

    private AppCore appCore;
    private JTable jTable;
    private JScrollPane jsp;
    private JPanel bottomStatus;


    private MainFrame() {

    }

    public static MainFrame getInstance(){
        if (instance==null){
            instance=new MainFrame();
            instance.initialise();
        }
        return instance;
    }

    public static void setInstance(MainFrame instance) {
        MainFrame.instance = instance;
    }

    public AppCore getAppCore() {
        return appCore;
    }

    public JTable getjTable() {
        return jTable;
    }

    public void setjTable(JTable jTable) {
        this.jTable = jTable;
    }

    public JScrollPane getJsp() {
        return jsp;
    }

    public void setJsp(JScrollPane jsp) {
        this.jsp = jsp;
    }

    public JPanel getBottomStatus() {
        return bottomStatus;
    }

    public void setBottomStatus(JPanel bottomStatus) {
        this.bottomStatus = bottomStatus;
    }


    private void initialise() {

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        jTable = new JTable();
        jTable.setPreferredScrollableViewportSize(new Dimension(500, 400));
        jTable.setFillsViewportHeight(true);
        this.add(new JScrollPane(jTable));


        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        int screenHeight = screenSize.height;
        int screenWidth = screenSize.width;
        this.setSize(screenWidth / 2, screenHeight / 2);
        this.setTitle("DataBase Management 0.1");


        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);


    }

    public void setAppCore(AppCore appCore) {
        this.appCore = appCore;
        this.appCore.addSubscriber(this);
        this.jTable.setModel(appCore.getTableModel());
    }


    @Override
    public void update(Notification notification) {

        if (notification.getCode() == NotificationCode.RESOURCE_LOADED){
            System.out.println((InformationResource)notification.getData());
        }

        else{
            jTable.setModel((TableModel) notification.getData());
        }

    }
}
