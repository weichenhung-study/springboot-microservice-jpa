package com.ntou.db.api.updatedisputedflag;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ntou.tool.JsonTool;
import com.ntou.tool.RegexpTool;
import lombok.Data;

@Data
public class UpdateDisputedFlagReq {
    private String uuid     = "";

    @JsonIgnore
    private String errMsg;
    public boolean checkReq(){
        if(!RegexpTool.LENGTH_36.matcher(uuid     ).matches()){this.errMsg = "uuid"+ RegexpTool.C_INVALID_NUM_LEN;return false;}
        return true;
    }
    @Override
    public String toString() {return JsonTool.format2Json(this);}
}
