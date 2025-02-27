package com.ntou.db.api.findcusbill;

import com.ntou.db.billrecord.BillrecordSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FindCusBillController {

    @Autowired
    BillrecordSvc billrecordSvc;

    @GetMapping("FindCusBill")
    public ResponseEntity<FindCusBillRes> doController(
//            @QueryParam("cid") String cid,
//            @QueryParam("cardType") String cardType,
//            @QueryParam("payDate") String payDate
    ) throws Exception {
        FindCusBillReq req = new FindCusBillReq();
//        req.setCid(cid);
//        req.setCardType(cardType);
//        req.setPayDate(payDate);
        return new FindCusBill().doAPI(req, billrecordSvc);
    }
}
