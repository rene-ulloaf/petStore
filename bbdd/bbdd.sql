CREATE TABLE "Animal" (
    "id" INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
    "nombre" TEXT,
    "idTipo" INTEGER NOT NULL,
    "idEspecie" INTEGER NOT NULL,
    "idRaza" INTEGER NOT NULL,
    "peso" TEXT,
    "edad" TEXT,
    "vigente" INTEGER NOT NULL DEFAULT (1)
);
CREATE TABLE sqlite_sequence(name,seq);
CREATE UNIQUE INDEX "indx_primary_animal" on animal (id ASC);
CREATE TABLE "Especie" (
    "idEspecie" INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
    "idTipo" INTEGER NOT NULL,
    "glosa" TEXT NOT NULL,
    "orden" INTEGER,
    "vigente" INTEGER NOT NULL DEFAULT (1)
);
CREATE TABLE "Raza" (
    "idRaza" INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
    "idEspecie" INTEGER NOT NULL,
    "glosa" TEXT NOT NULL,
    "orden" INTEGER,
    "vigente" INTEGER NOT NULL DEFAULT (1)
);
CREATE TABLE "Tipo" (
    "idTipo" INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
    "glosa" TEXT NOT NULL,
    "orden" INTEGER,
    "vigente" INTEGER NOT NULL DEFAULT (1)
);
CREATE UNIQUE INDEX "indx_primary_especie" on especie (idEspecie ASC);
CREATE UNIQUE INDEX "indx_primary_raza" on raza (idRaza ASC);
CREATE UNIQUE INDEX "indx_primary_tipo" on tipo (idTipo ASC);
