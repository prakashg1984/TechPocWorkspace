package com.pg.ai.mcp.client.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.ai.azure.openai.AzureOpenAiChatModel;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.mcp.SyncMcpToolCallbackProvider;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/springai/client")
public class ChatController {

    private final AzureOpenAiChatModel chatModel;

    private final SyncMcpToolCallbackProvider toolCallbackProvider;

    @GetMapping("/ask")
    public String ask(@RequestParam(name = "query") String query) {

        return ChatClient.create(chatModel)
                .prompt(query)
                .toolCallbacks(toolCallbackProvider)
                .call()
                .content();

    }
}
