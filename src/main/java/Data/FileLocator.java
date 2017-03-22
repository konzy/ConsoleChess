package Data;

/**
 * Created by Ryan on 3/21/2017.
 */
public class FileLocator {
    public String baseFileLocation;
    public FileLocator(){
        baseFileLocation = this.getClass().getProtectionDomain().getCodeSource().getLocation().toString();
        baseFileLocation = baseFileLocation.substring(6);
    }

}
