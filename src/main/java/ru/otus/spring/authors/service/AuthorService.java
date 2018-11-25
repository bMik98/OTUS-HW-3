package ru.otus.spring.authors.service;

import org.springframework.stereotype.Service;
import ru.otus.spring.authors.model.Author;
import ru.otus.spring.authors.repository.AuthorRepository;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class AuthorService {

    private final AuthorRepository authorRepository;

    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public List<Author> findAllAuthors() {
        return authorRepository.findAll();
    }

    public Author findAuthorById(int id) {
        return getById(id);
    }

    private Author getById(int id) {
        return authorRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Unable to find an author by id: " + id));
    }

    public void updateAuthor(Author author) {
        Author authorToUpdate = getById(author.getId());
        authorToUpdate.setFirstName(author.getFirstName());
        authorToUpdate.setLastName(author.getLastName());
        authorRepository.save(author);
    }

    public void deleteAuthorById(int id) {
        authorRepository.deleteById(id);
    }

    public void createAuthor(Author author) {
        authorRepository.save(author);
    }
}
