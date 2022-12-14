package com.vti.halloween.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.vti.halloween.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Set;

@Table(name = "card")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class CardEntity extends BaseEntity {

    @Column
    private String message;

    @Column
    private Integer isLuckyCard;

    @Column
    private String url;

    @JsonIgnore
    @OneToMany(mappedBy = "card")
    private Set<GameDataEntity> gameDataEntities;
}
