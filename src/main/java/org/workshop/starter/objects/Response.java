package org.workshop.starter.objects;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Setter
@Getter
@Builder
@ToString
public class Response implements java.io.Serializable {

    private String password;

}