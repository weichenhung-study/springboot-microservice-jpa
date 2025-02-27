package com.ntou.db.api.updateactivationrecord;

import com.ntou.db.api.findbycidandcardtype.GetCardHolderRes;
import com.ntou.db.cuscredit.CuscreditSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UpdateActivationRecordController {

    @Autowired
    CuscreditSvc cuscreditSvc;

    @PutMapping("UpdateActivationRecord")
    public ResponseEntity<UpdateActivationRecordRes> doController(@RequestBody UpdateActivationRecordReq req) throws Exception {
        return new UpdateActivationRecord().doAPI(req, cuscreditSvc);
    }
}