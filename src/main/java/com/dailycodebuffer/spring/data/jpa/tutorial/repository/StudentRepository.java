package com.dailycodebuffer.spring.data.jpa.tutorial.repository;

import com.dailycodebuffer.spring.data.jpa.tutorial.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    public List<Student> findByFirstName(String firstName);

    public List<Student> findByFirstNameContaining(String name);

    public List<Student> findByLastNameNotNull();

    List<Student> findByGuardianName(String guardianName);

    Student findByFirstNameAndLastName(String firstName, String lastName);

//    JPQL
    @Query(value = "select s from Student s where s.emailId = ?1 ")
    Student getStudentsByEmailAddress(String emailId);

    @Query(value = "select s.firstName from Student s where s.emailId = ?1 ")
    String getStudentsFirstNameByEmailAddress(String emailId);


//    native
    @Query(
            value = "select * from tbl_student s where s.email_address = ?1 ",
            nativeQuery = true
    )
    Student getStudentsByEmailAddressNative(String emailId);

    //    native named param
    @Query(
            value = "select * from tbl_student s where s.email_address = :emailId ",
            nativeQuery = true
    )
    Student getStudentsByEmailAddressNativeNamedParam(@Param("emailId") String emailId);

    @Modifying
    @Transactional
    @Query(
            value = "update tbl_student set first_name = ?1 where email_address =?2",
            nativeQuery = true
    )
    int updateStudentNameByEmailId(String firstName, String emailId);

}
