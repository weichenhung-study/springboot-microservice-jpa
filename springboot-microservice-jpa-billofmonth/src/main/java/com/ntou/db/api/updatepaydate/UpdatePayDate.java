package com.ntou.db.api.updatepaydate;

import com.ntou.db.billofmonth.Billofmonth;
import com.ntou.db.billofmonth.BillofmonthSvc;
import com.ntou.tool.Common;
import com.ntou.tool.ExecutionTimer;
import com.ntou.tool.DateTool;
import com.ntou.tool.ResTool;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static com.ntou.db.api.insertbill.InsertBillRC.*;


/** 繳交信用卡費 */
@Log4j2
public class UpdatePayDate {
    public ResponseEntity<UpdatePayDateRes> doAPI(UpdatePayDateReq req, BillofmonthSvc billofmonthSvc) throws Exception {
        ExecutionTimer.startStage(ExecutionTimer.ExecutionModule.APPLICATION.getValue());
		
		log.info(Common.API_DIVIDER + Common.START_B + Common.API_DIVIDER);
        log.info(Common.REQ + req);
        UpdatePayDateRes res = new UpdatePayDateRes();

        if(!req.checkReq())
            ResTool.regularThrow(res, VALIDATION_ERROR.getCode(), VALIDATION_ERROR.getContent(), req.getErrMsg());

        Billofmonth vo = setUpdatePayDate(req);
		
		ExecutionTimer.startStage(ExecutionTimer.ExecutionModule.DATABASE.getValue());
        Billofmonth updateCount = billofmonthSvc.updatePaid(vo);
        ExecutionTimer.endStage(ExecutionTimer.ExecutionModule.DATABASE.getValue());

		if(updateCount == null)
            ResTool.commonThrow(res, FAIL.getCode(), FAIL.getContent());

        ResTool.setRes(res, SUCCESS.getCode(), SUCCESS.getContent());

        log.info(Common.RES + res);
        log.info(Common.API_DIVIDER + Common.END_B + Common.API_DIVIDER);
        
		ExecutionTimer.endStage(ExecutionTimer.ExecutionModule.APPLICATION.getValue());
        ExecutionTimer.exportTimings(this.getClass().getSimpleName() + "_" + DateTool.getYYYYmmDDhhMMss() + ".txt");
		return ResponseEntity.status(HttpStatus.OK).body(res);
    }
    private Billofmonth setUpdatePayDate(UpdatePayDateReq req){
        Billofmonth vo = new Billofmonth();
//        vo.setCid(req.getCid());
//        vo.setCardType(req.getCardType());
        vo.setPayDate(DateTool.getDateTime());
        vo.setPaidAmount(req.getPayAmt());
        vo.setBillMonth(req.getPayDate());
        vo.setUuid(req.getUuid());
        return vo;
    }
}
