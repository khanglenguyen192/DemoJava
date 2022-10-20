package com.amaris.domain;

import com.amaris.domain.base.BaseEntity;
import lombok.*;
import net.minidev.json.annotate.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "userroles")
public class UserRole extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    private String name;

    @JsonIgnore
    @OneToMany(mappedBy = "role", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<AccountRoleMap> accountRoleMaps;

//    @ManyToMany(mappedBy = "roles", cascade = CascadeType.ALL)
//    private Set<Account> accounts = new HashSet<Account>();
}
