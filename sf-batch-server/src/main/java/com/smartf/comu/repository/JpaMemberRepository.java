//package com.smartf.comu.repository;//package hello.hellospring.repository;
//
//import hello.hellospring.domain.Member;
//
//import javax.persistence.EntityManager;
//import java.util.List;
//import java.util.Optional;
//
//public class JpaMemberRepository implements MemberRepository {
//
//    private final EntityManager em;
//
//    public JpaMemberRepository(EntityManager em) {
//        this.em = em;
//    }
//
//    @Override
//    public Member save(Member member) {
//        em.persist(member);
//        return member;
//    }
//
//    @Override
//    public Optional<Member> findById(Long id) {
//        Member member = em.find(Member.class, id);
//        return Optional.ofNullable(member);
//    }
//
//
//    @Override
//    public Optional<Member> findByEmail(String email) {
//        return Optional.empty();
//    }
//
//    @Override
//    public List<Member> findAll() {
//        List<Member> result = em.createQuery("select m from Member m", Member.class).getResultList();
//        return result;
//    }
//}
