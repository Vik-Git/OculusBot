package Bot.ASM;

import Bot.ASM.Analysis.FieldAnalyzer;
import Bot.Accesors.RSImageProducer;
import jdk.internal.org.objectweb.asm.Type;
import jdk.internal.org.objectweb.asm.tree.*;

import java.util.ArrayList;

import static jdk.internal.org.objectweb.asm.Opcodes.*;


/**
 * Created by Vik on 11/12/2016.
 */
public class RSImageProducerTransform {
    public static ClassNode producerNode;
    public static ClassNode superNode;
    private FieldAnalyzer fa;
        public RSImageProducerTransform(ArrayList<ClassNode> classNodes) {
        String supername="";
        for(ClassNode cNode:classNodes) {
            if(cNode.superName.equals("N")){
               superNode = cNode;
                fa = new FieldAnalyzer(superNode.fields);
            }
            if(cNode.name.equals("g")){
                producerNode = cNode;
                addXYIMGFields();
                addXGetter();
                addYGetter();
                addRenderedImgGetter();
            }
            for (String s : cNode.interfaces) {
                if (s.equals("java/awt/image/ImageObserver")) {
                    producerNode = cNode;
                    supername = producerNode.superName;
                    addXYIMGFields();
                    addXGetter();
                    addYGetter();
                    addRenderedImgGetter();
                }
            }
        }
        fa =new FieldAnalyzer(producerNode.fields);
        if(!fa.containsDesc("Ljava/awt/Image;")) {
                for (ClassNode cNode : classNodes) {
                    if (cNode.name.equals(supername)) {
                        producerNode = cNode;
                        fa =new FieldAnalyzer(producerNode.fields);
                        break;
                    }
                }
        }
        implementAccessor();
        addImgGetter();
    }



    private void implementAccessor(){
        producerNode.interfaces.add(Type.getInternalName(RSImageProducer.class));
    }

    private void addImgGetter(){
        FieldNode targetFieldNode = fa.getSingleFieldNode("Ljava/awt/Image;");
        MethodNode mn = new MethodNode(ACC_PUBLIC,"getImage","()Ljava/awt/Image;",null,null);
        InsnList stack = mn.instructions;
        stack.add(new VarInsnNode(ALOAD,0));
        stack.add(new FieldInsnNode(GETFIELD,producerNode.name,targetFieldNode.name,targetFieldNode.desc));
        stack.add(new InsnNode(ARETURN));
        producerNode.methods.add(mn);
    }
    private void addXYIMGFields(){
        FieldNode X = new FieldNode(ACC_PUBLIC,"ImageX","I",null,null);
        FieldNode Y = new FieldNode(ACC_PUBLIC,"ImageY","I",null,null);
        FieldNode IMG = new FieldNode(ACC_PUBLIC,"renderedImage","Ljava/awt/Image;",null,null);
        FieldNode bufferedIMG = new FieldNode(ACC_PUBLIC,"bufferedImage","Ljava/awt/image/BufferedImage;",null,null);
        producerNode.fields.add(X);
        producerNode.fields.add(Y);
        producerNode.fields.add(IMG);
        producerNode.fields.add(bufferedIMG);
        for (MethodNode m: producerNode.methods) {
            System.out.println(m.name);

            if(m.name.contains("init")){
                System.out.println("constructor found");
                InsnList l = new InsnList();
                l.add(new VarInsnNode(ALOAD,0));
                l.add(new TypeInsnNode(NEW,"java/awt/image/BufferedImage"));
                l.add(new InsnNode(DUP));
                l.add(new IntInsnNode(SIPUSH,765));
                l.add(new IntInsnNode(SIPUSH,503));
                l.add(new InsnNode(ICONST_2));
                l.add(new MethodInsnNode(INVOKESPECIAL,"java/awt/image/BufferedImage","<init>","(III)V",false));
                l.add(new FieldInsnNode(PUTFIELD, producerNode.name, "bufferedImage", "Ljava/awt/image/BufferedImage;"));
                m.instructions.insert(l);
            }

            if(m.desc.contains("Graphics")){
                m.instructions.remove(m.instructions.getLast());
                m.instructions.add(new VarInsnNode(ALOAD,0));
                m.instructions.add(new VarInsnNode(ILOAD,1));
                m.instructions.add(new FieldInsnNode(PUTFIELD,producerNode.name,X.name,X.desc));
                m.instructions.add(new VarInsnNode(ALOAD,0));
                m.instructions.add(new VarInsnNode(ILOAD,3));
                m.instructions.add(new FieldInsnNode(PUTFIELD,producerNode.name,Y.name,Y.desc));

                m.instructions.add(new VarInsnNode(ALOAD,0));
                m.instructions.add(new  FieldInsnNode(GETFIELD,producerNode.name,bufferedIMG.name,bufferedIMG.desc));
                m.instructions.add(new MethodInsnNode(INVOKEVIRTUAL, "java/awt/image/BufferedImage", "getGraphics", "()Ljava/awt/Graphics;", false));
                m.instructions.add(new VarInsnNode(ALOAD,0));
                m.instructions.add(new  FieldInsnNode(GETFIELD,producerNode.name,"a","Ljava/awt/Image;"));
                m.instructions.add(new VarInsnNode(ILOAD,3));
                m.instructions.add(new VarInsnNode(ILOAD,1));
                m.instructions.add(new VarInsnNode(ALOAD,0));
                m.instructions.add(new FieldInsnNode(GETFIELD,producerNode.name,"e","Ljava/awt/Component;"));
                m.instructions.add(new MethodInsnNode(INVOKEVIRTUAL,"java/awt/Graphics","drawImage","(Ljava/awt/Image;IILjava/awt/image/ImageObserver;)Z", false));
                m.instructions.add(new InsnNode(POP));
                m.instructions.add(new InsnNode(RETURN));

            }
           /* if(m.desc.contains("Component")){
                InsnList l = new InsnList();
                l.add(new VarInsnNode(ALOAD,0));
                l.add(new VarInsnNode(ALOAD,0));
                l.add(new FieldInsnNode(GETFIELD,producerNode.name,"a","Ljava/awt/Image;"));
                l.add(new FieldInsnNode(PUTFIELD,producerNode.name,IMG.name,IMG.desc));
                m.instructions.insertBefore(new InsnNode(RETURN),l);
            }*/

        }
    }

    private void addRenderedImgGetter() {
        MethodNode mn = new MethodNode(ACC_PUBLIC,"getRenderedImage","()Ljava/awt/image/BufferedImage;",null,null);
        InsnList stack = mn.instructions;
        stack.add(new VarInsnNode(ALOAD,0));
        stack.add(new FieldInsnNode(GETFIELD,producerNode.name,"bufferedImage","Ljava/awt/image/BufferedImage;"));
        stack.add(new InsnNode(ARETURN));
        producerNode.methods.add(mn);
    }

    private void addXGetter(){
        MethodNode mn = new MethodNode(ACC_PUBLIC,"getX","()I",null,null);
        InsnList stack = mn.instructions;
        stack.add(new VarInsnNode(ALOAD,0));
        stack.add(new FieldInsnNode(GETFIELD,producerNode.name,"ImageX","I"));
        stack.add(new InsnNode(IRETURN));
        producerNode.methods.add(mn);
    }
    private void addYGetter(){
        MethodNode mn = new MethodNode(ACC_PUBLIC,"getY","()I",null,null);
        InsnList stack = mn.instructions;
        stack.add(new VarInsnNode(ALOAD,0));
        stack.add(new FieldInsnNode(GETFIELD,producerNode.name,"ImageY","I"));
        stack.add(new InsnNode(IRETURN));
        producerNode.methods.add(mn);
    }



}
