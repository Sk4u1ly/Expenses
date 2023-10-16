package com.project.expence.reposittory;

import com.project.expence.entity.Expenses;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ExpensesRepository extends JpaRepository<Expenses,Integer> {

}
