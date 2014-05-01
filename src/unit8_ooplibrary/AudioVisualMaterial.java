

package unit8_ooplibrary;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URI;
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
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.ImageIcon;

/**
 *
 * @author John Mambo <john.mambo@gmail.com>
 */
public class AudioVisualMaterial extends LibraryMaterial{
   
    public enum AudioVisualType{
        UNDEFINED(0),
        AUDIOTAPE(1),
        CD(2), 
        DVD(3);
        
        private final int val;

        private AudioVisualType(int val){
            this.val = val;
        }
        
        /**
         * This method returns the Ordinal position of the Level enumerable value property.
         * @return {@code int} Corresponding ordinal position of the Level enumerable property. 
         */
        public int index(){
            return val;
        }
    }
    
    private AudioVisualType type;
    private ImageIcon coverImage;
    private Clip soundClip;
    
    public AudioVisualMaterial() {
        super();
        this.type = AudioVisualType.UNDEFINED;
        this.coverImage = null;
        this.soundClip = null;
    }
    
    public AudioVisualMaterial(AudioVisualType type, ImageIcon coverImage, Clip soundClip) {
        super();
        this.type = type;
        this.coverImage = coverImage;
        this.soundClip = soundClip;
    }
    
    public AudioVisualMaterial(String author, String title, BigDecimal price, short publicationYear, AudioVisualType type, ImageIcon coverImage, Clip soundClip) {
        super(author, title, price, publicationYear, coverImage);
        this.type = type;
        this.coverImage = coverImage;
        this.soundClip = soundClip;
    }
    
    public AudioVisualType getType() {
        return this.type;
    }

    public void setType(AudioVisualType type) {
        this.type = type;
    }

    public Clip getSoundClip() {
        return this.soundClip;
    }

    public void setSoundClip(Clip soundClip) {
        this.soundClip = soundClip;
    }

    @Override
    public ImageIcon getCoverImage() {
        if (this.coverImage != null) {
            return this.coverImage;
        }
        return super.getCoverImage();
    }
    
    @Override
    public void setCoverImage(ImageIcon coverImage) {
        super.setCoverImage(coverImage);
        this.coverImage = coverImage;
    }
    
    @Override
    public String displayInfo(){
        StringBuilder str = new StringBuilder();
        str.append(super.displayInfo());
        str.append(String.format("Type: %s%n", this.getType()));
        return str.toString();
    }
    public ImageIcon displayCover(URI file){
        //TODO: Implement displayCover 
        throw new UnsupportedOperationException();
    } 
    
    public static synchronized Clip getSoundClip(URL fileURL) throws IOException, LineUnavailableException, UnsupportedAudioFileException {
        try {
            Clip clip = AudioSystem.getClip();  
            AudioInputStream inputStream;
            // URI format: this.getClass().getResource("/Sound/sample.wav")
            inputStream = AudioSystem.getAudioInputStream(fileURL); 
            clip.open(inputStream);
            while (!Thread.currentThread().isInterrupted()) {
                //clip.start();
                return clip;
            }
            clip.stop();
        } catch (IOException | LineUnavailableException | UnsupportedAudioFileException ex) {
          Logger.getLogger(Unit8_OOPLibrary.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    } 
    
    public synchronized void playSoundClip(URL fileURL) throws IOException, LineUnavailableException, UnsupportedAudioFileException {
        try {
            Clip clip = AudioSystem.getClip();  
            AudioInputStream inputStream;
            // URI format: this.getClass().getResource("/Sound/sample.wav")
            inputStream = AudioSystem.getAudioInputStream(fileURL); 
            clip.open(inputStream);
            while (!Thread.currentThread().isInterrupted()) {
                clip.start();
            }
            clip.stop();
        } catch (IOException | LineUnavailableException | UnsupportedAudioFileException ex) {
          System.out.println(ex);
        }
    }
    
    public synchronized void playSoundClip(final URL fileURL, long seconds) throws IOException, LineUnavailableException, UnsupportedAudioFileException {
        final Runnable runnable = new Runnable() {
            @Override
            public void run() {
                try {
                    Clip clip = AudioSystem.getClip();                        
                    AudioInputStream inputStream;
                    // URI format: this.getClass().getResource("/Sound/sample.wav")
                    inputStream = AudioSystem.getAudioInputStream(fileURL);                        
                    clip.open(inputStream);
                    while (!Thread.currentThread().isInterrupted()) {
                        clip.start();
                    }
                    clip.stop();
                } catch (IOException | LineUnavailableException | UnsupportedAudioFileException ex) {
                    System.out.println(ex);
                }
            }
        };

        ExecutorService executor = Executors.newSingleThreadExecutor();
        List<Callable<String>> callables = new ArrayList<>();

        callables.add(new Callable<String>() {
            @Override
            public String call() throws Exception {
                runnable.run();
                return "Play Sound";
            }
        });

        try{
            executor.invokeAny(callables, seconds, TimeUnit.SECONDS); // Run with a timeout of given Seconds.
        }
        catch(InterruptedException | ExecutionException | TimeoutException ex){
            executor.shutdown();
        }
        executor.shutdown();
    }
}
