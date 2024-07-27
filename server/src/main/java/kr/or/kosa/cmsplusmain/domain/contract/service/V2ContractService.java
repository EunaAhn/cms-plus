package kr.or.kosa.cmsplusmain.domain.contract.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.or.kosa.cmsplusmain.domain.base.dto.PageReq;
import kr.or.kosa.cmsplusmain.domain.base.dto.PageRes;
import kr.or.kosa.cmsplusmain.domain.billing.dto.BillingListItemRes;
import kr.or.kosa.cmsplusmain.domain.billing.dto.BillingSearchReq;
import kr.or.kosa.cmsplusmain.domain.billing.repository.V2BillingRepository;
import kr.or.kosa.cmsplusmain.domain.contract.dto.ContractProductRes;
import kr.or.kosa.cmsplusmain.domain.contract.dto.ContractSearchReq;
import kr.or.kosa.cmsplusmain.domain.contract.dto.V2ContractListItemRes;
import kr.or.kosa.cmsplusmain.domain.contract.exception.ContractNotFoundException;
import kr.or.kosa.cmsplusmain.domain.contract.repository.V2ContractProductRepository;
import kr.or.kosa.cmsplusmain.domain.contract.repository.V2ContractRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class V2ContractService {

	private final V2ContractRepository contractRepository;
	private final V2BillingRepository billingRepository;
	private final V2ContractProductRepository contractProductRepository;

	/*
	 * 계약 목록 조회
	 *
	 * 총 발생 쿼리수: 3회
	 * 내용:
	 * 	계약 조회, 계약상품 목록 조회(+? batch_size=100), 전체 개수 조회
	 * */
	public PageRes<V2ContractListItemRes> searchContracts(Long vendorId, ContractSearchReq search, PageReq pageReq) {
		// 단일 페이지 결과
		List<V2ContractListItemRes> content = contractRepository.searchContracts(vendorId, search, pageReq);

		// 전체 개수
		long totalContentCount = contractRepository.countSearchedContracts(vendorId, search);

		return new PageRes<>((int) totalContentCount, pageReq.getSize(), content);
	}

	/**
	 * 계약의 모든 청구
	 * */
	public PageRes<BillingListItemRes> getBillingsByContract(Long vendorId, Long contractId, PageReq pageReq) {
		validateContractByVendor(vendorId, contractId);

		BillingSearchReq searchReq = new BillingSearchReq();
		searchReq.setContractId(contractId);

		List<BillingListItemRes> content = billingRepository.searchBillings(vendorId, searchReq, pageReq);
		long totalContentCount = billingRepository.countSearchedBillings(vendorId, searchReq);

		return new PageRes<>((int) totalContentCount, pageReq.getSize(), content);
	}

	/**
	 * 계약의 모든 계약상품
	 * */
	public List<ContractProductRes> getContractProducts(Long vendorId, Long contractId) {
		validateContractByVendor(vendorId, contractId);

		return contractProductRepository.findAllByContractId(contractId).stream()
			.map(ContractProductRes::fromEntity)
			.toList();
	}

	private void validateContractByVendor(Long vendorId, Long contractId) {
		if (!contractRepository.existsContractByVendorId(vendorId, contractId)) {
			throw new ContractNotFoundException("계약이 없습니다");
		}
	}
}