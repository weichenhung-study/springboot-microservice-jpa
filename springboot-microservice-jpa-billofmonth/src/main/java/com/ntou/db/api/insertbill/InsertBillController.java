package com.ntou.db.api.insertbill;

import com.ntou.db.billofmonth.BillofmonthSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class InsertBillController {

    @Autowired
    BillofmonthSvc billofmonthSvc;

    @PostMapping("InsertBill")
    public ResponseEntity<InsertBillRes> doController(@RequestBody InsertBillReq req) throws Exception {
        return new InsertBill().doAPI(req, billofmonthSvc);
    }
}
