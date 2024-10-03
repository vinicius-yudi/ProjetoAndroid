package com.yudi.projetoandroid

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class MeuBancoDeDados(context: Context) : SQLiteOpenHelper(context, NOME_BANCO, null, VERSION) {

    companion object {
        private const val NOME_BANCO = "androidapp.db"
        private const val VERSION = 1
    }

    override fun onCreate(db: SQLiteDatabase) {
        val sql =  """
        -- Criação da tabela User
        CREATE TABLE User (
            Id_User INTEGER PRIMARY KEY,
            Nome VARCHAR(255),
            Email VARCHAR(255) unique,
            Senha VARCHAR(255),
            Cpf VARCHAR(20) unique,
            Telefone VARCHAR(16)
        );
        
        CREATE TABLE Jogo (
            Id_Jogo INTEGER PRIMARY KEY,
            Nome VARCHAR(255),
            Tipo VARCHAR(255)
        );
        
        -- Criação da tabela Favorita para armazenar as relações entre User e Jogo
        CREATE TABLE Favorita (
            fk_User_Id_User INTEGER,
            fk_Jogo_Id_Jogo INTEGER
        );
        
        -- Adicionando restrição para chave estrangeira em Favorita que referencia User
        ALTER TABLE Favorita ADD CONSTRAINT FK_Favorita_1
            FOREIGN KEY (fk_User_Id_User)
            REFERENCES User (Id_User)
            ON DELETE RESTRICT;
        
        -- Adicionando restrição para chave estrangeira em Favorita que referencia Jogo
        ALTER TABLE Favorita ADD CONSTRAINT FK_Favorita_2
            FOREIGN KEY (fk_Jogo_Id_Jogo)
            REFERENCES Jogo (Id_Jogo)
            ON DELETE SET NULL;
    """.trimIndent()

        db.execSQL(sql)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {

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

    fun obterUsuarios(): List<Usuario> {
        val listaUsuarios = mutableListOf<Usuario>()
        val db = this.readableDatabase
        val query = "SELECT * FROM User"
        val cursor = db.rawQuery(query, null)

        if (cursor.moveToFirst()) {
            do {
                val idUser = cursor.getInt(cursor.getColumnIndexOrThrow("Id_User"))
                val nome = cursor.getString(cursor.getColumnIndexOrThrow("Nome"))
                val email = cursor.getString(cursor.getColumnIndexOrThrow("Email"))
                val senha = cursor.getString(cursor.getColumnIndexOrThrow("Senha"))
                val cpf = cursor.getString(cursor.getColumnIndexOrThrow("Cpf"))
                val telefone = cursor.getString(cursor.getColumnIndexOrThrow("Telefone"))

                val usuario = Usuario(idUser, nome, email, senha, cpf, telefone)
                listaUsuarios.add(usuario)
            } while (cursor.moveToNext())
        }

        cursor.close()
        db.close()
        return listaUsuarios
    }

}
