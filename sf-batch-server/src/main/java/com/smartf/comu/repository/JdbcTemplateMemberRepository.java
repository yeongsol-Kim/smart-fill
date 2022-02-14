//package com.smartf.comu.repository;//package hello.hellospring.repository;
//
//import hello.hellospring.domain.Member;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.jdbc.core.RowMapper;
//
//import javax.sql.DataSource;
//import java.util.List;
//import java.util.Optional;
//
//public class JdbcTemplateMemberRepository implements MemberRepository {
//    private  final JdbcTemplate jdbcTemplate;
//
//    @Autowired
//    public JdbcTemplateMemberRepository(DataSource dataSource) {
//        jdbcTemplate = new JdbcTemplate(dataSource);
//    }
//
//
//    @Override
//    public Member save(Member member) {
//        return null;
//    }
//
//    @Override
//    public Optional<Member> findById(Long id) {
//        return Optional.empty();
//    }
//
//    @Override
//    public Optional<Member> findByEmail(String email) {
//        return Optional.empty();
//    }
//
//    @Override
//    public List<Member> findAll() {
//        return jdbcTemplate.query("select * from users", memberRowMapper());
//    }
//
//    private RowMapper<Member> memberRowMapper() {
//        return (rs, rowNum) -> {
//            Member member = new Member();
//            member.setId(rs.getLong("id"));
//            member.setUserName(rs.getString("username"));
//            member.setEmail(rs.getString("email"));
//            member.setCar_id(rs.getString("car_number"));
//            member.setPhone_number(rs.getString("phone_number"));
//            return member;
//        };
//    }
//}
