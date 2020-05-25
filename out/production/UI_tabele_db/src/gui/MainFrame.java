package gui;

import actions.ActionManager;
import app.AppCore;

import drvo.TreeCellRenderer;
import drvo.TreeModel;
import drvo.TreeController;
import gui.table.RelationTableView;
import gui.table.TableView;
import observer.Notification;
import observer.Subscriber;
import observer.enums.NotificationCode;
import resource.DBNode;
import resource.implementation.Entity;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.TableModel;
import java.awt.*;
import java.util.ArrayList;


public class MainFrame extends JFrame implements Subscriber {

    private static MainFrame instance = null;

    private JTabbedPane topTab;
    private JTabbedPane botTab;
    private AppCore appCore;
    private JScrollPane jsp;
    private JPanel bottomStatus;
    private JScrollPane workspaceTreeScrollPane;
    private JTree drvo;
    private JSplitPane horizontalSplit;
    private JSplitPane verticalSplit;
    private TableView currentTV;

    private ArrayList<Integer> used;

    private ActionManager actionManager;


    private MainFrame() {
        this.setLayout(new BorderLayout());
        horizontalSplit = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        verticalSplit = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
        this.add(horizontalSplit, BorderLayout.CENTER);
        horizontalSplit.setRightComponent(verticalSplit);
        topTab = new JTabbedPane();
        botTab = new JTabbedPane();
        used = new ArrayList<>();
        topTab.addChangeListener(topChangeListener);
        botTab.addChangeListener(botChangeListener);
        actionManager = new ActionManager();
//        botTab.add(new JPanel());
//        topTab.add(new JPanel());

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

        verticalSplit.setTopComponent(topTab);
        verticalSplit.setBottomComponent(botTab);



        this.setLocationRelativeTo(null);
        this.setVisible(true);

    }

    public void setAppCore(AppCore appCore) {
        this.appCore = appCore;
        this.appCore.addSubscriber(this);
        setTree();
        verticalSplit.setResizeWeight(0.5);
        center();

    }

//    private void setTabs() {
//
//        jTable.setPreferredScrollableViewportSize(new Dimension(500, 400));
//        jTable.setFillsViewportHeight(true);
//        verticalSplit.setTopComponent(new JScrollPane(jTable));
//        JLabel placeholder = new JLabel();
//        verticalSplit.setBottomComponent(placeholder);
//        verticalSplit.setBottomComponent(bottomTabbedPane);
//
//
//
//    }

    private void setTree(){
        drvo = new JTree(new TreeModel(appCore.getIrRoot()));
        drvo.addTreeSelectionListener(new TreeController());
        drvo.setCellRenderer(new TreeCellRenderer());
        workspaceTreeScrollPane = new JScrollPane(drvo);

        horizontalSplit.setLeftComponent(workspaceTreeScrollPane);

    }

    private void center() {
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        setPreferredSize(new Dimension(screenSize.width/2, screenSize.height/2));
        setLocation(screenSize.width/4, screenSize.height/4);
        pack();
        horizontalSplit.setDividerLocation(getWidth()/3);
    }

    @Override
    public void update(Notification notification) {

        if (notification.getCode() == NotificationCode.RESOURCE_LOADED){
            System.out.println(notification.getData());
        }

        else{
            TableView tv = (TableView) topTab.getSelectedComponent();
            tv.getTable().setModel((TableModel) notification.getData());
        }

    }

    // Slusac za tabove (topTab)
    ChangeListener topChangeListener = new ChangeListener() {
        public void stateChanged(ChangeEvent changeEvent) {
            JTabbedPane sourceTabbedPane = (JTabbedPane) changeEvent.getSource();
            int index = sourceTabbedPane.getSelectedIndex();
            System.out.println("Tab changed to: " + sourceTabbedPane.getTitleAt(index));
            getAppCore().readDataFromTable(sourceTabbedPane.getTitleAt(index));


            getBotTab().removeAll();
            ArrayList<DBNode> entities = (ArrayList<DBNode>) getAppCore().getIrRoot().getChildren();
            Entity entity = null;
            for(DBNode e : entities) {
                if(e.getName().equals(sourceTabbedPane.getTitleAt(index))) {
                    entity = (Entity) e;
                    break;
                }

            }

            for(int j = 0; j < entity.getRelacije().size(); j++){
                RelationTableView relationTableView = new RelationTableView();
                relationTableView.getTable()
                        .setModel(MainFrame.getInstance().getAppCore().getRelationTableModel());

                relationTableView.setName(entity.getRelacije().get(j));
                getBotTab().add(relationTableView);
            }

            if(!entity.getRelacije().isEmpty())
                getAppCore().readDataFromTableRelation(entity.getRelacije().get(0));


        }
    };

    // Slusac za tabove (botTab)
    ChangeListener botChangeListener = new ChangeListener() {
        @Override
        public void stateChanged(ChangeEvent changeEvent) {
            JTabbedPane sourceTabbedPane = (JTabbedPane) changeEvent.getSource();
            int index = sourceTabbedPane.getSelectedIndex();
//            System.out.println("Tab changed to: " + sourceTabbedPane.getTitleAt(index));
            try{
                getAppCore().readDataFromTableRelation(sourceTabbedPane.getTitleAt(index));
            } catch(IndexOutOfBoundsException e) {
            }
        }
    };


    public void maximize() {
        setExtendedState(Frame.MAXIMIZED_BOTH);
    }

    public static void setInstance(MainFrame instance) {
        MainFrame.instance = instance;
    }

    public AppCore getAppCore() {
        return appCore;
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

    public JTabbedPane getTopTab() {
        return topTab;
    }

    public JTabbedPane getBotTab() {
        return botTab;
    }

    public TableView getCurrentTV() {
        return currentTV;
    }

    public void setCurrentTV(TableView currentTV) {
        this.currentTV = currentTV;
        topTab.setSelectedComponent(currentTV);
    }

    public void addUsed(int num) {
        used.add(num);
    }

    public ArrayList<Integer> getUsed() {
        return used;
    }

    public TableView getTVByID(int ID) {
        Component[] tabs = topTab.getComponents();
        for(int i = 0; i < tabs.length; i++) {
            TableView tv = (TableView) tabs[i];
            if(tv.getID() == ID) {
                return tv;
            }
        }
        return null;
    }

    public ActionManager getActionManager() {
        return actionManager;
    }
}
