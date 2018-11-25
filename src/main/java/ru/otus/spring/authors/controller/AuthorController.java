package ru.otus.spring.authors.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.otus.spring.authors.model.Author;
import ru.otus.spring.authors.service.AuthorService;

import java.util.List;

@Controller
public class AuthorController {

    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping("/")
    public String redirectToRoot() {
        return "redirect:/list/authors";
    }

    @GetMapping("/list/authors")
    public String getAllAuthors(Model model) {
        List<Author> authors = authorService.findAllAuthors();
        model.addAttribute("authors", authors);
        return "list";
    }

    @GetMapping("/create/authors")
    public String createAuthorPage(Model model) {
        Author author = new Author();
        model.addAttribute("author", author);
        return "create";
    }

    @PostMapping("/api/authors")
    public String createAuthor(@ModelAttribute Author author) {
        authorService.createAuthor(author);
        return "redirect:/list/authors";
    }

    @GetMapping("/edit/authors/{id}")
    public String getAuthorPage(@PathVariable("id") int id, Model model) {
        Author author = authorService.findAuthorById(id);
        model.addAttribute("author", author);
        return "edit";
    }

    @PutMapping("/api/authors")
    public String getAuthor(@ModelAttribute Author author) {
        authorService.updateAuthor(author);
        return "redirect:/list/authors";
    }

    @GetMapping("delete/authors/{id}")
    public String deleteAuthorPage(@PathVariable("id") int id, Model model) {
        Author author = authorService.findAuthorById(id);
        model.addAttribute("author", author);
        return "delete";
    }

    @DeleteMapping("/api/authors/{id}")
    public String deleteAuthor(@PathVariable("id") int id) {
        authorService.deleteAuthorById(id);
        return "redirect:/list/authors";
    }
}
