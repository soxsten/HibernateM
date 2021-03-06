package com.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@DiscriminatorValue("CONDENSER")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CondenserMicrophone extends Microphone {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String color;
}
