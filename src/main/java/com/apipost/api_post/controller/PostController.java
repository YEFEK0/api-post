package com.apipost.api_post.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.apipost.api_post.DTOs.PostRequestDTO;
import com.apipost.api_post.DTOs.PostResponseDTO;
import com.apipost.api_post.Service.PostService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    // Crear un Post
    @PostMapping
    public ResponseEntity<PostResponseDTO> createPost(@Valid @RequestBody PostRequestDTO requestDTO) {
        PostResponseDTO response = postService.createPost(requestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    // Obtener todos los posts
    @GetMapping
    public ResponseEntity<List<PostResponseDTO>> getAllPost() {
        return ResponseEntity.ok(postService.getAllPost());
    }

    // Obtener un post por ID
    @GetMapping("/{id}")
    public ResponseEntity<PostResponseDTO> getPostById(@PathVariable UUID id) {
        return ResponseEntity.ok(postService.getPostById(id));
    }

    // Actualizar un post
    @PutMapping("/{id}")
    public ResponseEntity<PostResponseDTO> updatePost(@PathVariable UUID id,
            @Valid @RequestBody PostRequestDTO requestDTO) {
        return ResponseEntity.ok(postService.updatePost(id, requestDTO));
    }

    // Eliminar un post
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePost(@PathVariable UUID id) {
        postService.deletePost(id);
        // Cumple exactamente con el requisito: Respuesta esperada 204 NO CONTENT
        return ResponseEntity.noContent().build();
    }

    // Dar like a un post
    @PostMapping("/{id}/like")
    public ResponseEntity<PostResponseDTO> likePost(@PathVariable UUID id) {
        return ResponseEntity.ok(postService.likePost(id));
    }

    // Buscar posts por autor
    @GetMapping("/author/{author}")
    public ResponseEntity<List<PostResponseDTO>> getPostsByAuthor(@PathVariable String author) {
        return ResponseEntity.ok(postService.getPostsByAuthor(author));
    }
}
