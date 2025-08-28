package istad.co.exstadbackendapi.features.province;

import istad.co.exstadbackendapi.domain.Province;
import istad.co.exstadbackendapi.features.province.dto.ProvinceResponse;
import istad.co.exstadbackendapi.mapper.ProvinceMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProvinceServiceImpl implements ProvinceService {
    private final ProvinceRepository provinceRepository;
    private final ProvinceMapper provinceMapper;
    @Override
    public List<ProvinceResponse> getAllProvinces() {
        List<Province> provinces = provinceRepository.findAll();
        return provinces.stream()
                .filter(province -> province.getIsDeleted().equals(false))
                .map(provinceMapper::toProvinceResponse)
                .toList();
    }
}
