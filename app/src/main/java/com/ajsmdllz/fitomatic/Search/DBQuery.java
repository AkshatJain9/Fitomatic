package com.ajsmdllz.fitomatic.Search;

import com.ajsmdllz.fitomatic.Search.Expressions.Exp;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query

import java.util.Objects;

public class DBQuery {

    public static Query getQuery(Exp e) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        if (e.show().equals("EMPTY")) {
            return null;
        }

        if (e.show().equals("USER")) {
            CollectionReference users = db.collection("users");
            Query q = users.whereEqualTo("firstname", e.getVal());
            return q;
        }

        CollectionReference posts = db.collection("posts");
        Query q = posts.whereNotEqualTo("auhtor", null);
        while (!e.show().equals("EMPTY")) {
            if (e.show().equals("ACTIVITY")) {

            }
            if (e.show().equals("POST")) {

            }
            if (e.show().equals("SIZE")) {

            }
            if (e.show().equals("TIME")) {

            }


            e = e.getNext();
        }
        return q;
    }

}
