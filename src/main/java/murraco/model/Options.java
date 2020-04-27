package murraco.model;

import murraco.model.Pool;
import java.util.Date;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
public class Options {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "option_id")
    private Integer poolOptionsId;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "pool_id", nullable = false)
    private Pool pool;

    @Column(name = "description", nullable = false)
    private String description;

    public Options() {  }

    public Options(Pool pool, String description) {
        this.pool = pool;
        this.description = description;
    }

    public Options(String description){
        this.setDescription(description);
    }

    public Integer getId() {
        return this.poolOptionsId;
    }

    public void setId(Integer id) {
        this.poolOptionsId = id;
    }

    public Pool getPool() {
        return this.pool;
    }

    public void setPool(Pool pool) {
        this.pool = pool;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}