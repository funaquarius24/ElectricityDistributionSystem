/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mine.Controller;

import java.util.List;
import mine.models.UpdateRunningThread;
import mine.models.ViewBlocks;
import mine.views.MainFrame;


/**
 *
 * @author AQUAVIRUS
 */
public class ThreadController 
{
    private ViewBlocks viewBlocks;
    private List runningBlocksList;
    private MainFrame mainFrame;
    UpdateRunningThread updateRunningThread;
    
    public ThreadController( ViewBlocks viewBlocks )
    {
        this.viewBlocks = viewBlocks;
        //this.updateRunningThread = updateRunningThread;
    }
    
    public void checkRunning()
    {
        Thread checkRunning = new Thread()
        {
            public void run()
            {
                while( true )
                {
                    runningBlocksList = viewBlocks.getRunningBlockNumbers();
                    if( runningBlocksList != null )
                    {
                        mainFrame.makeRunning( runningBlocksList );
                    }
                    try
                    {
                        sleep( 3000 );
                    }
                    catch( InterruptedException exception )
                    {
                        
                    }
                }
            }
        };
        checkRunning.start();
    }
    
}
