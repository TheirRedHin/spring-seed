package com.project.demo.entity;

import java.io.Serializable;

/**
 * <p>
 *
 * </p>
 *
 * @author redHin
 * @since 2021-02-21
 */
public class McCard implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "McCard{" +
            "id=" + id +
            ", name=" + name +
            "}";
    }
}
