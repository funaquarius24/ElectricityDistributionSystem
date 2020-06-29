/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mine.Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import javax.swing.JMenu;
import mine.models.ViewBlocks;
import mine.serial.FaultyThread;
import mine.serial.SendSerial;
import mine.serial.SendThread;
import mine.serial.SetPort;
import mine.views.MainFrame;
import mine.views.MenuView;

/**
 *
 * @author AQUAVIRUS
 */
public class SerialController 
{
    private SetPort setPort;
    private SendSerial sendSerial;
    private MenuView menuView;
    private ViewBlocks viewBlocks;
    private Thread startSend;
    protected boolean connected;
    private SendThread sendThread;
    private ExecutorService executorService;
    private MainFrame mainFrame;
    FaultyThread faultyThread;
    
    public SerialController( MenuView menuView, ViewBlocks viewBlocks, 
            MainFrame mainFrame  )
    {
        this.menuView = menuView;
        this.viewBlocks = viewBlocks;
        this.mainFrame = mainFrame;
        setPort = new SetPort();
        sendSerial = new SendSerial( setPort );
        sendThread = new SendThread( sendSerial , viewBlocks );
        faultyThread = new FaultyThread( sendSerial , mainFrame );
        menuView = new MenuView();
        addListenersForMenuView();
        addListenersForSetPort();
    }
    
    private void addListenersForSetPort()
    {
        setPort.addToggleConnectButtonActionListener( new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if( setPort.getToConnect() == true )
                {
                    sendSerial.connect();
                    
                    System.out.println( 38 );
                    if( sendSerial.isConnected() )
                    {
                        System.out.println( 743 );
                        setPort.getToggleConnectButton().setText( "Disconnect" );
                        startSending();
                    }
                    else
                    {
                        System.out.println( 74387438 );
                        setPort.getToggleConnectButton().setSelected( false );
                    }
                }
                else
                {
                    terminateSend();
                    
                    sendSerial.disconnect();
                    setPort.getToggleConnectButton().setText( "Connect" );
                }
                
            }
        } );
    }
    
    private void addListenersForMenuView()
    {
        
        menuView.addSetPortMenuItemActionListener( new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                showSetPort();
            }
        } );
    }
    
    private void showSetPort()
    {
        setPort.setVisible( true );
    }
    
    public void terminateSend()
    {
        sendThread.stopSend();
        faultyThread.stopSend();
        executorService.shutdownNow();
        executorService = null;
        mainFrame.makeDefault();
    }
    
    private void startSending()
    {
        sendThread.startSend();
        faultyThread.startSend();
        executorService = Executors.newCachedThreadPool();
        executorService.execute( sendThread );
        executorService.execute( faultyThread );
        
    }
    
    /**
    private void initiateSend( boolean connected )
    {
        startSend = new Thread()
        {
            public boolean connected = sendSerial.isConnected();
            public void run()
            {
                while( connected )
                {
                    sendSerial.send( viewBlocks.getRunningBlockNumbers(), 
                        viewBlocks.getBlockNumbers() );
                    
                    try
                    {
                        sleep( 1000 );
                    }
                    catch( InterruptedException exception )
                    {
                        exception.printStackTrace();
                    }
                }
                
            }
        };
        startSend.start();
    }
    */
    
}
