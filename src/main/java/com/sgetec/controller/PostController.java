package com.sgetec.controller;

import com.sgetec.model.Post;
import com.sgetec.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@RestController
public class PostController {

    @Autowired // → Injeção de dependência
    private PostRepository postRepository;

    @PostMapping("/posts")
    @ResponseStatus(HttpStatus.CREATED)
    public Post create(@RequestBody Post post, @AuthenticationPrincipal UserDetails userDetails) {
        // Mantendo referência do author ↓
        post.setAuthor(userDetails.getUsername());
        return postRepository.save(post);
    }

    @PostMapping("/posts/{id}")
    public Post editPost(@PathVariable Long id, @RequestBody Post postBody) {
        Optional<Post> result = postRepository.findById(id);
        Post post = null;

        if (result.isPresent()) {
            post = result.get();
            post.setContent(postBody.getContent());
            post.setPublicationDate(postBody.getPublicationDate());
            post.setAuthor(postBody.getAuthor());
            postRepository.save(post);
        }

        return post;
    }

    @GetMapping("/posts")
    public List<Post> listPosts(@RequestHeader("ListOption") String postsOptions,
                           @AuthenticationPrincipal UserDetails userDetails) {
        List<Post> postsList = postRepository.findAll();

        if (postsOptions.equals("ascending")) {
            // Retorna posts em ordem crescente de publicação
            postsList.sort(Comparator.comparing(Post::getPublicationDate));
            return postsList;

        } else if (postsOptions.equals("onlyMyPosts")) {
            // Retorna posts na ordem mais recente de publicação do atual usuário autenticado
            List<Post> onlyMyPosts = new ArrayList<>();
            for (Post post : postsList) {
                if (post.getAuthor().equals(userDetails.getUsername()))
                    onlyMyPosts.add(post);
            }
            onlyMyPosts.sort((o1, o2) -> o2.getPublicationDate().compareTo(o1.getPublicationDate()));
            return onlyMyPosts;
        }

        // Caso nenhum valor é especificado no header retorna posts na ordem mais recente de publicação
        postsList.sort((o1, o2) -> o2.getPublicationDate().compareTo(o1.getPublicationDate()));
        return postsList;
    }

    @GetMapping("/posts/{id}")
    public Optional<Post> getPost(@PathVariable Long id) {
        return postRepository.findById(id);
    }

    @DeleteMapping("/posts/{id}")
    public void deletePost(@PathVariable Long id) {
        Optional<Post> post = postRepository.findById(id);
        if (post.isPresent())
            postRepository.deleteById(id);
    }

}
