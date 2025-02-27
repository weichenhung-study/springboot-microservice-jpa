package com.ntou.db.billofmonth;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Qualifier("billingInfoEntityManagerFactory")
public interface BillofmonthRepo extends JpaRepository<Billofmonth, String>{
    List<Billofmonth> findByCidAndCardTypeAndBillMonth(String cid, String cardType, String billMonth);
    Billofmonth findByUuid(String uuid);
}
