START TRANSACTION;
INSERT INTO author(name) VALUES('Noam Chomsky');
INSERT INTO author(name) VALUES('Naomi Klein');
INSERT INTO author(name) VALUES('Ursula K. Le Guin');

INSERT INTO genre(name) VALUES('Fiction');
INSERT INTO genre(name) VALUES('Politics');
INSERT INTO genre(name) VALUES('Philosophy');

INSERT INTO publisher(name) VALUES('Springer');
INSERT INTO publisher(name) VALUES('Wiley');
INSERT INTO publisher(name) VALUES('Pearson');

INSERT INTO language(name) VALUES('Portuguese');
INSERT INTO language(name) VALUES('English');
INSERT INTO language(name) VALUES('Mandarin');

COMMIT;

START TRANSACTION;
INSERT INTO book(title, created_at, updated_at, created_by, copies_available,
  description, isbn, pages, publication_date, author_id, publisher_id, genre_id,
  language_id) VALUES('Manufacturing Consent: The Political Economy of the Mass Media',
  '2018-09-27', '2018-09-27', 1, 10, 'lorem ipsum', '0375714499', 412, '1988-01-01',
  1, 1, 2, 2);

INSERT INTO book(title, created_at, updated_at, created_by, copies_available,
  description, isbn, pages, publication_date, author_id, publisher_id, genre_id,
  language_id) VALUES('Understanding Power: The Indispensable Chomsky ',
  '2018-09-27', '2018-09-27', 1, 10, 'lorem ipsum', '1565847032', 391, '2002-02-01',
  1, 2, 2, 2);

INSERT INTO book(title, created_at, updated_at, created_by, copies_available,
  description, isbn, pages, publication_date, author_id, publisher_id, genre_id,
  language_id) VALUES('The Shock Doctrine: The Rise of Disaster Capitalism',
  '2018-09-27', '2018-09-27', 1, 10, 'lorem ipsum', '0805079831', 558, '2007-09-18',
  2, 1, 1, 1);

INSERT INTO book(title, created_at, updated_at, created_by, copies_available,
  description, isbn, pages, publication_date, author_id, publisher_id, genre_id,
  language_id) VALUES('This Changes Everything: Capitalism vs. The Climate',
  '2018-09-27', '2018-09-27', 1, 10, 'lorem ipsum', '1451697384', 576, '2014-09-16',
  2, 3, 1, 1);

INSERT INTO book(title, created_at, updated_at, created_by, copies_available,
  description, isbn, pages, publication_date, author_id, publisher_id, genre_id,
  language_id) VALUES('A Wizard of Earthsea (Earthsea Cycle #1)',
  '2018-09-27', '2018-09-27', 1, 10, 'lorem ipsum', '0553383043', 183, '2004-09-28',
  3, 1, 3, 3);

INSERT INTO book(title, created_at, updated_at, created_by, copies_available,
  description, isbn, pages, publication_date, author_id, publisher_id, genre_id,
  language_id) VALUES('The Dispossessed ',
  '2018-09-27', '2018-09-27', 1, 10, 'lorem ipsum', '0061054887', 387, '1994-10-20',
  3, 1, 3, 3);
COMMIT;