package com.trainingsv2.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.GenerationType;

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

    @Column(name = "account_id")
    private Integer accountId;

    @Column(name = "role_id")
    private Integer roleId;

//    @ManyToOne
//    @JoinColumn(name = "account_id", referencedColumnName = "account_id")
//    private Account account;
//
//    @ManyToOne
//    @JoinColumn(name = "role_id", referencedColumnName = "id")
//    private UserRole role;
}
