package kr.or.kosa.cmsplusbatch.global;

import kr.or.kosa.cmsplusbatch.domain.billing.entity.Billing;
import kr.or.kosa.cmsplusbatch.domain.billing.entity.BillingStatus;
import kr.or.kosa.cmsplusbatch.domain.billing.repository.BillingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class InvoiceWriter implements ItemWriter<Billing> {

    private final BillingRepository billingRepository;

    @Override
    public void write(Chunk<? extends Billing> chunk) throws Exception {
        for (Billing billing : chunk) {
            billing.setBillingStatus(BillingStatus.WAITING_PAYMENT);
            billingRepository.save(billing);
        }
    }

}