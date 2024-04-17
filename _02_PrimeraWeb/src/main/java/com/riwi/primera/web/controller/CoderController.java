package com.riwi.primera.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.riwi.primera.web.entity.Coder;
import com.riwi.primera.web.services.CoderService;

@Controller
@RequestMapping("/")
public class CoderController {
    @Autowired
    private CoderService objcCoderService;

    //metodo para mostrar la lista y enviarle la lista de coders

    @GetMapping
    public String showViewGetAll(Model objModel){
        //llamo al servicio y guardo la vista de coders
        List<Coder> list=this.objcCoderService.findAll();
        objModel.addAttribute("coderList", list);//llave-valor
        return "viewCoder";
    }

    @GetMapping("/form")
    public String showViewFormCoder(){
        return "viewForm";
    }
}
