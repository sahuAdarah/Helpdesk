package com.ai.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ai.service.AiService;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@RestController
@RequestMapping("/api/helpdesk")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5173")
public class AiController {

    private final AiService service;
//
//    @PostMapping("/chat")
//    public ResponseEntity<String> getResponse(@RequestBody String query, @RequestHeader("ConversationId") String conversationId) {
//        return ResponseEntity.ok(service.getResponseFromAssistant(query,conversationId));
//    }

//    @PostMapping("/chat")
//    public Flux<String> getResponse(@RequestBody String query, @RequestHeader("ConversationId") String conversationId) {
//        return service.getResponseFromAssistant(query,conversationId);
//    }
    @PostMapping(
    	    value = "/chat",
    	    produces = "text/plain"
    	)
    	public Flux<String> chat(
    	        @RequestBody String userMessage,
    	        @RequestHeader("ConversationId") String conversationId
    	) {
    	    return service.getResponseFromAssistant(userMessage, conversationId);
    	}

}