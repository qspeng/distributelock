package me.qspeng.distributelock.lock.entity;


import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "distributed_lock")
@EntityListeners(AuditingEntityListener.class)
public class DistributeLock {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String serviceKey;
    private String lockKey;
    private String owner;
    private int expireSeconds;
    @CreatedDate
    @Column(updatable = false, nullable = false)
    private Date createTime = new Date();

    @Column(nullable = false)
    private Date renewalTime = new Date();

    public DistributeLock(String serviceKey, String lockKey, String owner, int expireSeconds) {
        this.serviceKey = serviceKey;
        this.lockKey = lockKey;
        this.owner = owner;
        this.expireSeconds = expireSeconds;
    }

    public DistributeLock() {
    }
}
