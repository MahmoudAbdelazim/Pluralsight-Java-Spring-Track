package com.pluralsight.conference_scheduling;

import com.pluralsight.conference_scheduling.Utils.CalendarFactory;
import com.pluralsight.conference_scheduling.repository.HibernateSpeakerRepositoryImpl;
import com.pluralsight.conference_scheduling.repository.SpeakerRepository;
import com.pluralsight.conference_scheduling.service.SpeakerService;
import com.pluralsight.conference_scheduling.service.SpeakerServiceImpl;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import java.util.Calendar;

@Configuration
@ComponentScan({"com.pluralsight"})
public class AppConfig {

    @Bean(name = "cal")
    public CalendarFactory calendarFactory() {
        CalendarFactory factory = new CalendarFactory();
        factory.addDays(2);
        return factory;
    }

    @Bean
    public Calendar calendar() throws Exception{
        return calendarFactory().getObject();
    }

    /*
    @Bean(name = "speakerService")
    @Scope(value = BeanDefinition.SCOPE_SINGLETON)
    public SpeakerService getSpeakerService() {
        // service.setRepository(getSpeakerRepository());
        // return new SpeakerServiceImpl(getSpeakerRepository());
        SpeakerServiceImpl service = new SpeakerServiceImpl();
        return service;
    }
    */

    /*
    @Bean(name = "speakerRepository")
    @Scope(value = BeanDefinition.SCOPE_SINGLETON)
    public SpeakerRepository getSpeakerRepository() {
        return new HibernateSpeakerRepositoryImpl();
    }
    */
}
