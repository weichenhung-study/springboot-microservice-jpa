package com.ntou.db.cuscredit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CuscreditSvc {
    @Autowired
    private CuscreditRepo repo;

    public Cuscredit selectKey(String cid, String cardType) {
        return repo.findByCidAndCardType(cid, cardType);
    }
    public Cuscredit findCardHolderActivated(String cid, String cardType, String cardNum, String securityCode) {
        Cuscredit cuscreditVO  = repo.findByCidAndCardType(cid, cardType);
        if(cuscreditVO.getCardNum().equals(cardNum) && cuscreditVO.getSecurityCode().equals(securityCode)) {
            return cuscreditVO;
        }
        return null;
    }
    public Cuscredit saveCuscredit(Cuscredit model) {
        try {
            return repo.save(model);
        } catch (Exception e) {
            return null;
        }
    }

    public Cuscredit updateCardApprovalStatus(Cuscredit vo) {
        try {
            Cuscredit condition = repo.findByCidAndCardType(vo.getCid(), vo.getCardType());
            condition.setCardApprovalStatus(vo.getCardApprovalStatus());
            condition.setCardNum(vo.getCardNum());
            condition.setSecurityCode(vo.getSecurityCode());
            condition.setApplyRemark(vo.getApplyRemark());
            condition.setStatus(vo.getStatus());
            return repo.save(condition);
        } catch (Exception e) {
            return null;
        }
    }
    public Cuscredit updateActivationRecord(Cuscredit vo) {
        try {
            Cuscredit condition = repo.findByCidAndCardType(vo.getCid(), vo.getCardType());
            condition.setActivationRecord(vo.getActivationRecord());
            return repo.save(condition);
        } catch (Exception e) {
            return null;
        }
    }
}