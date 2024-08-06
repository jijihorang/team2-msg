package org.example.team2msg.msg.professor.dao;

import lombok.Cleanup;
import lombok.extern.log4j.Log4j2;
import org.example.team2msg.common.ConnectionUtil;
import org.example.team2msg.msg.professor.ProfessorVO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Optional;

@Log4j2
public enum ProfessorDAO {
    INSTANCE;

    public Optional<ProfessorVO> get(String word, String pw) throws Exception{

        String query = """
                select * from tbl_professor
                where
                    (pid = ? or pmail = ?)
                and
                    ppw = ?
                and
                    delFlag = false
                """;

        @Cleanup Connection con = ConnectionUtil.INSTANCE.getDs().getConnection();
        log.info("Database connection: {}", con);
        @Cleanup PreparedStatement ps = con.prepareStatement(query);
        ps.setString(1,word);
        ps.setString(2,word);
        ps.setString(3,pw);

        @Cleanup ResultSet rs = ps.executeQuery();

        if(rs.next() == false){
            log.info("Attempting to fetch professor with ID/Email: {} and Password: {}", word, pw);
            log.info("DB EMPTY:ERROR");
            return Optional.empty();
        }

        log.info("GET PROFESSOR DB INFO");
        ProfessorVO member = ProfessorVO.builder()
                .pno(rs.getInt("pno"))
                .pid(rs.getString("pid"))
                .ppw(rs.getString("ppw"))
                .pmail(rs.getString("pmail"))
                .delflag(rs.getBoolean("delFlag"))
                .build();

        return Optional.of(member);
    }
}
