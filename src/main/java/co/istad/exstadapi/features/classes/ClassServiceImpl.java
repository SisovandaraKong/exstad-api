package co.istad.exstadapi.features.classes;

import co.istad.exstadapi.base.BasedMessage;
import co.istad.exstadapi.domain.Class;
import co.istad.exstadapi.features.classes.dto.ClassRequest;
import co.istad.exstadapi.features.classes.dto.ClassResponse;
import co.istad.exstadapi.features.classes.dto.ClassUpdate;
import co.istad.exstadapi.mapper.ClassMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ClassServiceImpl implements ClassService {
    private final ClassRepository classRepository;
    private final ClassMapper classMapper;

    @Override
    public List<ClassResponse> getAllClasses() {
        List<Class> classes = classRepository.findAllByIsDeletedFalse();
        return classes
                .stream()
                .map(classMapper::toClassResponse)
                .toList();
    }

    @Override
    public ClassResponse getClassByUuid(String uuid) {
        Class aClass = classRepository.findByUuid(uuid)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Class with UUID " + uuid + " not found"));
        return classMapper.toClassResponse(aClass);
    }

    @Override
    public ClassResponse getClassByName(String name) {
        Class aClass = classRepository.findByRoomIgnoreCase(name)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Class with name " + name + " not found"));
        return classMapper.toClassResponse(aClass);
    }

    @Override
    public ClassResponse createClass(ClassRequest classRequest) {
        Class aClass = classMapper.fromClassRequest(classRequest);
        aClass.setUuid(UUID.randomUUID().toString());
        aClass.setIsDeleted(false);
        aClass.setIsEnabled(true);
        aClass = classRepository.save(aClass);
        return classMapper.toClassResponse(aClass);
    }

    @Override
    public ClassResponse updateClass(String uuid ,ClassUpdate classUpdate) {
        Class aClass = classRepository.findByUuid(uuid)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Class with UUID " + uuid + " not found"));
        classMapper.updateClassFromRequest(classUpdate, aClass);
        aClass = classRepository.save(aClass);
        return classMapper.toClassResponse(aClass);
    }

    @Transactional
    @Override
    public BasedMessage softDeleteClass(String uuid) {
        if (!classRepository.existsByUuid(uuid)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Class with UUID " + uuid + " not found");
        }
        classRepository.softDeleteByUuid(uuid);
        return new BasedMessage("Class " + uuid + " has been deleted!");
    }

    @Transactional
    @Override
    public BasedMessage restoreClass(String uuid) {
        if (!classRepository.existsByUuid(uuid)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Class with UUID " + uuid + " not found");
        }
        classRepository.restoreByUuid(uuid);
        return new BasedMessage("Class " + uuid + " has been restored!");
    }

    @Transactional
    @Override
    public BasedMessage hardDeleteClass(String uuid) {
        if (!classRepository.existsByUuid(uuid)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Class with UUID " + uuid + " not found");
        }
        classRepository.deleteByUuid(uuid);
        return new BasedMessage("Class " + uuid + " has been permanently deleted!");
    }

    @Transactional
    @Override
    public BasedMessage disableClass(String uuid) {
        if (!classRepository.existsByUuid(uuid)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Class with UUID " + uuid + " not found");
        }
        classRepository.disableByUuid(uuid);
        return new BasedMessage("Class " + uuid + " has been disabled!");
    }

    @Transactional
    @Override
    public BasedMessage enableClass(String uuid) {
        if (!classRepository.existsByUuid(uuid)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Class with UUID " + uuid + " not found");
        }
        classRepository.enableByUuid(uuid);
        return new BasedMessage("Class " + uuid + " has been enabled!");
    }

    @Transactional
    @Override
    public BasedMessage setToWeekend(String uuid) {
        if (!classRepository.existsByUuid(uuid)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Class with UUID " + uuid + " not found");
        }
        classRepository.setToWeekendByUuid(uuid);
        return new BasedMessage("Class " + uuid + " has been set to weekend!");
    }

    @Transactional
    @Override
    public BasedMessage setToWeekday(String uuid) {
        if (!classRepository.existsByUuid(uuid)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Class with UUID " + uuid + " not found");
        }
        classRepository.setToWeekdayByUuid(uuid);
        return new BasedMessage("Class " + uuid + " has been set to weekday!");
    }
}
