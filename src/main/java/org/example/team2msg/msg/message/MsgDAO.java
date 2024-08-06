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
                    mno, sender, receiver, title, content, senddate
                from
                    tbl_message
                where
                    mno = ? and receiver = ?
                """;

        @Cleanup Connection con = ConnectionUtil.INSTANCE.getDs().getConnection();
        @Cleanup PreparedStatement ps = con.prepareStatement(sql);

        ps.setInt(1, mno);

        @Cleanup ResultSet rs = ps.executeQuery();

        if( ! rs.next() ){
            return Optional.empty();
        }

        MsgVO detail = MsgVO.builder()
                .mno(rs.getInt("mno"))
                .sender(rs.getString("sender"))
                .title(rs.getString("title"))
                .content(rs.getString("content"))
                .senddate(rs.getTimestamp("senddate"))
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

        ps.setString(1, receiver);
        ps.setString(2, title);
        ps.setString(3, content);

        @Cleanup ResultSet rs = ps.executeQuery();

        if( ! rs.next() ){
            throw new Exception();
        }

        String sender = rs.getString("sender");

        // insert
        String query = """
                insert into tbl_message (sender, receiver, title, content)
                values (?, ?, ?, ?)
                """;

        @Cleanup PreparedStatement pst = con.prepareStatement(query);
        pst.setString(1, sender);
        pst.setString(2, receiver);
        pst.setString(3, title);
        pst.setString(4, content);

        int count = pst.executeUpdate();

        if (count != 1) {
            throw new Exception();
        }

        pst.close();

        pst = con.prepareStatement("select last_insert_id()");

        @Cleanup ResultSet rst = pst.executeQuery();

        rs.next();

        Integer mno = rst.getInt("mno");

        return mno;

    }
}
