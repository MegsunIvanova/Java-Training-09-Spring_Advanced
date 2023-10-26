package bg.softuni.booksserver.service.impl;

import bg.softuni.booksserver.model.dto.AuthorDTO;
import bg.softuni.booksserver.model.dto.BookDTO;
import bg.softuni.booksserver.model.entity.AuthorEntity;
import bg.softuni.booksserver.model.entity.BookEntity;
import bg.softuni.booksserver.repository.AuthorRepository;
import bg.softuni.booksserver.repository.BookRepository;
import bg.softuni.booksserver.service.BooksService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BooksServiceImpl implements BooksService {
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    public BooksServiceImpl(BookRepository bookRepository,
                            AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }

    @Override
    public List<BookDTO> getAllBooks() {
        return bookRepository.findAll()
                .stream()
                .map(BooksServiceImpl::mapBookToDTO)
                .toList();
    }

    @Override
    public Optional<BookDTO> findBookById(Long id) {
        return bookRepository
                .findById(id)
                .map(BooksServiceImpl::mapBookToDTO);
    }

    @Override
    public void deleteBookById(Long id) {
        bookRepository.deleteById(id);
    }

    @Override
    public Long createBook(BookDTO bookDTO) {
        Optional<AuthorEntity> authorOpt = authorRepository
                .findByName(bookDTO.getAuthor().getName());

        BookEntity newBook = new BookEntity()
                .setAuthor(authorOpt.orElseGet(() -> authorRepository.save(
                        new AuthorEntity()
                                .setName(bookDTO.getAuthor().getName()))))
                .setIsbn(bookDTO.getIsbn())
                .setTitle(bookDTO.getTitle());

        newBook = bookRepository.save(newBook);

        return newBook.getId();
    }

    private static BookDTO mapBookToDTO(BookEntity bookEntity) {
        return new BookDTO().setId(bookEntity.getId())
                .setTitle(bookEntity.getTitle())
                .setIsbn(bookEntity.getIsbn())
                .setAuthor(mapAuthorToDTO(bookEntity.getAuthor()));
    }

    private static AuthorDTO mapAuthorToDTO(AuthorEntity authorEntity) {
        return new AuthorDTO().setName(authorEntity.getName());
    }
}
