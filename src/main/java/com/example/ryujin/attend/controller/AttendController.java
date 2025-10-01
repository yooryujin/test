package com.example.ryujin.attend.controller;

import com.example.ryujin.attend.dto.AttendRequest;
import com.example.ryujin.attend.dto.AttendResponse;
import com.example.ryujin.attend.service.AttendService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class AttendController {

    private final AttendService attendService;

    @GetMapping("/attend/new")
    public String newAttendForm() {
        return "attend-form";
    }

    @GetMapping("/attend-list")
    public String attendList(Model model) {
        List<AttendResponse> attendList = attendService.findAllAttends();
        model.addAttribute("attendList", attendList);
        return "attend-list";
    }


    @PostMapping("/attend/create")
    public String createAttend(@ModelAttribute AttendRequest attendRequest) {
        attendService.createAttend(attendRequest);
        return "redirect:/attend-list";
    }


}
