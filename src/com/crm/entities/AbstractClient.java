package com.crm.entities;

public abstract class AbstractClient {
    private long id;
    private String name;

    protected AbstractClient(long id, String name) {
        setId(id);
        setName(name);
    }

    public long getId() {
        return id;
    }

    protected void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    protected void setName(String name) {
        this.name = name;
    }
}
