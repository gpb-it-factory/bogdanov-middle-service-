package ru.gazprombank.payhub.middleservice.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.gazprombank.payhub.middleservice.client.AccountClient;
import ru.gazprombank.payhub.middleservice.dto.CreateAccountRequestDto;
import ru.gazprombank.payhub.middleservice.dto.ResponseMessage;

@Slf4j
@RestController
@RequestMapping("/api/v1/users/{id}/accounts")
@RequiredArgsConstructor
public class AccountController {
    private final AccountClient accountClient;

    @PostMapping
    public ResponseMessage createAccount(@PathVariable("id") Long userId,
                                         @RequestBody @Valid CreateAccountRequestDto requestDto) {
        log.info("Create user: {}", requestDto.accountName());
        accountClient.create(userId, requestDto);
        return new ResponseMessage(String.format("Аккаунт %s создан", requestDto.accountName()));
    }
}