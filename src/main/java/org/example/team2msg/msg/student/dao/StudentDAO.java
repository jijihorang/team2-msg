package org.example.team2msg.msg.student.dao;

import lombok.Cleanup;
import org.example.team2msg.common.ConnectionUtil;
import org.example.team2msg.msg.student.vo.StudentVO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Optional;

public enum StudentDAO {
    INSTANCE;

    // 아이디가 틀렸는지 비번이 틀렸는지 모르기 때문에 Optional 사용
    public Optional<StudentVO> get(String word, String pw) throws Exception {

        String query = "select * from tbl_student" +
                "                where" +
                "                    (sid = ? or smail= ?)" +
                "                and" +
                "                    spw = ?" +
                "                and" +
                "                    delflag = false";


        @Cleanup Connection con = ConnectionUtil.INSTANCE.getDs().getConnection();
        @Cleanup PreparedStatement ps = con.prepareStatement(query);

        // get(String word, String pw)에서 ? 해당하는 값 3개 넣어주기 !
        ps.setString(1, word);
        ps.setString(2, word);
        ps.setString(3, pw);

        @Cleanup ResultSet rs = ps.executeQuery();

        // 쿼리는 실행되었으나 값이 틀려서 안나올 수 있기 때문에 끊어야 함 !
        if (!rs.next()) {
            return Optional.empty(); // Optional은 null 대신 사용
        }

        // 값 뽑기
        StudentVO member = StudentVO.builder()
                .sno(rs.getInt("sno"))
                .sid(rs.getString("sid"))
                .spw(rs.getString("spw")) // 굳이 값을 안가져 와도 되지만 패스워드 변경하는 경우가 있기 때문에 사용
                .smail(rs.getString("smail"))
                .delFlag(rs.getBoolean("delflag"))
                .build();

        return Optional.of(member); // Optional에 담아서 보냄
    }


}
