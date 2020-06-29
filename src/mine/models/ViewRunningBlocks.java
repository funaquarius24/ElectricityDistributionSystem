/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mine.models;

import static com.sun.java.accessibility.util.AWTEventMonitor.addWindowListener;
import java.awt.event.WindowListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.net.InetAddress;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;
import org.joda.time.*;
/**
 *
 * @author AQUAVIRUS
 */
public class ViewRunningBlocks 
{
    private static Connection conn;
    private Vector<Vector<String>> blockVector;
    private String day;
    private DateTime timeNow;
    private SimpleDateFormat dateFormat;
    private DateTime startTime;
    private DateTime endTime;
    
    
    public ViewRunningBlocks( Connection conn )
    {
        ViewRunningBlocks.conn = conn;
        init();
        getRunningBlocks( "friday" );
    }
    
    public ViewRunningBlocks()
    {
        init();
    }
    
    private void init()
    {
        
    }
    
    public Vector getRunningBlocks( String day )
    {
        this.day = new SimpleDateFormat("EEEE").format( DateTime.now() );
        try
        {
            blockVector = new Vector<>();

            PreparedStatement preBlock = conn.prepareStatement("select * from Block");
            
            ResultSet rsBlock = preBlock.executeQuery();
            

            while(rsBlock.next())
            {
                Vector<String> block = new Vector<String>();
                block.add( rsBlock.getString(1) );
                block.add( rsBlock.getString(2) );
                block.add( rsBlock.getString(3) );
                block.add( checkRunning( rsBlock.getString( 1 ) ) );
                blockVector.add( block );
            }

        }
        catch( SQLException ex )
        {
            ex.printStackTrace();
        }
        return blockVector;
    }

    private String checkRunning(String block ) {
        try
        {
            String statement = String.format
                ( "SELECT START_TIME, DURATION FROM %s WHERE BLOCK_NUMBER = %s", day, block );
            PreparedStatement preDay = conn.prepareStatement( statement );
            ResultSet rsDay = preDay.executeQuery();
            while( rsDay.next() )
            {
                Date date = rsDay.getTimestamp( 1 );
                startTime = new DateTime( date );
                endTime.plusHours( rsDay.getInt( 2 ) );
                if( startTime.isBeforeNow() && endTime.isAfterNow() )
                {
                    return "Yes";
                }
                else
                {
                    return "NO";
                }
            }
        }
        catch( SQLException ex )
        {
            ex.printStackTrace();
        }
        
        return "NO";
    }
    
    
}
