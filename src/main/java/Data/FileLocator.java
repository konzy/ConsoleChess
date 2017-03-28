package Data;

/**
 * Created by Ryan on 3/21/2017.
 */
public class FileLocator {
    public String baseFileLocation;
    public FileLocator(){
        baseFileLocation = this.getClass().getProtectionDomain().getCodeSource().getLocation().toString().substring(6);
        baseFileLocation = baseFileLocation.substring(0, baseFileLocation.length() - 14);
    }

    @Override
    public String toString() {
        return baseFileLocation;
    }
}
