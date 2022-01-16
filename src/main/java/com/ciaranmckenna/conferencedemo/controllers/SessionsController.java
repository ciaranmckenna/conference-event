package com.ciaranmckenna.conferencedemo.controllers;

import com.ciaranmckenna.conferencedemo.models.Session;
import com.ciaranmckenna.conferencedemo.repositories.ISessionRepository;
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
@RequestMapping("/api/v1/sessions")//url route path
public class SessionsController
{
   @Autowired
   private ISessionRepository iSessionRepository;

   @GetMapping
   public List<Session> list()
   {
      return iSessionRepository.findAll();
   }

   @GetMapping
   @RequestMapping("{id}")
   public Session get(@PathVariable Long id)
   {
      return iSessionRepository.getOne(id);
   }

   @PostMapping
   // Spring MVC is taking all the attributes in a json payload and marshalling them into a session object
   @ResponseStatus(HttpStatus.CREATED) // annotation overwrites the default 200 status, instead 201 for created will be the response
   public Session create(@RequestBody final Session session)
   {
      return iSessionRepository.saveAndFlush(session); // doesnt get committed to the db until you flush it
   }

   @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
   public void delete(@PathVariable Long id)
   {
      //TODO add functionality for deleting children records
      iSessionRepository.deleteById(id);
   }

   @RequestMapping(value = "{id}", method = RequestMethod.PUT)
   public Session update(@PathVariable Long id, @RequestBody Session session)
   {
      //TODO: Add validation that all attributes are passed in, otherwise return a 400 bad payload
      Session existingSession = iSessionRepository.getOne(id);
      BeanUtils.copyProperties(session, existingSession, "session_id");
      return iSessionRepository.saveAndFlush(existingSession);
   }
}
