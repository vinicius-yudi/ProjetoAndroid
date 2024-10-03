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
        INSERT INTO Jogo (Nome, Tipo) VALUES
            ('The Legend of Zelda', 'Aventura'),
            ('Super Mario Odyssey', 'Plataforma'),
            ('Fortnite', 'Tiro'),
            ('Call of Duty', 'Tiro'),
            ('FIFA 22', 'Simulação'),
            ('Red Dead Redemption 2', 'Aventura'),
            ('Minecraft', 'Sandbox'),
            ('Overwatch', 'Tiro'),
            ('Final Fantasy XV', 'Ação'),
            ('Assassins Creed Valhalla', 'Aventura'),
            ('League of Legends', 'Multiplayer'),
            ('Dota 2', 'Multiplayer'),
            ('Apex Legends', 'Tiro'),
            ('Cyberpunk 2077', 'Ação'),
            ('Hades', 'Ação'),
            ('Among Us', 'Multiplayer'),
            ('Fall Guys', 'Multiplayer'),
            ('Resident Evil Village', 'Aventura'),
            ('Horizon Zero Dawn', 'Aventura'),
            ('The Witcher 3 Wild Hunt', 'Aventura'),
            ('God of War', 'Ação'),
            ('Ghost of Tsushima', 'Ação'),
            ('Battlefield V', 'Tiro'),
            ('Valorant', 'Tiro'),
            ('Rocket League', 'Corrida'),
            ('Gran Turismo 7', 'Corrida'),
            ('The Sims 4', 'Simulação'),
            ('Animal Crossing: New Horizons', 'Simulação'),
            ('Stardew Valley', 'Simulação'),
            ('Persona 5', 'Aventura'),
            ('Dark Souls III', 'Ação'),
            ('Sekiro: Shadows Die Twice', 'Ação'),
            ('Monster Hunter: World', 'Ação'),
            ('Genshin Impact', 'Aventura'),
            ('PUBG', 'Tiro'),
            ('Hitman', 'Ação'),
            ('No Mans Sky', 'Aventura'),
            ('DOOM Eternal', 'Tiro'),
            ('Cuphead', 'Plataforma'),
            ('Hollow Knight', 'Plataforma'),
            ('Ori and the Will of the Wisps', 'Plataforma'),
            ('Mortal Kombat 11', 'Luta'),
            ('Street Fighter V', 'Luta'),
            ('Tekken 7', 'Luta'),
            ('NBA 2K22', 'Simulação'),
            ('PES 2021', 'Simulação'),
            ('F1 2021', 'Corrida'),
            ('Need for Speed Heat', 'Corrida'),
            ('Forza Horizon 5', 'Corrida'),
            ('Crash Bandicoot 4', 'Plataforma'),
            ('Sonic Mania', 'Plataforma'),
            ('Borderlands 3', 'Tiro'),
            ('Far Cry 6', 'Tiro'),
            ('Just Cause 4', 'Ação'),
            ('Spider-Man: Miles Morales', 'Aventura'),
            ('Control', 'Aventura'),
            ('Halo Infinite', 'Tiro'),
            ('Warframe', 'Ação'),
            ('Splatoon 2', 'Tiro'),
            ('Super Smash Bros. Ultimate', 'Luta'),
            ('Street Fighter IV', 'Luta'),
            ('Tekken 6', 'Luta'),
            ('Mario Kart 8 Deluxe', 'Corrida'),
            ('Crash Team Racing', 'Corrida'),
            ('Gran Turismo Sport', 'Corrida'),
            ('NFS Most Wanted', 'Corrida'),
            ('Yakuza: Like a Dragon', 'Aventura'),
            ('Dead by Daylight', 'Multiplayer'),
            ('The Division 2', 'Multiplayer'),
            ('Phasmophobia', 'Multiplayer'),
            ('RimWorld', 'Simulação'),
            ('SimCity', 'Simulação'),
            ('Cities: Skylines', 'Simulação'),
            ('Terraria', 'Sandbox'),
            ('Starbound', 'Sandbox'),
            ('Rust', 'Sandbox'),
            ('ARK Survival Evolved', 'Sandbox'),
            ('7 Days to Die', 'Sandbox'),
            ('Portal 2', 'Plataforma'),
            ('Celeste', 'Plataforma'),
            ('Rayman Legends', 'Plataforma'),
            ('Donkey Kong Country: Tropical Freeze', 'Plataforma'),
            ('LittleBigPlanet 3', 'Plataforma'),
            ('Shovel Knight', 'Plataforma'),
            ('Brawlhalla', 'Luta'),
            ('Guilty Gear Strive', 'Luta'),
            ('Dragon Ball FighterZ', 'Luta'),
            ('Dirt Rally 2.0', 'Corrida'),
            ('Project CARS 2', 'Corrida'),
            ('Wipeout Omega Collection', 'Corrida'),
            ('TrackMania Turbo', 'Corrida'),
            ('Mad Max', 'Ação'),
            ('Watch Dogs: Legion', 'Ação'),
            ('Tomb Raider', 'Aventura'),
            ('Rise of the Tomb Raider', 'Aventura'),
            ('Journey', 'Aventura'),
            ('Shadow of the Colossus', 'Aventura');
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

    @SuppressLint("SuspiciousIndentation")
    fun obterJogos(): List<Jogo> {
    val listaJogos = mutableListOf<Jogo>()
    val db = this.readableDatabase
    val query = "SELECT * FROM Jogo order by tipo"
    val cursor = db.rawQuery(query, null)

        if (cursor.moveToFirst()) {
            do {
                val idJogo = cursor.getInt(cursor.getColumnIndexOrThrow("Id_Jogo"))
                val nome = cursor.getString(cursor.getColumnIndexOrThrow("Nome"))
                val tipo = cursor.getString(cursor.getColumnIndexOrThrow("Tipo"))

                val jogo = Jogo(idJogo, nome, tipo)
                listaJogos.add(jogo)
            } while (cursor.moveToNext())
        }

        cursor.close()
        db.close()

        return listaJogos
    }

    fun obterUsuarios(): Cursor {
        val db = this.readableDatabase
        val query = "SELECT id, email FROM Usuario"
        return db.rawQuery(query, null)
    }

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
    val query = "SELECT Jogo.Nome, Jogo.Tipo FROM Favorita INNER JOIN Jogo ON Favorita.fk_Jogo_Id_Jogo = Jogo.Id_Jogo WHERE Favorita.fk_User_Id_User = ?"
    return db.rawQuery(query, arrayOf(userId.toString()))
}

}
