package com.vpacheco.vlib.util;

import com.vpacheco.vlib.exception.AppException;
import com.vpacheco.vlib.model.*;
import com.vpacheco.vlib.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Component
public class DatabaseSeeder {
  @Autowired
  GenreRepository genreRepository;

  @Autowired
  UserRepository userRepository;

  @Autowired
  RoleRepository roleRepository;

  @Autowired
  PasswordEncoder passwordEncoder;

  @Autowired
  CustomerRepository customerRepository;

  @Autowired
  AuthorRepository authorRepository;

  @Autowired
  PublisherRepository publisherRepository;

  @Autowired
  LanguageRepository languageRepository;

  @Autowired
  BookRepository bookRepository;

  private void seedGenres() {
    if (genreRepository.count() == 0) {
      List<String> genres = new ArrayList<>(Arrays.asList("Fiction",
          "Politics", "Philosophy"));

      for (String name : genres) {
        Genre genre = new Genre();
        genre.setName(name);
        genreRepository.save(genre);
      }
    }
  }

  private void seedAuthors() {
    if (authorRepository.count() == 0) {
      List<String> authors = new ArrayList<>(Arrays.asList("Noam Chomsky",
          "Naomi Klein", "Ursula K. Le Guin"));

      for (String name : authors) {
        Author author = new Author();
        author.setName(name);
        authorRepository.save(author);

      }
    }
  }

  private void seedPublishers() {
    if (publisherRepository.count() == 0) {
      List<String> publishers = new ArrayList<>(Arrays.asList("Springer",
          "Wiley", "Pearson"));

      for (String name : publishers) {
        Publisher publisher = new Publisher();
        publisher.setName(name);
        publisherRepository.save(publisher);
      }
    }
  }

  private void seedLanguages() {
    if (languageRepository.count() == 0) {
      List<String> languages = new ArrayList<>(Arrays.asList("Portuguese",
          "English", "Mandarin"));

      for (String name : languages) {
        Language language = new Language();
        language.setName(name);
        languageRepository.save(language);
      }
    }
  }

  private void seedAdminUser() {
    if (!userRepository.existsByUsername("admin"))
      seedUser("test admin", "admin", "admin@admin.pt",
        "sideways", RoleName.ROLE_ADMIN);
  }

  private void seedCustomerUser() {
    if (!userRepository.existsByUsername("customer")) {
      User user = seedUser("test customer", "customer",
          "customer@customer.pt", "sideways", RoleName.ROLE_USER);

      Customer customer = new Customer();
      customer.setUser(user);
      customerRepository.save(customer);
    }
  }

  private User seedUser(String name, String username, String email,
                        String password, RoleName roleName) {
    User user = new User(name, username, email, password);

    user.setPassword(passwordEncoder.encode(user.getPassword()));

    Role userRole = roleRepository.findByName(RoleName.ROLE_USER)
        .orElseThrow(() -> new AppException("User Role not set."));

    user.setRoles(Collections.singleton(userRole));

    return userRepository.save(user);
  }

  private void seedRoles() {
    if (roleRepository.count() == 0) {
      Role adminRole = new Role();
      adminRole.setName(RoleName.ROLE_ADMIN);
      roleRepository.save(adminRole);

      Role userRole = new Role();
      userRole.setName(RoleName.ROLE_USER);
      roleRepository.save(userRole);
    }
  }

  private void seedBooks() {
    if (bookRepository.count() == 0) {
      List<Author> authors = authorRepository.findAll();
      List<Genre> genres = genreRepository.findAll();
      List<Publisher> publishers = publisherRepository.findAll();
      List<Language> languages = languageRepository.findAll();

      Book book1 = new Book();
      book1.setTitle("Manufacturing Consent: The Political Economy of the Mass Media");
      book1.setPublicationDate(LocalDate.of(1988, 1, 1));
      book1.setCopiesAvailable(10);
      book1.setIsbn("0805079831");
      book1.setPages(412);
      book1.setAuthor(authors.get(0));
      book1.setPublisher(publishers.get(0));
      book1.setGenre(genres.get(1));
      book1.setLanguage(languages.get(1));
      bookRepository.save(book1);

      Book book2 = new Book();
      book2.setTitle("Understanding Power: The Indispensable Chomsky");
      book2.setPublicationDate(LocalDate.of(2002, 2,1));
      book2.setCopiesAvailable(10);
      book2.setIsbn("0375714499");
      book2.setPages(391);
      book2.setAuthor(authors.get(0));
      book2.setPublisher(publishers.get(1));
      book2.setGenre(genres.get(1));
      book1.setLanguage(languages.get(0));
      bookRepository.save(book2);

      Book book3 = new Book();
      book3.setTitle("This Changes Everything: Capitalism vs. The Climate");
      book3.setPublicationDate(LocalDate.of(2014,9,16));
      book3.setCopiesAvailable(10);
      book3.setIsbn("1565847032");
      book3.setPages(576);
      book3.setAuthor(authors.get(1));
      book3.setPublisher(publishers.get(2));
      book3.setGenre(genres.get(2));
      book3.setLanguage(languages.get(2));
      bookRepository.save(book3);

      Book book4 = new Book();
      book4.setTitle("The Dispossessed");
      book4.setPublicationDate(LocalDate.of(1994, 10, 20));
      book4.setCopiesAvailable(10);
      book4.setIsbn("0553383043");
      book4.setPages(387);
      book4.setAuthor(authors.get(2));
      book4.setPublisher(publishers.get(0));
      book4.setGenre(genres.get(2));
      book4.setLanguage(languages.get(2));
      bookRepository.save(book4);
    }
  }

  @EventListener
  public void seed(ContextRefreshedEvent event) {
    seedRoles();
    seedAdminUser();
    seedCustomerUser();
    seedGenres();
    seedAuthors();
    seedPublishers();
    seedLanguages();
    seedBooks();
  }
}
