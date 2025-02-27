package com.ntou.db.api.updatedisputedflag;

import com.ntou.db.billrecord.BillrecordSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UpdateDisputedFlagController {

    @Autowired
    BillrecordSvc billrecordSvc;

    @PutMapping("UpdateDisputedFlag")
    public ResponseEntity<UpdateDisputedFlagRes> doController(@RequestBody UpdateDisputedFlagReq req) throws Exception {
        return new UpdateDisputedFlag().doAPI(req, billrecordSvc);
    }
}
