package iitm.apl.MazeGenerator;

import java.awt.event.ActionEvent;

/**
 *
 * @author Sathyanarayanan S
 */
public class ButtonListener implements java.awt.event.ActionListener {
    private Recorder f;
    
    ButtonListener(Recorder f)
    {
        this.f=f;
    }
    
    public void actionPerformed(ActionEvent ae) {
        if(ae.getActionCommand().compareToIgnoreCase("next")==0)
        {            
            f.loadNextSnap();
        }
        if(ae.getActionCommand().compareToIgnoreCase("prev")==0)
        {
            f.loadPrevSnap();
        }
        if(ae.getActionCommand().compareTo("Play")==0)
        {
            f.playSequence();
        }
        if(ae.getActionCommand().compareTo("Rewind")==0)
        {
            f.rewind();            
        }
    }
}
