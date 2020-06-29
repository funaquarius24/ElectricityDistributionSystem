/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mine.serial;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import processing.core.PApplet;
import processing.serial.Serial;

/**
 *
 * @author AQUAVIRUS
 */
@SuppressWarnings("serial")
public class SendSerial extends PApplet{
    //private String dataToSend;
    private String[] ports;
    private String portName;
    private Serial myPort;
    private StringBuilder stringBuilder;


    private SetPort window = null;
    private boolean connected;

    public SendSerial( SetPort window )
    {
            this.window = window;
            searchForPorts();
    }

    public void searchForPorts()
    {
        Thread searchThread = new Thread()
        {
            public void run()
            {
                ports = Serial.list();
                //Set<String> mySet = new HashSet<>( Arrays.asList( ports ) );
                //window.updateComPort( mySet.toArray( new String[ 0 ] ) );
                window.updateComPort( ports );
                while( true )
                {
                    if( !Arrays.equals( ports , Serial.list() ) )
                    {
                        System.out.println( ports.equals( Serial.list() ) );
                        ports = Serial.list();
                        window.updateComPort( ports );

                    }
                    try
                    {
                        sleep( 3000 );
                    }
                    catch( InterruptedException e )
                    {

                    }
                }

            }
        };
        searchThread.start();

    }

    public void connect()  throws RuntimeException
    {
        try
        {
            portName = (String)window.comPortSelector.getSelectedItem();
            myPort = new Serial( this, portName, 9600 );
            System.out.println("Connected.");
            connected = true;
        }
        catch( java.lang.RuntimeException e )
        {
            e.printStackTrace();
        }
    }
    
    public boolean isConnected()
    {
        return connected;
    }

    public void send( String data ) {
        myPort.write( data );
    }
    
    public Serial getMyPort()
    {
        return myPort;
    }

    public void send( List runningBlocks, List blockNumber )
    {
        List dataAsList = new ArrayList();
        int nextRunningBlock = 0;
        int previousRunningBlock = 0;
        stringBuilder = new StringBuilder();
        Iterator runningIterator = runningBlocks.iterator();
        while( runningIterator.hasNext() )
        {
            nextRunningBlock = (int)runningIterator.next();
            for( int i = previousRunningBlock; i < nextRunningBlock; i++ )
            {
                if( i != nextRunningBlock - 1 )
                {
                    dataAsList.add( i , 0 );
                }
                else
                {
                    dataAsList.add( i , 1 );
                }

            }
            previousRunningBlock = nextRunningBlock;
        }
        
        for (Iterator<Integer> iter = dataAsList.iterator(); iter.hasNext(); ) 
        {
            stringBuilder.append( Integer.toString( iter.next() ) );
    
        }
        send( stringBuilder.toString() );
        System.out.println( stringBuilder.toString() );
    }

    public void disconnect() {
        System.out.println( portName + " Disconnected" );
        if( myPort != null )
        {
            myPort.dispose();
        }
        connected = false;

    }
    
}
