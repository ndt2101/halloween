package com.vti.halloween.dto;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResponseDTO<T> {
    private Integer status;
    private String message;
    private T data;
}
