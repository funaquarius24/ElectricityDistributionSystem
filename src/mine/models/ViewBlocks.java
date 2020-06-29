/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mine.models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.net.InetAddress;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Vector;
import org.joda.time.DateTime;
/**
 *
 * @author AQUAVIRUS
 */
public class ViewBlocks 
{
    private static Connection conn;
    private Vector<Vector<String>> blockVector;
    private Vector<Vector<String>> schedule;
    private String day;
    private String scheduleDay;
    private DateTime timeNow;
    private SimpleDateFormat dateFormat;
    private int startTime;
    private int endTime;
    private int duration;
    private PreparedStatement preBlock;
    private PreparedStatement preRunningBlock;
    private ResultSet rsStartDuration;
    private PreparedStatement preStartDuration;
    private PreparedStatement tempPre;
    private ResultSet tempRe;
    private String block;
    List runningBlockNumber;
    List blockNumber;
    
    
    public ViewBlocks( Connection conn )
    {
        ViewBlocks.conn = conn;
        init();
        getBlocks();
        getRunningBlocks();
    }
    
    private void init()
    {
        this.day = new SimpleDateFormat("EEEE").format( new Date() );
        if( day.equalsIgnoreCase( "Wednesday" ) )
        {
            day = "Wednessday";
        }
    }
    
    public Vector getRunningBlocks()
    {
        
        try
        {
            blockVector = new Vector<>();
            runningBlockNumber = new ArrayList();

            String selectPart = "select RUNNING.BLOCK_NUMBER, BLOCK.ADDRESS from RUNNING ";
            String joinPart = "INNER JOIN BLOCK ON BLOCK.BLOCK_NUMBER = RUNNING.BLOCK_NUMBER";
            
            preRunningBlock = conn.prepareStatement( selectPart + joinPart );
            ResultSet rsRunningBlock = preRunningBlock.executeQuery();
            
            while(rsRunningBlock.next())
            {
                Vector<String> runningBlock = new Vector<String>();
                runningBlock.add( rsRunningBlock.getString(1) );
                runningBlock.add( rsRunningBlock.getString(2) );
                runningBlockNumber.add( rsRunningBlock.getInt( 1 ) );
                blockVector.add( runningBlock );
            }

        }
        catch( SQLException ex )
        {
            ex.printStackTrace();
        }
        return blockVector;
    }
    
    public List< Integer > getBlockNumbers()
    {
        return blockNumber;
    }
    
    public List<Integer> getRunningBlockNumbers()
    {
        return runningBlockNumber;
    }
    
    public Vector getBlocks()
    {
        clearRunning();
        timeNow = new DateTime();
        blockNumber = new ArrayList();
        try
        {
            blockVector = new Vector<>();

            preBlock = conn.prepareStatement("select * from Block");
            ResultSet rsBlock = preBlock.executeQuery();
            
            while(rsBlock.next())
            {
                Vector<String> block = new Vector<String>();
                block.add( rsBlock.getString(1) );
                blockNumber.add( rsBlock.getInt( 1 ) );
                block.add( rsBlock.getString(2) );
                block.add( rsBlock.getString(3) );
                block.add( checkRunning( rsBlock.getString( 1 ) ) );
                block.add( rsBlock.getString( 4 ) );
                blockVector.add( block );
            }
            
            return blockVector;
        }
        catch( SQLException ex )
        {
            ex.printStackTrace();
        }
        return null;
    }

    private String checkRunning(String block ) {
        try
        {
            this.block = block;
            String statement = String.format
                ( "SELECT START_TIME, DURATION FROM %s WHERE BLOCK_NUMBER = %s", day, block );
            preStartDuration = conn.prepareStatement( statement );
            rsStartDuration = preStartDuration.executeQuery();
            while( rsStartDuration.next() )
            {
                Time time = rsStartDuration.getTime( 1 );
                duration = ( int )rsStartDuration.getDouble( 2 );
                
                startTime = new DateTime( time ).getHourOfDay();
                endTime = startTime + duration ;
                //System.out.println( day );
                //System.out.println( startTime );
                //System.out.println( endTime );
                //System.out.println( timeNow.getHourOfDay() );
                //System.out.println( startTime <= timeNow.getHourOfDay() && endTime >= timeNow.getHourOfDay() );
                if( startTime <= timeNow.getHourOfDay() && endTime >= timeNow.getHourOfDay() )
                {
                    try
                    {
                        String data = String.format
                            ( "INSERT INTO RUNNING(BLOCK_NUMBER) VALUES ( ? )" );
                        tempPre = conn.prepareStatement( data );
                        tempPre.setInt( 1 , Integer.parseInt( block ) );
                        tempPre.executeUpdate();
                    }catch( Exception ex )
                    {
                        ex.printStackTrace();
                    }
                    
                    return "Yes";
                }
            }
        }
        catch( SQLException ex )
        {
            ex.printStackTrace();
        }
        
        return "NO";
    }
    
    private boolean isRunning( String check )
    {
        if( check.equals( "YES" ) )
            return true;
        return false;
    }

    public void clearRunning() {
        try
        {
            tempPre = conn.prepareStatement( "TRUNCATE TABLE RUNNING" );
            tempPre.executeUpdate();
        }
        catch( Exception ex )
        {
            ex.printStackTrace();
        }
    }

    public Vector getSchedule( String day ) 
    {
        if( day != null )
        {
            this.scheduleDay = day;
        }
        else
        {
            scheduleDay = this.day;
        }
        try
        {
            schedule = new Vector<>();

            PreparedStatement pre = conn.prepareStatement("select * from " + scheduleDay );

            ResultSet rs = pre.executeQuery();

            while(rs.next())
            {
                System.out.println( rs.getString(1) );
                Vector<String> block = new Vector<String>();
                block.add( rs.getString(1) );
                block.add( rs.getString(2) );
                block.add( rs.getString(3) );
                schedule.add( block );
            }
            return schedule;
        }catch( Exception e )
        {
            e.printStackTrace();
        }
        return null;
    }
    
    public Vector getSchedule()
    {
        return getSchedule( day );
    }
    
    public int getNumberOfRows()
    {
        try
        {
            blockVector = new Vector<>();

            preBlock = conn.prepareStatement("SELECT COUNT(*) FROM Block");
            ResultSet rsBlock = preBlock.executeQuery();
            
            rsBlock.next();
            return rsBlock.getInt( 1 );
        }
        catch( SQLException ex )
        {
            ex.printStackTrace();
        }
        return 0;
    }
}
