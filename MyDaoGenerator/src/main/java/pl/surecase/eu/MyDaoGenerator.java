package pl.surecase.eu;

import java.util.Arrays;
import java.util.List;

public class MyDaoGenerator {

    private static final List<? extends SchemaProvider> SCHEMA_PROVIDERS = Arrays.asList(
            new ProgressableItemSchemaProvider()
    );

    public static void main(String args[]) throws Exception {
        SchemasGenerator.generate(SCHEMA_PROVIDERS, args[0]);
    }
}
