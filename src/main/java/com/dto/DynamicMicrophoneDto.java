package com.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class DynamicMicrophoneDto extends MicrophoneDto {
    private String weight;
}
