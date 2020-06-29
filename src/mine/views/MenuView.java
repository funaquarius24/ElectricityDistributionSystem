/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mine.views;

import java.awt.event.ActionListener;
import javax.swing.JMenuItem;
import javax.swing.event.MenuListener;

/**
 *
 * @author AQUAVIRUS
 */
public class MenuView extends javax.swing.JFrame {

    /**
     * Creates new form MenuView1
     */
    public MenuView() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuBar1 = new javax.swing.JMenuBar();
        FileMenu = new javax.swing.JMenu();
        homeMenuItem = new javax.swing.JMenuItem();
        setPortMenuItem = new javax.swing.JMenuItem();
        exitMenuItem = new javax.swing.JMenuItem();
        ViewMenu = new javax.swing.JMenu();
        allBlocksMenuItem = new javax.swing.JMenuItem();
        runningMenuItem = new javax.swing.JMenuItem();
        faultyMenuItem = new javax.swing.JMenuItem();
        EditMenu = new javax.swing.JMenu();
        createBlockMenuItem = new javax.swing.JMenuItem();
        allocateBlockMenuItem = new javax.swing.JMenuItem();
        changeBlockMenuItem = new javax.swing.JMenuItem();
        jMenu1 = new javax.swing.JMenu();
        viewScheduleMenuItem = new javax.swing.JMenuItem();
        editScheduleMenuItem = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jMenuBar1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jMenuBar1.setPreferredSize(new java.awt.Dimension(163, 30));

        FileMenu.setText("File");
        FileMenu.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        homeMenuItem.setText("Home");
        FileMenu.add(homeMenuItem);

        setPortMenuItem.setText("Set Port");
        FileMenu.add(setPortMenuItem);

        exitMenuItem.setText("Exit");
        FileMenu.add(exitMenuItem);

        jMenuBar1.add(FileMenu);

        ViewMenu.setText("View Blocks");

        allBlocksMenuItem.setText("All Blocks");
        ViewMenu.add(allBlocksMenuItem);

        runningMenuItem.setText("Running");
        ViewMenu.add(runningMenuItem);

        faultyMenuItem.setText("Faulty");
        ViewMenu.add(faultyMenuItem);

        jMenuBar1.add(ViewMenu);

        EditMenu.setText("Edit Blocks");

        createBlockMenuItem.setText("Create");
        EditMenu.add(createBlockMenuItem);

        allocateBlockMenuItem.setText("Allocate");
        EditMenu.add(allocateBlockMenuItem);

        changeBlockMenuItem.setText("Change Info");
        EditMenu.add(changeBlockMenuItem);

        jMenuBar1.add(EditMenu);

        jMenu1.setText("Schedule");

        viewScheduleMenuItem.setText("View");
        jMenu1.add(viewScheduleMenuItem);

        editScheduleMenuItem.setText("Edit");
        jMenu1.add(editScheduleMenuItem);

        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 270, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public JMenuItem getEditScheduleMenuItem()
    {
        return editScheduleMenuItem;
    }
    
    public JMenuItem getViewScheduleMenuItem()
    {
        return viewScheduleMenuItem;
    }
    
    public void addHomeMenuItemActionListener( ActionListener listener )
    {
        homeMenuItem.addActionListener( listener );
    }
    
    public void addExitMenuItemActionListener( ActionListener listener )
    {
        exitMenuItem.addActionListener( listener );
    }
    
    public void addCreateBlockMenuItemActionListener( ActionListener listener )
    {
        createBlockMenuItem.addActionListener( listener );
    }
    
    public void addFaultyMenuItemActionListener( ActionListener listener )
    {
        faultyMenuItem.addActionListener( listener );
    }
    
    public void addAllocateMenuItemListener( ActionListener listener )
    {
        allocateBlockMenuItem.addActionListener(listener );
    }
    
    public void addAllBlocksMenuItemActionListener( ActionListener listener )
    {
        allBlocksMenuItem.addActionListener( listener );
    }
    
    public void addChangeBlockMenuItemActionListener( ActionListener listener )
    {
        changeBlockMenuItem.addActionListener( listener );
    }
    
    public void addRunningBlocksMenuItemActionListener( ActionListener listener )
    {
        runningMenuItem.addActionListener( listener );
    }
    
    public void addViewScheduleMenuItemActionListener( ActionListener listener )
    {
        viewScheduleMenuItem.addActionListener( listener );
    }
    
    public void addEditScheduleMenuItemActionListener( ActionListener listener )
    {
        editScheduleMenuItem.addActionListener( listener );
    }
    
    public void addSetPortMenuItemActionListener( ActionListener listener )
    {
        setPortMenuItem.addActionListener( listener );
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu EditMenu;
    private javax.swing.JMenu FileMenu;
    private javax.swing.JMenu ViewMenu;
    private javax.swing.JMenuItem allBlocksMenuItem;
    private javax.swing.JMenuItem allocateBlockMenuItem;
    private javax.swing.JMenuItem changeBlockMenuItem;
    private javax.swing.JMenuItem createBlockMenuItem;
    private javax.swing.JMenuItem editScheduleMenuItem;
    private javax.swing.JMenuItem exitMenuItem;
    private javax.swing.JMenuItem faultyMenuItem;
    private javax.swing.JMenuItem homeMenuItem;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem runningMenuItem;
    private javax.swing.JMenuItem setPortMenuItem;
    private javax.swing.JMenuItem viewScheduleMenuItem;
    // End of variables declaration//GEN-END:variables
}
