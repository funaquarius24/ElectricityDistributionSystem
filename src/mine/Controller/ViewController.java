/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mine.Controller;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.Observable;
import java.util.Observer;
import java.util.Vector;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javax.swing.JFrame;
import mine.models.*;
import mine.views.*;



/**
 *
 * @author AQUAVIRUS
 */
public class ViewController implements Observer
{
    LoginModel model;
    MainFrame mainFrame;
    MenuView menuView;
    Toolkit tool;
    Dimension dim;
    ViewBlocks viewBlocks;
    BlocksGui blocksGui;
    ShowRunningBlocks showRunningBlocks;
    private AllocateFrame11 allocateFrame;
    private String blockName;
    private String day;
    private int duration;
    private ErrorDialog errorDialog;
    private InsertModel insertModel;
    private CreateBlockFrame createBlockFrame;
    private ShowSchedule showSchedule;
    private FaultyFrame faultyFrame;
    private SerialController serialController;
    private UpdateRunningThread updateRunningThread;
    ExecutorService updateRunningExecutorService;
    
    
    
    public ViewController( LoginModel model )
    {
        this.model = model;
        init();
    }
    
    private void init()
    {   
        menuView = new MenuView();
        insertModel = new InsertModel( model.getConnection() );
        viewBlocks = new ViewBlocks( model.getConnection() );
        viewBlocks.clearRunning();
        errorDialog = new ErrorDialog();
        updateRunningThread = new UpdateRunningThread();
        updateRunningExecutorService = Executors.newCachedThreadPool();
        createBlockObjects();
        showMainView();
        addListenersForMenu();
        insertModel.addObserver( this );
        updateRunningThread.addObserver( this );
        
    }
    
    private void createBlockObjects()
    {
        mainFrame = new MainFrame( viewBlocks.getBlockNumbers(), 
                viewBlocks.getRunningBlockNumbers()  );
        blocksGui = new BlocksGui( viewBlocks.getBlocks() );
        showRunningBlocks = new ShowRunningBlocks( viewBlocks.getRunningBlocks() );
        faultyFrame = new FaultyFrame();
        serialController = new SerialController( menuView, viewBlocks, mainFrame );
        allocateFrame = new AllocateFrame11();
        showSchedule = new ShowSchedule( viewBlocks.getSchedule() );
        
        createBlockFrame = new CreateBlockFrame();
        
    }
    
    private void showRunningBlocks()
    {
        showRunningBlocks.setVisible( true );
    }
    
    private void getSystemSize()
    {
        tool = Toolkit.getDefaultToolkit();
        dim = new Dimension( 461 , 464 );
    }
    
    private void showMainView()
    {
        addMenu( mainFrame );
        mainFrame.setTitle( "WELCOME" );
        getSystemSize();
        mainFrame.setSize( dim );
        mainFrame.setVisible( true );
        updateRunningExecutorService.execute( updateRunningThread );
    }
    
    private void addMenu( JFrame frame )
    {
        frame.setJMenuBar( menuView.getJMenuBar() );
    }
    
    private void showBlocksWindow()
    {
        blocksGui.getBlocksTable().setEnabled( false );
        blocksGui.setVisible( true );
    }
    
    private void showCreateBlocksFrame()
    {
        addListenersForCreate();
        createBlockFrame.setVisible( true );
    }
    
    private void showAllocateWindow()
    {
        addListenersForAllocate();
        allocateFrame.setVisible( true );
    }
    
    private void showSchedule( ActionEvent e )
    {
        if( e.getSource() == menuView.getEditScheduleMenuItem() )
        {
            showSchedule.getScheduleTable().setEnabled( true );
        }
        else
        {
            showSchedule.getScheduleTable().setEnabled( false );
        }
        addListenersForShowShedule();
        showSchedule.setVisible( true );
    }
    
    private void showFaulty()
    {
        faultyFrame.setVisible( true );
    }
    
    private void allocateButtonActionPerformed() throws IllegalArgumentException
    {
        String day = allocateFrame.getAllocateDay();
        String blockNumber = allocateFrame.getAllocateBlockNumberFieldText();
        String startTime = allocateFrame.getAllocateStartTime();
        String duration = allocateFrame.getAllocateDurationFieldText();
        try
        {
            boolean blockNumberFieldIsEmpty = blockNumber.length() < 1;
            boolean startFieldIsEmpty = startTime.length() <= 1;
            boolean durationFieldIsEmpty = duration.length() < 1;

            if( blockNumberFieldIsEmpty || startFieldIsEmpty ||
                   durationFieldIsEmpty )
            {
                throw new IllegalArgumentException( "Empty Field..." );
            }
            try
            {
                insertModel.allocateBlock( day, blockNumber, startTime, duration );
                errorDialog.show(allocateFrame ,  "SUCCESSFULLY ALLOCATED." );
            }
            catch( Exception exception )
            {
                errorDialog.show( allocateFrame , 
                        "UNABLE TO ALLOCATE. Please do not allocate to a non-existent block." );
            }
            
        }catch( IllegalArgumentException ex )
        {
            errorDialog.show( allocateFrame , "Please Fill All Fields.");
        }
        
    }
    
    private void createButtonActionPerformed() throws IllegalArgumentException
    {
        try
        {
            String blockAddress = createBlockFrame.getBlockAddressText();
            String blockNick = createBlockFrame.getBlockNickText();
            boolean blockAddressIsEmpty = blockAddress.length() < 1;
            if( blockAddressIsEmpty )
            {
                throw new IllegalArgumentException( "ADDRESS EMPTY..." );
            }
            System.out.println( blockAddressIsEmpty );
            System.out.println( blockNick );
            try
            {
                insertModel.addNewBlock( blockAddress, blockNick );
                errorDialog.show( createBlockFrame ,  "SUCCESSFULLY CREATED." );
            }
            catch( Exception exception )
            {
                errorDialog.show( createBlockFrame ,  "CANNOT CREATE BECAUSE SOME ERROR OCCURS." );
            }
            
            
        }catch( IllegalArgumentException exception )
        {
            errorDialog.show( createBlockFrame , "ADDRESS CANNOT BE EMPTY." );
        }
    }
    
    private void addListenersForMainFrame()
    {
        mainFrame.addListenerForClose( new WindowListener() {

            @Override
            public void windowOpened(WindowEvent e) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void windowClosing(WindowEvent e) {
                viewBlocks.clearRunning();
            }

            @Override
            public void windowClosed(WindowEvent e) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void windowIconified(WindowEvent e) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void windowDeiconified(WindowEvent e) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void windowActivated(WindowEvent e) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void windowDeactivated(WindowEvent e) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        } );
    }
    
    private void addListenersForAllocate()
    {
        allocateFrame.addAllocateButtonActionListener( new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                allocateButtonActionPerformed();
            }
        } );
    }
    
    private void addListenersForCreate()
    {
        createBlockFrame.addCreateButtonActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                createButtonActionPerformed();
            }
        } );
    }
    
    private void  addListenersForShowShedule()
    {
        showSchedule.addDayComboBoxItemListener( new ItemListener() {

            @Override
            public void itemStateChanged(ItemEvent e) {
                day = showSchedule.getComboBoxText();
                showSchedule.updateTableData( viewBlocks.getSchedule( day ) );
            }
        } );
    }
    
    private void addListenersForMenu()
    {
        
        menuView.addAllocateMenuItemListener( new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                showAllocateWindow();
            }
        } );
        
        menuView.addHomeMenuItemActionListener( new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                showMainView();
            }
        } );
        
        menuView.addAllBlocksMenuItemActionListener( new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                showBlocksWindow();
            }
        } );
        
        menuView.addCreateBlockMenuItemActionListener( new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                showCreateBlocksFrame();
            }
        } );
        
        menuView.addRunningBlocksMenuItemActionListener( new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                showRunningBlocks();
            }
        } );
        
        menuView.addViewScheduleMenuItemActionListener( new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                showSchedule( e );
            }
        } );
        
        menuView.addEditScheduleMenuItemActionListener( new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                showSchedule( e );
            }
        } );
        
        menuView.addExitMenuItemActionListener( new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                viewBlocks.clearRunning();
                System.exit( 0 );
            }
        } );
        
        menuView.addFaultyMenuItemActionListener( new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                showFaulty();
            }
        } );
        
        menuView.addChangeBlockMenuItemActionListener( new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        } );
    }

    @Override
    public void update(Observable o, Object arg) {
        Vector<Vector<String>> dataForGetBlocks = viewBlocks.getBlocks();
        if( arg == "CREATE" )
        {
            //System.out.println("Create");
           blocksGui.updateTableData(dataForGetBlocks );
           mainFrame.addBlock();
        }
        else
        {
            Vector<Vector<String>> dataForGetSchedule = viewBlocks.getSchedule( day );
            System.out.println(" Allocate ");
            showSchedule.updateTableData( dataForGetSchedule );
            blocksGui.updateTableData( dataForGetBlocks );
            showRunningBlocks.updateTableData( viewBlocks.getRunningBlocks() );
            mainFrame.makeRunning( viewBlocks.getRunningBlockNumbers() );
        }
    }
    
}
