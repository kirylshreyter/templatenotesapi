package com.kirylshreyter.templatenotesapi.controller.v1;

import com.kirylshreyter.templatenotesapi.assembler.UserModelAssembler;
import com.kirylshreyter.templatenotesapi.exception.UserAlreadyExistException;
import com.kirylshreyter.templatenotesapi.model.SignUpDetails;
import com.kirylshreyter.templatenotesapi.model.User;
import com.kirylshreyter.templatenotesapi.service.SignUpService;
import com.kirylshreyter.templatenotesapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@CrossOrigin
public class SignUpController {
    @Autowired
    private UserModelAssembler assembler;

    @Autowired
    private SignUpService signUpService;

    @Autowired
    private UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<?> createUser(@RequestBody @Valid SignUpDetails signUpDetails, BindingResult bindingResult) throws Exception {
        if (bindingResult.hasErrors()) return ResponseEntity.badRequest().body(bindingResult.getAllErrors());
        if (userService.existsByEmail(signUpDetails.getEmail())) throw new UserAlreadyExistException(signUpDetails.getEmail());
        EntityModel<User> entityModel = assembler.toModel(signUpService.createAccount(signUpDetails));
        return ResponseEntity.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(entityModel);
    }

    @InitBinder
    public void initBinder(WebDataBinder dataBinder) {
        dataBinder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }
}
