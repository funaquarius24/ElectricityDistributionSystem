/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mine.views;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author AQUAVIRUS
 */
public class ErrorDialog {
    
    public void show( JFrame frame, String message )
    {
        JOptionPane.showMessageDialog( frame , message );
    }
    
}
