package nl.nathalie.journal.entry.textentry;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class TextEntryController {

    private TextEntryRepository textEntryRepository;

    @Value("${spring.application.name}")
    String appName;

    public TextEntryController(TextEntryRepository textEntryRepository) {
        this.textEntryRepository = textEntryRepository;
    }

    @GetMapping("entry/text/new")
    public ModelAndView getForm(Model model) {
        ModelAndView mav = new ModelAndView();
        mav.addObject("appName", appName);
        mav.addObject("pageTitle", "New Entry");
        mav.addObject("entry", new TextEntry());
        mav.setViewName("forms/textentry");

        return mav;
    }

    @PostMapping("entry/text/new")
    public ModelAndView saveEntry(@ModelAttribute("entry") TextEntry entry) {
        TextEntry savedEntry = textEntryRepository.save(entry);
        ModelAndView mav = new ModelAndView();
        String viewName = "redirect:/entry/text/" + savedEntry.getId();
        mav.setViewName(viewName);
        return mav;
    }

    @GetMapping("/entry/text/{id}")
    public ModelAndView getById(@PathVariable Long id, Model model) {
        ModelAndView mav = new ModelAndView();
        TextEntry entry = textEntryRepository.findById(id).orElse(new TextEntry());

        if (entry.isNew()) {
            mav.setViewName("redirect:/entry/text/new");
        } else {
            mav.addObject("entry", entry);
            mav.addObject("appName", appName);
            mav.addObject("pageTitle", entry.getTitle());
            mav.setViewName("forms/textentry");
        }

        return mav;
    }

    @PutMapping("/entry/text/{id}")
    public ModelAndView updateEntry(@ModelAttribute("entry") TextEntry entry, @PathVariable Long id) {
        ModelAndView mav = new ModelAndView();
        textEntryRepository.save(entry);

        String viewName = "redirect:/entry/text/" + id;
        mav.setViewName(viewName);
        return mav;
    }

    @GetMapping("/delete/text/{id}")
    public ModelAndView deleteEntry(@PathVariable Long id) {
        ModelAndView mav = new ModelAndView();
        textEntryRepository.deleteById(id);
        mav.setViewName("redirect:/entries");
        return mav;
    }
}
