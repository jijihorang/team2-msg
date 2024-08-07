package org.example.team2msg.msg.message.dao;

import lombok.Cleanup;
import lombok.extern.log4j.Log4j2;
import org.example.team2msg.common.ConnectionUtil;
import org.example.team2msg.msg.message.MsgVO;

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
                """
                ;

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

        updateReadStatus(mno);

        return Optional.of(detail);
    }

    private void updateReadStatus(Integer mno) throws Exception {
        String updateSql = """
            update tbl_message
            set 
                is_read = true
            where 
                mno = ?
        """;

        @Cleanup Connection con = ConnectionUtil.INSTANCE.getDs().getConnection();
        @Cleanup PreparedStatement ps = con.prepareStatement(updateSql);

        ps.setInt(1, mno);
        ps.executeUpdate();
    }


    // 메시지 전송
    public Integer sendMessage(MsgVO msg) throws Exception {
        String query = """
            insert into tbl_message (receiver, title, content, sender, senddate, is_read, is_broadcast)
            values (?, ?, ?, ?, NOW(), ?, ?)
            """;

        @Cleanup Connection con = ConnectionUtil.INSTANCE.getDs().getConnection();
        @Cleanup PreparedStatement pst = con.prepareStatement(query);

        pst.setString(1, msg.getReceiver());
        pst.setString(2, msg.getTitle());
        pst.setString(3, msg.getContent());
        pst.setString(4, msg.getSender());
        pst.setBoolean(5, msg.isIs_read());
        pst.setBoolean(6, msg.isIs_broadcast());

        int count = pst.executeUpdate();

        if (count != 1) {
            throw new Exception("Message sending failed");
        }

        pst = con.prepareStatement("select last_insert_id()");
        @Cleanup ResultSet rst = pst.executeQuery();

        rst.next();
        return rst.getInt(1);
    }

    // 학생 목록 가져오기
    public List<String> getStudentList() throws Exception {
        String sql = "SELECT sid FROM tbl_student";

        @Cleanup Connection con = ConnectionUtil.INSTANCE.getDs().getConnection();
        @Cleanup PreparedStatement ps = con.prepareStatement(sql);
        @Cleanup ResultSet rs = ps.executeQuery();

        List<String> studentList = new ArrayList<>();

        while (rs.next()) {
            studentList.add(rs.getString("sid"));
        }

        return studentList;
    }

    // 교수 목록 가져오기
    public List<String> getProfessorList() throws Exception {
        String sql = "SELECT pid FROM tbl_professor";

        @Cleanup Connection con = ConnectionUtil.INSTANCE.getDs().getConnection();
        @Cleanup PreparedStatement ps = con.prepareStatement(sql);
        @Cleanup ResultSet rs = ps.executeQuery();

        List<String> professorList = new ArrayList<>();

        while (rs.next()) {
            professorList.add(rs.getString("pid"));
        }

        return professorList;
    }

    // 받은 쪽지 리스트
    public List<MsgVO> getReceivedMessages(String receiver) throws Exception {
        String query = """
            select mno, sender, receiver, title, content, senddate, is_read, is_broadcast
            from tbl_message
            where receiver = ?
            order by senddate desc
            """;

        @Cleanup Connection con = ConnectionUtil.INSTANCE.getDs().getConnection();
        @Cleanup PreparedStatement pst = con.prepareStatement(query);

        pst.setString(1, receiver);

        @Cleanup ResultSet rs = pst.executeQuery();

        List<MsgVO> messages = new ArrayList<>();

        while (rs.next()) {
            MsgVO msg = MsgVO.builder()
                    .mno(rs.getInt("mno"))
                    .sender(rs.getString("sender"))
                    .receiver(rs.getString("receiver"))
                    .title(rs.getString("title"))
                    .content(rs.getString("content"))
                    .senddate(rs.getTimestamp("senddate"))
                    .is_read(rs.getBoolean("is_read"))
                    .is_broadcast(rs.getBoolean("is_broadcast"))
                    .build();

            messages.add(msg);
        }

        return messages;
    }

    // 보낸 쪽지 리스트
    public List<MsgVO> getSentMessages(String sender) throws Exception {
        String query = """
            select mno, sender, receiver, title, content, senddate, is_read, is_broadcast
            from tbl_message
            where sender = ?
            order by senddate desc
            """;

        @Cleanup Connection con = ConnectionUtil.INSTANCE.getDs().getConnection();
        @Cleanup PreparedStatement pst = con.prepareStatement(query);

        pst.setString(1, sender);

        @Cleanup ResultSet rs = pst.executeQuery();

        List<MsgVO> messages = new ArrayList<>();

        while (rs.next()) {
            MsgVO msg = MsgVO.builder()
                    .mno(rs.getInt("mno"))
                    .sender(rs.getString("sender"))
                    .receiver(rs.getString("receiver"))
                    .title(rs.getString("title"))
                    .content(rs.getString("content"))
                    .senddate(rs.getTimestamp("senddate"))
                    .is_read(rs.getBoolean("is_read"))
                    .is_broadcast(rs.getBoolean("is_broadcast"))
                    .build();

            messages.add(msg);
        }

        return messages;
    }

}
