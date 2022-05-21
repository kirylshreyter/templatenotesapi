package com.kirylshreyter.templatenotesapi.controller.v1;

import com.kirylshreyter.templatenotesapi.assembler.UserModelAssembler;
import com.kirylshreyter.templatenotesapi.event.OnRegistrationCompleteEvent;
import com.kirylshreyter.templatenotesapi.exception.UserAlreadyExistException;
import com.kirylshreyter.templatenotesapi.exception.UserInvalidTokenException;
import com.kirylshreyter.templatenotesapi.exception.UserTokenExpiredException;
import com.kirylshreyter.templatenotesapi.model.SignUpDetails;
import com.kirylshreyter.templatenotesapi.model.User;
import com.kirylshreyter.templatenotesapi.model.VerificationToken;
import com.kirylshreyter.templatenotesapi.service.SignUpService;
import com.kirylshreyter.templatenotesapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.MessageSource;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.*;

@RestController
@CrossOrigin
@RequestMapping("/sign_up")
public class SignUpController {
    @Autowired
    private UserModelAssembler assembler;

    @Autowired
    private SignUpService signUpService;

    @Autowired
    private UserService userService;

    @Autowired
    ApplicationEventPublisher eventPublisher;

    @Autowired
    private MessageSource messages;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody @Valid SignUpDetails signUpDetails, BindingResult bindingResult, HttpServletRequest request) {
        if (bindingResult.hasErrors()) return ResponseEntity.badRequest().body(bindingResult.getAllErrors());
        if (userService.existsByEmail(signUpDetails.getEmail()))
            throw new UserAlreadyExistException(signUpDetails.getEmail());
        User user = signUpService.createAccount(signUpDetails);
        String appUrl = request.getContextPath();
        eventPublisher.publishEvent(new OnRegistrationCompleteEvent(user, request.getLocale(), appUrl));
        EntityModel<User> entityModel = assembler.toModel(user);
        return ResponseEntity.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(entityModel);
    }

    @GetMapping("/confirm")
    public ResponseEntity<?> confirm(HttpServletRequest request, @RequestParam("token") String token) {
        Locale locale = request.getLocale();

        VerificationToken verificationToken = userService.getVerificationToken(token);
        if (verificationToken == null) {
            String message = messages.getMessage("auth.message.invalidToken", null, locale);
            throw new UserInvalidTokenException(message);
        }

        User user = verificationToken.getUser();
        Calendar cal = Calendar.getInstance();
        if ((verificationToken.getExpiryDate().getTime() - cal.getTime().getTime()) <= 0) {
            String message = messages.getMessage("auth.message.expired", null, locale);
            throw new UserTokenExpiredException(message);
        }

        user.setEnabled(true);
        userService.saveUser(user);
        HashMap<String, List<String>> result = new HashMap<>();
        List<String> resultMessages = new ArrayList<>();
        resultMessages.add(messages.getMessage("auth.message.confirmed", null, locale));
        result.put("messages", resultMessages);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @InitBinder
    private void initBinder(WebDataBinder dataBinder) {
        dataBinder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }
}
