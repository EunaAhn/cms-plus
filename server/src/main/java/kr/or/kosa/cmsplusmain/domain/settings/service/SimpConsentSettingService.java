package kr.or.kosa.cmsplusmain.domain.settings.service;

import kr.or.kosa.cmsplusmain.domain.payment.entity.method.PaymentMethod;
import kr.or.kosa.cmsplusmain.domain.payment.entity.type.PaymentType;
import kr.or.kosa.cmsplusmain.domain.product.dto.ProductListItemRes;
import kr.or.kosa.cmsplusmain.domain.product.entity.Product;
import kr.or.kosa.cmsplusmain.domain.product.repository.ProductRepository;
import kr.or.kosa.cmsplusmain.domain.product.service.ProductService;
import kr.or.kosa.cmsplusmain.domain.settings.dto.AvailableOptionsDto;
import kr.or.kosa.cmsplusmain.domain.settings.dto.SimpConsentSettingDto;
import kr.or.kosa.cmsplusmain.domain.settings.entity.SimpConsentSetting;
import kr.or.kosa.cmsplusmain.domain.settings.repository.SimpConsentSettingCustomRepository;
import kr.or.kosa.cmsplusmain.domain.vendor.repository.VendorCustomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityNotFoundException;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class SimpConsentSettingService {

    private final SimpConsentSettingCustomRepository simpConsentSettingRepository;
    private final VendorCustomRepository vendorRepository;
    private final ProductRepository productRepository;
    private final ProductService productService;


    /* 고객 간편동의 설정 세팅 조회 */
    public SimpConsentSettingDto getSetting(Long vendorId) {
        SimpConsentSetting setting = simpConsentSettingRepository.findByVendorUsername(vendorId);
        if (setting == null) {
            throw new EntityNotFoundException("SimpConsentSetting not found for vendor: " + vendorId);
        }
        return convertToDto(setting);
    }

    /* 고객 간편동의 설정 세팅 수정 */
    @Transactional
    public SimpConsentSettingDto updateSetting(Long vendorId, SimpConsentSettingDto dto) {
        SimpConsentSetting setting = simpConsentSettingRepository.findByVendorUsername(vendorId);
        if (setting == null) {
            throw new EntityNotFoundException("SimpConsentSetting not found for vendor: " + vendorId);
        }

        Set<PaymentMethod> autoPaymentMethods = new HashSet<>(PaymentType.getAutoPaymentMethods());
        setting.getSimpConsentPayments().clear();
        setting.getSimpConsentPayments().addAll(
                dto.getPaymentMethods().stream()
                        .filter(autoPaymentMethods::contains)
                        .collect(Collectors.toSet())
        );

        setting.getSimpConsentProducts().clear();
        dto.getProductIds().forEach(productId -> {
            Product product = productRepository.findById(productId)
                    .orElseThrow(() -> new EntityNotFoundException("Product not found with id: " + productId));
            setting.addProduct(product);
        });

        return convertToDto(setting);
    }


    public AvailableOptionsDto getAvailableOptions(Long vendorId) {
        Set<PaymentMethod> availablePaymentMethods = new HashSet<>(PaymentType.getAutoPaymentMethods());

        List<ProductListItemRes> availableProducts = productService.findAvailableProductsByVendorUsername(vendorId);

        return new AvailableOptionsDto(availablePaymentMethods, availableProducts);
    }



    private SimpConsentSettingDto convertToDto(SimpConsentSetting setting) {
        SimpConsentSettingDto dto = new SimpConsentSettingDto();
        dto.setId(setting.getId());
        dto.setVendorUsername(setting.getVendor().getUsername());
        dto.setPaymentMethods(setting.getSimpConsentPayments());
        dto.setProductIds(setting.getSimpConsentProducts().stream().map(Product::getId).collect(java.util.stream.Collectors.toSet()));
        return dto;
    }
}