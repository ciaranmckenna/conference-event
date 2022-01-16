package com.ciaranmckenna.conferencedemo.repositories;

import com.ciaranmckenna.conferencedemo.models.Session;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ISessionRepository extends JpaRepository<Session,Long>
{


}
