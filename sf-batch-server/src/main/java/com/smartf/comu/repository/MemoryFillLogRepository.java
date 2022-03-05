//package com.smartf.comu.repository;
//
//import com.smartf.comu.domain.FillLog;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//public class MemoryFillLogRepository implements FillLogRepository {
//
//    private static Map<Long, FillLog> store = new HashMap<>();
//    private static long sequence = 0L;
//
//    @Override
//    public FillLog save(FillLog fillLog) {
//        fillLog.setId(++sequence);
//        store.put(fillLog.getId(), fillLog);
//        return fillLog;
//    }
//
//    @Override
//    public List<FillLog> findAll() {
//        return new ArrayList<>(store.values());
//    }
//
//}
