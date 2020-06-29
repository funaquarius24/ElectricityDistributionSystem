/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mine.main;

import java.lang.reflect.Method;
import java.util.Scanner;
import javax.swing.JFrame;
import mine.Controller.ViewController;
import mine.models.LoginModel;

/**
 *
 * @author AQUAVIRUS
 */
public class MainTest {
    public static void main( String[] args )
    {
        try
        {
            LoginModel loginModel = new LoginModel();
            loginModel.createConnection( "root" , "root" );
            ViewController control = new ViewController( loginModel );
        }catch( Exception e )
        {
            
        }
        
    }
    
}
