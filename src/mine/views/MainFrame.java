/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mine.views;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.swing.*;
public class MainFrame extends JFrame
{
    Container cont;
    JPanel panel;
    JScrollPane scrollpane;
    GridBagLayout gridbag;
    GridBagConstraints c;
    Dimension test;
    private List<JButton> blocksList;
    private List<Integer> runningBlocksList;
    private int numberOfBlocks;
    List blockNumberList;
    boolean testTrue;

    
    public MainFrame( List blockNumberList, List runningBlocks )
     {
        this.blockNumberList = blockNumberList;
        this.numberOfBlocks = blockNumberList.size();
        this.runningBlocksList = runningBlocks;
        System.out.println( runningBlocks );
        cont = getContentPane();
        panel = new JPanel();
        scrollpane = new JScrollPane(panel);
        panel.setLayout(new ModifiedFlowLayout( java.awt.FlowLayout.LEFT ));
        cont.add(scrollpane);
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        
        blocksList = new ArrayList<>();
        for( int i = 1; i <= numberOfBlocks; i++ )
        {
            blocksList.add( new JButton( Integer.toString( i ) ) );
        }
        for(int i=0; i < blocksList.size(); i++) {
            setUpBlocks( blocksList.get( i ) );
        }
        if( runningBlocksList != null )
        {
            makeRunning( runningBlocksList );
        }
    }
    
    public void addBlock()
    {
        numberOfBlocks++;
        blocksList.add( new JButton( Integer.toString( numberOfBlocks ) ) );
        setUpBlocks( blocksList.get( numberOfBlocks - 1 ) );
        System.out.println(" Added ");
        setVisible( false );
        setVisible( true );
    }
    
    private void setUpBlocks(JButton block) {
        Dimension dim = new Dimension( 100, 100 );
        block.setBackground( new Color( 200, 200, 200 ) );
        block.setPreferredSize( dim );
        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(block);
        block.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 101, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        panel.add(block);

    }
    
    public void makeRunning( List<Integer> runningBlocksList )
    {
        System.out.println("makeRunning");
        this.runningBlocksList = runningBlocksList;
        makeRunning();
    }
    
    public void makeRunning()
    {
        System.out.println("makeRunning");
        Iterator iter = runningBlocksList.iterator();
        while (iter.hasNext()) {
            int next = ( int )iter.next();
            changeBlockColor( next - 1 , Color.GREEN );
        }
    }
    
    public void makeFaulty( List<Integer> faultyBlocksList )
    {
        System.out.println("makeFaulty");
        makeDefault();
        if( !testTrue )
        {
            if( faultyBlocksList != null )
            {
                Integer[] runningBlocksInteger = (Integer[]) runningBlocksList.toArray( new Integer[ 0 ] );

                Iterator iter = faultyBlocksList.iterator();
                while ( iter.hasNext() ) {
                    int next = ( int )iter.next();
                    for( int i = 0; i < runningBlocksInteger.length; i++ )
                    {
                        boolean test = next == (int)runningBlocksInteger[ i ];
                        if( test )
                        {
                            changeBlockColor( next - 1 , Color.RED );
                            break;
                        }
                    }

                }
            }
        }
        
        
    }
    
    private void testState( int number )
    {
        
    }
    
    public void makeDefault()
    {
        System.out.println("makeDefault");
        Iterator iter = blockNumberList.iterator();
        while (iter.hasNext()) {
            int next = ( int )iter.next();
            changeBlockColor( next - 1 , new Color( 200, 200, 200 ) );
        }
        makeRunning();
    }
    
    private void changeBlockColor( int index, Color color )
    {
        Component block = panel.getComponent( index );
        block.setBackground( color );
    }

    public void addListenerForClose(WindowListener windowListener) {
        addWindowListener( windowListener );
    }
} 