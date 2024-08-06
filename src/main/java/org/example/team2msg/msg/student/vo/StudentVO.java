<<<<<<<< HEAD:src/main/java/org/example/team2msg/msg/student/StudVO.java
package org.example.team2msg.msg.student;
========
package org.example.team2msg.msg.student.vo;
>>>>>>>> bd7dadc849f5737f111be1e39627a6d1ed4248f9:src/main/java/org/example/team2msg/msg/student/vo/StudentVO.java

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
