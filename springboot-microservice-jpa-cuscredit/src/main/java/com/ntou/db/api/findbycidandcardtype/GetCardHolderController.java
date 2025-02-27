package com.ntou.db.api.findbycidandcardtype;

import com.ntou.db.cuscredit.CuscreditSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GetCardHolderController {

    @Autowired
    CuscreditSvc cuscreditSvc;

    @GetMapping("GetCardHolder")
    public ResponseEntity<GetCardHolderRes> doController(
            @RequestParam("cid") String cid,
            @RequestParam("cardType") String cardType) throws Exception {
        GetCardHolderReq req = new GetCardHolderReq();
        req.setCid(cid);
        req.setCardType(cardType);
        return new GetCardHolder().doAPI(req, cuscreditSvc);
    }
}