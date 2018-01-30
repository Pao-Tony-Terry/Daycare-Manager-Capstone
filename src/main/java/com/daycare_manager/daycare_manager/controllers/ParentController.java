package com.daycare_manager.daycare_manager.controllers;


import com.daycare_manager.daycare_manager.daos.ChildrenRepository;
import com.daycare_manager.daycare_manager.daos.UsersRepository;
import com.daycare_manager.daycare_manager.model.Child;
import com.daycare_manager.daycare_manager.model.User;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ParentController {

    private ChildrenRepository childrenRepository;

    private UsersRepository usersRepository;

//    public ParentController(ChildrenRepository childrenRepository) {
//        this.childrenRepository = childrenRepository;
//    }


    public ParentController(ChildrenRepository childrenRepository, UsersRepository usersRepository) {
        this.childrenRepository = childrenRepository;
        this.usersRepository = usersRepository;
    }

    @GetMapping("/parent/enroll")
    public String showEnrollForm(Model viewModel) {
        // new Child to catch the form to enroll one kid
        viewModel.addAttribute("child", new Child());
        return "users/enroll";
    }

    @PostMapping("/parent/enroll")
    public String enrollAChild(@ModelAttribute Child child) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        child.setParent(usersRepository.findOne(user.getId()));
        childrenRepository.save(child);
        return "redirect:/user/parent";
    }



//    @PostMapping("posts/create")
//    // we are not using pathvariable here because is comming from a form
//    public String savePost(@ModelAttribute Post post){
//        // this is coming form the db, and UserWithRoles is not mapped.
//        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        post.setUser(usersDao.findOne(user.getId()));
//        postService.save(post);
//        return "redirect:/posts";
//    }

}
