//package org.example.team2msg.msg;
//
//import lombok.Cleanup;
//import lombok.extern.log4j.Log4j2;
//import org.example.team2msg.common.ConnectionUtil;
//
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.util.Optional;
//
//@Log4j2
//public enum MsgDAO {
//    INSTANCE;
//
//    public int checkExist(String sid, String smail) throws Exception {
//
//        String query = "SELECT COUNT(*) FROM tbl_student WHERE (sid = ? or smail=?)";
//
//        @Cleanup Connection connection = ConnectionUtil.INSTANCE.getDs().getConnection();
//        @Cleanup PreparedStatement ps = connection.prepareStatement(query);
//        ps.setString(1, sid);
//        ps.setString(2, smail);
//        @Cleanup ResultSet rs = ps.executeQuery();
//
//        rs.next();
//
//        return rs.getInt(1);
//
//    }
//
//    public Optional<StudVO> get(String word, String pw) throws Exception {
//
//        String query = """
//                select * from tbl_student
//                where
//                    (sid = ? or smail = ? )
//                  and
//                    upw = ?
//                  and
//                    delflag = false
//                """;
//
//        @Cleanup Connection con = ConnectionUtil.INSTANCE.getDs().getConnection();
//        @Cleanup PreparedStatement ps = con.prepareStatement(query);
//        ps.setString(1, word);
//        ps.setString(2, word);
//        ps.setString(3, pw);
//
//        @Cleanup ResultSet rs = ps.executeQuery();
//
//        if( ! rs.next() ){
//            return Optional.empty();
//        }
//        StudVO student = StudVO.builder()
//                .sno(rs.getInt("sno"))
//                .sid(rs.getString("sid"))
//                .spw(rs.getString("spw"))
//                .smail(rs.getString("smail"))
//                .delFlag(rs.getBoolean("delflag"))
//                .build();
//
//        return Optional.of(student);
//
//    }
//
//}
