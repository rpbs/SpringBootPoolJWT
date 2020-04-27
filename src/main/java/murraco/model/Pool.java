package murraco.model;

import java.util.Date;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.Size;

import murraco.model.Options;

@Entity
public class Pool {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PoolId")
    private Integer id;

    @OneToOne(fetch = FetchType.LAZY, cascade =  CascadeType.ALL)
    @JoinColumn(name = "UserId", nullable = false)
    private User creator;

    @Column(name = "CreatedDate")
    private Date createdDate;

    @Column(name = "Title", nullable = false)
    private String title;

    @Column(name = "Description", nullable = false)
    private String description;

    @ElementCollection(fetch = FetchType.LAZY)
    private List<Options> options;

    public List<Options> getOptions() {
        return this.options;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setOptions(List<Options> options) {
        this.options = options;
    };

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getCreatedDate() {
        return this.createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }
    
    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getCreator() {
        return this.creator;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }


}