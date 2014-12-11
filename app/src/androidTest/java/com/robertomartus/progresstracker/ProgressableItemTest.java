package com.robertomartus.progresstracker;

import android.test.AndroidTestCase;

import com.cleancoder.base.android.util.TaggedLogger;
import com.robertomartus.progresstracker.data.DaoSession;
import com.robertomartus.progresstracker.data.ProgressableItem;
import com.robertomartus.progresstracker.data.ProgressableItemDao;

import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by Leonid on 11.12.2014.
 */
public class ProgressableItemTest extends AndroidTestCase {
    private static final TaggedLogger logger = TaggedLogger.forClass(ProgressableItemTest.class);

    private DaoSessionHelper.SetupResult setupResult;
    private ProgressableItem item;
    private ProgressableItemDao dao;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        setupResult = DaoSessionHelper.setupDaoSession(mContext, "progressable-item-test-db");
        DaoSession daoSession = setupResult.getDaoMaster().newSession();
        dao = daoSession.getProgressableItemDao();
        dao.deleteAll();
        item = new ProgressableItem(1L, "item", "test item", "no tags");
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
        setupResult.getDatabase().close();
        setupResult = null;
        dao = null;
    }

    public void testEquals() {
        assertEquals(item, copyOf(item));
    }

    private static ProgressableItem copyOf(ProgressableItem item) {
        return new ProgressableItem(item.getId(), item.getName(), item.getDescription(), item.getTags());
    }

    public void testNotEquals_DifferentId() {
        ProgressableItem itemWithDifferentId = copyOf(item);
        itemWithDifferentId.setId(item.getId() + 1);
        assertThat(itemWithDifferentId, not(item));
    }

    public void testNotEquals_DifferentName() {
        ProgressableItem itemWithDifferentName = copyOf(item);
        itemWithDifferentName.setName("Not the same " + item.getName());
        assertThat(itemWithDifferentName, not(item));
    }

    public void testNotEquals_DifferentDescription() {
        ProgressableItem itemWithDifferentDescription = copyOf(item);
        itemWithDifferentDescription.setDescription("Not the same " + item.getDescription());
        assertThat(itemWithDifferentDescription, not(item));
    }

    public void testNotEquals_DifferentTags() {
        ProgressableItem itemWithDifferentTags = copyOf(item);
        itemWithDifferentTags.setTags("Not the same " + item.getTags());
        assertThat(itemWithDifferentTags, not(item));
    }

    public void testInsertLoad() throws Throwable {
        dao.insert(item);
        assertEquals(item, dao.load(item.getId()));
    }

}
