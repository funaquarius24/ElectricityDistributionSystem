/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mine.models;

import java.text.SimpleDateFormat;
import java.util.Observable;
import org.joda.time.DateTime;

/**
 *
 * @author Jnathy
 */
public class UpdateRunningThread extends Observable implements Runnable {
    private String day;
    private final int sleepTime = 60000;
    private DateTime timeNow;
    private SimpleDateFormat dateFormat;
    private int previousHour;
    
    public UpdateRunningThread()
    {
        
    }

    @Override
    public void run() {
        while( true )
        {
            timeNow = new DateTime();
            int thisHour = timeNow.getHourOfDay();
            if( previousHour < thisHour )
            {
                previousHour = thisHour;
                setChanged();
                notifyObservers( "UPDATE" );
            }
            
            try
            {
                Thread.sleep( sleepTime );
            }
            catch( InterruptedException exception )
            {
                
            }
            
        }
    }
    
}
