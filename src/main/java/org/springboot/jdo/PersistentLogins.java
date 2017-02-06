package org.springboot.jdo;

import javax.persistence.*;
import java.util.Date;

/**
 * 持久化登录信息
 * <p>
 * Created by goldendba@gmail.com on 2017/2/3.
 */
@Entity
@Table(name = "persistent_logins")
public class PersistentLogins extends IdEntity implements java.io.Serializable {
    @Column(name = "username", length = 64, nullable = false)
    private String username;
    @Column(name = "token", length = 64, nullable = false)
    private String token;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "last_used", nullable = false)
    private Date lastUsed;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Date getLastUsed() {
        return lastUsed;
    }

    public void setLastUsed(Date lastUsed) {
        this.lastUsed = lastUsed;
    }
}
