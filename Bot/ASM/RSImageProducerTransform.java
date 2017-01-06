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
    private FieldAnalyzer fa;
        public RSImageProducerTransform(ArrayList<ClassNode> classNodes) {
        String supername="";
        for(ClassNode cNode:classNodes) {
            if(cNode.name.equals("g")){
                producerNode = cNode;
                addXYFields();
                addXGetter();
                addYGetter();
            }
            for (String s : cNode.interfaces) {
                if (s.equals("java/awt/image/ImageObserver")) {
                    producerNode = cNode;
                    supername = producerNode.superName;
                    addXYFields();
                    addXGetter();
                    addYGetter();
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
    private void addXYFields(){
        FieldNode X = new FieldNode(ACC_PUBLIC,"ImageX","I",null,null);
        FieldNode Y = new FieldNode(ACC_PUBLIC,"ImageY","I",null,null);
        producerNode.fields.add(X);
        producerNode.fields.add(Y);
        for (MethodNode m: producerNode.methods) {
            System.out.println(m.name);
            if(m.desc.contains("Graphics")){
                m.instructions.remove(m.instructions.getLast());
                m.instructions.add(new VarInsnNode(ALOAD,0));
                m.instructions.add(new VarInsnNode(ILOAD,1));
                m.instructions.add(new FieldInsnNode(PUTFIELD,producerNode.name,X.name,X.desc));
                m.instructions.add(new VarInsnNode(ALOAD,0));
                m.instructions.add(new VarInsnNode(ILOAD,3));
                m.instructions.add(new FieldInsnNode(PUTFIELD,producerNode.name,Y.name,Y.desc));
                m.instructions.add(new InsnNode(RETURN));
            }
        }
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
