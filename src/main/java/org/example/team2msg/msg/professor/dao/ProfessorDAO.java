package org.example.team2msg.msg.professor.dao;

import lombok.Cleanup;
import org.example.team2msg.common.ConnectionUtil;
import org.example.team2msg.msg.professor.ProfessorVO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Optional;

public enum ProfessorDAO {
    INSTANCE;

    public Optional<ProfessorVO> get(String word, String pw) throws Exception{

        String query = """
                select * from tbl_member
                where
                    (uid = ? or email = ?)
                and
                    upw = ?
                and
                    delflag = false
                """;

        @Cleanup Connection con = ConnectionUtil.INSTANCE.getDs().getConnection();
        @Cleanup PreparedStatement ps = con.prepareStatement(query);
        ps.setString(1,word);
        ps.setString(2,word);
        ps.setString(3,pw);

        @Cleanup ResultSet rs = ps.executeQuery();

        if(rs.next() == false){
            return Optional.empty();
        }
        ProfessorVO member = ProfessorVO.builder()
                .pno(rs.getInt("pno"))
                .pid(rs.getString("pid"))
                .ppw(rs.getString("ppw"))
                .pmail(rs.getString("pmail"))
                .delflag(rs.getBoolean("delflag"))
                .build();

        return Optional.of(member);
    }
}
