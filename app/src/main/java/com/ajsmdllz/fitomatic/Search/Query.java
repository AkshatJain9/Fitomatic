package com.ajsmdllz.fitomatic.Search;

import java.util.Objects;

public class Query {
    String collection;
    String document;

    public Query(String collection, String document) {
        this.collection = collection;
        this.document = document;
    }

    public Query (String collection) {
        this.collection = collection;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Query query = (Query) o;
        return Objects.equals(collection, query.collection) && Objects.equals(document, query.document);
    }

    @Override
    public int hashCode() {
        return Objects.hash(collection, document);
    }
}
