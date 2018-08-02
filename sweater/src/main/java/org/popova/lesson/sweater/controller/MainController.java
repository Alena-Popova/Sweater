package org.popova.lesson.sweater.controller;

import org.popova.lesson.sweater.domain.Message;
import org.popova.lesson.sweater.domain.User;
import org.popova.lesson.sweater.repos.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Controller
public class MainController {
    @Autowired
    private MessageRepository messageRepository;

    @Value("${upload.path}")
    private String uploadPath;

    @GetMapping("/")
    public String greeting(Map<String, Object> model) {
        return "greeting";
    }


    @GetMapping("/main")
    public String main(@RequestParam(required = false, defaultValue = "") String filter, Model model) {
        Iterable<Message> messages = messageRepository.findAll();

        if(filter != null && !filter.isEmpty()) {
            messages =  messageRepository.findByTag(filter);
        } else {
            messages = messageRepository.findAll();
        }

        model.addAttribute("messages", messages);
        model.addAttribute("filter", filter);
        return "main";
    }

    @PostMapping("/main")
    public String add(
            @AuthenticationPrincipal User user,
            @Valid Message message,
            BindingResult bindingResult,
            Model model,
            @RequestParam("file") MultipartFile file
    ) throws IOException {
        message.setAuthor(user);

        if (bindingResult.hasErrors()) {
            model.mergeAttributes(ControllerUtils.getErrors(bindingResult));
            model.addAttribute("message", message);
        } else {
            if (file != null && !file.getOriginalFilename().isEmpty()) {
                File upliadDir = new File(uploadPath);
                if (!upliadDir.exists()) {
                    upliadDir.mkdir();
                }
                String uuDir = UUID.randomUUID().toString();
                String resultFilename = uuDir + "." + file.getOriginalFilename();
                file.transferTo(new File(uploadPath + "/" + resultFilename));
                message.setFilename(resultFilename);
            }
            model.addAttribute("message", null);
            messageRepository.save(message);
        }
        Iterable<Message> messages = messageRepository.findAll();
        model.addAttribute("messages", messages);
        return "main";
    }


}
