package co.istad.exstadapi.features.classes;

import co.istad.exstadapi.domain.Class;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;


public interface ClassRepository extends JpaRepository<Class,Integer> {

    Optional<Class> findByUuid(String uuid);
    List<Class> findAllByIsDeletedFalse();
    Optional<Class> findByClassNameIgnoreCase(String className);
    boolean existsByUuid(String uuid);

    @Modifying
    @Query("UPDATE Class c SET c.isDeleted = true WHERE c.uuid = ?1")
    void softDeleteByUuid(String uuid);

    @Modifying
    @Query("UPDATE Class c SET c.isDeleted = false WHERE c.uuid = ?1")
    void restoreByUuid(String uuid);

    void deleteByUuid(String uuid);

    @Modifying
    @Query("UPDATE Class c SET c.isEnabled = false WHERE c.uuid = ?1")
    void disableByUuid(String uuid);

    @Modifying
    @Query("UPDATE Class c SET c.isEnabled = true WHERE c.uuid = ?1")
    void enableByUuid(String uuid);

    @Modifying
    @Query("UPDATE Class c SET c.isWeekend = true WHERE c.uuid = ?1")
    void setToWeekendByUuid(String uuid);

    @Modifying
    @Query("UPDATE Class c SET c.isWeekend = false WHERE c.uuid = ?1")
    void setToWeekdayByUuid(String uuid);
}
