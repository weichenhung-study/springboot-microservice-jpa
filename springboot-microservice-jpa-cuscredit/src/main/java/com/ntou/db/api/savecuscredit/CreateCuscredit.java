package com.ntou.db.api.savecuscredit;

import com.ntou.db.cuscredit.Cuscredit;
import com.ntou.db.cuscredit.CuscreditSvc;
import com.ntou.db.cuscredit.CuscreditTool;
import com.ntou.tool.Common;
import com.ntou.tool.ExecutionTimer;
import com.ntou.tool.DateTool;
import com.ntou.tool.ResTool;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static com.ntou.db.api.savecuscredit.CreateCuscreditRC.*;

/** 新增客戶信用卡資訊 */
@Log4j2
public class CreateCuscredit {
    public ResponseEntity<CreateCuscreditRes> doAPI(CreateCuscreditReq req,CuscreditSvc cuscreditSvc) throws Exception {
		ExecutionTimer.startStage(ExecutionTimer.ExecutionModule.APPLICATION.getValue());

		log.info(Common.API_DIVIDER + Common.START_B + Common.API_DIVIDER);
        log.info(Common.REQ + req);
        CreateCuscreditRes res = new CreateCuscreditRes();

        if(!req.checkReq())
            ResTool.regularThrow(res, VALIDATION_ERROR.getCode(), CreateCuscreditRC.VALIDATION_ERROR.getContent(), req.getErrMsg());
		
		ExecutionTimer.startStage(ExecutionTimer.ExecutionModule.DATABASE.getValue());
        Cuscredit cusDateBill = cuscreditSvc.selectKey(
            req.getCid(), req.getCardType());
		ExecutionTimer.endStage(ExecutionTimer.ExecutionModule.DATABASE.getValue());

        if(cusDateBill!=null)
            ResTool.commonThrow(res, DUPLICATE_APPLICATION.getCode(), DUPLICATE_APPLICATION.getContent());

        Cuscredit bInsertCusDateBill = cuscreditSvc.saveCuscredit(voCuscreditInsert(req));
        if(bInsertCusDateBill == null)
            ResTool.commonThrow(res, INSERT_FAIL.getCode(), INSERT_FAIL.getContent());

        ResTool.setRes(res, SUCCESS.getCode(), SUCCESS.getContent());

        log.info(Common.RES + res);
        log.info(Common.API_DIVIDER + Common.END_B + Common.API_DIVIDER);
        
		ExecutionTimer.endStage(ExecutionTimer.ExecutionModule.APPLICATION.getValue());
        ExecutionTimer.exportTimings(this.getClass().getSimpleName() + "_" + DateTool.getYYYYmmDDhhMMss() + ".txt");
		return ResponseEntity.status(HttpStatus.OK).body(res);
    }
    private Cuscredit voCuscreditInsert(CreateCuscreditReq req){
        Cuscredit vo = new Cuscredit();
        vo.setChName               (req.getChName               ());
        vo.setEnName               (req.getEnName               ());
        vo.setCid  			       (req.getCid  			    ());
        vo.setCidReissueDate       (req.getCidReissueDate       ());
        vo.setCidReissueLocation   (req.getCidReissueLocation   ());
        vo.setCidReissueStatus     (req.getCidReissueStatus     ());
        vo.setBirthDate            (req.getBirthDate            ());
        vo.setMaritalStatus        (req.getMaritalStatus        ());
        vo.setEducation            (req.getEducation            ());
        vo.setCurrentResidentialAdd(req.getCurrentResidentialAdd());
        vo.setResidentialAdd       (req.getResidentialAdd       ());
        vo.setCellphone            (req.getCellphone            ());
        vo.setEmail                (req.getEmail                ());
        vo.setCompanyName          (req.getCompanyName          ());
        vo.setCompanyIndustry      (req.getCompanyIndustry      ());
        vo.setOccupation           (req.getOccupation           ());
        vo.setDepartment           (req.getDepartment           ());
        vo.setJobTitle             (req.getJobTitle             ());
        vo.setDateOfEmployment     (req.getDateOfEmployment     ());
        vo.setCompanyAddress       (req.getCompanyAddress       ());
        vo.setCompanyPhoneNum      (req.getCompanyPhoneNum      ());
        vo.setAnnualIncome         (req.getAnnualIncome         ());
        vo.setCardApprovalStatus   (CuscreditTool.CardApprovalStatus.PROCESS.getValue());
        vo.setActivationRecord     (CuscreditTool.ActivationRecord.NOTOPEN.getValue());
        vo.setEventCode            (req.getEventCode            ());
        vo.setRegidate 		       (DateTool.getDateTime());
        vo.setIssuing_bank 	       ("803");
        vo.setCardType             (req.getCardType             ());
        vo.setRemark			   (req.getRemark			    ());
        return vo;
    }
}
