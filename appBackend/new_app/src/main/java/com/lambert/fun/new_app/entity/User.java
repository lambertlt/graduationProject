package com.lambert.fun.new_app.entity;

import com.lambert.fun.new_app.service.serviceImpl.RoleServiceImpl;
import com.lambert.fun.new_app.util.BeanContext;
import lombok.Data;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.hibernate.annotations.Proxy;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Data
@Entity(name = "t_user") // jpa 查询时指代实体表的名称
@Table(name = "t_user") // 数据库中真实表的名称
@Proxy(lazy = false)
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    /*
     * TABLE：使用一个特定的数据库表格来保存主键。
     * SEQUENCE：根据底层数据库的序列来生成主键，条件是数据库支持序列。
     * IDENTITY：主键由数据库自动生成（主要是自动增长型）
     * AUTO：主键由程序控制。
     * */
    private Long id;
    private Long power; // 用户的权利
    private String username; // 用户名
    private String password; // 密码
    private String avatar; // 用户头像地址
    @Temporal(TemporalType.TIMESTAMP)
    private Date date; // 时间
    private boolean accountNonExpired;
    private boolean accountNonLocked;
    private boolean credentialsNonExpired;
    private boolean enabled;
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    // fetch = FetchType.EAGER:异步加载 ；cascade = CascadeType.PERSIST 级联操作
    /*
     * CascadeType.REMOVE
     * Cascade remove operation，级联删除操作。
     * 删除当前实体时，与它有映射关系的实体也会跟着被删除。
     * CascadeType.MERGE
     * Cascade merge operation，级联更新（合并）操作。
     * 当Student中的数据改变，会相应地更新Course中的数据。
     * CascadeType.DETACH
     * Cascade detach operation，级联脱管/游离操作。
     * 如果你要删除一个实体，但是它有外键无法删除，你就需要这个级联权限了。它会撤销所有相关的外键关联。
     * CascadeType.REFRESH
     * Cascade refresh operation，级联刷新操作。
     * 假设场景 有一个订单,订单里面关联了许多商品,这个订单可以被很多人操作,那么这个时候A对此订单和关联的商品进行了修改,与此同时,B也进行了相同的操作,但是B先一步比A保存了数据,那么当A保存数据的时候,就需要先刷新订单信息及关联的商品信息后,再将订单及商品保存。
     * CascadeType.ALL
     * Cascade all operations，清晰明确，拥有以上所有级联操作权限。
     * */
    @NotFound(action = NotFoundAction.IGNORE) // 找不到外健数据时忽略。子表中没有主表中ID对应的记录。这样，当子表中没找到数据时，主表中对应的field就是null，而不会报错了。
    private List<Role> roles;

    public User() {
    }

    public User(Long id, Long power, String username, String password, String avatar, boolean accountNonExpired, boolean accountNonLocked, boolean credentialsNonExpired, boolean enabled, List<Role> roles, Date date) {
        this.id = id;
        this.power = power;
        this.username = username;
        this.password = password;
        this.avatar = avatar;
        this.accountNonExpired = accountNonExpired;
        this.accountNonLocked = accountNonLocked;
        this.credentialsNonExpired = credentialsNonExpired;
        this.enabled = enabled;
        this.roles = roles;
        this.date = date;
    }

    public static User getUser(String username, String password, Long power, List<Role> roles) {
        User user = new User(-1L, power, username, password, null, true, true, true, true, roles, new Date());
        return user;
    }

    public static User createUser(Long id) {
        User user = new User();
        user.setEnabled(true);
        user.setCredentialsNonExpired(true);
        user.setAccountNonLocked(true);
        user.setAccountNonExpired(true);
        user.setDate(new Date());
        // 进行service调用
        RoleServiceImpl roleService = BeanContext.getApplicationContext().getBean(RoleServiceImpl.class);
        Role role = roleService.loadRoleById(id);
        List<Role> roles = new ArrayList<>();
        roles.add(role);
        user.setRoles(roles);
        return user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        for (Role role : getRoles()) {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        }
        return authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return accountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return accountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return credentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", power=" + power +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", avatar='" + avatar + '\'' +
                ", date=" + date +
                ", accountNonExpired=" + accountNonExpired +
                ", accountNonLocked=" + accountNonLocked +
                ", credentialsNonExpired=" + credentialsNonExpired +
                ", enabled=" + enabled +
                ", roles=" + roles +
                '}';
    }
}
