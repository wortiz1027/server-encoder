package org.workshop.starter.objects;

import lombok.*;

@Setter
@Getter
@Builder
@ToString
@AllArgsConstructor
public class Message implements java.io.Serializable {

    public static final long serialVersionUID = 1L;

    private String password;
    private Algorithm algorithm;

}