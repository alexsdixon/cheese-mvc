package org.launchcode.cheesemvc.controllers;

import org.launchcode.cheesemvc.models.Cheese;
import org.launchcode.cheesemvc.models.CheeseData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;



@Controller
@RequestMapping("cheese")
public class CheeseController {


    @RequestMapping(value = "")
    public String index(Model model) {

        model.addAttribute("cheeses", CheeseData.getAll());
        model.addAttribute("title", "My Cheese");
        return "cheese/index";
    }

    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String displayAddCheeseForm(Model model) {
        model.addAttribute("title", "Add Cheese");
        return "cheese/add";
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String processAddCheeseForm(@ModelAttribute Cheese newCheese, Model model) {
        String c = newCheese.getName();

        if (c.equals("")) {
            String error = "Cheese name is required";
            model.addAttribute("error", error);
            return "/cheese/add";
        }
        else {
            CheeseData.add(newCheese);
            return "redirect:";
        }
    }

    @RequestMapping(value = "remove", method = RequestMethod.GET)
    public String displayRemoveCheeseForm( Model model) {
            model.addAttribute("cheeses", CheeseData.getAll());
            model.addAttribute("title", "Remove Cheese");
            return "cheese/remove";

    }

    @RequestMapping(value = "remove", method = RequestMethod.POST)
    public String processRemoveCheeseForm(@RequestParam int[] cheeseIds) {
        for (int cheeseId: cheeseIds) {
            CheeseData.remove(cheeseId);
        }


        return "redirect:";
    }
    @RequestMapping(value = "edit/{cheeseId}", method = RequestMethod.GET)
    public String displayEditForm(Model model, @PathVariable int cheeseId){
         Cheese currentCheese = CheeseData.getById(cheeseId);
         model.addAttribute("cheese", currentCheese);
         return "cheese/edit";
    }


   @RequestMapping(value = "edit", method = RequestMethod.POST)
    public String processEditForm(Model model, int cheeseId, String name, String description ) {

        Cheese currentCheese = CheeseData.getById(cheeseId);
        currentCheese.setName(name);
        currentCheese.setDescription(description);
        CheeseData.remove(cheeseId);
        CheeseData.add(currentCheese);
        return "redirect:";
    }



}