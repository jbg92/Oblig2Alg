/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algoblig2;

/**
 *
 * @author John-Berge
 */
public class AVLTree<E extends Comparable<E>> extends BST<E> {
    public AVLTree(){
    }
    public AVLTree(E[] objects){
        super(objects);
    }
    @Override
    protected AVLTreeNode<E> createNewNode(E e){
        return new AVLTreeNode<E>(e);
    }
    @Override
    public boolean insert(E e){
        boolean successful = super.insert(e);
        if(!successful){
            return false;
        }
        else{
            balancePath(e);
        }
        return true;
    }
    public void updateHeight(AVLTreeNode<E> node){
        if(node.left==null&&node.right==null){
            node.height=0;
            node.weight=1;
        }
        else if(node.left==null){
            node.height=1+((AVLTreeNode<E>)(node.right)).height;
            node.weight=1+((AVLTreeNode<E>)(node.right)).weight;
        }
        else if(node.right==null){
            node.height=1+((AVLTreeNode<E>)(node.left)).height;
            node.weight=1+((AVLTreeNode<E>)(node.left)).weight;
        }
        else{
            node.height=1+Math.max(((AVLTreeNode<E>)(node.right)).height, ((AVLTreeNode<E>)(node.left)).height);
            node.weight=1+((AVLTreeNode<E>)(node.left)).weight+((AVLTreeNode<E>)(node.right)).weight;
        }
    }
    
    
    public void balancePath(E e){
        java.util.ArrayList<TreeNode<E>> path=path(e);
        for(int i=path.size()-1;i>=0;i--){
            AVLTreeNode<E> A=(AVLTreeNode<E>)(path.get(i));
            updateHeight(A);
            AVLTreeNode<E> parentOfA=(A==root)?null:(AVLTreeNode<E>)(path.get(i-1));
            
            switch (balanceFactor(A)){
                case -2:
                    if(balanceFactor((AVLTreeNode<E>)A.left)<=0){
                        balanceLL(A,parentOfA);
                    }
                    else{
                        balanceLR(A,parentOfA);
                    }
                break;
                case +2:
                        if(balanceFactor((AVLTreeNode<E>)A.right)>=0){
                        balanceRR(A,parentOfA);
                    }
                    else{
                        balanceRL(A,parentOfA);
                    }
                break;
            }
        }
    }
    
    private int balanceFactor(AVLTreeNode<E> node){
        if(node.right==null){
            return -node.height;
        }
        else if(node.left==null){
            return node.height;
        }
        else{
            return ((AVLTreeNode<E>)node.right).height-((AVLTreeNode<E>)node.left).height;
        }
    }
    
    private void balanceLL(TreeNode<E> A, TreeNode<E> parentOfA){
        TreeNode<E> B=A.left;
        if(A==root){
            root=B;
        }
        else{
            if(parentOfA.left==A){
                parentOfA.left=B;
            }
            else{
                parentOfA.right=B;
            }
        }
        A.left=B.right;
        B.right=A;
        updateHeight((AVLTreeNode<E>)A);
        updateHeight((AVLTreeNode<E>)B);
    }
    private void balanceLR(TreeNode<E> A, TreeNode<E> parentOfA){
        TreeNode<E> B=A.left;
        TreeNode<E> C=B.right;
        
        if (A==root) {
            root=C;
        }
        else{
            if (parentOfA.left==A) {
                parentOfA=C;
            } 
            else {
                parentOfA.right=C;
            }
        }
        A.left=C.right;
        B.right=C.left;
        C.left=B;
        C.right=A;
        
        updateHeight((AVLTreeNode<E>)A);
        updateHeight((AVLTreeNode<E>)B);
        updateHeight((AVLTreeNode<E>)C);
    }
    private void balanceRR(TreeNode<E> A, TreeNode<E> parentOfA){
        TreeNode<E> B=A.right;
        if(A==root){
            root=B;
        }
        else{
            if(parentOfA.left==A){
                parentOfA.left=B;
            }
            else{
                parentOfA.right=B;
            }
        }
        A.right=B.left;
        B.left=A;
        updateHeight((AVLTreeNode<E>)A);
        updateHeight((AVLTreeNode<E>)B);
    }
    private void balanceRL(TreeNode<E> A, TreeNode<E> parentOfA){
        TreeNode<E> B=A.right;
        TreeNode<E> C=B.left;
        
        if (A==root) {
            root=C;
        }
        else{
            if (parentOfA.left==A) {
                parentOfA=C;
            } 
            else {
                parentOfA.right=C;
            }
        }
        A.right=C.left;
        B.left=C.right;
        C.left=B;
        C.right=A;
        
        updateHeight((AVLTreeNode<E>)A);
        updateHeight((AVLTreeNode<E>)B);
        updateHeight((AVLTreeNode<E>)C);
    }
    
    protected static class AVLTreeNode<E> extends BST.TreeNode<E>{
        protected int height=0;
        protected int weight=0;
        
        public AVLTreeNode(E e){
            super(e);
        }
    }
}
