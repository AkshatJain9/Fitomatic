package com.ajsmdllz.fitomatic.Search;

import com.ajsmdllz.fitomatic.Search.Expressions.Exp;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

/**
 * Given an expression, will return a query to be sent to a database
 */
public class DBQuery {
    private static DBQuery instance = null;
    private DBQuery(){};

    public static DBQuery getInstance() {
        if (instance == null) {
            return new DBQuery();
        }
        return instance;
    }

    public Query getQuery(Exp e) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        CollectionReference posts = db.collection("posts");

        if (e.show().equals("EMPTY")) {
            posts.whereNotEqualTo("auhtor", null);
        }

        if (e.show().equals("USER")) {
            CollectionReference users = db.collection("users");
            return users.whereEqualTo("firstname", e.getVal());
        }

        Query q = posts.whereNotEqualTo("auhtor", null);
        while (!e.show().equals("EMPTY")) {
            if (e.show().equals("ACTIVITY")) {
                q = q.whereArrayContains("activity", e.getVal());
            }
            if (e.show().equals("POST")) {
                q = q.whereEqualTo("title", e.getVal());
            }
            if (e.show().equals("SIZE")) {
                q = q.whereNotEqualTo("maxParticipants", null);
            }
            if (e.show().equals("TIME")) {
                q = q.whereEqualTo("date", e.getVal());
            }
            e = e.getNext();
        }
        return q;
    }

}
