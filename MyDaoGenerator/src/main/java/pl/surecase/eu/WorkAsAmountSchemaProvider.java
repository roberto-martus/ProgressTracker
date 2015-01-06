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
        Entity entity = schema.addEntity("WorkAsAmountGreenDaoImpl");
        entity.implementsInterface(Paths.getDataPackage() + ".WorkAsAmount");
        entity.addIdProperty();
        entity.addLongProperty("done");
        entity.addLongProperty("total");
        entity.setHasKeepSections(true);
        entity.implementsSerializable();
        return schema;
    }
}
