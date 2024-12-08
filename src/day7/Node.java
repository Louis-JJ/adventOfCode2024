package src.day7;

public class Node {
    public long value;
    public Node right;
    public Node center;
    public Node left;

    public Node(long value){
        this.value = value;
    }

    public Node addAddition(long value){
        this.left = new Node(this.value+value);
        return this.left;
    }

    public Node addMultiplication(long value){
        this.right = new Node(this.value*value);
        return this.right;
    }

    public Node addConcatenation(long value){
        this.center = new Node(Long.parseLong(String.join("", String.valueOf(this.value), String.valueOf(value))));
        return this.center;
    }
}
