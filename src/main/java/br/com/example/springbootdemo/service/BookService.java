package br.com.example.springbootdemo.service;

import br.com.example.springbootdemo.entity.BookEntity;
import br.com.example.springbootdemo.entity.dto.BookDTO;
import br.com.example.springbootdemo.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    public List<BookEntity> findAll() {
        return bookRepository.findAll();
    }

    public BookEntity findById(Long id) {
        Optional<BookEntity> entity = bookRepository.findById(id);
        if (entity.isPresent()){
            return entity.get();
        }
        throw new RuntimeException();
    }

    public BookEntity save(BookDTO dto) {

        BookEntity entity = new BookEntity();
        entity.setName(dto.getName());
        entity.setYear(dto.getYear());
        entity.setEdition(dto.getEdition());
        entity.setAuthor(dto.getAuthor());
        return bookRepository.save(entity);
    }

    public BookEntity update(BookEntity entity, BookDTO dto) {
        entity.setName(dto.getName());
        entity.setYear(dto.getYear());
        entity.setEdition(dto.getEdition());
        entity.setAuthor(dto.getAuthor());
        return bookRepository.save(entity);
    }

    public void delete(Long id) {
        bookRepository.deleteById(id);
    }
}
