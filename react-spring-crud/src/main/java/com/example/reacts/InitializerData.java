package com.example.reacts;

import com.example.reacts.model.Event;
import com.example.reacts.model.Group;
import com.example.reacts.repository.GroupRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Collections;
import java.util.stream.Stream;

/**
 * @author zhuwh
 * @date 2019/3/8 15:56
 * @desc
 */
@Component
class InitializerData implements CommandLineRunner {
    private final GroupRepository repository;

    public InitializerData(GroupRepository repository) {
        this.repository = repository;
    }

    @Override
    public void run(String... strings) {
       /* Stream.of("Denver JUG", "Utah JUG", "Seattle JUG",
                "Richmond JUG").forEach(name ->
                repository.save(new Group(name))
        );

        Group djug = repository.findByName("Denver JUG");
        Event e = Event.builder().title("Full Stack Reactive")
                .description("Reactive with Spring Boot + React")
                .date(Instant.parse("2018-12-12T18:00:00.000Z"))
                .build();
        djug.setEvents(Collections.singleton(e));
        repository.save(djug);

        repository.findAll().forEach(System.out::println);*/
    }
}
