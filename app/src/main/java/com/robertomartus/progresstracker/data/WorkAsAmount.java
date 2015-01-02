package com.robertomartus.progresstracker.data;

/**
 * Created by Leonid on 02.01.2015.
 */
public interface WorkAsAmount extends Work {
    Long getDone();
    Long getTotal();
    void setDone(Long done);
    void setTotal(Long total);
}
