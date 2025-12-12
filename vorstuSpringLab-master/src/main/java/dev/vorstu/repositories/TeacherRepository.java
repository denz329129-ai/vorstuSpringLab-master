package dev.vorstu.repositories;

import dev.vorstu.entity.Teacher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TeacherRepository extends JpaRepository<Teacher, Long> {

    @Query("SELECT s FROM Teacher s WHERE " +
            "(s.fio LIKE CONCAT('%', :name, '%') OR " +
//            "s.groups.groupName LIKE CONCAT('%', :name, '%') OR " +
            "s.phoneNumber LIKE CONCAT('%', :name, '%'))")
    Page<Teacher> findTeachersWithPagination1(@Param("name") String name,
                                                Pageable pageable);


    @Query("SELECT s FROM Teacher s join fetch s.groups where " +
            "(s.fio LIKE CONCAT('%', :name, '%') OR " +
//            "s.groups.groupName LIKE CONCAT('%', :name, '%') OR " +
            "s.phoneNumber LIKE CONCAT('%', :name, '%'))")
    Page<Teacher> findTeachersWithPagination(@Param("name") String name,
                                             Pageable pageable);

}
