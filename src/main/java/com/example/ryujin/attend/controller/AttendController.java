package com.example.ryujin.attend.controller;

import com.example.ryujin.attend.dto.AttendRequest;
import com.example.ryujin.attend.dto.AttendResponse;
import com.example.ryujin.attend.entity.Attend;
import com.example.ryujin.attend.helper.PageWrapper;
import com.example.ryujin.attend.service.AttendService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class AttendController {

    private final AttendService attendService;


    @GetMapping("/attend/new")
    public String newAttendForm() {
        return "attend-form";
    }

    @PostMapping("/attend/create")
    public String createAttend(@Valid @ModelAttribute AttendRequest attendRequest) {
        attendService.createAttend(attendRequest);
        return "redirect:/attend-list";
    }

    @GetMapping("/attend-list")
    public String getAttendList(
            @PageableDefault(size = 5, sort = "timestamp", direction = Sort.Direction.DESC)
            Pageable pageable,
            Model model) {

        Page<AttendResponse> attendPage = attendService.getAttendList(pageable);
        model.addAttribute("attendList", attendPage.getContent());
        model.addAttribute("pageInfo", new PageWrapper<>(attendPage, 5));
        return "attend-list";
    }

}