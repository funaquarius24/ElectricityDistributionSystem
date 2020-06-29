/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mine.serial;

import mine.models.ViewBlocks;

/**
 *
 * @author AQUAVIRUS
 */
public class SendThread implements Runnable{
    private final SendSerial sendSerial;
    private ViewBlocks viewBlocks;
    private final int sleepTime = 5000;
    private boolean connected;
    
    public SendThread( SendSerial sendSerial, ViewBlocks viewBlocks )
    {
        this.sendSerial = sendSerial;
        this.viewBlocks = viewBlocks;
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
            sendSerial.send( viewBlocks.getRunningBlockNumbers(), 
                viewBlocks.getBlockNumbers() );

            try
            {
                Thread.sleep( sleepTime );
            }
            catch( InterruptedException exception )
            {
                exception.printStackTrace();
                Thread.currentThread().interrupt();
            }
        }
    }
    
}
