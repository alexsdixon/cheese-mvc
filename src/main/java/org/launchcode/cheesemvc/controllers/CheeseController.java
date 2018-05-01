package org.launchcode.cheesemvc.controllers;

import org.launchcode.cheesemvc.models.Cheese;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;



@Controller
@RequestMapping("cheese")
public class CheeseController {

    static ArrayList<Cheese> cheeses = new ArrayList<>();

    @RequestMapping(value = "")
    public String index(Model model) {

        model.addAttribute("cheeses", cheeses);
        model.addAttribute("title", "My Cheese");
        return "cheese/index";
    }

    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String displayAddCheeseForm(Model model) {
        model.addAttribute("title", "Add Cheese");
        return "cheese/add";
    }

    //    @RequestMapping(value = "add", method = RequestMethod.POST)
//    public String processAddCheeseForm(HttpServletRequest request) {
//        String cheeseName = request.getParameter("cheeseName");
//    }
    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String processAddCheeseForm(@RequestParam String cheeseName, @RequestParam String cheeseDescription, Model model) {
        if (cheeseName.equals("")) {
            String error = "Cheese name is required";
            model.addAttribute("error", error);
            return "/cheese/add";
        }
        else {
            cheeses.add(new Cheese(cheeseName, cheeseDescription));
            System.out.println(cheeseName + " added");
            // redirect to /cheese
            return "redirect:";
        }
    }

    @RequestMapping(value = "remove", method = RequestMethod.GET)
    public String displayRemoveCheeseForm(HttpServletRequest request, Model model) {
        String cheese = request.getParameter("cheese");
        if (cheese == null) {
            model.addAttribute("cheeses", cheeses);
            model.addAttribute("title", "Remove Cheese");
            return "cheese/remove";
        }
        else {
            for (Cheese c : cheeses) {
                if (c.getName().equals(cheese)) {
                    cheeses.remove(c);
                    break;
                }
            }
            return "redirect:";
        }
    }

    @RequestMapping(value = "remove", method = RequestMethod.POST)
    public String processRemoveCheeseForm(@RequestParam ArrayList<String> cheeseName) {
        for (String cheese : cheeseName) {
            for (Cheese c : cheeses) {
                if (c.getName().equals(cheese)) {
                    cheeses.remove(c);
                    break;
                }
            }
            System.out.println(cheeseName + " removed");
        }

        return "redirect:";
    }

}