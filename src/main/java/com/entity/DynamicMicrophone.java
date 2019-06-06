package com.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@DiscriminatorValue("DYNAMIC")
@Getter
@Setter
public class DynamicMicrophone extends Microphone {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String weight;
}
