package Bot.Script;

import Bot.Misc.Config;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

/**
 * Created by Vik on 6/01/2017.
 */
public class ScriptLoader {

    public Class loaddClass(String scriptName){
        URLClassLoader loader = new URLClassLoader(createURL());
        Class c = null;
        System.out.println(scriptName);
        try {
            return loader.loadClass(scriptName.replace(".class",""));
        } catch (ClassNotFoundException e) {
            System.out.println(scriptName);
            System.out.println("Class doesn't exsist");
        }
        return c;
    }

    private URL[] createURL()  {
        File classFile = new File(Config.selectedScriptPath);
        URL[] jarURL = new URL[1];
        try {
            jarURL[0] = classFile.toURI().toURL();
        } catch (MalformedURLException e) {
            System.out.println("No jar to be found");
        }
        return jarURL;
    }
}
