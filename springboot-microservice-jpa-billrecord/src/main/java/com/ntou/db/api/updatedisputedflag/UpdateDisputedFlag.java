package com.ntou.db.api.updatedisputedflag;

import com.ntou.db.billrecord.Billrecord;
import com.ntou.db.billrecord.BillrecordSvc;
import com.ntou.tool.Common;
import com.ntou.tool.ExecutionTimer;
import com.ntou.tool.DateTool;
import com.ntou.tool.ResTool;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static com.ntou.db.api.updatedisputedflag.UpdateDisputedFlagRC.*;

/** 爭議款項申請:上註記  */
@Log4j2
public class UpdateDisputedFlag {
    public ResponseEntity<UpdateDisputedFlagRes> doAPI(UpdateDisputedFlagReq req, BillrecordSvc billrecordSvc) throws Exception {
        ExecutionTimer.startStage(ExecutionTimer.ExecutionModule.APPLICATION.getValue());

		log.info(Common.API_DIVIDER + Common.START_B + Common.API_DIVIDER);
        log.info(Common.REQ + req);
        UpdateDisputedFlagRes res = new UpdateDisputedFlagRes();

        if(!req.checkReq())
            ResTool.regularThrow(res, VALIDATION_ERROR.getCode(), VALIDATION_ERROR.getContent(), req.getErrMsg());

		ExecutionTimer.startStage(ExecutionTimer.ExecutionModule.DATABASE.getValue());
        Billrecord updateResult = billrecordSvc
                .updateDisputedFlag(voBillrecordSelect(req));
		ExecutionTimer.endStage(ExecutionTimer.ExecutionModule.DATABASE.getValue());
	
        if(updateResult == null)
            ResTool.commonThrow(res, FAIL.getCode(), FAIL.getContent());
        ResTool.setRes(res, SUCCESS.getCode(), SUCCESS.getContent());

        log.info(Common.RES + res);
        log.info(Common.API_DIVIDER + Common.END_B + Common.API_DIVIDER);
        
		ExecutionTimer.endStage(ExecutionTimer.ExecutionModule.APPLICATION.getValue());
        ExecutionTimer.exportTimings(this.getClass().getSimpleName() + "_" + DateTool.getYYYYmmDDhhMMss() + ".txt");
		return ResponseEntity.status(HttpStatus.OK).body(res);
    }
    private Billrecord voBillrecordSelect(UpdateDisputedFlagReq req){
        Billrecord vo = new Billrecord();
        vo.setUuid		(req.getUuid());
        vo.setDisputedFlag("01");
        return vo;
    }
}
