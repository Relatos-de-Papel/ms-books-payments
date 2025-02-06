package com.unir.payments.facade.model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class User {

    private Integer cc;
    private String firstName;
    private String lastName;
    private String email;
}
