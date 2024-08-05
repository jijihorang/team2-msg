package org.example.team2msg.vo;

import lombok.*;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class StudVO {
    private Integer sno;
    private String sid;
    private String spw;
    private String smail;
    private boolean delFlag;
}
