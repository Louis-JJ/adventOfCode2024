package src.day7;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Enigme {
    long result;
    long[] operandes;

    public Enigme(String inputLine){
        String[] splittedInput = inputLine.split(": ");
        this.result = Long.parseLong(splittedInput[0]);
        String[] ops = splittedInput[1].split(" ");
        this.operandes = new long[ops.length];
        for(int i = 0; i < ops.length; i++){
            this.operandes[i] = Integer.parseInt(ops[i]);
        }
    }

    public long isValid(){
        Node root = null;
        List<Node> leafNodes = new ArrayList<>();
        for(long operande : operandes){
            if(null == root){
                root = new Node(operandes[0]);
                leafNodes.add(root);
            } else {
                leafNodes = leafNodes.stream().flatMap(leaf -> Stream.of(leaf.addAddition(operande), leaf.addMultiplication(operande), leaf.addConcatenation(operande))).collect(Collectors.toList());
            }
        }
        return leafNodes.stream().anyMatch(node -> node.value == result) ? result : 0;
    }
}
