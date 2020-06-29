/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mine.views;

import java.util.Vector;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import mine.models.ViewBlocks;

/**
 *
 * @author AQUAVIRUS
 */
public class BlocksGui extends javax.swing.JFrame {

    private ViewBlocks blocks;
    private Vector<Vector<String>> data;
    private Vector<String> header;
    private DefaultTableModel tableModel;
    /**
     * Creates new form BlocksGui
     */
    public BlocksGui( Vector<Vector<String>> data ){
        this.data = data;
        
        header = new Vector<>();
        header.add( "Block Number" );
        header.add( "Address" );
        header.add( "Date Created" );
        header.add( "Running" );
        header.add( "Block Nick" );
        tableModel = new DefaultTableModel( data, header );
        initComponents();
        setDefaultCloseOperation( DISPOSE_ON_CLOSE );
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jScrollPane2 = new javax.swing.JScrollPane();
        blocksTable = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new java.awt.GridBagLayout());

        blocksTable.setModel(tableModel);
        jScrollPane2.setViewportView(blocksTable);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 565;
        gridBagConstraints.ipady = 317;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        getContentPane().add(jScrollPane2, gridBagConstraints);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public JTable getBlocksTable()
    {
        return blocksTable;
    }
    
    public void updateTableData( Vector<Vector<String>> data )
    {
        this.data = data;
        blocksTable.setModel( new DefaultTableModel( data, header ) );
        tableModel.fireTableDataChanged();
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable blocksTable;
    private javax.swing.JScrollPane jScrollPane2;
    // End of variables declaration//GEN-END:variables
}
