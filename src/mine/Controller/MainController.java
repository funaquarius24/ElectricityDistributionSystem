/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mine.Controller;

import mine.models.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.InetAddress;
import java.sql.SQLException;
import mine.views.*;

/**
 *
 * @author AQUAVIRUS
 */
public class MainController {
    private Login login;
    private LoginModel model;
    Thread waitConnect;
    private int errorCode;
    private ViewController viewController;
    private String username;
    private String password;
    private ErrorDialog errorDialog;
    private static final String INVALID = "INVALID CREDENTIAL. RETRY...";
    private static final String UNKNOWN = "UNKNOWN ERROR. RETRY...";
    
    public MainController( LoginModel model ) {
        this.model = model;
        initComponents();
        initObjects();
    }
    
    private void initObjects()
    {
        login = new Login();
        addListeners();
        login.setVisible( true );
        errorDialog = new ErrorDialog();
    }
    
    private void addListeners()
    {
        login.addLoginButtonActionListener( new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                loginButtonActionPerformed();
            }
        } );
        
        login.addPasswordFieldActionListener( new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                loginButtonActionPerformed();
            }
        } );
    }
    
    
    
    private void checkErrorCode()
    {
        errorCode = model.getErrorCode();
        if( errorCode == 2 )
        {
            errorDialog.show( login, INVALID );
        }
        else
        {
            errorDialog.show( login, UNKNOWN );
        }
    }
    
    public void loginButtonActionPerformed()
    {
        try
        {
        username = login.getUsernameFieldText();
        password = login.getPasswordFieldText();
        if ( username.equals("") || password.equals("") ) {
                errorDialog.show( login , "Fill All Fields!" );
                return;
            }
        model.createConnection( username,
                password );
        login.setVisible( false );
        login.dispose();
        viewController = new ViewController( model );
        }catch( SQLException exception )
        {
            checkErrorCode();
        }
    }

    private void initComponents() {
    }
    
    
}
