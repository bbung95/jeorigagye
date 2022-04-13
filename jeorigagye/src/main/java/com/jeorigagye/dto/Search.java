package com.jeorigagye.dto;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Search {

    private String searchKeyword;
    private String type;
    private int culPage;

}
