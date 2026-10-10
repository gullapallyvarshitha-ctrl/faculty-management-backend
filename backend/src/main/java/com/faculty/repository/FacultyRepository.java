package com.faculty.repository;

import com.faculty.model.Faculty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FacultyRepository extends JpaRepository<Faculty, Long> {

    boolean existsByEmail(String email);

    boolean existsByEmailAndIdNot(String email, Long id);

    List<Faculty> findByDepartmentIgnoreCase(String department);

    List<Faculty> findByDesignationIgnoreCase(String designation);

    List<Faculty> findByDepartmentIgnoreCaseAndDesignationIgnoreCase(String department, String designation);

    @Query("SELECT f FROM Faculty f WHERE " +
           "LOWER(f.name) LIKE LOWER(CONCAT('%', :query, '%')) OR " +
           "LOWER(f.email) LIKE LOWER(CONCAT('%', :query, '%')) OR " +
           "LOWER(f.department) LIKE LOWER(CONCAT('%', :query, '%')) OR " +
           "LOWER(f.designation) LIKE LOWER(CONCAT('%', :query, '%')) OR " +
           "LOWER(f.specialization) LIKE LOWER(CONCAT('%', :query, '%'))")
    List<Faculty> searchFaculty(@Param("query") String query);

    @Query("SELECT f FROM Faculty f WHERE " +
           "(:query IS NULL OR :query = '' OR " +
           " LOWER(f.name) LIKE LOWER(CONCAT('%', :query, '%')) OR " +
           " LOWER(f.email) LIKE LOWER(CONCAT('%', :query, '%')) OR " +
           " LOWER(f.specialization) LIKE LOWER(CONCAT('%', :query, '%'))) AND " +
           "(:dept IS NULL OR :dept = '' OR LOWER(f.department) = LOWER(:dept)) AND " +
           "(:desig IS NULL OR :desig = '' OR LOWER(f.designation) = LOWER(:desig)) " +
           "ORDER BY f.id DESC")
    List<Faculty> searchAndFilter(
            @Param("query") String query,
            @Param("dept") String department,
            @Param("desig") String designation
    );

    long countByDesignationIgnoreCase(String designation);

    long countByDesignationContainingIgnoreCase(String keyword);

    List<Faculty> findTop5ByOrderByIdDesc();
}
