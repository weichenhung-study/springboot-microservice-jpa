package com.ntou.db.api.savecuscredit;

import com.ntou.db.cuscredit.CuscreditSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CreateCuscreditController {

    @Autowired
    CuscreditSvc cuscreditSvc;

    @PostMapping("CreateCuscredit")
    public ResponseEntity<CreateCuscreditRes> doController(@RequestBody CreateCuscreditReq req) throws Exception {
        return new CreateCuscredit().doAPI(req, cuscreditSvc);
    }
}