package com.apipost.api_post.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.apipost.api_post.DTOs.PostRequestDTO;
import com.apipost.api_post.DTOs.PostResponseDTO;
import com.apipost.api_post.Models.Post;
import com.apipost.api_post.Repository.PostRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    // Crear un Post
    public PostResponseDTO createPost(PostRequestDTO requestDTO) {
        Post post = Post.builder()
                .author(requestDTO.getAuthor())
                .content(requestDTO.getContent())

                .build();

        Post savedPost = postRepository.save(post);
        return mapToDTO(savedPost);
    }

    // Obtener todos los posts
    public List<PostResponseDTO> getAllPost() {
        return postRepository.findAll()
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    // 2.3 Obtener un post por ID
    public PostResponseDTO getPostById(UUID id) {
        Post post = findPostOrThrow404(id);
        return mapToDTO(post);

    }

    // Actualizar un post
    public PostResponseDTO updatePost(UUID id, PostRequestDTO requestDTO) {
        Post post = findPostOrThrow404(id);
        post.setContent(requestDTO.getContent());
        if (requestDTO.getAuthor() != null) {
            post.setAuthor(requestDTO.getAuthor());
        }

        Post updatedPost = postRepository.save(post);
        return mapToDTO(updatedPost);
    }

    // Eliminar un post
    public void deletePost(UUID id) {
        Post post = findPostOrThrow404(id);
        postRepository.delete(post);
    }

    // Dar like a un post
    public PostResponseDTO likePost(UUID id) {
        Post post = findPostOrThrow404(id);
        post.setLikes(post.getLikes() + 1);

        Post updatedPost = postRepository.save(post);
        return mapToDTO(updatedPost);
    }

    // Buscar posts por autor
    public List<PostResponseDTO> getPostsByAuthor(String author) {
        return postRepository.findByAuthor(author)
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    // Método para buscar el post o lanzar error 404 automáticamente
    private Post findPostOrThrow404(UUID id) {
        return postRepository.findById(id)
                .orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Post no encontrado con el ID: " + id));
    }

    // Método para convertir la Entidad a DTO de salida
    private PostResponseDTO mapToDTO(Post post) {
        return PostResponseDTO.builder()
                .id(post.getId())
                .author(post.getAuthor())
                .content(post.getContent())
                .likes(post.getLikes())
                .createdAt(post.getCreatedAt())
                .build();
    }
}
