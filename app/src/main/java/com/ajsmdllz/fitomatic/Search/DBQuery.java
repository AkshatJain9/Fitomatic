package com.ajsmdllz.fitomatic.Search;

import com.ajsmdllz.fitomatic.Search.Expressions.Exp;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import java.util.ArrayList;

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

    public Query getQuery(Exp e, CollectionReference colref) {
        Query q = colref.whereNotEqualTo("author", null);
        if (e.show().equals("EMPTY")) {
            return q;
        }

        while (e != null && !e.show().equals("EMPTY")) {
            if (e.show().equals("USER")) {
                q = q.whereEqualTo("author", e.getVal());
            }

            if (e.show().equals("ACTIVITY")) {
                ArrayList<String> temp = new ArrayList<>();
                while (e.show().equals("ACTIVITY")) {
                    temp.add(e.getVal());
                    e = e.getNext();
                }
                q = q.whereIn("activity", temp);
            }

            if (e.show().equals("POST")) {
                StringBuilder postTitle = new StringBuilder();
                while (e.show().equals("POST")) {
                    postTitle.append(e.getVal());
                    e = e.getNext();
                    if (e.show().equals("POST")) {
                        postTitle.append(" ");
                    }
                }
                System.out.println(postTitle);
                q = q.whereEqualTo("title", postTitle.toString());
            }

            if (e.show().equals("TIME")) {
                q = q.whereEqualTo("date", e.getVal());
            }

            e = e.getNext();
        }
        return q;
    }

}
