package com.amaris.domain;

import com.amaris.domain.Account;
import com.amaris.domain.Catalog;
import com.amaris.domain.UserRole;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "account_role_map")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AccountRoleMap {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "account_id", referencedColumnName = "account_id")
    private Account account;

    @ManyToOne
    @JoinColumn(name = "role_id", referencedColumnName = "id")
    private UserRole role;
}
