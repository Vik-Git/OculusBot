package Bot.Server;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

/**
 * Created by Vik on 27/11/2016.
 */
public class RSPSLoader  {
    String jarLocation ="";

    public RSPSLoader(String location){
        this.jarLocation = location;
    }

    public Class loaddClass(String targetClass){
        URLClassLoader loader = new URLClassLoader(createURL());
        Class c = null;
        try {
            return loader.loadClass(targetClass);
        } catch (ClassNotFoundException e) {
            System.out.println("Class doesn't exsist");
        }
        return c;
    }

    private URL[] createURL()  {
        File jarFile = new File(jarLocation);
        URL[] jarURL = new URL[1];
        try {
           jarURL[0] = jarFile.toURI().toURL();
        } catch (MalformedURLException e) {
            System.out.println("No jar to be found");
        }
        return jarURL;
    }


}
