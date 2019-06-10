package com.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
class HeavyCarDto extends CarDto {
    private String color;
}
