package app;


public class Node{

    // 0 for black and 1 for red
    private int color;
    private String key;
    private Node l;
    private Node r;
    private Node p;
    private Boolean isNil;

    public Node(){

    }

    public Node(String key){

        this.setKey(key);
        this.clearIsNil();
        this.setColorToR();
        this.l = new Node();
        this.r = new Node();
        this.p = new Node();
        this.getLeft().setNil();
        this.getRight().setNil();
        this.getParent().setNil();

    }

    public void setColorToB(){

        this.color = 0;

    }

    public void setColorToR(){

        this.color = 1;

    }

    public void setColor(int color){

        if(color == 0){

            this.setColorToB();
            return;

        }

        this.setColorToR();

    }

    public int getColor(){

        return (this.color);

    }

    public void setKey(String key){

        this.key = key;
    
    }

    public String getKey(){

        return (this.key);

    }

    public void setLeft(Node node){

        this.l = node;

    }

    public Node getLeft(){

        return (this.l);

    }

    public void setRight(Node node){

        this.r = node;

    }

    public Node getRight(){

        return (this.r);

    }

    public void setParent(Node node){

        this.p = node;

    }

    public Node getParent(){

        return (this.p);

    }

    public void setNil(){

        this.isNil = true;
        this.setColorToB();

    }

    public void clearIsNil(){

        this.isNil = false;

    }

    public Boolean getIsNil(){

        return(this.isNil);
    
    }

}