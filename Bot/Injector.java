package Bot;

import Bot.ASM.ClientTransform;
import Bot.ASM.RSImageProducerTransform;
import jdk.internal.org.objectweb.asm.ClassReader;
import jdk.internal.org.objectweb.asm.ClassWriter;
import jdk.internal.org.objectweb.asm.tree.ClassNode;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.jar.JarOutputStream;

/**
 * Created by Vik on 3/12/2016.
 */
public class Injector {
    private String jarLocation;
    private String injJarLocation;
    private ArrayList<ClassNode> classNodes =  new ArrayList<ClassNode>();


    public Injector(String jarLocation){
        this.jarLocation = jarLocation;
        this.injJarLocation = jarLocation.replace(".jar","_inj.jar");
    }


    public void loadClasses() throws IOException {
        System.out.println(jarLocation);
        JarFile jarFile = new JarFile(jarLocation);
        Enumeration e = jarFile.entries();

        URL[] urls = { new URL("jar:file:" + jarLocation+"!/") };

        while (e.hasMoreElements()) {
            JarEntry je = (JarEntry) e.nextElement();
            if(je.isDirectory() || !je.getName().endsWith(".class")){
                continue;
            }
            ClassReader cr =new ClassReader(jarFile.getInputStream(je));
            ClassNode node = new ClassNode();
            cr.accept(node,0);
            classNodes.add(node);
            System.out.println("Loaded: "+node.name);
        }
    }

    public void modifyClasses() throws ClassNotFoundException {
        new RSImageProducerTransform(classNodes);
        new ClientTransform(classNodes);
    }


    public void dumpClasses() throws IOException {
        JarOutputStream out = new JarOutputStream(new FileOutputStream(new File(injJarLocation)));
        for (ClassNode n:classNodes) {
            ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_MAXS);
            n.accept(cw);
            out.putNextEntry(new JarEntry(n.name + ".class"));
            out.write(cw.toByteArray());
            //System.out.println("Dumped: "+n.name);
        }
        out.flush();
        out.close();
    }
}
