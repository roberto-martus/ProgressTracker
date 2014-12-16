package com.robertomartus.progresstracker.data;

// THIS CODE IS GENERATED BY greenDAO, EDIT ONLY INSIDE THE "KEEP"-SECTIONS

// KEEP INCLUDES - put your custom includes here
// KEEP INCLUDES END

import com.google.common.base.Objects;

/**
 * Entity mapped to table PROGRESSABLE_ITEM.
 */
public class ProgressableItem {

    private Long id;
    private String name;
    private String description;
    private String tags;

    // KEEP FIELDS - put your custom fields here
    // KEEP FIELDS END

    public ProgressableItem() {
    }

    public ProgressableItem(Long id) {
        this.id = id;
    }

    public ProgressableItem(Long id, String name, String description, String tags) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.tags = tags;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    // KEEP METHODS - put your custom methods here

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ProgressableItem)) {
            return false;
        }
        ProgressableItem other = (ProgressableItem) obj;
        return Objects.equal(id, other.id) &&
                Objects.equal(name, other.name) &&
                Objects.equal(description, other.description) &&
                Objects.equal(tags, other.tags);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id, name, description, tags);
    }

    @Override
    public String toString() {
        return Objects.toStringHelper(this)
                .add("id", id)
                .add("name", name)
                .add("description", description)
                .add("tags", tags)
                .toString();
    }
    // KEEP METHODS END

}