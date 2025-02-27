package com.ntou.db.api.findcusbill;

import com.ntou.db.billofmonth.Billofmonth;
import com.ntou.spec.SvcRes;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class FindCusBillRes extends SvcRes {
    private List<Billofmonth> result;
}
