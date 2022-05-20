package com.ajsmdllz.fitomatic;

import com.ajsmdllz.fitomatic.Posts.Post;
import java.util.ArrayList;

/**
 * AVLPosts Stores Queried Posts from the Database in Tree form for Optimised Insertion and Selection
 */
public class AVLPosts {
    public Post post;
    public AVLPosts leftNode;
    public AVLPosts rightNode;


    public AVLPosts (Post p, AVLPosts ln, AVLPosts rn) {
        this.post = p;
        this.leftNode = ln;
        this.rightNode = rn;
    }

    public AVLPosts() {
        this.post = null;
    }

    /**
     * Insert Post in Accordance to AVL Tree Rules, the "Order" is determined by the number of likes
     * @param p Post to be Inserted
     * @return Tree with inserted Post
     */
    public AVLPosts insert(Post p) {
        // Base cases, insertion at leaves
        if (p == null) {
            return this;
        }

        if (this.post == null) {
            this.post = p;
            return this;
        }

        // Insert into right subtree
        if (p.likes > this.post.likes) {
            AVLPosts rightins;
            if (this.rightNode == null) {
                this.rightNode = new AVLPosts();
            }
            rightins = new AVLPosts(this.post, this.leftNode, this.rightNode.insert(p));
            // Checking if right rotation is needed
            if (rightins.getBalanceFactor() > 1) {
                // Checking if double rotation is needed
                if ((rightins.leftNode != null) && (rightins.leftNode.getBalanceFactor() < 0)) {
                    rightins.leftNode = rightins.leftNode.leftRotate();
                }
                rightins = rightins.rightRotate();
                return rightins;
            }
            // Checking if left rotation is needed
            if (rightins.getBalanceFactor() < -1) {
                // Checking if double rotation is needed
                if (!(rightins.rightNode == null) && (rightins.rightNode.getBalanceFactor() > 0)) {
                    rightins.rightNode = rightins.rightNode.rightRotate();
                }
                rightins = rightins.leftRotate();
                return rightins;
            }
            return rightins;
        } else { // Insert into left-subtree
            if (this.leftNode == null) {
                this.leftNode = new AVLPosts();
            }
            AVLPosts leftins = new AVLPosts(this.post, this.leftNode.insert(p), this.rightNode);
            // Checking if left rotation is needed
            if (leftins.getBalanceFactor() > 1) {
                // Checking if double rotation is needed
                if (!(leftins.leftNode == null) && (leftins.leftNode.getBalanceFactor() < 0)) {
                    leftins.leftNode = leftins.leftNode.leftRotate();
                }
                leftins = leftins.rightRotate();
                return leftins;
            }
            // Checking if right rotation is needed
            if (leftins.getBalanceFactor() < -1) {
                // Checking if double rotation is needed
                if (!(leftins.rightNode == null) && (leftins.rightNode.getBalanceFactor() > 0)) {
                    leftins.rightNode = leftins.rightNode.rightRotate();
                }
                leftins = leftins.leftRotate();
                return leftins;
            }
            return leftins;
        }
    }

    /**
     * Right Rotates the Tree around the current node
     * @return AVL Tree having been rotated
     */
    public AVLPosts rightRotate() {
        if (this.leftNode == null || this.leftNode.post == null) {
            return this;
        }
        // Assigning new nodes
        AVLPosts newParent = this.leftNode;
        AVLPosts newLeftOfCurrent = newParent.rightNode;
        AVLPosts curr = this;
        // Setting relevant children of new nodes
        curr.leftNode = newLeftOfCurrent;
        newParent.rightNode = curr;

        return newParent;
    }

    /**
     * Left Rotates the Tree around the current node
     * @return AVL Tree having been rotated
     */
    public AVLPosts leftRotate() {
        if (this.rightNode == null || this.rightNode.post == null){
            return this;
        }
        // Assigning new nodes
        AVLPosts newParent = this.rightNode;
        AVLPosts newRightOfCurrent = newParent.leftNode;
        AVLPosts curr = this;
        // Setting relevant children of new nodes
        curr.rightNode = newRightOfCurrent;
        newParent.leftNode = curr;

        return newParent;
    }

    /**
     * Counts Distance to Root Nodes for Height of Tree (Recursive)
     * @return Height of Tree
     */
    public int getHeight() {
        // Getting leftNode Height
        int leftNodeHeight;
        if (this.leftNode == null) {
            leftNodeHeight = 0;
        } else {
            leftNodeHeight = 1 + this.leftNode.getHeight();
        }
        // Getting rightNode Height
        int rightNodeHeight;
        if (this.rightNode == null) {
            rightNodeHeight = 0;
        } else {
            rightNodeHeight = 1 + this.rightNode.getHeight();
        }

        return Math.max(leftNodeHeight, rightNodeHeight);
    }

    /**
     * Calculates Height of Subtrees to get Balance Factor
     * @return Balance Factor of Tree at current node
     */
    public int getBalanceFactor() {
        int leftHeight;
        int rightHeight;
        // Left Height
        if (leftNode == null) {
            leftHeight = 0;
        } else {
            leftHeight = leftNode.getHeight();
        }
        // Right Height
        if (rightNode == null) {
            rightHeight = 0;
        } else {
            rightHeight = rightNode.getHeight();
        }

        return leftHeight - rightHeight;
    }

    /**
     * Iterates Over Tree From Most Likes posts to least-liked
     * @return ArrayList of Posts from Most-Liked to Least-Liked
     */
    public ArrayList<Post> iterator() {
        if (this.post == null) return new ArrayList<>();
        ArrayList<Post> out = new ArrayList<>();
        if (rightNode != null) {
            out.addAll(this.rightNode.iterator());
        }
        out.add(this.post);
        if (leftNode != null) {
            out.addAll(this.leftNode.iterator());
        }
        return out;
    }

}

