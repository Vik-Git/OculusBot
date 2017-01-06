package Bot.ASM;

import Bot.ASM.Analysis.FieldAnalyzer;
import Bot.Accesors.Client;
import Bot.Accesors.RSImageProducer;
import jdk.internal.org.objectweb.asm.Type;
import jdk.internal.org.objectweb.asm.tree.*;

import java.util.ArrayList;

import static jdk.internal.org.objectweb.asm.Opcodes.*;

/**
 * Created by Vik on 11/12/2016.
 */
public class ClientTransform {
    private ClassNode clientNode;
    public ClientTransform(ArrayList<ClassNode> classNodes) {
        for(ClassNode cNode:classNodes){
            if(cNode.name.equals("client")){
               clientNode = cNode;
            }
        }
        implementAccessor();
        addRSImageGetter();
    }


    private void implementAccessor(){
        clientNode.interfaces.add(Type.getInternalName(Client.class));
    }

    private  void addRSImageGetter(){
        FieldAnalyzer fa = new FieldAnalyzer(clientNode.fields);
        if(fa.containsDesc(RSImageProducerTransform.producerNode.name)){
            int index = 0;
            for (FieldNode f: fa.getAllFieldNode("L"+RSImageProducerTransform.producerNode.name+";")) {
                MethodNode mn = new MethodNode(ACC_PUBLIC,"getProducer"+index,"()L"+Type.getInternalName(RSImageProducer.class)+";",null,null);
                InsnList stack = mn.instructions;
                stack.add(new VarInsnNode(ALOAD,0));
                stack.add(new FieldInsnNode(GETFIELD,clientNode.name,f.name,f.desc));
                stack.add(new InsnNode(ARETURN));
                clientNode.methods.add(mn);
                index++;
                System.out.println(f.name + "   "+index);
            }
        }
    }


}
