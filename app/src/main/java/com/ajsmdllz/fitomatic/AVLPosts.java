package com.ajsmdllz.fitomatic;

import com.ajsmdllz.fitomatic.Posts.Post;
import com.ajsmdllz.fitomatic.Posts.SingleActivity;
import com.ajsmdllz.fitomatic.Registration.User;

import java.sql.Array;
import java.util.ArrayList;

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

    public AVLPosts (Post p) {
        this.post = p;
        this.leftnode = new AVLPosts();
        this.rightnode = new AVLPosts();
    }


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


    public AVLPosts rightRotate() {
        if (this.leftnode == null || this.leftnode.post == null) {
            return this;
        }

        AVLPosts newParent = this.leftnode;
        AVLPosts newLeftOfCurrent = newParent.rightnode;
        AVLPosts curr = this;

        curr.leftnode = newLeftOfCurrent;
        newParent.rightnode = curr;
        return newParent;
    }

    public AVLPosts leftRotate() {
        if (this.rightnode == null || this.rightnode.post == null){
            return this;
        }

        AVLPosts newParent = this.rightnode;
        AVLPosts newRightOfCurrent = newParent.leftnode;
        AVLPosts curr = this;

        curr.rightnode = newRightOfCurrent;
        newParent.leftnode = curr;


        return newParent;
    }


    public int getHeight() {
        // Check whether leftNode or rightNode are EmptyTree
        int leftNodeHeight;
        if (this.leftnode == null) {
            leftNodeHeight = 0;
        } else {
            leftNodeHeight = 1 + this.leftnode.getHeight();
        }

        int rightNodeHeight;
        if (this.rightnode == null) {
            rightNodeHeight = 0;
        } else {
            rightNodeHeight = 1 + this.rightnode.getHeight();
        }

        return Math.max(leftNodeHeight, rightNodeHeight);
    }


    public int getBalanceFactor() {
        int leftHeight;
        int rightHeight;

        if (leftnode == null) {
            leftHeight = 0;
        } else {
            leftHeight = leftnode.getHeight();
        }

        if (rightnode == null) {
            rightHeight = 0;
        } else {
            rightHeight = rightnode.getHeight();
        }

        return leftHeight - rightHeight;
    }


    public ArrayList<Post> iterator() {
        if (this.post == null) return new ArrayList<>();
        ArrayList<Post> out = new ArrayList<>();
        out.add(this.post);
        if (rightnode != null) {
            out.addAll(this.rightnode.iterator());
        }
        if (leftnode != null) {
            out.addAll(this.leftnode.iterator());
        }
        return out;
    }



}

