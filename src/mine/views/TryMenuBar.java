/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mine.views;

import javax.swing.JMenu;
import javax.swing.JMenuBar;

/**
 *
 * @author AQUAVIRUS
 */
public class TryMenuBar  extends JMenuBar{
    private javax.swing.JMenu allocateMenu;
    private javax.swing.JMenuItem allocationMenuItem;
    private javax.swing.JMenu editMenu;
    private javax.swing.JMenu fileMenu;
    private javax.swing.JMenu homeMenu;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JMenuItem runningMenuItem;
    private javax.swing.JMenuItem scheduleMenuItem;
    private javax.swing.JMenu viewMenu;
    
    public TryMenuBar()
    {
        init();;
    }
    
    private void init()
    {
        menuBar = new javax.swing.JMenuBar();
        fileMenu = new javax.swing.JMenu();
        homeMenu = new javax.swing.JMenu();
        viewMenu = new javax.swing.JMenu();
        scheduleMenuItem = new javax.swing.JMenuItem();
        allocationMenuItem = new javax.swing.JMenuItem();
        runningMenuItem = new javax.swing.JMenuItem();
        editMenu = new javax.swing.JMenu();
        allocateMenu = new javax.swing.JMenu();

        fileMenu.setText("File");
        fileMenu.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        menuBar.add(fileMenu);

        homeMenu.setText("Home");
        homeMenu.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        menuBar.add(homeMenu);

        viewMenu.setText("View");
        viewMenu.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        scheduleMenuItem.setText("Schedule");
        viewMenu.add(scheduleMenuItem);

        allocationMenuItem.setText("Allocation");
        viewMenu.add(allocationMenuItem);

        runningMenuItem.setText("Running");
        viewMenu.add(runningMenuItem);

        menuBar.add(viewMenu);

        editMenu.setText("Edit");
        editMenu.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        menuBar.add(editMenu);

        allocateMenu.setText("Allocate");
        allocateMenu.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        allocateMenu.setIconTextGap(6);
        menuBar.add(allocateMenu);
    }
    
}
