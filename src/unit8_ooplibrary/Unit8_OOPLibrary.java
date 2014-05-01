

package unit8_ooplibrary;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 *
 * @author John Mambo <john.mambo@gmail.com>
 */
public class Unit8_OOPLibrary {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        final boolean DEBUG = false;
        
        if (DEBUG) {
            final Inventory inv = new Inventory(new AudioVisualMaterial());
            //final URL soundFileURL = inv.getClass().getResource("/Sound/error.wav");
            final URL soundFileURL = inv.getClass().getResource("/Sound/02 ANDERSON Plink, Plank, Plunk!.wav");
            
            System.out.print(inv.getLibraryMaterial().displayInfo());
        
            try{
            ((AudioVisualMaterial) inv.getLibraryMaterial()).playSoundClip(soundFileURL, 10);
            } catch (IOException | LineUnavailableException | UnsupportedAudioFileException ex) {
              Logger.getLogger(Unit8_OOPLibrary.class.getName()).log(Level.SEVERE, null, ex);
            }

            //<editor-fold defaultstate="collapsed" desc="Play Sound directly">
            /*
            try {
            Clip clip = AudioSystem.getClip();
            clip = ((AudioVisualMaterial)inv.getLibraryMaterial()).getSoundClip(soundFileURL);

            while (!Thread.currentThread().isInterrupted()) {
            clip.start();
            
            }
            clip.stop();
            } catch (IOException | LineUnavailableException | UnsupportedAudioFileException ex) {
            Logger.getLogger(Unit8_OOPLibrary.class.getName()).log(Level.SEVERE, null, ex);
            }
            */
            //</editor-fold>
 
            //<editor-fold defaultstate="collapsed" desc="Playing Sound indirectly - preferred">
            /*
            final Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    try {
                        ((AudioVisualMaterial) inv.getLibraryMaterial()).playSoundClip(soundFileURL);
                    } catch (IOException | LineUnavailableException | UnsupportedAudioFileException ex) {
                        Logger.getLogger(Unit8_OOPLibrary.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            };
            runnable.run();
            */
            //</editor-fold> 
                  
            //<editor-fold defaultstate="collapsed" desc="Play Sounds using ExecuteService execute() via anonymous method">
            /*
            final Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    try {
                        ((AudioVisualMaterial) inv.getLibraryMaterial()).playSoundClip(soundFileURL);
                    } catch (IOException | LineUnavailableException | UnsupportedAudioFileException ex) {
                        Logger.getLogger(Unit8_OOPLibrary.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            };
                        
            ExecutorService executor = Executors.newSingleThreadExecutor();
            executor.execute(new Runnable() {
                @Override
                public void run() {
                    runnable.run();
                }
            });
            executor.shutdown();
            */
            //</editor-fold>
                       
            //<editor-fold defaultstate="collapsed" desc="Play Sounds using ExecuteService invokeAny() - Preferred">
            /*            
            final Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    try {
                        ((AudioVisualMaterial) inv.getLibraryMaterial()).playSoundClip(soundFileURL);
                    } catch (IOException | LineUnavailableException | UnsupportedAudioFileException ex) {
                        Logger.getLogger(Unit8_OOPLibrary.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            };
                        
            ExecutorService executor = Executors.newSingleThreadExecutor();
            List<Callable<String>> callables = new ArrayList<Callable<String>>();
            
            callables.add(new Callable<String>() {
                public String call() throws Exception {
                    runnable.run();
                    return "Play Sound";
                }
            });
            
            try{
                executor.invokeAny(callables, 1, TimeUnit.SECONDS); // Run with a timeout of 1 Seconds.
            }
            catch(InterruptedException | ExecutionException | TimeoutException ex){
                executor.shutdown();
            }
            executor.shutdown();
            */
            //</editor-fold>  
            
            Inventory inventory = new Inventory(new LibraryMaterial() {});
            inventory.AddInventory(inventory);
            Inventory inventory1 = new Inventory(new LibraryMaterial() {});
            inventory.AddInventory(inventory1);
            System.out.print(inventory.nextLibraryMaterialID()+"\n");
            
            
            System.out.print("Done!\n");
        }//end if DEBUG 
        
        /**
         * Running the GUI
         */
        LibraryGUI ui = null;
        try{
            ui = new LibraryGUI();
        }
        catch(Exception ex){
            if(ui == null){
            } else {
                ui.dispose();
            }
        }
    }//end method MAIN
}//end class Unit8_OOPLibrary 
