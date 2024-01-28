package com.heydiecode.tictactoe.controller;

import com.heydiecode.tictactoe.service.TictactoeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class TictactoeController {

    Logger log = LoggerFactory.getLogger(TictactoeController.class);
    private TictactoeService service;

    @GetMapping("/tictactoe")
    public String getGame(@RequestParam(name = "size", defaultValue = "3")int size, Model model) {

    if(service == null) {
       service = new TictactoeService(size);
    }

        if(service.checkWinV2()) {
            model.addAttribute("winner", service.getWinner());

        }
        else if (service.checkDraw()) {
            model.addAttribute("draw", true);
        }
        model.addAttribute("game", service);
        return "tictactoe";
    }

    @RequestMapping(value = "tictactoe/move", method = {RequestMethod.GET, RequestMethod.POST})
    public String makeMove(int row, int col) {
        if(service != null) {
            service.makeMove(row, col);
        }

        return "redirect:/tictactoe";
    }

    @RequestMapping(value = "/tictactoe/restart", method = {RequestMethod.GET, RequestMethod.POST})
    public String restartGame(@RequestParam(name = "size", defaultValue = "3")int size) {
        service = new TictactoeService(size);
        return "redirect:/tictactoe";
    }
}
