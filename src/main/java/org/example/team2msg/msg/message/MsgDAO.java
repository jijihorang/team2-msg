package org.example.team2msg.msg.message;

import lombok.Cleanup;
import lombok.extern.log4j.Log4j2;
import org.example.team2msg.common.ConnectionUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Log4j2
public enum MsgDAO {
    INSTANCE;

    MsgDAO(){

    }

    // 쪽지 전체 조회
    public List<MsgVO> list(String receiver) throws Exception{

        String sql = """
                select
                    mno, sender, title, senddate, is_read
                from
                    tbl_message
                where
                    receiver = ?
                """
                ;

        @Cleanup Connection con = ConnectionUtil.INSTANCE.getDs().getConnection();
        @Cleanup PreparedStatement ps = con.prepareStatement(sql);

        ps.setString(1, receiver);

        @Cleanup ResultSet rs = ps.executeQuery();

        List<MsgVO> list = new ArrayList<>();

        while(rs.next()){

            MsgVO vo = MsgVO.builder()
                    .mno(rs.getInt("mno"))
                    .sender(rs.getString("sender"))
                    .title(rs.getString("title"))
                    .senddate(rs.getTimestamp("senddate"))
                    .is_read(rs.getBoolean("is_read"))
                    .build();

            list.add(vo);

        } // end while


        return list;
    }

    // 쪽지 상세 조회
    public Optional<MsgVO> get(Integer mno, String receiver) throws Exception{

        final String sql = """
                select
                    mno, sender, receiver, title, content, senddate, is_read, is_broadcast
                from
                    tbl_message
                where
                    mno = ? and receiver = ?
                """;

        @Cleanup Connection con = ConnectionUtil.INSTANCE.getDs().getConnection();
        @Cleanup PreparedStatement ps = con.prepareStatement(sql);

        ps.setInt(1, mno);
        ps.setString(2, receiver);

        @Cleanup ResultSet rs = ps.executeQuery();

        if( ! rs.next() ){
            return Optional.empty();
        }

        MsgVO detail = MsgVO.builder()
                .mno(rs.getInt("mno"))
                .sender(rs.getString("sender"))
                .receiver(rs.getString("receiver"))
                .title(rs.getString("title"))
                .content(rs.getString("content"))
                .senddate(rs.getTimestamp("senddate"))
                .is_read(rs.getBoolean("is_read"))
                .is_broadcast(rs.getBoolean("is_broadcast"))
                .build();

        return Optional.of(detail);
    }

    // 쪽지 발송
    public Integer sendMessage(String receiver, String title, String content) throws Exception{

        String sql = """
                select
                    pid
                from
                    tbl_professor
                """;

        @Cleanup Connection con = ConnectionUtil.INSTANCE.getDs().getConnection();
        @Cleanup PreparedStatement ps = con.prepareStatement(sql);

        @Cleanup ResultSet rs = ps.executeQuery();

        if( ! rs.next() ){
            throw new Exception();
        }

        // insert
        String query = """
                insert into tbl_message (receiver, title, content)
                values (?, ?, ?)
                """;

        @Cleanup PreparedStatement pst = con.prepareStatement(query);

        pst.setString(1, receiver);
        pst.setString(2, title);
        pst.setString(3, content);

        log.info(receiver);

        int count = pst.executeUpdate(); // 여기서 자꾸 오류남

        if (count != 1) {
            throw new Exception();
        }

        pst = con.prepareStatement("select last_insert_id()");

        @Cleanup ResultSet rst = pst.executeQuery();

        rst.next();

        Integer mno = rst.getInt(1);

        return mno;

    }
}
