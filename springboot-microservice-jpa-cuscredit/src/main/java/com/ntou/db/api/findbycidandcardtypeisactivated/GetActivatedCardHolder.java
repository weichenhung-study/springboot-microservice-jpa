package com.ntou.db.api.findbycidandcardtypeisactivated;

import com.ntou.db.cuscredit.Cuscredit;
import com.ntou.db.cuscredit.CuscreditSvc;
import com.ntou.tool.Common;
import com.ntou.tool.ExecutionTimer;
import com.ntou.tool.DateTool;
import com.ntou.tool.ResTool;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/** 查詢有效卡片持有者資料 */
@Log4j2
public class GetActivatedCardHolder {
    public ResponseEntity<GetActivatedCardHolderRes> doAPI(GetActivatedCardHolderReq req, CuscreditSvc cuscreditSvc) throws Exception {
        ExecutionTimer.startStage(ExecutionTimer.ExecutionModule.APPLICATION.getValue());

		log.info(Common.API_DIVIDER + Common.START_B + Common.API_DIVIDER);
        log.info(Common.REQ + req);
        GetActivatedCardHolderRes res = new GetActivatedCardHolderRes();

        if(!req.checkReq())
            ResTool.regularThrow(res, GetActivatedCardHolderRC.VALIDATION_ERROR.getCode(), GetActivatedCardHolderRC.VALIDATION_ERROR.getContent(), req.getErrMsg());
		
		ExecutionTimer.startStage(ExecutionTimer.ExecutionModule.DATABASE.getValue());
        Cuscredit voCuscredit = cuscreditSvc.findCardHolderActivated(
                req.getCid(), req.getCardType(), req.getCardNum(), req.getSecurityCode());
        ExecutionTimer.endStage(ExecutionTimer.ExecutionModule.DATABASE.getValue());

		if(voCuscredit == null)
            ResTool.commonThrow(res, GetActivatedCardHolderRC.NODATA.getCode(), GetActivatedCardHolderRC.NODATA.getContent());

        res.setResult(voCuscredit);

        ResTool.setRes(res, GetActivatedCardHolderRC.SUCCESS.getCode(), GetActivatedCardHolderRC.SUCCESS.getContent());

        log.info(Common.RES + res);
        log.info(Common.API_DIVIDER + Common.END_B + Common.API_DIVIDER);

		ExecutionTimer.endStage(ExecutionTimer.ExecutionModule.APPLICATION.getValue());
        ExecutionTimer.exportTimings(this.getClass().getSimpleName() + "_" + DateTool.getYYYYmmDDhhMMss() + ".txt");
		return ResponseEntity.status(HttpStatus.OK).body(res);
    }
}
