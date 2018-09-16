package nl.nathalie.journal.entry.task;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class TaskController {

    private TaskRepository taskRepository;

    @Value("${spring.application.name}")
    String appName;

    public TaskController(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @GetMapping("entry/task/new")
    public ModelAndView getForm(Model model) {
        ModelAndView mav = new ModelAndView();
        mav.addObject("appName", appName);
        mav.addObject("pageTitle", "New Task");
        mav.addObject("task", new Task());
        mav.setViewName("forms/task");

        return mav;
    }

    @GetMapping("/entry/task/{id}")
    public ModelAndView getById(@PathVariable Long id, Model model) {
        ModelAndView mav = new ModelAndView();
        Task task = taskRepository.findById(id).orElse(new Task());

        if (task.isNew()) {
            mav.setViewName("redirect:/entry/task/new");
        } else {
            mav.addObject("task", task);
            mav.addObject("appName", appName);
            mav.addObject("pageTitle", task.getTitle());
            mav.setViewName("forms/task");
        }

        return mav;
    }
}
