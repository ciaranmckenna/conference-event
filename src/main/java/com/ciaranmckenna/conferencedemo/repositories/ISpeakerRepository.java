package com.ciaranmckenna.conferencedemo.repositories;

import com.ciaranmckenna.conferencedemo.models.Speaker;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ISpeakerRepository extends JpaRepository<Speaker, Long>
{


}
