package com.examportalservice.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name="roles")
@NoArgsConstructor
public class Role {
    @Id
    private Long roleId;
    private  String roleName;

    // role fetch garda userrole na aayos  ..userrole ko function call garda matra userrole aayos
    @OneToMany(cascade = CascadeType.ALL, fetch =  FetchType.LAZY, mappedBy = "role")
    private Set<UserRole> userRoles = new HashSet<>();


}
