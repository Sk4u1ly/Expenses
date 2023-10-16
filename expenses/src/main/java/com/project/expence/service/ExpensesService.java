package com.project.expence.service;


import com.project.expence.entity.Expenses;
import com.project.expence.reposittory.ExpensesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class ExpensesService {

    @Autowired
    private ExpensesRepository expensesRepo;

    public Expenses saveFile(MultipartFile file) {
        try {
            String filename = file.getOriginalFilename();
            Expenses expenses = new Expenses(filename, file.getContentType(), file.getBytes());
            return expensesRepo.save(expenses);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Optional<Expenses> getFile(Integer fileId) {
        return expensesRepo.findById(fileId);
    }

    public List<Expenses> getFiles() {
        return expensesRepo.findAll();
    }
}

