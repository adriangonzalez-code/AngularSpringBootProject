package com.driagon.springboot.backend.chat.app.controllers;

import com.driagon.springboot.backend.chat.app.documents.Mensaje;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import java.util.Date;

@Controller
public class ChatController {

    @MessageMapping("/mensage")
    @SendTo("/chat/mensaje")
    public Mensaje recibeMensaje(Mensaje mensaje) {
        mensaje.setFecha(new Date().getTime());
        mensaje.setTexto("Recibido por el broker: " + mensaje.getTexto());

        return mensaje;
    }
}