package com.ntou.db.api.savecuscredit;

import com.ntou.db.cuscredit.Cuscredit;
import com.ntou.spec.SvcRes;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateCuscreditRes extends SvcRes {
    private Cuscredit result;
}