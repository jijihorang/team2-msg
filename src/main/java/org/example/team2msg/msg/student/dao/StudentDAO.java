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

        String query = "SELECT * FROM tbl_student" +
                " WHERE (sid = ? OR smail = ?)" +
                " AND spw = ?" +
                " AND delFlag = false";

        @Cleanup Connection con = ConnectionUtil.INSTANCE.getDs().getConnection();
        @Cleanup PreparedStatement ps = con.prepareStatement(query);

        // get(String word, String pw)에서 ? 해당하는 값 3개 넣어주기
        ps.setString(1, word);
        ps.setString(2, word);
        ps.setString(3, pw);

        @Cleanup ResultSet rs = ps.executeQuery();

        // 쿼리는 실행되었으나 값이 틀려서 안나올 수 있기 때문에 끊어야 함
        if (!rs.next()) {
            return Optional.empty(); // Optional은 null 대신 사용
        }

        // 값 뽑기
        StudentVO member = StudentVO.builder()
                .sno(rs.getInt("sno"))
                .sid(rs.getString("sid"))
                .spw(rs.getString("spw")) // 굳이 값을 안가져 와도 되지만 패스워드 변경하는 경우가 있기 때문에 사용
                .smail(rs.getString("smail"))
                .delFlag(rs.getBoolean("delFlag"))
                .build();

        return Optional.of(member); // Optional에 담아서 보냄
    }

    // 회원가입 메서드
    public boolean register(String sid, String smail, String spw) throws Exception {
        String query = "INSERT INTO tbl_student (sid, smail, spw) VALUES (?, ?, ?)";

        StudentVO member = StudentVO.builder()
                .sid(sid)
                .smail(smail)
                .spw(spw)
                .build();

        @Cleanup Connection con = ConnectionUtil.INSTANCE.getDs().getConnection();
        @Cleanup PreparedStatement ps = con.prepareStatement(query);

        ps.setString(1, member.getSid());
        ps.setString(2, member.getSmail());
        ps.setString(3, member.getSpw());

        int rows = ps.executeUpdate();
        return rows > 0; // 삽입된 행이 있는지 확인하여 성공 여부 반환
    }

    // 중복 확인 메서드 추가
    public boolean isDuplicate(String sid, String smail) throws Exception {
        String query = "SELECT COUNT(*) FROM tbl_student WHERE sid = ? OR smail = ?";

        @Cleanup Connection con = ConnectionUtil.INSTANCE.getDs().getConnection();
        @Cleanup PreparedStatement ps = con.prepareStatement(query);

        ps.setString(1, sid);
        ps.setString(2, smail);

        @Cleanup ResultSet rs = ps.executeQuery();
        rs.next();
        return rs.getInt(1) > 0; // 중복된 결과가 있으면 true 반환
    }
}
