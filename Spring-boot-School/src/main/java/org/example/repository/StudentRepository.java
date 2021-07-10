package org.example.repository;

import org.example.entitiy.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public interface StudentRepository extends JpaRepository<Student, UUID> {

    void deleteAllByGroupsId(UUID groupId);

    @Query(value = "select * from student where lower(first_name) like concat('%',lower(:search),'%') or lower(last_Name) like concat('%',lower(:search),'%') or groups_id in (select id from groups where lower(name)  like concat('%',lower(:search) ,'%'))" + "or passport_id in (select id from passport where lower(serial) like concat('%',lower(:search),'%') or lower(number) like concat('%',lower(:search),'%'))  ", nativeQuery = true)
    ArrayList<Student> getStudentByAllParametrs(String search);

}
