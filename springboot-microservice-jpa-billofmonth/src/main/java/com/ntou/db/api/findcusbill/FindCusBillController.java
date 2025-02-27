package com.ntou.db.api.findcusbill;

import com.ntou.db.billofmonth.BillofmonthSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FindCusBillController {

    @Autowired
    BillofmonthSvc billofmonthSvc;

    @GetMapping("FindCusBill")
    public ResponseEntity<FindCusBillRes> doController(
            @RequestParam("cid") String cid,
            @RequestParam("cardType") String cardType,
            @RequestParam("payDate") String payDate
    ) throws Exception {
        FindCusBillReq req = new FindCusBillReq();
        req.setCid(cid);
        req.setCardType(cardType);
        req.setPayDate(payDate);
        return new FindCusBill().doAPI(req, billofmonthSvc);
    }
}
