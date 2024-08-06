package org.example.team2msg.common;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class PageInfo {
    private int page;
    private int start;
    private int end;
    private boolean prev;
    private boolean next;

    public PageInfo(int page, int size, int total) {
        this.page = page <= 0 ? 1 : page;
        this.end = (int)Math.ceil(this.page / 10.0) * 10;
        this.start = this.end - 9;

        this.prev = this.start > 1;
        int realEnd = (int) Math.ceil((double) total / size);
        this.next = this.end < realEnd;

        if (this.end > realEnd) {
            this.end = realEnd;
        }
    }
}
