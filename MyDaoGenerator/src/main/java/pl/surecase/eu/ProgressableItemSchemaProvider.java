package pl.surecase.eu;

import de.greenrobot.daogenerator.Entity;
import de.greenrobot.daogenerator.Schema;

/**
 * Created by Leonid on 11.12.2014.
 */
public class ProgressableItemSchemaProvider implements SchemaProvider {

    @Override
    public Schema getSchema() {
        Schema schema = new Schema(1, "com.robertomartus.progresstracker.data");
        Entity entity = schema.addEntity("ProgressableItem");
        entity.addIdProperty();
        entity.addStringProperty("name");
        entity.addStringProperty("description");
        entity.addStringProperty("tags");
        entity.setHasKeepSections(true);
        return schema;
    }
}
