package com.ntou.db.api.findbycidandcardtype;

import com.ntou.db.cuscredit.Cuscredit;
import com.ntou.spec.SvcRes;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetCardHolderRes extends SvcRes {
    private Cuscredit result;
}