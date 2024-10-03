package com.yudi.projetoandroid

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class MeuBancoDeDados(context: Context) : SQLiteOpenHelper(context, NOME_BANCO, null, VERSION) {

    companion object {
        private const val NOME_BANCO = "androidapp.db"
        private const val VERSION = 1
    }

@SuppressLint("SuspiciousIndentation")
override fun onCreate(db: SQLiteDatabase) {
    val sqlUser = """
    -- Criação da tabela User
    CREATE TABLE User (
        Id_User INTEGER PRIMARY KEY autoincrement,
        Nome VARCHAR(255),
        Email VARCHAR(255) UNIQUE,
        Senha VARCHAR(255),
        Cpf VARCHAR(20) UNIQUE,
        Telefone VARCHAR(16)
    );
    """.trimIndent()

    val sqlJogo = """
    CREATE TABLE Jogo (
        Id_Jogo INTEGER PRIMARY KEY autoincrement,
        Nome VARCHAR(255) NOT NULL,
        Tipo VARCHAR(255) NOT NULL
    );
    """.trimIndent()

    val sqlFavorita = """
    CREATE TABLE Favorita (
        Id_Favorita INTEGER PRIMARY KEY,
        fk_User_Id_User INTEGER,
        fk_Jogo_Id_Jogo INTEGER,
        FOREIGN KEY (fk_User_Id_User) REFERENCES User (Id_User) ON DELETE RESTRICT,
        FOREIGN KEY (fk_Jogo_Id_Jogo) REFERENCES Jogo (Id_Jogo) ON DELETE SET NULL
    );

    """.trimIndent()

    db.execSQL(sqlUser)
    db.execSQL(sqlJogo)
    db.execSQL(sqlFavorita)

    val popularTabelaJogo = """
        INSERT INTO Jogo (Id_Jogo, Nome, Tipo) VALUES
    (1, 'The Legend of Zelda', 'Aventura'),
    (2, 'Super Mario Odyssey', 'Plataforma'),
    (3, 'Fortnite', 'Tiro'),
    (4, 'Call of Duty', 'Tiro'),
    (5, 'FIFA 22', 'Simulação'),
    (6, 'Red Dead Redemption 2', 'Aventura'),
    (7, 'Minecraft', 'Sandbox'),
    (8, 'Overwatch', 'Tiro'),
    (9, 'Final Fantasy XV', 'Ação'),
    (10, 'Assassins Creed Valhalla', 'Aventura'),
    (11, 'League of Legends', 'Multiplayer'),
    (12, 'Dota 2', 'Multiplayer'),
    (13, 'Apex Legends', 'Tiro'),
    (14, 'Cyberpunk 2077', 'Ação'),
    (15, 'Hades', 'Ação'),
    (16, 'Among Us', 'Multiplayer'),
    (17, 'Fall Guys', 'Multiplayer'),
    (18, 'Resident Evil Village', 'Aventura'),
    (19, 'Horizon Zero Dawn', 'Aventura'),
    (20, 'The Witcher 3 Wild Hunt', 'Aventura'),
    (21, 'God of War', 'Ação'),
    (22, 'Ghost of Tsushima', 'Ação'),
    (23, 'Battlefield V', 'Tiro'),
    (24, 'Valorant', 'Tiro'),
    (25, 'Rocket League', 'Corrida'),
    (26, 'Gran Turismo 7', 'Corrida'),
    (27, 'The Sims 4', 'Simulação'),
    (28, 'Animal Crossing: New Horizons', 'Simulação'),
    (29, 'Stardew Valley', 'Simulação'),
    (30, 'Persona 5', 'Aventura'),
    (31, 'Dark Souls III', 'Ação'),
    (32, 'Sekiro: Shadows Die Twice', 'Ação'),
    (33, 'Monster Hunter: World', 'Ação'),
    (34, 'Genshin Impact', 'Aventura'),
    (35, 'PUBG', 'Tiro'),
    (36, 'Hitman', 'Ação'),
    (37, 'No Mans Sky', 'Aventura'),
    (38, 'DOOM Eternal', 'Tiro'),
    (39, 'Cuphead', 'Plataforma'),
    (40, 'Hollow Knight', 'Plataforma'),
    (41, 'Ori and the Will of the Wisps', 'Plataforma'),
    (42, 'Mortal Kombat 11', 'Luta'),
    (43, 'Street Fighter V', 'Luta'),
    (44, 'Tekken 7', 'Luta'),
    (45, 'NBA 2K22', 'Simulação'),
    (46, 'PES 2021', 'Simulação'),
    (47, 'F1 2021', 'Corrida'),
    (48, 'Need for Speed Heat', 'Corrida'),
    (49, 'Forza Horizon 5', 'Corrida'),
    (50, 'Crash Bandicoot 4', 'Plataforma'),
    (51, 'Sonic Mania', 'Plataforma'),
    (52, 'Borderlands 3', 'Tiro'),
    (53, 'Far Cry 6', 'Tiro'),
    (54, 'Just Cause 4', 'Ação'),
    (55, 'Spider-Man: Miles Morales', 'Aventura'),
    (56, 'Control', 'Aventura'),
    (57, 'Halo Infinite', 'Tiro'),
    (58, 'Warframe', 'Ação'),
    (59, 'Splatoon 2', 'Tiro'),
    (60, 'Super Smash Bros. Ultimate', 'Luta'),
    (61, 'Street Fighter IV', 'Luta'),
    (62, 'Tekken 6', 'Luta'),
    (63, 'Mario Kart 8 Deluxe', 'Corrida'),
    (64, 'Crash Team Racing', 'Corrida'),
    (65, 'Gran Turismo Sport', 'Corrida'),
    (66, 'NFS Most Wanted', 'Corrida'),
    (67, 'Yakuza: Like a Dragon', 'Aventura'),
    (68, 'Dead by Daylight', 'Multiplayer'),
    (69, 'The Division 2', 'Multiplayer'),
    (70, 'Phasmophobia', 'Multiplayer'),
    (71, 'RimWorld', 'Simulação'),
    (72, 'SimCity', 'Simulação'),
    (73, 'Cities: Skylines', 'Simulação'),
    (74, 'Terraria', 'Sandbox'),
    (75, 'Starbound', 'Sandbox'),
    (76, 'Rust', 'Sandbox'),
    (77, 'ARK Survival Evolved', 'Sandbox'),
    (78, '7 Days to Die', 'Sandbox'),
    (79, 'Portal 2', 'Plataforma'),
    (80, 'Celeste', 'Plataforma'),
    (81, 'Rayman Legends', 'Plataforma'),
    (82, 'Donkey Kong Country: Tropical Freeze', 'Plataforma'),
    (83, 'LittleBigPlanet 3', 'Plataforma'),
    (84, 'Shovel Knight', 'Plataforma'),
    (85, 'Brawlhalla', 'Luta'),
    (86, 'Guilty Gear Strive', 'Luta'),
    (87, 'Dragon Ball FighterZ', 'Luta'),
    (88, 'Dirt Rally 2.0', 'Corrida'),
    (89, 'Project CARS 2', 'Corrida'),
    (90, 'Wipeout Omega Collection', 'Corrida'),
    (91, 'TrackMania Turbo', 'Corrida'),
    (92, 'Mad Max', 'Ação'),
    (93, 'Watch Dogs: Legion', 'Ação'),
    (94, 'Tomb Raider', 'Aventura'),
    (95, 'Rise of the Tomb Raider', 'Aventura'),
    (96, 'Journey', 'Aventura'),
    (97, 'Shadow of the Colossus', 'Aventura');
        """.trimIndent()

        db.execSQL(popularTabelaJogo)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        //NÃO inserir dados aqui
    }

    fun inserirUsuario(nome: String, email: String, senha: String, cpf: String, telefone: String): Long {
        val db = this.writableDatabase
        val valores = ContentValues()
        valores.put("Nome", nome)
        valores.put("Email", email)
        valores.put("Senha", senha)
        valores.put("Cpf", cpf)
        valores.put("Telefone", telefone)

        return db.insert("User", null, valores)
    }

    fun verificarLogin(email: String, senha: String): Boolean {
        val db = this.readableDatabase
        val query = "SELECT * FROM User WHERE Email = ? AND Senha = ?"
        val cursor = db.rawQuery(query, arrayOf(email, senha))

        val loginValido = cursor.count > 0
        cursor.close()
        db.close()

        return loginValido
    }

    fun obterJogos(): List<Jogo> {
        val db = this.readableDatabase
        val query = "SELECT Id_Jogo, Nome, Tipo FROM Jogo"
        val cursor = db.rawQuery(query, null)
        val jogos = mutableListOf<Jogo>()

        if (cursor.moveToFirst()) {
            do {
                val idJogo = cursor.getInt(cursor.getColumnIndexOrThrow("Id_Jogo"))
                val nome = cursor.getString(cursor.getColumnIndexOrThrow("Nome"))
                val tipo = cursor.getString(cursor.getColumnIndexOrThrow("Tipo"))
                jogos.add(Jogo(idJogo, nome, tipo))
            } while (cursor.moveToNext())
        }
        cursor.close()
        return jogos
    }

    fun obterUsuarios(): Cursor {
        val db = this.readableDatabase
        val query = "SELECT id, email FROM Usuario"
        return db.rawQuery(query, null)
    }

    @Deprecated(message = "Não utilizado")
    fun obterUserIdPeloLogin(email: String): Int? {
        val db = this.readableDatabase
        val query = "SELECT id FROM User WHERE User.email = ?"
        val cursor: Cursor = db.rawQuery(query, arrayOf(email))
        return if (cursor.moveToFirst()) {
            val userId = cursor.getInt(cursor.getColumnIndexOrThrow("id"))
            cursor.close()
            userId
        } else {
            cursor.close()
            null
        }
    }


    fun inserirFavorito(userId: Int, gameId: Int): Long {
        val db = this.writableDatabase
        val values = ContentValues().apply {
            put("user_id", userId)
            put("game_id", gameId)
        }
        return db.insert("Favorito", null, values)
    }

    fun obterFavoritos(userId: Int): Cursor {
        val db = this.readableDatabase
        val query = "SELECT Jogo.Id_Jogo, Jogo.Nome, Jogo.Tipo FROM Favorita INNER JOIN Jogo ON Favorita.fk_Jogo_Id_Jogo = Jogo.Id_Jogo WHERE Favorita.fk_User_Id_User = ?"
        return db.rawQuery(query, arrayOf(userId.toString()))
    }

    fun adicionarFavorito(userId: Int, jogoId: Int): Long {
        val db = this.writableDatabase
        val values = ContentValues().apply {
            put("fk_User_Id_User", userId)
            put("fk_Jogo_Id_Jogo", jogoId)
        }
        return db.insert("Favorita", null, values)
    }

    fun removerFavorito(userId: Int, jogoId: Int): Int {
        val db = this.writableDatabase
        return db.delete("Favorita", "fk_User_Id_User = ? AND fk_Jogo_Id_Jogo = ?", arrayOf(userId.toString(), jogoId.toString()))
    }

}
