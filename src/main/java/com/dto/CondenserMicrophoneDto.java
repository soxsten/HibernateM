package com.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
class CondenserMicrophoneDto extends MicrophoneDto {
    private String color;
}
