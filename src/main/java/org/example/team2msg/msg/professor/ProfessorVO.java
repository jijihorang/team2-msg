package org.example.team2msg.msg.professor;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Getter
public class ProfessorVO {
    private Integer pno;
    private String pid;
    private String ppw;
    private String pmail;
    private boolean delflag;
}
