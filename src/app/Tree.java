package app;


public class Tree{

    private int height;
    private Node root;
    private int bHeight;

    public Tree(){

        this.height = 0;
        this.root = new Node();
        this.root.setNil();

    }

    private void leftRotate(Node x) {
        
        try{

            Node y = (Node)x.getRight().clone();
            x.setRight(y.getLeft());

            if(!y.getLeft().getIsNil()){

                y.getLeft().setParent(x);

            }

            y.setParent(x.getParent());

            if(x.getParent().getIsNil()){

                this.setRoot(y);

            }else if(x.getParent().getLeft().equals(x)){

                x.getParent().setLeft(y);

            }else{

                x.getParent().setRight(y);

            }

            y.setLeft(x);
            x.setParent(y);

        }catch(CloneNotSupportedException c){}
        
    }

    private void rightRotate(Node y) {
        
        try{

            Node x = (Node)y.getLeft().clone();
            y.setLeft(x.getRight());

            if(!x.getRight().getIsNil()){

                x.getRight().setParent(y);

            }

            x.setParent(y.getParent());

            if(y.getParent().getIsNil()){

                this.setRoot(x);

            }else if(y.getParent().getRight().equals(y)){

                y.getParent().setRight(x);

            }else{

                y.getParent().setLeft(x);

            }

            x.setRight(y);
            y.setParent(x);

        }catch(CloneNotSupportedException c){}

    }

    public void insert(Node z){

        if(this.getRoot().getIsNil()){

            this.setRoot(z);
        
        }else{

            Node c = this.getRoot();

            while(!c.getIsNil()){

                if(z.getKey() == c.getKey()){

                    System.out.println("Key already exists");
                    return;

                }else if(z.getKey() < c.getKey()){

                    if(c.getLeft().getIsNil()){

                        c.setLeft(z);
                        z.setParent(c);
                        break;

                    }else{

                        c = c.getLeft();

                    }

                }else{

                    if(c.getRight().getIsNil()){

                        c.setRight(z);
                        z.setParent(c);
                        break;

                    }else{

                        c = c.getRight();

                    }

                }

            }

        }

        
        insertFix(z);

    }

    public void insertFix(Node z){

        if(this.getRoot().equals(z)){
            
            z.setColorToB();
            return;

        }
        
        if(z.getParent().getColor() == 0){

            return;

        }else{

            Node y = new Node();

            while(z.getParent().getColor() == 1){

                if(z.getParent().equals(z.getParent().getParent().getLeft())){

                    y = z.getParent().getParent().getRight();
                    
                    if(y.getColor() == 1){

                        z.getParent().setColorToB();
                        y.setColorToB();
                        z.getParent().getParent().setColorToR();
                        z = z.getParent().getParent();

                    }else if(z.getParent().getRight().equals(z)){

                        z = z.getParent();
                        this.leftRotate(z);


                    }else{

                        z.getParent().setColorToB();
                        z.getParent().getParent().setColorToR();
                        rightRotate(z.getParent().getParent());

                    }

                }else{

                    y = z.getParent().getParent().getLeft();
                    
                    if(y.getColor() == 1){

                        z.getParent().setColorToB();
                        y.setColorToB();
                        z.getParent().getParent().setColorToR();
                        z = z.getParent().getParent();

                    }else if(z.getParent().getLeft().equals(z)){

                        z = z.getParent();
                        this.rightRotate(z);


                    }else{

                        z.getParent().setColorToB();
                        z.getParent().getParent().setColorToR();
                        leftRotate(z.getParent().getParent());

                    }

                }

            }

            this.getRoot().setColorToB();

        }

    }

    

    public void setRoot(Node root){

        this.root = root;

    }

    public Node getRoot(){

        return (this.root);

    }

    public int getHeight(){

        return (this.height);

    }

    public int getBHeight(){

        return (this.bHeight);

    }

}