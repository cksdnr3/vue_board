package com.example.board.repository.board;


import com.example.board.entity.board.VueBoard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

// @Repository
// 해당 클래스를 자동으로 bean으로 등록
@Repository
public class VueBoardRepository {

    // @Autowired
    // DataSource라는 Type에 맞는 bean을 찾아서 setter parameter에 주입
    // 현재 프로젝트는 yaml파일에 datasource가 정의되어 있다.
    @Autowired
    // jdbcTemplate객체를 생성하여 이 객체에 DataSource를 주입
    // jdbcTemplate이란?
    // JDBC API를 이용하려면 DB연동에 필요한 Connection 객체와 쿼리 실행을 위한 PreparedStatement객체를 생성해야한다.
    // 그리고 쿼리 실행 후에는 finally 블록에서 ResultSe, PreparedStatement, Connection 타입 객체들을 close()로 닫는다.
    // DB연동을 할때 비슷한 코드가 구조적으로 박복되는데, 이를 줄이기 위해 jdbcTemplate 패턴을 사용한다.
    private JdbcTemplate jdbcTemplate;

    public void create(VueBoard board) throws Exception {
        String query = "insert into board (title, content, writer) values (?, ?, ?)";

        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(
            new PreparedStatementCreator() {
                // preparedStatement 는 statement와 달리 캐시사용을 한다. 보안적으로 statement보다 뛰어나기 때문에 권장함.

                @Override
                // DB연동에 필요한 Connection 객체
                // preparedStatement 란
                // SQL문을 표현하는 statement객체는 실행시 sql 명령어를 지정하여 여러 sql문을 하나의 statement객체로 수행이 가능하다.(재사용 가능)
                // 하지만 preparedStatement는 객체 생성시에 지정된 sql명령어만을 실행할 수 있다.(재활용 불가)
                // SQL문에 변수가 들어갈 자리는 '?'로 표시한다. ? 에 대응되는 값을 지정할 때는 setXXX 메소드를 통해 설정한다.
                // Connection 객체의 reparedStatement(sqlquery) 를 통해 생성된다.
                public PreparedStatement createPreparedStatement(Connection con)
                        throws SQLException {
                    PreparedStatement ps = con.prepareStatement(query, new String[] {"boardNo"});
                    // parameterIndex 는 query의 ? 의  index를 말한다.
                    ps.setString(1, board.getTitle());
                    ps.setString(2, board.getContent());
                    ps.setString(3, board.getWriter());
                    return ps;
                }
            }, keyHolder);

        board.setBoardNo(keyHolder.getKey().longValue());
    }

    public VueBoard read(Long boardNo) throws Exception {

        // DAO에서는 CRUD API를 제공 -> DB에 접근해서 record를 object로 넘겨준다.
        // 즉, SQL의 결과(record type)을 객체 (object type = entity)에 매핑하여 결과를 리턴한다.
        List<VueBoard> results = jdbcTemplate.query(
            "select board_no, title, content, writer, reg_date " +
                    "from board where board_no = ?",
            new RowMapper<VueBoard>() {
                @Override
                // ResultSet에는 DB에서 읽어온 것(record)이 담긴다.
                public VueBoard mapRow(ResultSet rs, int rowNum)
                        throws SQLException {

                    // 매핑 과정: 개발자가 정의해야함
                    VueBoard board = new VueBoard();

                    board.setBoardNo(rs.getInt("board_no"));
                    board.setTitle(rs.getString("title"));
                    board.setContent(rs.getString("content"));
                    board.setWriter(rs.getString("writer"));
                    board.setRegDate(rs.getDate("reg_date"));

                    System.out.println("VueBoardRepository: " + board);

                    return board;
                }
            }, boardNo
        );

        return results.isEmpty() ? null : results.get(0);
    }

    public void update(VueBoard board) throws Exception {
        String query = "update board set title = ?, content = ? " +
                "where board_no = ?";
        jdbcTemplate.update(query, board.getTitle(),
                board.getContent(), board.getBoardNo());
    }

    public void delete(Long boardNo) throws Exception {
        String query = "delete from board where board_no = ?";
        jdbcTemplate.update(query, boardNo);
    }

    public List<VueBoard> list() throws Exception {
        List<VueBoard> results = jdbcTemplate.query(
            "select board_no, title, content, writer, reg_date from board " +
                    "where board_no > 0 order by board_no desc, reg_date desc",
            new RowMapper<VueBoard>() {
                @Override
                public VueBoard mapRow(ResultSet rs, int rowNum) throws SQLException {
                    VueBoard board = new VueBoard();

                    board.setBoardNo(rs.getInt("board_no"));
                    board.setTitle(rs.getString("title"));
                    board.setContent(rs.getString("content"));
                    board.setWriter(rs.getString("writer"));
                    board.setRegDate(rs.getDate("reg_date"));
                    return board;
                }
            }
        );
        return results;
    }
}