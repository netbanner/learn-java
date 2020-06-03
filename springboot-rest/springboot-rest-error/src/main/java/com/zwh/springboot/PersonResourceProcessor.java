package com.zwh.springboot;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceProcessor;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.UriComponents;

@Component
public class PersonResourceProcessor implements ResourceProcessor<Resource<Person>> {
    @Override
    public Resource<Person> process(Resource<Person> personResource) {

        String id = Long.toString(personResource.getContent().getId());

        UriComponents uriComponents = ServletUriComponentsBuilder.fromCurrentContextPath().path("/people/{id}/photo").buildAndExpand(id);

        String uril = uriComponents.toUriString();
        personResource.add(new Link(uril,"photo"));
        return personResource;
    }
}
