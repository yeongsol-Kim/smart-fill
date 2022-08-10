package com.smartf.comu.service;

import com.smartf.comu.domain.Notice;
import com.smartf.comu.repository.NoticeRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class NoticeService {

    private final NoticeRepository noticeRepository;

    public NoticeService(NoticeRepository noticeRepository) {
        this.noticeRepository = noticeRepository;
    }

    // 공지 검색
    public Notice getNotice(Long id) {
        return noticeRepository.findById(id).orElse(null);
    }


    // 공지 목록
    public List<Notice> getNoticeList() {
        return noticeRepository.findAll(Sort.by(Sort.Direction.DESC, "writeDate"));
    }


    public void insertNotice(Notice notice) {
        noticeRepository.save(notice);
    }

    public void deleteNotice(Long id) {
        noticeRepository.deleteById(id);
    }

}
