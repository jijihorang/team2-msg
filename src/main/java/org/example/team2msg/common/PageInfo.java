package org.example.team2msg.common;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class PageInfo {
    private int page; //current page
    private int start;
    private int end;

    private boolean prev;
    private boolean next;

    public PageInfo(int page, int size, int total) {

        this.page = page <= 0 ? 1: page;

        end = (int) (Math.ceil(this.page /10.0) * 10);

        start = end - 9;

        prev = start == 1 ? false : true ;

        // 100 < 120
        if( end * size < total ) {
            next = true;
        }else {
            next = false;
            end =  (int) (Math.ceil(total / (double)size));
        }//end else
    }
}
