CREATE DATABASE IF NOT EXISTS ilcavallino CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE ilcavallino;

-- tabella dei fornitori
CREATE TABLE IF NOT EXISTS fornitori (
    partitaIva     VARCHAR(11)  PRIMARY KEY,
    ragione_sociale VARCHAR(200) NOT NULL,
    email           VARCHAR(150)
);


-- tabella degli ingredienti

CREATE TABLE IF NOT EXISTS ingredienti (
    id       INT AUTO_INCREMENT PRIMARY KEY,
    nome     VARCHAR(200) NOT NULL,
    scadenza DATE
);

-- tabella degli allergeni
CREATE TABLE IF NOT EXISTS allergeni (
    codiceAllergene INT PRIMARY KEY,
    nome VARCHAR(200) NOT NULL
);

-- tabella piatti
CREATE TABLE IF NOT EXISTS piatti (
    id       INT AUTO_INCREMENT PRIMARY KEY,
    nome     VARCHAR(200)   NOT NULL,
    prezzo   DECIMAL(10,2)  NOT NULL
);

-- tabella ruoli
CREATE TABLE IF NOT EXISTS ruoli(
    idRuolo INT AUTO_INCREMENT PRIMARY KEY,
    nomeRuolo VARCHAR(200) NOT NULL
);

-- tabella persone
CREATE TABLE IF NOT EXISTS persone(
    cf VARCHAR(200) PRIMARY KEY,
    nome VARCHAR(200) NOT NULL,
    cognome VARCHAR(200) NOT NULL,
    idRuolo INT NOT NULL,
    FOREIGN KEY(idRuolo) REFERENCES ruoli(idRuolo) ON DELETE CASCADE
);

-- tabella vendite
CREATE TABLE IF NOT EXISTS vendite(
    id INT AUTO_INCREMENT PRIMARY KEY,
    cfOperatore VARCHAR(16) NOT NULL,
    dataOra TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (cfOperatore) REFERENCES persone(cf)
    );

-- tabella degli ingredienti forniti da fornitore
CREATE TABLE IF NOT EXISTS ingredienti_fornitore(
    partitaIvaFornitore VARCHAR(11) NOT NULL,
    idIngrediente INT NOT NULL,
    PRIMARY KEY (partitaIvaFornitore,idIngrediente),
    FOREIGN KEY(partitaIvaFornitore) REFERENCES fornitori(partitaIva)ON DELETE CASCADE,
    FOREIGN KEY (idIngrediente) REFERENCES ingredienti(id) ON DELETE CASCADE
);

-- tabella degli allergeni degli ingredienti
CREATE TABLE IF NOT EXISTS allergeni_ingrediente(
    codiceAllergene INT NOT NULL,
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
    FOREIGN KEY (piatto_id) REFERENCES piatti(id) ON DELETE CASCADE,
    FOREIGN KEY (idIngrediente) REFERENCES ingredienti(id) ON DELETE CASCADE
);

-- tabella delle scorte e delle soglie minime
CREATE TABLE IF NOT EXISTS scorte(
    idIngrediente INT NOT NULL,
    quantita INT NOT NULL,
    sogliaMinima INT NOT NULL,
    PRIMARY KEY(idIngrediente),
    FOREIGN KEY (idIngrediente) REFERENCES ingredienti(id) ON DELETE CASCADE
);

-- tabelle delle vendite dei piatti
CREATE TABLE IF NOT EXISTS vendita_piatto(
    idVendita INT NOT NULL,
    idPiatto INT NOT NULL,
    quantita INT NOT NULL,
    PRIMARY KEY (idVendita, idPiatto),
    FOREIGN KEY(idVendita) REFERENCES vendite(id) ON DELETE CASCADE,
    FOREIGN KEY(idPiatto) REFERENCES piatti(id)
);

-- tabella dei pagamenti
CREATE TABLE IF NOT EXISTS pagamenti(
    id INT AUTO_INCREMENT PRIMARY KEY,
    idVendita INT NOT NULL UNIQUE,
    metodoPagamento VARCHAR(50) NOT NULL,
    importo DECIMAL(10,2) NOT NULL,
    dataOra TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY(idVendita) REFERENCES vendite(id) ON DELETE CASCADE
);

-- tabella del menu del giorno
CREATE TABLE IF NOT EXISTS menudelgiorno(
    id INT AUTO_INCREMENT PRIMARY KEY,
    dataMenu DATE NOT NULL UNIQUE
);

--tabella dei piatti nel menu
CREATE TABLE IF NOT EXISTS piatti_menu(
    idMenu INT NOT NULL,
    idPiatto INT NOT NULL
    prezzo INT NOT NULL,
    PRIMARY KEY (idMenu, idPiatto),
    FOREIGN KEY(idMenu) REFERENCES menudelgiorno(id) ON DELETE CASCADE,
    FOREIGN KEY(idPiatto) REFERENCES piatti(id) ON DELETE CASCADE
);

-- tabella degli accordi di fornitura
CREATE TABLE IF NOT EXISTS accordiFornitura(
    cfSocio VARCHAR(16) NOT NULL,
    partitaIvaFornitore VARCHAR(11) NOT NULL,
    dataAccordo DATE NOT NULL,
    durata INT NOT NULL,
    PRIMARY KEY (cfSocio, partitaIvaFornitore),
    FOREIGN KEY(cfSocio) REFERENCES persone(cf) ON DELETE CASCADE,
    FOREIGN KEY(partitaIvaFornitore) REFERENCES fornitori(partitaIva) ON DELETE CASCADE
);




