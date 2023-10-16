package com.project.expence.controller;

import com.project.expence.entity.Expenses;
import com.project.expence.service.ExpensesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
public class ExpensesController {

    @Autowired
    private ExpensesService expensesService;

    @GetMapping("/expenses")
    public String get(Model model) {
        List<Expenses> exps= expensesService.getFiles();
        model.addAttribute("exps", exps);
        return "exp";
    }


    @PostMapping("/uploadFile")
    public String uploadMultipleFiles(@RequestParam("files") MultipartFile[] files) {
        for (MultipartFile file : files)
            expensesService.saveFile(file);
        return "redirect:/expenses";
    }
    @GetMapping("/downloadFile/{fileId}")
    public ResponseEntity<ByteArrayResource> downloadFile(@PathVariable Integer fileId) {
      Expenses exp = expensesService.getFile(fileId).get();
            return ResponseEntity.ok()
                    .contentType(MediaType.parseMediaType(exp.getFileType()))
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + exp.getFileName() + "\"")
                    .body(new ByteArrayResource(exp.getData()));

    }
}


