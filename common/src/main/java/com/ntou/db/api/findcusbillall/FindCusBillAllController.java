package com.ntou.db.api.findcusbillall;

import com.ntou.db.billrecord.BillrecordSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FindCusBillAllController {

    @Autowired
    BillrecordSvc billrecordSvc;

    @GetMapping("FindCusBillAll")
    public ResponseEntity<FindCusBillAllRes> doController(
            @RequestParam("cid") String cid,
            @RequestParam("cardType") String cardType,
            @RequestParam("startDate") String startDate,
            @RequestParam("endDate") String endDate
    ) throws Exception {
        FindCusBillAllReq req = new FindCusBillAllReq();
        req.setCid(cid);
        req.setCardType(cardType);
        req.setStartDate(startDate);
        req.setEndDate(endDate);
        return new FindCusBillAll().doAPI(req, billrecordSvc);
    }
}
