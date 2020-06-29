/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mine.models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Vector;
import java.util.Observable;
import org.joda.time.DateTime;

/**
 *
 * @author AQUAVIRUS
 */
public class InsertModel extends Observable{
    
    private String blockName;
    private String blockNumber;
    private String duration;
    private PreparedStatement prepareAllocate;
    private static Connection conn;
    private String startTime;
    private String blockAddress;
    private String blockNick;
    private PreparedStatement prepareCreate;
    private String day;
    
    public InsertModel( Connection conn )
    {
        this.conn = conn;
    }
    
    public void addNewBlock( String address, String nick ) throws Exception
    {
        this.blockAddress = address;
        this.blockNick = nick;
        
        try
        {
            String dataWithNick = String.format
                            ( "INSERT INTO electricitydb.BLOCK( ADDRESS, NICK ) VALUES ( ?, ? )" );
            String dataWithoutNick = String.format
                            ( "INSERT INTO electricitydb.BLOCK( ADDRESS ) VALUES ( ? )" );
            if( blockNick.length() >= 2 )
            {
                prepareCreate = conn.prepareStatement( dataWithNick );
                prepareCreate.setString( 1 , blockAddress );
                prepareCreate.setString( 2 , blockNick );
            }
            else
            {
                prepareCreate = conn.prepareStatement( dataWithoutNick );
                prepareCreate.setString( 1 , blockAddress );
            }
            prepareCreate.executeUpdate();
            setChanged();
            notifyObservers( "CREATE" );
        }catch( Exception exception )
        {
            exception.printStackTrace();
            throw new Exception();
        }
    }
    
    public void allocateBlock( String day, String number, String startTime, String duration ) throws Exception
    { 
        this.blockNumber = number;
        this.startTime = startTime;
        this.duration = duration;
        this.day = day;
        try
        {
            String data = String.format
                            ( "INSERT INTO %s( %s, %s, %s ) VALUES ( ?, ?, ? )", 
                                    day, "BLOCK_NUMBER", "START_TIME", "DURATION"  );
            prepareAllocate = conn.prepareStatement( data );
            prepareAllocate.setInt( 1 , Integer.parseInt( number ) );
            prepareAllocate.setString( 2 , startTime );
            prepareAllocate.setInt( 3 , Integer.parseInt( duration ) );
            prepareAllocate.executeUpdate();
            setChanged();
            notifyObservers( "ALLOCATE" );
        }catch( Exception e )
        {
            e.printStackTrace();
            throw new Exception();
            
        }
    }
    
    
    
}
