package kr.or.kosa.cmsplusmain.domain.statics.dto.query;

import java.time.LocalDate;

import com.querydsl.core.annotations.QueryProjection;

import kr.or.kosa.cmsplusmain.domain.billing.entity.BillingStatus;
import lombok.Getter;

@Getter
public class MonthBillingQueryRes {
	private final LocalDate billingDate;
	private final BillingStatus billingStatus;
	private final int billingCount;
	private final long billingPrice;

	@QueryProjection
	public MonthBillingQueryRes(LocalDate billingDate, BillingStatus billingStatus, int billingCount,
		long billingPrice) {
		this.billingDate = billingDate;
		this.billingStatus = billingStatus;
		this.billingCount = billingCount;
		this.billingPrice = billingPrice;
	}
}
