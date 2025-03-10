// DO NOT SUBMIT THIS FILE TO GRADESCOPE

/**
 * Generic class implementing a Binary Node of a Binary Search Tree (BST)
 * 
 * Note: DO NOT MAKE ANY CHANGES TO THIS CLASS. Your submission will use the UNMODIFIED file on Gradescope.
 *
 * @param <T> type of the data carried by this binary node
 */
public class BSTNode<T extends Comparable<T>> {

  /**
   * Data stored in this node
   */
  private T data;
  /**
   * Reference to the left child
   */
  private BSTNode<T> left;
  /**
   * Reference to the right child
   */
  private BSTNode<T> right;

  /**
   * Creates a BSTNode with a given data value
   * 
   * @param data data carried by this binary node
   */
  public BSTNode(T data) {
    this.data = data;
  }

  /**
   * Creates a BSTNode with given data value, a reference to a left child (left subtree) and a
   * reference to a right child (right sub-tree)
   * 
   * @param data  element held by this binary node
   * @param left  reference to the left child
   * @param right reference to the right child
   */
  public BSTNode(T data, BSTNode<T> left, BSTNode<T> right) {
    this.data = data;
    this.left = left;
    this.right = right;
  }

  /**
   * Getter of left child
   * 
   * @return the left
   */
  public BSTNode<T> getLeft() {
    return left;
  }

  /**
   * Setter for left child
   * 
   * @param left the left to set
   */
  public void setLeft(BSTNode<T> left) {
    this.left = left;
  }

  /**
   * Getter of the right child
   * 
   * @return the right child of this binary node
   */
  public BSTNode<T> getRight() {
    return right;
  }

  /**
   * Setter for the right child
   * 
   * @param right the right to set
   */
  public void setRight(BSTNode<T> right) {
    this.right = right;
  }

  /**
   * Getter of data field
   * 
   * @return the data held by this node
   */
  public T getData() {
    return data;
  }

  @Override
  /**
   * Returns true if the given object is a BSTNode which is recursively equal to this one. That is,
   * the data stored in the node is identical to the data stored in this node, and the left and
   * right node pointers are both recursively equal to this one.
   * 
   * @param o Object to compare with this BSTNode
   */
  public boolean equals(Object o) {
    if (o instanceof BSTNode<?> bstNode) {
      return data.equals(bstNode.data)
          && (left == bstNode.left || (left != null && left.equals(bstNode.left)))
          && (right == bstNode.right || (right != null && right.equals(bstNode.right)));
    } else {
      return false;
    }
  }
}
