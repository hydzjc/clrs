package clrs;

/**
 * Created by huangyd on 2017/1/31.
 * 性质：
 * 1.每个结点或者是红色，或者是黑色。
 * 2.根结点都是黑色的。
 * 3.每个叶结点都是黑色的。
 * 4.如果一个结点是红色的，则它的两个子结点都是黑色的。
 * 5.对每个结点，从该结点到其叶子结点的简单路径上面，均包含相同数量的黑色结点。
 * 根据以上性质，RB-trees 的hight <= 2log n
 */
public class RedBlackTree {
    private TreeNode root = null;

    private enum RbColor {BLACK,RED}
    private static class TreeNode{
        TreeNode LeftChild;
        TreeNode RightChild;
        TreeNode Parent;
        RbColor color;//0：black;1:red
        int data;
        TreeNode(int data){
            this.LeftChild = null;
            this.RightChild = null;
            this.data  = data;
            this.Parent = null;
            this.color = RbColor.RED;
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
    private  void LeftRotate(TreeNode ptr){
        TreeNode y = ptr.RightChild;
        ptr.RightChild = y.LeftChild;

        if (ptr.Parent == null) {
            this.root = ptr.RightChild;
        }else if (ptr.Parent.LeftChild == ptr){
            y.Parent = ptr.Parent;
            ptr.Parent.LeftChild = y;
        }else if( ptr.Parent.RightChild == ptr){
            y.Parent = ptr.Parent;
            ptr.Parent.RightChild = y;
        }
        y.LeftChild = ptr;
        ptr.Parent = y;
    }
    private void RightRotate(TreeNode ptr){
        TreeNode y = ptr.LeftChild;
        ptr.LeftChild = y.RightChild;
        if(ptr.Parent == null){
            this.root = ptr.LeftChild;
        }else if (ptr.Parent.LeftChild == ptr){
            y.Parent = ptr.Parent;
            ptr.Parent.LeftChild = y;
        }else if( ptr.Parent.RightChild == ptr){
            y.Parent = ptr.Parent;
            ptr.Parent.RightChild = y;
        }
        y.RightChild = ptr;
        ptr.Parent = y;
    }
    //维持RB树的三种情况调整，其中维护过程只会破坏性质2或者性质4.
    //前提，x和x.parent都是红色的。
    //考虑x.parent是它父节点的左子树的情况（右子树的情况刚好对应）
    //case1：如果x的叔结点是红色的，则进行着色，X.parent和叔结点改成黑色，X.PP结点改成红色（保持总黑色高度不变）
    //case2：x的叔叔结点是黑色的，如果x.p是右孩子，则左旋。
    //case3：x的叔叔结点是黑色的，如果x.p是左孩子，则右旋。
    private void RbInsertFixup(TreeNode z){
        TreeNode y = null;
        while(z.Parent.color ==RbColor.RED){
            if(z.Parent ==z.Parent.Parent.LeftChild){
                y = z.Parent.Parent.RightChild;
                //case 1:
                if (y.color == RbColor.RED){
                    z.Parent.color = RbColor.BLACK;
                    y.color = RbColor.BLACK;
                    z.Parent.Parent.color = RbColor.RED;
                    z = z.Parent.Parent;
                }else {
                    //case 2:
                    if(z == z.Parent.RightChild){
                        z = z.Parent;
                        LeftRotate(z);
                    }
                    //case 3:
                    z.Parent.color = RbColor.BLACK;
                    z.Parent.Parent.color = RbColor.RED;
                    RightRotate(z.Parent.Parent);
                }
            }else{
                y = z.Parent.Parent.LeftChild;
                //case 1:
                if (y.color == RbColor.RED){
                    z.Parent.color = RbColor.BLACK;
                    y.color = RbColor.BLACK;
                    z.Parent.Parent.color = RbColor.RED;
                    z = z.Parent.Parent;
                }else {
                    //case 2:
                    if(z == z.Parent.LeftChild){
                        z = z.Parent;
                        RightRotate(z);
                    }
                    //case 3:
                    z.Parent.color = RbColor.BLACK;
                    z.Parent.Parent.color = RbColor.RED;
                    LeftRotate(z.Parent.Parent);
                }
            }
        }
    }

    public void Insert(int x){

    }
}
