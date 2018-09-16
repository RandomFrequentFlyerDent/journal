package nl.nathalie.journal.entry.event;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class EventController {

    private EventRepository eventRepository;

    @Value("${spring.application.name}")
    String appName;

    public EventController(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    @GetMapping("entry/event/new")
    public ModelAndView getForm(Model model) {
        ModelAndView mav = new ModelAndView();
        mav.addObject("appName", appName);
        mav.addObject("pageTitle", "New Event");
        mav.addObject("event", new Event());
        mav.setViewName("forms/task");

        return mav;
    }

    @GetMapping("/entry/event/{id}")
    public ModelAndView getById(@PathVariable Long id, Model model) {
        ModelAndView mav = new ModelAndView();
        Event event = eventRepository.findById(id).orElse(new Event());

        if (event.isNew()) {
            mav.setViewName("redirect:/entry/event/new");
        } else {
            mav.addObject("event", event);
            mav.addObject("appName", appName);
            mav.addObject("pageTitle", event.getTitle());
            mav.setViewName("forms/event");
        }

        return mav;
    }

}
