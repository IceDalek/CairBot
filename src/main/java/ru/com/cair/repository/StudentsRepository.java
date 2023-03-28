package ru.com.cair.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.com.cair.entity.Student;

@Repository
public interface StudentsRepository extends JpaRepository<Student, String> {
}
