package test;

import javax.persistence.Persistence;

public class SchemaBuilder {
    public static void main(String[] args) {
        Persistence.generateSchema("PUTest", null);
        Persistence.generateSchema("PU", null);
    }
}
