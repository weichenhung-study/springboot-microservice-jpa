package com.ntou.db.api.updatecardapprovalstatus;

import com.ntou.db.cuscredit.Cuscredit;
import com.ntou.db.cuscredit.CuscreditSvc;
import com.ntou.tool.Common;
import com.ntou.tool.ExecutionTimer;
import com.ntou.tool.DateTool;
import com.ntou.tool.ResTool;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static com.ntou.db.api.updatecardapprovalstatus.UpdateCardApprovalStatusRC.*;

/**  審核信用卡 更新信用卡狀態 */
@Log4j2
public class UpdateCardApprovalStatus {
    public ResponseEntity<UpdateCardApprovalStatusRes> doAPI(UpdateCardApprovalStatusReq req, CuscreditSvc cuscreditSvc) throws Exception {
        ExecutionTimer.startStage(ExecutionTimer.ExecutionModule.APPLICATION.getValue());

		log.info(Common.API_DIVIDER + Common.START_B + Common.API_DIVIDER);
        log.info(Common.REQ + req);
        UpdateCardApprovalStatusRes res = new UpdateCardApprovalStatusRes();

        if (!req.checkReq())
            ResTool.regularThrow(res, VALIDATION_ERROR.getCode(), UpdateCardApprovalStatusRC.VALIDATION_ERROR.getContent(), req.getErrMsg());
		
		ExecutionTimer.startStage(ExecutionTimer.ExecutionModule.DATABASE.getValue());
        Cuscredit updateCount = cuscreditSvc.updateCardApprovalStatus(voCuscreditUpdate(req));
		ExecutionTimer.endStage(ExecutionTimer.ExecutionModule.DATABASE.getValue());

		if (updateCount == null)
            ResTool.setRes(res, UPDATE_FAIL.getCode(), UPDATE_FAIL.getContent());

        ResTool.setRes(res, SUCCESS.getCode(), SUCCESS.getContent());

        log.info(Common.RES + res);
        log.info(Common.API_DIVIDER + Common.END_B + Common.API_DIVIDER);
        
		ExecutionTimer.endStage(ExecutionTimer.ExecutionModule.APPLICATION.getValue());
        ExecutionTimer.exportTimings(this.getClass().getSimpleName() + "_" + DateTool.getYYYYmmDDhhMMss() + ".txt");
		return ResponseEntity.status(HttpStatus.OK).body(res);
    }
    private Cuscredit voCuscreditUpdate(UpdateCardApprovalStatusReq req){
        Cuscredit vo = new Cuscredit();
        vo.setCid  		           (req.getCid  	 ());
        vo.setCardType             (req.getCardType ());
        vo.setCardApprovalStatus   (req.getCardApprovalStatus());
        vo.setCardNum              (req.getCardNum           ());
        vo.setSecurityCode         (req.getSecurityCode      ());
        vo.setStatus               (req.getStatus            ());
        vo.setApplyRemark          (req.getApplyRemark       ());
        return vo;
    }
}
