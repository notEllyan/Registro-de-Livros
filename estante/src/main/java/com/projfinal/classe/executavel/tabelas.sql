Create table autorjdbc(
    codAutor Integer generated always as identity,
    nome varChar(255)
);

Create table livrojdbc(
    codAutor Integer references autorjdbc,
    titulo varChar(255),
    codLivro Integer generated always as identity
);