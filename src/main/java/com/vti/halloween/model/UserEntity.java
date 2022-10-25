package com.vti.halloween.model;

import com.vti.halloween.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "user")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserEntity extends BaseEntity {

    @Column
    @NotBlank
    private String account;

    @Column
    @NotBlank
    private String fullName;

    @Column
    @NotBlank
    private String password;

}
