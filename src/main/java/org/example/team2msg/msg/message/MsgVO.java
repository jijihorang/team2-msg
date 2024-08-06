package org.example.team2msg.msg.message;

import lombok.*;

import java.sql.Timestamp;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class MsgVO {
    private Integer mno;
    private String sender;
    private String receiver;
    private String title;
    private String content;
    private Timestamp senddate;
    private boolean is_read;
    private boolean is_broadcast;

    public boolean isIs_read() {  // Getter 추가
        return is_read;
    }

    public void setIs_read(boolean is_read) {  // Setter 추가
        this.is_read = is_read;
    }

    public boolean isIs_broadcast() {  // Getter 추가
        return is_broadcast;
    }

    public void setIs_broadcast(boolean is_broadcast) {  // Setter 추가
        this.is_broadcast = is_broadcast;
    }

}
