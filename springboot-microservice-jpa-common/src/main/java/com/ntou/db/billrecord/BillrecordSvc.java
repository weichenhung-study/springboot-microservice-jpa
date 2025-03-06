package com.ntou.db.billrecord;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BillrecordSvc {

    @Autowired
    private BillrecordRepo repo;

    public List<Billrecord> selectCusBill(Billrecord vo, String sDate, String eDate) {
        return repo.findByBuyDateBetweenAndDisputedFlag(sDate, eDate, vo.getDisputedFlag());
    }
    public Billrecord saveBillrecord(Billrecord model) {
        try {
            return repo.save(model);
        } catch (Exception e) {
            return null; // 失敗時回傳 null
        }
    }

    public List<Billrecord> selectCusBillAll(Billrecord vo, String sDate, String eDate) {
        return repo.findByCidAndCardTypeAndBuyDateBetweenOrderByBuyDateDesc(vo.getCid(), vo.getCardType(), sDate, eDate);
    }
    public Billrecord findKey(String uuid) {
        return repo.findByUuid(uuid);
    }

    public Billrecord updateDisputedFlag(Billrecord vo) {
        try{
            Billrecord condition = repo.findByUuid(vo.getUuid());
            condition.setDisputedFlag(vo.getDisputedFlag());
            return repo.save(condition);
        } catch (Exception e) {
            return null;
        }
//        return repo.findByCidAndCardType(vo.getCid(), vo.getCardType())
//                .map(record -> {
//                    record.setDisputedFlag(vo.getDisputedFlag());
//                    return repo.save(record);
//                })
//                .orElse(null); // 找不到資料時回傳 null
    }
}