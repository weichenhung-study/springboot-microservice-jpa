package com.ntou.db.api.findbycidandcardtypeisactivated;

import com.ntou.db.cuscredit.Cuscredit;
import com.ntou.spec.SvcRes;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetActivatedCardHolderRes extends SvcRes {
    private Cuscredit result;
}