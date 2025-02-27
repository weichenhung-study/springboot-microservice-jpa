package com.ntou.db.api.findbycidandcardtypeisactivated;

import com.ntou.db.cuscredit.CuscreditSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GetActivatedCardHolderController {

    @Autowired
    CuscreditSvc cuscreditSvc;

    @GetMapping("GetActivatedCardHolder")
    public ResponseEntity<GetActivatedCardHolderRes> doController(
            @RequestParam("cid") String cid,
            @RequestParam("cardType") String cardType,
            @RequestParam("cardNum") String cardNum,
            @RequestParam("securityCode") String securityCode) throws Exception {
        GetActivatedCardHolderReq req = new GetActivatedCardHolderReq();
        req.setCid(cid);
        req.setCardType(cardType);
        req.setCardNum(cardNum);
        req.setSecurityCode(securityCode);
        return new GetActivatedCardHolder().doAPI(req,cuscreditSvc);
    }
}