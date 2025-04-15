package com.ntou.db.api.findcusbill;

import com.ntou.db.billofmonth.Billofmonth;
import com.ntou.db.billofmonth.BillofmonthSvc;
import static com.ntou.db.api.findcusbill.FindCusBillRC.*;

import com.ntou.tool.Common;
import com.ntou.tool.ExecutionTimer;
import com.ntou.tool.DateTool;
import com.ntou.tool.ResTool;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;


/** 本期信用卡帳單查詢 */
@Log4j2
public class FindCusBill {
    public ResponseEntity<FindCusBillRes> doAPI(FindCusBillReq req, BillofmonthSvc billofmonthSvc) throws Exception {
        ExecutionTimer.startStage(ExecutionTimer.ExecutionModule.APPLICATION.getValue());

		log.info(Common.API_DIVIDER + Common.START_B + Common.API_DIVIDER);
        log.info(Common.REQ + req);
        FindCusBillRes res = new FindCusBillRes();

        if(!req.checkReq())
            ResTool.regularThrow(res, VALIDATION_ERROR.getCode(), VALIDATION_ERROR.getContent(), req.getErrMsg());

        Billofmonth vo = setUpdatePayDate(req);
		
		ExecutionTimer.startStage(ExecutionTimer.ExecutionModule.DATABASE.getValue());
        List<Billofmonth> listBillofmonth = billofmonthSvc.findBills(vo);
        if(listBillofmonth.isEmpty())
            ResTool.commonThrow(res, NODATA.getCode(), NODATA.getContent());
		ExecutionTimer.endStage(ExecutionTimer.ExecutionModule.DATABASE.getValue());

        res.setResult(listBillofmonth);

        ResTool.setRes(res, SUCCESS.getCode(), SUCCESS.getContent());

        log.info(Common.RES + res);
        log.info(Common.API_DIVIDER + Common.END_B + Common.API_DIVIDER);
        
		ExecutionTimer.endStage(ExecutionTimer.ExecutionModule.APPLICATION.getValue());
        ExecutionTimer.exportTimings(this.getClass().getSimpleName() + "_" + DateTool.getYYYYmmDDhhMMss() + ".txt");
		return ResponseEntity.status(HttpStatus.OK).body(res);
    }
    private Billofmonth setUpdatePayDate(FindCusBillReq req){
        Billofmonth vo = new Billofmonth();
        vo.setCid(req.getCid());
        vo.setCardType(req.getCardType());
        vo.setPayDate(DateTool.getDateTime());
        vo.setBillMonth(req.getPayDate());
        return vo;
    }
}
