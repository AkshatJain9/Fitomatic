package com.ajsmdllz.fitomatic.Search;

import com.ajsmdllz.fitomatic.Search.Expressions.Exp;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.Query;

import java.util.ArrayList;

/**
 * Given an expression, will return a query to be sent to a database
 */
public class DBQuery {
    private static DBQuery instance = null;
    private DBQuery(){}

    public static DBQuery getInstance() {
        if (instance == null) {
            instance = new DBQuery();
        }
        return instance;
    }

    /**
     * Given an Expression returned by the Parser, and a Collection to Search, returns a Query Object
     * @param e Expression Generated by Parser
     * @param colRef Collection Reference (Post by Default) to be searched
     * @return Firestore Query Object to be used to gather posts
     */
    public Query getQuery(Exp e, CollectionReference colRef) {
        // Initiating query, if empty expression passed in, then return default (all) query
        Query q = colRef.whereNotEqualTo("author", null);
        if (e.show().equals("EMPTY")) {
            return q;
        }

        // Similar to traversing linked list, iteratively build upon query
        while (e != null && !e.show().equals("EMPTY")) {
            // Searching for only users with specified email
            if (e.show().equals("USER")) {
                q = q.whereEqualTo("author", e.getVal());
            }
            // Searching any number of Activities
            if (e.show().equals("ACTIVITY")) {
                ArrayList<String> temp = new ArrayList<>();
                while (e.show().equals("ACTIVITY")) {
                    temp.add(e.getVal());
                    e = e.getNext();
                }
                q = q.whereIn("activity", temp);
            }

            // Searching possible multi-worded titles
            if (e.show().equals("POST")) {
                StringBuilder postTitle = new StringBuilder();
                while (e.show().equals("POST")) {
                    postTitle.append(e.getVal());
                    e = e.getNext();
                    if (e.show().equals("POST")) {
                        postTitle.append(" ");
                    }
                }
                q = q.whereEqualTo("title", postTitle.toString());
            }

            // Searching Date/Time Field
            if (e.show().equals("TIME")) {
                q = q.whereEqualTo("date", e.getVal());
            }

            e = e.getNext();
        }
        return q;
    }

}
