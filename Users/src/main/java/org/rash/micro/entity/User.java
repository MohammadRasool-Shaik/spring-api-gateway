/**
 *
 */
package org.rash.micro.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @author Rasool.Shaik
 */
@Entity
@Table(name = "users")
public class User implements Serializable {

    private static final long serialVersionUID = -6272073593793343818L;

    @Id
    @Column(name = "username")
    private String userName;

    private String password;

    private boolean enabled;

    @Column(unique = true)
    private String email;

    private String roles;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }
}
