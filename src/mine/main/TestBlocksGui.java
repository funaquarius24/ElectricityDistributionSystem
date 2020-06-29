/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mine.main;

import mine.models.ViewRunningBlocks;
import mine.views.ShowSchedule;

/**
 *
 * @author AQUAVIRUS
 */
public class TestBlocksGui 
{
    public static void main( String[] args )
    {
        try{
            ShowSchedule view = new ShowSchedule( null );
            view.setVisible(true);
        }catch( Exception e )
        {
            
        }
        
        
    }
    
}
