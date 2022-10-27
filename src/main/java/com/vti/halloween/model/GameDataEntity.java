package com.vti.halloween.model;

import com.vti.halloween.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "game_data")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GameDataEntity extends BaseEntity {

    @Column(unique = true)
    @NotBlank
    private String account;

    @ManyToOne
    @JoinColumn(name = "card_id")
    private CardEntity card;

    @Column
    private boolean winPrize;
}
