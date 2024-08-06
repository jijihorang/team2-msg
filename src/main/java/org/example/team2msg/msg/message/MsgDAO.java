package org.example.team2msg.msg.message;

import lombok.Cleanup;
import lombok.extern.log4j.Log4j2;
import org.example.team2msg.common.ConnectionUtil;
import org.example.team2msg.msg.student.vo.StudentVO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.Optional;

@Log4j2
public enum MsgDAO {
    INSTANCE;

    MsgDAO(){

    }

    public List<MsgVO> list() throws Exception{

        String sql = "select mno, sender, title, senddate, is_read from tbl_message where sender in (select sid from tbl_student where delFlag = false)";

        @Cleanup Connection con = ConnectionUtil.INSTANCE.getDs().getConnection();
        @Cleanup PreparedStatement ps = con.prepareStatement(sql);

        @Cleanup ResultSet rs = ps.executeQuery();

        List<MsgVO> list = MsgVO.builder()
                .mno(rs.getInt("mno"))
                .sender(rs.getString("sender"))
                .title(rs.getString("title"))
                .senddate(rs.getTimestamp("senddate"))

    }



}
