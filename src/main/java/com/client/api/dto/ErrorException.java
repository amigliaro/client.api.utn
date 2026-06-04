package com.client.api.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ErrorException {
    private int status;
        private String message;
    }
