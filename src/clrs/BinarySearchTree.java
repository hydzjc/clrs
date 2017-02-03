package clrs;
/**
 * Created by huangyd on 2017/1/29.
 */

public class BinarySearchTree {
    private TreeNode root = null;
    private static class TreeNode{
        TreeNode LeftChild;
        TreeNode RightChild;
        TreeNode Parent;
        int data;
        TreeNode(int data){
            this.LeftChild = null;
            this.RightChild = null;
            this.data  = data;
            this.Parent = null;
        }
    }
    boolean IsEmpty(){
        if (root == null){
            return true;
        }
        return false;
    }
    //中序遍历
    public static void  InorderTreeWalk(TreeNode x){
        if (x == null)
            return;
        InorderTreeWalk(x.LeftChild);
        System.out.print(" "+x.data);
        InorderTreeWalk(x.RightChild);
    }
    //先序遍历
    public static void PreorderTreeWalk(TreeNode x){
        if (x == null)
            return;
        System.out.print(" "+x.data);
        PreorderTreeWalk(x.LeftChild);
        PreorderTreeWalk(x.RightChild);
    }
    //后续遍历
    public static void PostorderTreeWalk(TreeNode x){
        if (x == null)
            return;
        PostorderTreeWalk(x.LeftChild);
        PostorderTreeWalk(x.RightChild);
        System.out.print(" "+x.data);
    }
    public TreeNode TreeSearch(int x){
        TreeNode pNode = root;
        while(pNode != null && pNode.data != x)
        {
            if (pNode.data >x)
                pNode = pNode.LeftChild;
            else if (pNode.data<x)
                pNode = pNode.RightChild;
        }
        return pNode;
    }
    public TreeNode TreeMinmun(TreeNode ptr){
        //TreeNode ptr = root;
        while(ptr.LeftChild != null){
            ptr = ptr.LeftChild;
        }
        return ptr;
    }
    public TreeNode TreeMinmun(){
        TreeNode ptr = root;
        while(ptr.LeftChild != null){
            ptr = ptr.LeftChild;
        }
        return ptr;
    }
    public TreeNode TreeMaxmun(TreeNode ptr){
        //TreeNode ptr = root;
        while(ptr.RightChild != null){
            ptr = ptr.RightChild;
        }
        return  ptr;
    }
    public TreeNode TreeMaxmun(){
        TreeNode ptr = root;
        while(ptr.RightChild != null){
            ptr = ptr.RightChild;
        }
        return  ptr;
    }
    //后续
    public TreeNode TreeSuccessor(TreeNode ptr){
        //如果有右孩子，那么右孩子的最小值就是他的后续
        if (ptr.RightChild != null){
            return TreeMinmun(ptr.RightChild);
        }
        //如果没有右孩子，那么找到其子树对应的第一个不是右孩子的父节点。
        TreeNode  ptrParent = ptr.Parent;
        while(ptrParent != null && ptr == ptrParent.RightChild)
        {
            ptr = ptrParent;
            ptrParent = ptr.Parent;
        }
        return ptrParent;

    }
    //前驱
    public TreeNode TreePredecessor(TreeNode ptr){
        //TO_DO
        if(ptr.LeftChild!= null){
            return TreeMaxmun(ptr.LeftChild);
        }
        TreeNode pParent = ptr.Parent;
        while(pParent !=  null && pParent.LeftChild == ptr){
            ptr = pParent;
            pParent = ptr.Parent;
        }
        return pParent;
    }



    public  void  Insert(int x){
        TreeNode newNode = new TreeNode(x);
        TreeNode T_ptr = root;
        TreeNode pParent = null;
       // Node m  = null;
        if (root == null){
            root = newNode;
            return;
        }
        while( T_ptr != null){
            pParent = T_ptr;
            if (T_ptr.data < newNode.data)
            {
                T_ptr  = T_ptr.RightChild;
            }else if (T_ptr.data > newNode.data){
                T_ptr = T_ptr.LeftChild;
            }else{
                return ;//已经有相同元素，插入失败
            }
        }

        if(pParent.data > newNode.data){
            newNode.Parent = pParent;
            pParent.LeftChild = newNode;

        }else if(pParent.data < newNode.data){
            newNode.Parent = pParent;
            pParent.RightChild = newNode;
        }
        return ;
    }
    //用vtree 代替utree
    private void Transplant(TreeNode u,TreeNode v){
        if (u.Parent == null) {
            root = v;
        } else if (u == u.Parent.LeftChild) {
            u.Parent.LeftChild = v;
        }else{
            u.Parent.RightChild = v;
        }
        if (v != null){
            v.Parent = u.Parent;
        }
    }
    public void Delete (TreeNode DelNode){
        if (DelNode == null)
            return ;
        if (DelNode.LeftChild == null){
            Transplant(DelNode,DelNode.RightChild);
        }else if(DelNode.RightChild == null){
            Transplant(DelNode,DelNode.LeftChild);
        }else {
            //找后续，将后续翻转后接到左孩子
            TreeNode TmpTreeNode = TreeMinmun(DelNode.RightChild);
            if(TmpTreeNode.Parent != DelNode){
                Transplant(TmpTreeNode,TmpTreeNode.Parent);
                TmpTreeNode.RightChild = DelNode.RightChild;
                TmpTreeNode.RightChild.Parent = DelNode;
            }
            Transplant(DelNode, TmpTreeNode);
            TmpTreeNode.LeftChild = DelNode.LeftChild;
            TmpTreeNode.LeftChild.Parent = DelNode;
        }

    }
    public static void  main (String args[]){
        int[] A ={3,5,7,4,9,11,25,15,17,16};
        BinarySearchTree T = new BinarySearchTree();
        for(int i = 0;i<A.length;i++){
            T.Insert(A[i]);
        }
        InorderTreeWalk(T.root);



        //16没有孩子
        TreeNode x = T.TreeSearch(16);
        System.out.println("\nx.date = "+ x.data);
        T.Delete(x);
        InorderTreeWalk(T.root);

        //15只有右孩子
        x = T.TreeSearch(15);
        System.out.println("\nx.date = "+ x.data);
        T.Delete(x);
        InorderTreeWalk(T.root);

        //5有左右孩子
         x = T.TreeSearch(5);
        System.out.println("\nx.date = "+ x.data);
        T.Delete(x);
        InorderTreeWalk(T.root);
    }
}
