package istad.co.exstadbackendapi.features.province;

import istad.co.exstadbackendapi.domain.Province;
import istad.co.exstadbackendapi.features.province.dto.ProvinceResponse;

import java.util.List;

public interface ProvinceService {
    List<ProvinceResponse> getAllProvinces();
}
