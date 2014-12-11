package pl.surecase.eu;

import de.greenrobot.daogenerator.DaoGenerator;

/**
 * Created by Leonid on 11.12.2014.
 */
public class SchemasGenerator {

    public static void generate(Iterable<? extends SchemaProvider> schemaProviders,
                                String outputDir)  throws Exception {
        for (SchemaProvider schemaProvider : schemaProviders) {
            new DaoGenerator().generateAll(schemaProvider.getSchema(), outputDir);
        }
    }

}
