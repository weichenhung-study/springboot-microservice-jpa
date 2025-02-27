package com.ntou.db.api.insertcusdatebill;

import com.ntou.db.billrecord.BillrecordSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class InsertCusDateBillController {

    @Autowired
    BillrecordSvc billrecordSvc;

    @PostMapping("InsertCusDateBill")
    public ResponseEntity<InsertCusDateBillRes> doController(@RequestBody InsertCusDateBillReq req) throws Exception {
        return new InsertCusDateBill().doAPI(req, billrecordSvc);
    }
}
