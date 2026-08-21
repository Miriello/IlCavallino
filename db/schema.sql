

CREATE DATABASE IF NOT EXISTS ilcavallino CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE ilcavallino;

-- tabella dei fornitori
CREATE TABLE IF NOT EXISTS fornitori (
    partita_iva     VARCHAR(11)  PRIMARY KEY,
    ragione_sociale VARCHAR(200) NOT NULL,
    email           VARCHAR(150),
);


-- tabella degli ingredienti

CREATE TABLE IF NOT EXISTS ingredienti (
    id       INT AUTO_INCREMENT PRIMARY KEY,
    nome     VARCHAR(200) NOT NULL,
    scadenza DATE
    );

-- tabella degli allergeni
CREATE TABLE IF NOT EXISTS allergeni (
    codiceAllergene VARCHAR(200) PRIMARY KEY,
    nome VARCHAR(200) NOT NULL,
);

-- tabella piatti
CREATE TABLE IF NOT EXISTS piatti (
    id       INT AUTO_INCREMENT PRIMARY KEY,
    nome     VARCHAR(200)   NOT NULL,
    prezzo   DECIMAL(10,2)  NOT NULL
    );

-- tabella vendite
CREATE TABLE IF NOT EXISTS vendite(
    id INT AUTO_INCREMENT PRIMARY KEY,

);

-- tabella persone
CREATE TABLE IF NOT EXISTS persone(
    cf VARCHAR(200) PRIMARY KEY,
    nome VARCHAR(200) NOT NULL,
    cognome VARCHAR(200) NOT NULL,
    idRuolo VARCHAR(200) NOT NULL,
    FOREIGN KEY(idRuolo) REFERENCES ruoli(idRuolo) ON DELETE CASCADE
);

-- tabella ruoli
CREATE TABLE IF NOT EXISTS ruoli(
    idRuolo INT AUTO_INCREMENT PRIMARY KEY,
    nomeRuolo VARCHAR(200) NOT NULL
);

-- tabella degli ingredienti forniti da fornitore

CREATE TABLE IF NOT EXISTS ingredienti_fornitore(
    partitaIvaFornitore VARCHAR(200) NOT NULL,
    idIngrediente INT NOT NULL,
    PRIMARY KEY (partitaIvaFornitore,idIngrediente),
    FOREIGN KEY(partitaIvaFornitore) REFERENCES fornitori(partitaIva)ON DELETE CASCADE,
    FOREIGN KEY (idIngrediente) REFERENCES ingredienti(id) ON DELETE CASCADE
)

-- tabella degli allergeni degli ingredienti

CREATE TABLE IF NOT EXISTS allergeni_ingrediente(
    codiceAllergene VARCHAR(200) NOT NULL,
    idIngrediente INT NOT NULL,
    PRIMARY KEY(codiceAllergene,idIngrediente),
    FOREIGN KEY (codiceAllergene) REFERENCES allergeni(codiceAllergene) ON DELETE CASCADE,
    FOREIGN KEY (idIngrediente) REFERENCES ingredienti(id) ON DELETE CASCADE
);

-- tabella ingredienti del piatto

CREATE TABLE IF NOT EXISTS piatto_ingredienti(
    piatto_id      INT NOT NULL,
    idIngrediente INT NOT NULL,
    PRIMARY KEY (piatto_id, idIngrediente),
    FOREIGN KEY (piatto_id) REFERENCES piatti(id) ON DELETE CASCADE
    FOREIGN KEY (idIngrediente) REFERENCES ingredienti(id) ON DELETE CASCADE
);

-- tabella dei ruoli del personale

CREATE TABLE IF NOT EXISTS persona_ruolo(
    cfPersona VARCHAR(200) NOT NULL,
    idRuolo VARCHAR(200) NOT NULL,
    PRIMARY KEY (cfPersona,idRuolo)
    FOREIGN KEY (cfPersona) REFERENCES persone(cf) ON DELETE CASCADE,
    FOREIGN KEY (idRuolo) REFERENCES ruoli(id) ON DELETE CASCADE,
);

-- tabella delle scorte e delle soglie minime

CREATE TABLE IF NOT EXISTS scorte(
    idIngrediente INT NOT NULL,
    quantita INT NOT NULL,
    sogliaMinima INT NOT NULL,
    PRIMARY KEY(idIngrediente),
    FOREIGN KEY (idIngrediente) REFERENCES ingredienti(id) ON DELETE CASCADE,
);



