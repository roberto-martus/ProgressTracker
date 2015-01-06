package com.robertomartus.progresstracker.data;

// THIS CODE IS GENERATED BY greenDAO, EDIT ONLY INSIDE THE "KEEP"-SECTIONS

// KEEP INCLUDES - put your custom includes here
// KEEP INCLUDES END

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;

/**
 * Entity mapped to table WORK_AS_AMOUNT_GREEN_DAO_IMPL.
 */
public class WorkAsAmountGreenDaoImpl implements com.robertomartus.progresstracker.data.WorkAsAmount, java.io.Serializable {

    private Long id;
    private Long done;
    private Long total;

    // KEEP FIELDS - put your custom fields here
    // KEEP FIELDS END

    public WorkAsAmountGreenDaoImpl() {
    }

    public WorkAsAmountGreenDaoImpl(Long id) {
        this.id = id;
    }

    public WorkAsAmountGreenDaoImpl(Long id, Long done, Long total) {
        this.id = id;
        this.done = done;
        this.total = total;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getDone() {
        return done;
    }

    public void setDone(Long done) {
        this.done = done;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    // KEEP METHODS - put your custom methods here

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("id", id)
                .add("done", done)
                .add("total", total)
                .toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof WorkAsAmountGreenDaoImpl)) {
            return false;
        }
        WorkAsAmountGreenDaoImpl other = (WorkAsAmountGreenDaoImpl) obj;
        return Objects.equal(id, other.id) &&
                Objects.equal(done, other.done) &&
                Objects.equal(total, other.total);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id, done, total);
    }
    // KEEP METHODS END

}
