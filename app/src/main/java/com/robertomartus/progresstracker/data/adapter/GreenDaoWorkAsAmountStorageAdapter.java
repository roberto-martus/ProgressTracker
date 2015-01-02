package com.robertomartus.progresstracker.data.adapter;

import android.content.Context;

import com.robertomartus.progresstracker.data.DaoSession;
import com.robertomartus.progresstracker.data.WorkAsAmount;
import com.robertomartus.progresstracker.data.WorkAsAmountGreenDaoImpl;
import com.robertomartus.progresstracker.data.WorkAsAmountGreenDaoImplDao;
import com.robertomartus.progresstracker.meta.ProgressTrackerApplication;

import java.util.List;

/**
 * Created by Leonid on 02.01.2015.
 */
public class GreenDaoWorkAsAmountStorageAdapter implements StorageAdapter<WorkAsAmount, Long> {

    private final WorkAsAmountGreenDaoImplDao dao;

    public GreenDaoWorkAsAmountStorageAdapter(Context context) {
        ProgressTrackerApplication appContext = (ProgressTrackerApplication) context.getApplicationContext();
        DaoSession daoSession = appContext.getDaoSession();
        dao = daoSession.getWorkAsAmountGreenDaoImplDao();
    }

    @Override
    public WorkAsAmount loadByKey(Long key) {
        return dao.load(key);
    }

    @Override
    public List<? extends WorkAsAmountGreenDaoImpl> loadAll() {
        return dao.loadAll();
    }

    @Override
    public void insert(WorkAsAmount entity) {
        dao.insert((WorkAsAmountGreenDaoImpl) entity);
    }

    @Override
    public void insert(Iterable<? extends WorkAsAmount> entities) {
        dao.insertInTx((Iterable<WorkAsAmountGreenDaoImpl>) entities);
    }

    @Override
    public void insertOrReplace(WorkAsAmount entity) {
        dao.insertOrReplace((WorkAsAmountGreenDaoImpl) entity);
    }

    @Override
    public void deleteByKey(Long key) {
        dao.deleteByKey(key);
    }

    @Override
    public void deleteByKeys(Iterable<Long> keys) {
        dao.deleteByKeyInTx(keys);
    }

    @Override
    public void update(Iterable<? extends WorkAsAmount> entities) {
        dao.updateInTx((Iterable<WorkAsAmountGreenDaoImpl>) entities);
    }

    @Override
    public void update(WorkAsAmount entity) {
        dao.update((WorkAsAmountGreenDaoImpl) entity);
    }

    @Override
    public void refresh(WorkAsAmount entity) {
        dao.refresh((WorkAsAmountGreenDaoImpl) entity);
    }

}
