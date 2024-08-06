package org.example.team2msg.msg.student.vo;

import lombok.*;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class StudentVO {
    private Integer sno;
    private String sid;
    private String spw;
    private String smail;
    private boolean delFlag;
}
