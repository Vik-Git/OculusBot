package Bot.ASM.Analysis;

import jdk.internal.org.objectweb.asm.tree.FieldNode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vik on 20/12/2016.
 */
public class FieldAnalyzer {

    private List<FieldNode> fNodes;

    public FieldAnalyzer(List<FieldNode> fNodes){
        this.fNodes =fNodes;
    }

    public boolean containsDesc(String desc){
        for (FieldNode f: fNodes) {
            if(f.desc.equals("L"+desc+";")){
                return true;
            }
        }
        return false;
    }

    public FieldNode getSingleFieldNode(String desc){
        for (FieldNode f: fNodes) {
            if(f.desc.equals(desc)){
                return f;
            }
            System.out.println(f.desc);
        }
        return null;
    }

    public ArrayList<FieldNode> getAllFieldNode(String desc){
        ArrayList<FieldNode>  targetnodes = new ArrayList<>();
        for (FieldNode f: fNodes) {
            if(f.desc.equals(desc)){
                targetnodes.add(f);
            }
        }
        return targetnodes;
    }






}
