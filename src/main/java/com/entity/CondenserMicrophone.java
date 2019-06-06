package com.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@DiscriminatorValue("CONDENSER")
@Getter
@Setter
public class CondenserMicrophone extends Microphone {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String color;
}
