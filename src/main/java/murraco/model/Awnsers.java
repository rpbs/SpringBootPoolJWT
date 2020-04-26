package murraco.model;

import murraco.model.Pool;
import java.util.Date;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
public class Awnsers {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "anwser_id")
    private Integer anserId;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "pool_id", nullable = false)
    private Pool pool;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "option_id", nullable = false)
    private Options option;

    public Options getOption() {
        return this.option;
    }

    public void setOption(Options option) {
        this.option = option;
    };

    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    };
    public Integer getAnserId() {
        return this.anserId;
    }

    public void setAnserId(Integer anserId) {
        this.anserId = anserId;
    };

}