package com.ntou.db.api.insertbill;

import com.ntou.db.billofmonth.Billofmonth;
import com.ntou.spec.SvcRes;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
public class InsertBillRes extends SvcRes {
    private ArrayList<Billofmonth> result;
}
