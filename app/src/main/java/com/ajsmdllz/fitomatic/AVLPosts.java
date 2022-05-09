package com.ajsmdllz.fitomatic;

import com.ajsmdllz.fitomatic.Posts.Post;
import java.util.ArrayList;

/**
 * AVLPosts Stores Queried Posts from the Database in Tree form for Optimised Insertion and Selection
 */
public class AVLPosts {
    public Post post;
    public AVLPosts leftnode;
    public AVLPosts rightnode;


    public AVLPosts (Post p, AVLPosts ln, AVLPosts rn) {
        this.post = p;
        this.leftnode = ln;
        this.rightnode = rn;
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
        if (p == null) {
            return this;
        }

        if (this.post == null) {
            this.post = p;
            return this;
        }

        if (p.likes > this.post.likes) {
            AVLPosts rightins;
            if (this.rightnode == null) {
                this.rightnode = new AVLPosts();
            }
            rightins = new AVLPosts(this.post, this.leftnode, this.rightnode.insert(p));
            if (rightins.getBalanceFactor() > 1) {
                if ((rightins.leftnode != null) && (rightins.leftnode.getBalanceFactor() < 0)) {
                    rightins.leftnode = rightins.leftnode.leftRotate();
                }
                rightins = rightins.rightRotate();
                return rightins;
            }
            if (rightins.getBalanceFactor() < -1) {
                if (!(rightins.rightnode == null) && (rightins.rightnode.getBalanceFactor() > 0)) {
                    rightins.rightnode = rightins.rightnode.rightRotate();
                }
                rightins = rightins.leftRotate();
                return rightins;
            }
            return rightins;
        } else {
            if (this.leftnode == null) {
                this.leftnode = new AVLPosts();
            }
            AVLPosts leftins = new AVLPosts(this.post, this.leftnode.insert(p), this.rightnode);
            if (leftins.getBalanceFactor() > 1) {
                if (!(leftins.leftnode == null) && (leftins.leftnode.getBalanceFactor() < 0)) {
                    leftins.leftnode = leftins.leftnode.leftRotate();
                }
                leftins = leftins.rightRotate();
                return leftins;
            }
            if (leftins.getBalanceFactor() < -1) {
                if (!(leftins.rightnode == null) && (leftins.rightnode.getBalanceFactor() > 0)) {
                    leftins.rightnode = leftins.rightnode.rightRotate();
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
        if (this.leftnode == null || this.leftnode.post == null) {
            return this;
        }
        // Assigning new nodes
        AVLPosts newParent = this.leftnode;
        AVLPosts newLeftOfCurrent = newParent.rightnode;
        AVLPosts curr = this;
        // Setting relevant children of new nodes
        curr.leftnode = newLeftOfCurrent;
        newParent.rightnode = curr;

        return newParent;
    }

    /**
     * Left Rotates the Tree around the current node
     * @return AVL Tree having been rotated
     */
    public AVLPosts leftRotate() {
        if (this.rightnode == null || this.rightnode.post == null){
            return this;
        }
        // Assigning new nodes
        AVLPosts newParent = this.rightnode;
        AVLPosts newRightOfCurrent = newParent.leftnode;
        AVLPosts curr = this;
        // Setting relevant children of new nodes
        curr.rightnode = newRightOfCurrent;
        newParent.leftnode = curr;

        return newParent;
    }

    /**
     * Counts Distance to Root Nodes for Height of Tree (Recursive)
     * @return Height of Tree
     */
    public int getHeight() {
        // Getting leftNode Height
        int leftNodeHeight;
        if (this.leftnode == null) {
            leftNodeHeight = 0;
        } else {
            leftNodeHeight = 1 + this.leftnode.getHeight();
        }
        // Getting rightNode Height
        int rightNodeHeight;
        if (this.rightnode == null) {
            rightNodeHeight = 0;
        } else {
            rightNodeHeight = 1 + this.rightnode.getHeight();
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
        if (leftnode == null) {
            leftHeight = 0;
        } else {
            leftHeight = leftnode.getHeight();
        }
        // Right Height
        if (rightnode == null) {
            rightHeight = 0;
        } else {
            rightHeight = rightnode.getHeight();
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
        if (rightnode != null) {
            out.addAll(this.rightnode.iterator());
        }
        out.add(this.post);
        if (leftnode != null) {
            out.addAll(this.leftnode.iterator());
        }
        return out;
    }



}

