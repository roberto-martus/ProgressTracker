package pl.surecase.eu;

import de.greenrobot.daogenerator.Entity;
import de.greenrobot.daogenerator.Schema;

/**
 * Created by Leonid on 20.12.2014.
 */
public class WorkAsAmountSchemaProvider implements SchemaProvider {
    private static final int VERSION = 1;

    @Override
    public Schema getSchema() {
        Schema schema = new Schema(VERSION, Paths.getGeneratedClassesDestination());
        Entity entity = schema.addEntity("WorkAsAmount");
        entity.implementsInterface(Paths.getDataPackage() + ".Work");
        entity.addIdProperty();
        entity.addLongProperty("amount");
        entity.setHasKeepSections(true);
        return schema;
    }
}
