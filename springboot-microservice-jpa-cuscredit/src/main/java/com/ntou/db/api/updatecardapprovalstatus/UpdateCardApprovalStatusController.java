package com.ntou.db.api.updatecardapprovalstatus;

import com.ntou.db.cuscredit.CuscreditSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UpdateCardApprovalStatusController {

    @Autowired
    CuscreditSvc cuscreditSvc;

    @PutMapping("UpdateCardApprovalStatus")
    public ResponseEntity<UpdateCardApprovalStatusRes> doController(@RequestBody UpdateCardApprovalStatusReq req) throws Exception {
        return new UpdateCardApprovalStatus().doAPI(req, cuscreditSvc);
    }
}