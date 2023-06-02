package com.victormorais.ecommerce.controllers;

import com.victormorais.ecommerce.domain.UsuarioDTO;
import com.victormorais.ecommerce.services.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/user")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @RequestMapping(value = "/cadastrar", method = RequestMethod.POST)
    public ResponseEntity<UsuarioDTO> cadastrarUsuario(@RequestBody @Valid UsuarioDTO usuarioDTO){

        UsuarioDTO response = usuarioService.cadastrarUsuario(usuarioDTO);

        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(response);

    }

    @RequestMapping(value = "/listar", method = RequestMethod.GET)
    public ResponseEntity<List<UsuarioDTO>> listarUsuario(@RequestParam Map<String, String> params){

        List<UsuarioDTO> users = usuarioService.listarUsuarios(params);

        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(users);

    }

    @RequestMapping(value = "/{user_id}", method = RequestMethod.DELETE)
    public ResponseEntity<String> deletarUsuario(@PathVariable("user_id") String userId){

        String s = usuarioService.deletarUsuario(UUID.fromString(userId));

        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(s);

    }

    @RequestMapping(value = "/{user_id}", method = RequestMethod.PUT)
    public ResponseEntity<UsuarioDTO> updateUsuario(@PathVariable("user_id") String userId,
                                                @RequestBody UsuarioDTO usuarioDTO){

        UsuarioDTO response = usuarioService.updateUsuario(UUID.fromString(userId), usuarioDTO);

        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(response);

    }

}
