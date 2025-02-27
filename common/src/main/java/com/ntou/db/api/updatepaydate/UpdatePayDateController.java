package com.ntou.db.api.updatepaydate;

import com.ntou.db.billofmonth.BillofmonthSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UpdatePayDateController {

    @Autowired
    BillofmonthSvc billofmonthSvc;

    @PutMapping("UpdatePayDate")
    public ResponseEntity<UpdatePayDateRes> doController(@RequestBody UpdatePayDateReq req) throws Exception {
        return new UpdatePayDate().doAPI(req, billofmonthSvc);
    }
}
