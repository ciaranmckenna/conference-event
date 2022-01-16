package com.ciaranmckenna.conferencedemo.controllers;

import com.ciaranmckenna.conferencedemo.models.Speaker;
import com.ciaranmckenna.conferencedemo.repositories.ISpeakerRepository;
import java.util.List;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/speakers")
public class SpeakersController
{
   @Autowired /// gives crud access to database
   private ISpeakerRepository iSpeakerRepository;

   @GetMapping
   public List<Speaker> list()
   {
      return iSpeakerRepository.findAll();
   }

   @GetMapping
   @RequestMapping("{id}")
   public Speaker get(@PathVariable Long id)
   {
      return iSpeakerRepository.getOne(id);
   }

   @PostMapping
   @ResponseStatus(HttpStatus.CREATED)
   public Speaker create(@RequestBody final Speaker speaker)
   {
      return iSpeakerRepository.saveAndFlush(speaker);
   }

   @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
   public Speaker delete(@PathVariable Long id)
   {
      return iSpeakerRepository.getOne(id);
   }

   @RequestMapping(value = "{id}", method = RequestMethod.PUT)
   public Speaker update(@PathVariable Long id, @RequestBody Speaker speaker)
   {
      Speaker existingSpeaker = iSpeakerRepository.getOne(id);
      // ignoring the speaker id as it should not be updated
      BeanUtils.copyProperties(speaker, existingSpeaker, "speaker_id" );
      return iSpeakerRepository.saveAndFlush(existingSpeaker);
   }

}
