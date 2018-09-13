package nl.nathalie.journal;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/journal")
public class MainController {
//    @Autowired
//    private TextEntryRepository textEntryRepository;
//
//    @Autowired
//    private EventRepository eventRepository;
//
//    @Value("${spring.application.name}")
//    String appName;
//
//    @GetMapping
//    public String homePage(Model model) {
//        model.addAttribute("appName", appName);
//        return "home";
//    }
//
//    @GetMapping("/entry/add")
//    public String getTextEntryForm(Model model) {
//        return "forms/textentry";
//    } // bootstrap not on classpath?
//
//    @PostMapping("/entry/add")
//    public ModelAndView addTextEntry(@RequestBody TextEntry textEntry) {
//        textEntryRepository.save(textEntry);
//        return new ModelAndView("redirect:journal/entries");
//    }
//
//    @GetMapping(path = "/entries")
//    public @ResponseBody
//    Iterable<? extends Entry> getAllEntries() {
//        Iterable<TextEntry> textEntries = textEntryRepository.findAll();
//        Iterable<Event> calendarEntries = eventRepository.findAll();
//
//        return Iterables.concat(textEntries, calendarEntries);
//    }
}
