package com.ntou.db.api.findcusbill;

import com.ntou.db.billrecord.Billrecord;
import com.ntou.db.billrecord.BillrecordSvc;
import com.ntou.tool.Common;
import com.ntou.tool.ExecutionTimer;
import com.ntou.tool.DateTool;
import com.ntou.tool.ResTool;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static com.ntou.db.api.findcusbill.FindCusBillRC.*;

/** 查詢該月帳單的所有客戶資料 */
@Log4j2
public class FindCusBill {
   public ResponseEntity<FindCusBillRes> doAPI(FindCusBillReq req, BillrecordSvc billrecordSvc) throws Exception {
        ExecutionTimer.startStage(ExecutionTimer.ExecutionModule.APPLICATION.getValue());

		log.info(Common.API_DIVIDER + Common.START_B + Common.API_DIVIDER);
        log.info(Common.REQ + req);
        FindCusBillRes res = new FindCusBillRes();

//        if(!req.checkReq())
//            ResTool.regularThrow(res, VALIDATION_ERROR.getCode(), VALIDATION_ERROR.getContent(), req.getErrMsg());
		
		ExecutionTimer.startStage(ExecutionTimer.ExecutionModule.DATABASE.getValue());
        List<Billrecord> billList = billrecordSvc
                .selectCusBill(voBillrecordSelect(), DateTool.getFirstDayOfMonth(), DateTool.getLastDayOfMonth());
		ExecutionTimer.endStage(ExecutionTimer.ExecutionModule.DATABASE.getValue());

		if(billList.isEmpty())
			ResTool.commonThrow(res, NODATA.getCode(), NODATA.getContent());

        res.setResult(billList);

        ResTool.setRes(res, SUCCESS.getCode(), SUCCESS.getContent());

        log.info(Common.RES + res);
        log.info(Common.API_DIVIDER + Common.END_B + Common.API_DIVIDER);
        
		ExecutionTimer.endStage(ExecutionTimer.ExecutionModule.APPLICATION.getValue());
        ExecutionTimer.exportTimings(this.getClass().getSimpleName() + "_" + DateTool.getYYYYmmDDhhMMss() + ".txt");
		return ResponseEntity.status(HttpStatus.OK).body(res);
    }
    private Billrecord voBillrecordSelect(){
        Billrecord vo = new Billrecord();
        vo.setDisputedFlag      ("00");
        return vo;
    }
}
