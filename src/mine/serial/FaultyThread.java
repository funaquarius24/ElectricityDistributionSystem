/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mine.serial;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import mine.models.ViewBlocks;
import mine.views.FaultyFrame;
import mine.views.MainFrame;

/**
 *
 * @author AQUAVIRUS
 */
public class FaultyThread implements Runnable
{
    private SendSerial sendSerial;
    private MainFrame mainFrame;
    private final int sleepTime = 500;
    private boolean connected;
    private String faultyDataAsString;
    private List<Integer> faultyDataAsList;
    private List<Integer> previousFaultyDataAsList;
    private FaultyFrame faultyFrame;
    private ViewBlocks viewBlocks;
    
    public FaultyThread( SendSerial sendSerial, MainFrame mainFrame )
    {
        this.sendSerial = sendSerial;
        this.mainFrame = mainFrame;
        //this.faultyFrame = faultyFrame;
        this.viewBlocks = viewBlocks;
        previousFaultyDataAsList = new ArrayList();
    }
    
    public void startSend()
    {
        this.connected = true;
    }
    
    public void stopSend()
    {
        this.connected = false;
    }
    
    @Override
    public void run() {
        while( this.connected )
        {
            if( sendSerial.getMyPort().available() > 0 )
            {
                faultyDataAsString = sendSerial.getMyPort().readString();
                faultyDataAsList = new ArrayList();
                char firstChar = faultyDataAsString.charAt(0);
                if( !( firstChar == '1' || firstChar == '0' ) )
                    break;
                char character = '1';
                for(int i = 0; i < faultyDataAsString.length(); i++){
                    if(faultyDataAsString.charAt(i) == character){
                       faultyDataAsList.add(i + 1);
                    }
                }
                
                System.out.println( faultyDataAsList );
                if( !faultyDataAsList.equals( previousFaultyDataAsList ) )
                {
                    mainFrame.makeFaulty( faultyDataAsList );
                    //faultyFrame.updateTableData( viewBlocks.getRunningBlocks(), faultyDataAsList );
                    previousFaultyDataAsList = faultyDataAsList;
                }
                else if( faultyDataAsList.size() == 0 )
                {
                    mainFrame.makeDefault();
                    previousFaultyDataAsList = faultyDataAsList;
                }
            }
            try
            {
                Thread.sleep( sleepTime );
            }
            catch( InterruptedException exception )
            {
                //exception.printStackTrace();
                Thread.currentThread().interrupt();
            }
        }
    }
}
