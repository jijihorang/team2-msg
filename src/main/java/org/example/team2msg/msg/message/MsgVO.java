package org.example.team2msg.msg.message;

import lombok.*;

import java.sql.Timestamp;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString
public class MsgVO {

    Integer mno;
    String sender;
    String receiver;
    String title;
    String content;
    Timestamp senddate;
    boolean is_read;
    boolean is_broadcast;

}
