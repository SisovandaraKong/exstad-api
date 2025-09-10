package istad.co.exstadbackendapi.features.scholarClass;

import istad.co.exstadbackendapi.base.BasedMessage;
import istad.co.exstadbackendapi.domain.Class;
import istad.co.exstadbackendapi.domain.Scholar;
import istad.co.exstadbackendapi.domain.ScholarClass;
import istad.co.exstadbackendapi.features.classes.ClassRepository;
import istad.co.exstadbackendapi.features.scholar.ScholarRepository;
import istad.co.exstadbackendapi.features.scholarClass.dto.ScholarClassRequest;
import istad.co.exstadbackendapi.features.scholarClass.dto.ScholarClassResponse;
import istad.co.exstadbackendapi.features.scholarClass.dto.ScholarClassUpdate;
import istad.co.exstadbackendapi.mapper.ScholarClassMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ScholarClassServiceImpl implements ScholarClassService{
    private final ScholarClassRepository scholarClassRepository;
    private final ScholarRepository scholarRepository;
    private final ClassRepository classRepository;
    private final ScholarClassMapper scholarClassMapper;

    @Override
    public ScholarClassResponse createScholarIntoClass(ScholarClassRequest scholarClassRequest) {
        ScholarClass scholarClass = scholarClassMapper.toScholarClassRequest(scholarClassRequest);
        scholarClass.setUuid(UUID.randomUUID().toString());
        scholarClass.setIsDeleted(false);
            scholarClass = scholarClassRepository.save(scholarClass);
            return scholarClassMapper.toScholarClassResponse(scholarClass);
    }

    @Override
    public List<ScholarClassResponse> getAllScholarClasses() {
        List<ScholarClass> scholarClasses = scholarClassRepository.findAll();
        return scholarClasses
                .stream()
                .map(scholarClassMapper::toScholarClassResponse)
                .toList();
    }

    @Override
    public ScholarClassResponse getScholarClassByUuid(String uuid) {
        ScholarClass scholarClass = scholarClassRepository.findByUuid(uuid)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "ScholarClass with UUID "+ uuid +" not found"));
        return scholarClassMapper.toScholarClassResponse(scholarClass);
    }

    @Override
    public ScholarClassResponse updateScholarClassByUuid(String uuid, ScholarClassUpdate scholarClassUpdate) {
        ScholarClass scholarClass = scholarClassRepository.findByUuid(uuid)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "ScholarClass with UUID "+ uuid +" not found"));
        scholarClassMapper.updateScholarClassFromRequest(scholarClassUpdate, scholarClass);
        scholarClass = scholarClassRepository.save(scholarClass);
        return scholarClassMapper.toScholarClassResponse(scholarClass);
    }

    @Transactional
    @Override
    public BasedMessage softDeleteScholarClassByUuid(String uuid) {
        if (!scholarClassRepository.existsByUuid(uuid)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ScholarClass with UUID " + uuid + " not found");
        }
        scholarClassRepository.softDeleteByUuid(uuid);
        return new BasedMessage("Scholar Class " + uuid + " deleted successfully");
    }

    @Transactional
    @Override
    public BasedMessage restoreScholarClassByUuid(String uuid) {
        if (!scholarClassRepository.existsByUuid(uuid)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ScholarClass with UUID " + uuid + " not found");
        }
        scholarClassRepository.restoreByUuid(uuid);
        return new BasedMessage("Scholar Class " + uuid + " restored successfully");
    }

    @Transactional
    @Override
    public BasedMessage hardDeleteScholarClassByUuid(String uuid) {
        if (!scholarClassRepository.existsByUuid(uuid)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ScholarClass with UUID " + uuid + " not found");
        }
        scholarClassRepository.deleteByUuid(uuid);
        return new BasedMessage("Scholar Class " + uuid + " deleted permanently");
    }

    @Override
    public List<ScholarClassResponse> getAllClassesByOneScholarUuid(String scholarUuid) {
        Scholar scholar = scholarRepository.findByUuid(scholarUuid)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Scholar with UUID "+ scholarUuid +" not found"));
        return scholarClassRepository.findAllByScholar(scholar).stream()
                .map(scholarClassMapper::toScholarClassResponse)
                .toList();
    }

    @Override
    public List<ScholarClassResponse> getAllScholarsByOneClassUuid(String classUuid) {
        Class aClass = classRepository.findByUuid(classUuid)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Class with UUID "+ classUuid +" not found"));
        return scholarClassRepository.findAllByAClass(aClass)
                .stream()
                .map(scholarClassMapper::toScholarClassResponse)
                .toList();
    }

    @Transactional
    @Override
    public BasedMessage markAsPaid(String uuid) {
        if (!scholarClassRepository.existsByUuid(uuid)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ScholarClass with UUID " + uuid + " not found");
        }
            scholarClassRepository.markAsPaidByUuid(uuid);
            return new BasedMessage("Scholar Class " + uuid + " has been marked as paid");
    }

    @Transactional
    @Override
    public BasedMessage markAsUnpaid(String uuid) {
        if (!scholarClassRepository.existsByUuid(uuid)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ScholarClass with UUID " + uuid + " not found");
        }
            scholarClassRepository.markAsUnpaidByUuid(uuid);
            return new BasedMessage("Scholar Class " + uuid + " has been marked as unpaid");
    }

    @Transactional
    @Override
    public BasedMessage markAsReminded(String uuid) {
        if (!scholarClassRepository.existsByUuid(uuid)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ScholarClass with UUID " + uuid + " not found");
        }
            scholarClassRepository.markAsRemindedByUuid(uuid);
            return new BasedMessage("Scholar Class " + uuid + " has been marked as reminded");
    }

    @Transactional
    @Override
    public BasedMessage markAsUnreminded(String uuid) {
        if (!scholarClassRepository.existsByUuid(uuid)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ScholarClass with UUID " + uuid + " not found");
        }
            scholarClassRepository.markAsUnremindedByUuid(uuid);
            return new BasedMessage("Scholar Class " + uuid + " has been marked as unreminded");
    }
}
