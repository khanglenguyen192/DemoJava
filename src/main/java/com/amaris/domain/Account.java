package com.amaris.domain;

import com.amaris.domain.base.BaseEntity;
import lombok.*;
import net.minidev.json.annotate.JsonIgnore;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "accounts")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Account extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "account_id")
    private Integer accountId;

    private String firstName;

    private String lastName;

    private String email;

    private String password;

    private String phone;

    @Nullable
    @Column(nullable = true)
    private String accountNumber;

    @JsonIgnore
    @OneToMany(mappedBy = "account", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<AccountRoleMap> accountRoleMaps;

//    @ManyToMany(cascade = CascadeType.ALL)
//    @JoinTable(name = "account_role_map", joinColumns = { @JoinColumn(name = "account_id")}, inverseJoinColumns = { @JoinColumn(name = "role_id")})
//    private Set<UserRole> roles = new HashSet<UserRole>();
//
//    public void setRoles(List<UserRole> roles)
//    {
//        roles.addAll(roles);
//    }
}
