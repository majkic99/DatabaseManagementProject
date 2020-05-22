package gui;

import app.AppCore;

import drvo.DrvoModel;
import drvo.DrvoSlusac;
import observer.Notification;
import observer.Subscriber;
import observer.enums.NotificationCode;

import javax.swing.*;
import javax.swing.table.TableModel;
import javax.swing.tree.TreePath;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;


public class MainFrame extends JFrame implements Subscriber {

    private static MainFrame instance = null;

    private AppCore appCore;
    private JTable jTable;
    private JScrollPane jsp;
    private JPanel bottomStatus;
    private JScrollPane workspaceTreeScrollPane;
    private JTree drvo;
    private JSplitPane horizontalSplit;
    private JSplitPane verticalSplit;


    private MainFrame() {
        this.setLayout(new BorderLayout());
        horizontalSplit = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        verticalSplit = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
        this.add(horizontalSplit, BorderLayout.CENTER);
        horizontalSplit.setRightComponent(verticalSplit);
        jTable = new JTable();

    }

    public static MainFrame getInstance(){
        if (instance==null){
            instance=new MainFrame();
            instance.initialise();
        }
        return instance;
    }


    private void initialise() {

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.setTitle("DataBase Management 0.1");

        center();

        this.setLocationRelativeTo(null);
        this.setVisible(true);

    }

    public void setAppCore(AppCore appCore) {
        this.appCore = appCore;
        this.appCore.addSubscriber(this);
        this.jTable.setModel(appCore.getTableModel());
        setTree();
        setTable();
        center();
    }

    private void setTable() {


//        jTable.setPreferredScrollableViewportSize(new Dimension(500, 400));
        jTable.setFillsViewportHeight(true);

        verticalSplit.setTopComponent(new JScrollPane(jTable)); //.


    }

    private void setTree(){
        drvo = new JTree(new DrvoModel(appCore.getIrRoot()));
        drvo.addTreeSelectionListener(new DrvoSlusac());
        workspaceTreeScrollPane = new JScrollPane(drvo);

        horizontalSplit.setLeftComponent(workspaceTreeScrollPane);

    }

    private void center() {
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        int screenHeight = screenSize.height;
        int screenWidth = screenSize.width;
        this.setSize(screenWidth/3 * 2, screenHeight/3 * 2);
        pack();
        this.setLocation(screenWidth/2, screenHeight/2);
    }

    @Override
    public void update(Notification notification) {

        if (notification.getCode() == NotificationCode.RESOURCE_LOADED){
            System.out.println(notification.getData());
        }

        else{
            jTable.setModel((TableModel) notification.getData());
        }

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
}
