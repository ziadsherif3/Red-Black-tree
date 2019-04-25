package app;


public class Tree{

    private Node root;

    public Tree(){

        this.root = new Node();
        this.root.setNil();

    }

    private void leftRotate(Node x) {
        

        Node y = x.getRight();
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
        
    }

    private void rightRotate(Node y) {
        
        

        Node x = y.getLeft();
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
                        rightRotate(z);


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

    public int getTreeHeight(Node node){

        //  In order not to initialize 3 variables in each recursion
        int leftHeight = 0;
        
        int rightHeight = 0;
        
        int max = 0;
        
        return (getHeight(node, leftHeight, rightHeight, max));
    }

    public int getHeight(Node node, int leftHeight, int rightHeight, int max){

        // number of nodes from root to nil // nill not counted
        if(node.getIsNil()){

            return 0; // to count edges returns -1

        }

        leftHeight = getHeight(node.getLeft(), leftHeight, rightHeight, max);
        rightHeight = getHeight(node.getRight(), leftHeight, rightHeight, max);
        max = leftHeight > rightHeight ? leftHeight : rightHeight;

        return (1 + max);

    }

    public Node search(int key){

        Node c = this.getRoot();

        while(!c.getIsNil()){

            if(key > c.getKey()){

                c = c.getRight();

            }else if(key < c.getKey()){

                c = c.getLeft();

            }else{

                break;

            }

        }

        if(c.getIsNil()){

            System.out.println( key + " Key is not found");
            return (c);
        
        }

        System.out.println(c.getKey() + " Key is found");
        return (c);

    }

    public void rbTransplant(Node u, Node v){

        if(u.getParent().getIsNil()){

            this.setRoot(v);

        }else if(u.getParent().getLeft().equals(u)){

            u.getParent().setLeft(v);

        }else{

            u.getParent().setRight(v);

        }

        v.setParent(u.getParent());

    }

    public Node treeMinimum(Node z){

        while(!z.getLeft().getIsNil()){
            
            z = z.getLeft();

        }
        
        return (z);

    }

    public void rbDelete(Node z){

        if(z.getIsNil()){

            return;

        }
        
        Node y = z;
        Node x = new Node();
        int yOriginalColor = y.getColor();

        if(z.getLeft().getIsNil()){

            x = z.getRight();
            rbTransplant(z, z.getRight());

        }else if(z.getRight().getIsNil()){

            x = z.getLeft();
            rbTransplant(z, z.getLeft());

        }else{

            y = treeMinimum(z.getRight());
            yOriginalColor = y.getColor();
            x = y.getRight();

            if(y.getParent().equals(z)){

                x.setParent(y);

            }else{

                rbTransplant(y, y.getRight());
                y.setRight(z.getRight());
                y.getRight().setParent(y);

            }

            rbTransplant(z, y);
            y.setLeft(z.getLeft());
            y.getLeft().setParent(y);
            y.setColor(z.getColor());

        }

        if(yOriginalColor == 0){

            rbDeleteFix(x);

        }

        System.out.println("Key deleted");

    }

    public void rbDeleteFix(Node x){

        Node w = new Node();

        while(!this.getRoot().equals(x) && x.getColor() == 0){

            if(x.getParent().getLeft().equals(x)){

                w = x.getParent().getRight();
                // Case 1
                if(w.getColor() == 1){

                    w.setColorToB();
                    x.getParent().setColorToR();
                    leftRotate(x.getParent());
                    w = x.getParent().getRight();

                }
                //Case 2
                if(w.getLeft().getColor() == 0 && w.getRight().getColor() == 0){

                    w.setColorToR();
                    x = x.getParent();
                
                }else if(w.getRight().getColor() == 0){
                    //Case 3
                    w.getLeft().setColorToB();
                    w.setColorToR();
                    rightRotate(w);
                    w = x.getParent().getRight();


                }else{
                    //Case 4
                    w.setColor(x.getParent().getColor());
                    x.getParent().setColorToB();
                    w.getRight().setColorToB();
                    leftRotate(x.getParent());
                    x = this.getRoot();

                }

            }else{

                    w = x.getParent().getLeft();
                // Case 1
                if(w.getColor() == 1){

                    w.setColorToB();
                    x.getParent().setColorToR();
                    rightRotate(x.getParent());
                    w = x.getParent().getLeft();

                }
                //Case 2
                if(w.getRight().getColor() == 0 && w.getLeft().getColor() == 0){

                    w.setColorToR();
                    x = x.getParent();
                
                }else if(w.getLeft().getColor() == 0){
                    //Case 3
                    w.getRight().setColorToB();
                    w.setColorToR();
                    leftRotate(w);
                    w = x.getParent().getLeft();


                }else{
                    //Case 4
                    w.setColor(x.getParent().getColor());
                    x.getParent().setColorToB();
                    w.getLeft().setColorToB();
                    rightRotate(x.getParent());
                    x = this.getRoot();

                }

            }

        }

        x.setColorToB();

    }

    public void setRoot(Node root){

        this.root = root;

    }

    public Node getRoot(){

        return (this.root);

    }

}