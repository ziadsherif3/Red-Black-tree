package app;

public class App {
    public static void main(String[] args) throws Exception {

        Tree t = new Tree();
        t.insert(new Node(5));
        t.insert(new Node(7));
        t.insert(new Node(6));
        t.insert(new Node(4));
        System.out.println(t.getRoot().getKey() + " " + t.getRoot().getColor());
        System.out.println(t.getRoot().getLeft().getKey() + " " + t.getRoot().getLeft().getColor());
        System.out.println(t.getRoot().getRight().getKey() + " " + t.getRoot().getRight().getColor());
        System.out.println(t.getRoot().getLeft().getLeft().getKey() + " " + t.getRoot().getLeft().getLeft().getColor());
        System.out.println(t.getTreeHeight(t.getRoot()));
        t.rbDelete(t.search(6));
        System.out.println(t.getRoot().getKey() + " " + t.getRoot().getColor());
        System.out.println(t.getRoot().getLeft().getKey() + " " + t.getRoot().getLeft().getColor());
        System.out.println(t.getRoot().getRight().getKey() + " " + t.getRoot().getRight().getColor());
        System.out.println(t.getTreeHeight(t.getRoot()));


    }
}