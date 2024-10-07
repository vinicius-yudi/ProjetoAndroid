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
    (1, 'A Little to the Left', 'Aventura'),
(2, 'A Plague Tale: Requiem', 'Ação'),
(3, 'A Way Out', 'Multiplayer'),
(4, 'Against the Storm', 'Simulação'),
(5, 'Age of Empires Definitive Edition', 'Simulação'),
(6, 'Age of Empires II: Definitive Edition', 'Simulação'),
(7, 'Age of Empires III: Definitive Edition', 'Simulação'),
(8, 'Age of Empires IV: Anniversary Edition', 'Simulação'),
(9, 'Age of Mythology: Retold Standard Edition', 'Simulação'),
(10, 'Alice: Madness Returns™', 'Ação'),
(11, 'All You Need is Help', 'Aventura'),
(12, 'Amnesia: The Bunker', 'Aventura'),
(13, 'Among Us', 'Multiplayer'),
(14, 'An Elder Scrolls Legend: Battlespire', 'Aventura'),
(15, 'Another Crabs Treasure', 'Ação'),
(16, 'Anthem™', 'Ação'),
(17, 'Ara: History Untold Standard Edition', 'Simulação'),
(18, 'Arcade Paradise', 'Ação'),
(19, 'ARK: Survival Ascended', 'Sandbox'),
(20, 'ARK: Ultimate Survivor Edition', 'Sandbox'),
(21, 'Arx Fatalis', 'Aventura'),
(22, 'As Dusk Falls', 'Aventura'),
(23, 'Assassins Creed® Odyssey', 'Ação'),
(24, 'Assassins Creed® Origins', 'Ação'),
(25, 'Assassins Creed® Valhalla', 'Ação'),
(26, 'ASTRONEER', 'Sandbox'),
(27, 'Atlas Fallen: Reign of Sand', 'Ação'),
(28, 'Battlefield 3 Premium Edition', 'Tiro'),
(29, 'Battlefield 4™ Premium Edition', 'Tiro'),
(30, 'Battlefield™ 1 Revolution', 'Tiro'),
(31, 'Battlefield™ 2042', 'Tiro'),
(32, 'Battlefield™ V Edição Ano 2', 'Tiro'),
(33, 'BATTLETECH', 'Simulação'),
(34, 'Battletoads', 'Ação'),
(35, 'Ben 10 Uma super viagem', 'Aventura'),
(36, 'BlazBlue: Cross Tag Battle Special Edition', 'Luta'),
(37, 'Bleeding Edge', 'Ação'),
(38, 'Bloodstained: Ritual of the Night', 'Ação'),
(39, 'Bluey: o video game', 'Aventura'),
(40, 'Botany Manor', 'Simulação'),
(41, 'Broforce', 'Ação'),
(42, 'Broken Age', 'Aventura'),
(43, 'Brotato', 'Ação'),
(44, 'Brothers: a Tale of Two Sons', 'Aventura'),
(45, 'Burnout™ Paradise Remastered', 'Corrida'),
(46, 'Call of Duty®: Modern Warfare® III', 'Tiro'),
(47, 'Call of the Wild: The Angler™', 'Simulação'),
(48, 'Car Mechanic Simulator 2021', 'Simulação'),
(49, 'Cassette Beasts', 'Aventura'),
(50, 'Celeste', 'Plataforma'),
(51, 'Chants of Sennaar', 'Aventura'),
(52, 'Chivalry 2', 'Ação'),
(53, 'Cities Skylines - Windows 10 Edition', 'Simulação'),
(54, 'Cities Skylines II - PC Edition', 'Simulação'),
(55, 'Citizen Sleeper', 'Aventura'),
(56, 'Clone Drone in the Danger Zone', 'Ação'),
(57, 'Close to the Sun', 'Aventura'),
(58, 'Cocoon', 'Aventura'),
(59, 'Command & Conquer™ Remastered Collection', 'Simulação'),
(60, 'Commonhood', 'Simulação'),
(61, 'Conan Exiles', 'Sandbox'),
(62, 'Control Ultimate Edition', 'Ação'),
(63, 'Cooking Simulator', 'Simulação'),
(64, 'Coral Island', 'Simulação'),
(65, 'Core Keeper', 'Aventura'),
(66, 'Crackdown 3', 'Ação'),
(67, 'Crash Bandicoot™ N. Sane Trilogy', 'Plataforma'),
(68, 'Creatures of Ava', 'Aventura'),
(69, 'Cricket 24: The Official Game Of The Ashes', 'Simulação'),
(70, 'Crusader Kings III', 'Simulação'),
(71, 'Crysis 3', 'Tiro'),
(72, 'Crysis®', 'Tiro'),
(73, 'Crysis® 2 Maximum Edition', 'Tiro'),
(74, 'Darkest Dungeon', 'Aventura'),
(75, 'Day of the Tentacle Remastered', 'Aventura'),
(76, 'Dead by Daylight', 'Ação'),
(77, 'Dead Cells', 'Plataforma'),
(78, 'Dead Space', 'Ação'),
(79, 'Dead Space 2', 'Ação'),
(80, 'Dead Space 3', 'Ação'),
(81, 'Deaths Door', 'Aventura'),
(82, 'DEATHLOOP', 'Ação'),
(83, 'Deep Rock Galactic', 'Multiplayer'),
(84, 'Depersonalization', 'Aventura'),
(85, 'Descenders', 'Corrida'),
(86, 'Diablo® IV', 'Ação'),
(87, 'Dicey Dungeons', 'Aventura'),
(88, 'DIRT 5', 'Corrida'),
(89, 'Dishonored 2', 'Ação'),
(90, 'Dishonored® Definitive Edition', 'Ação'),
(91, 'Dishonored®: Death of the Outsider™', 'Ação'),
(92, 'Disney Dreamlight Valley', 'Simulação'),
(93, 'Disneyland Adventures', 'Aventura'),
(94, 'DOOM + DOOM II', 'Tiro'),
(95, 'DOOM 3', 'Tiro'),
(96, 'DOOM 64', 'Tiro'),
(97, 'DOOM Eternal', 'Tiro'),
(98, 'Dordogne', 'Aventura'),
(99, 'Dragon Age™ II', 'RPG'),
(100, 'Dragon Age™: Inquisition', 'RPG'),
(101, 'Dragon Age™: Origins', 'RPG'),
(102, 'Dune: Spice Wars', 'Simulação'),
(103, 'Dungeon Keeper™', 'Simulação'),
(104, 'Dungeons 4', 'Simulação'),
(105, 'Dyson Sphere Program', 'Simulação'),
(106, 'EA SPORTS FC™ 24', 'Esportes'),
(107, 'Escape Academy', 'Aventura'),
(108, 'EVERSPACE™ 2', 'Ação'),
(109, 'Exoprimal', 'Ação'),
(110, 'F1® 22', 'Corrida'),
(111, 'F1® 23', 'Corrida'),
(112, 'F1® Manager 2023', 'Simulação'),
(113, 'Fallout 3: Game of the Year Edition', 'Ação'),
(114, 'Fallout 4', 'Ação'),
(115, 'Fallout 76', 'Multiplayer'),
(116, 'Far Cry® 5', 'Ação'),
(117, 'Far Cry® 6', 'Ação'),
(118, 'Farming Simulator 22', 'Simulação'),
(119, 'Fe', 'Aventura'),
(120, 'Firewatch', 'Aventura'),
(121, 'Flintlock: The Siege of Dawn', 'Ação'),
(122, 'Flock', 'Simulação'),
(123, 'FOR HONOR: MARCHING FIRE EDITION', 'Ação'),
(124, 'Forager', 'Simulação'),
(125, 'Forza Horizon 4', 'Corrida'),
(126, 'Forza Horizon 5', 'Corrida'),
(127, 'Forza Motorsport', 'Corrida'),
(128, 'Frog Detective O Mistério Completo', 'Aventura'),
(129, 'From Space', 'Ação'),
(130, 'Frostpunk', 'Simulação'),
(131, 'Full Throttle Remastered', 'Aventura'),
(132, 'Gang Beasts', 'Multiplayer'),
(133, 'Gears of War 4', 'Ação'),
(134, 'Gears Tactics', 'Simulação'),
(135, 'Generation Zero®', 'Ação'),
(136, 'Ghostwire: Tokyo', 'Ação'),
(137, 'Goat Simulator 3', 'Sandbox'),
(138, 'GRID Legends', 'Corrida'),
(139, 'Grim Fandango Remastered', 'Aventura'),
(140, 'Grounded', 'Sandbox'),
(141, 'Halo Infinite (Campanha)', 'Ação'),
(142, 'Halo: The Master Chief Collection', 'Tiro'),
(143, 'Hell Let Loose', 'Tiro'),
(144, 'Hellblade: Senuas Sacrifice', 'Ação'),
(145, 'Hi-Fi RUSH', 'Ação'),
(146, 'Hollow Knight', 'Plataforma'),
(147, 'House Flipper', 'Simulação'),
(148, 'Human Fall Flat', 'Ação'),
(149, 'Immortals Fenyx Rising™', 'Aventura'),
(150, 'It Takes Two', 'Multiplayer'),
(151, 'Indivisible', 'Ação'),
(152, 'Injustice™ 2', 'Luta'),
(153, 'Inside', 'Aventura'),
(154, 'Insurgency: Sandstorm', 'Tiro'),
(155, 'It Takes Two', 'Multiplayer'),
(156, 'Jade Empire™: Edição Especial', 'RPG'),
(157, 'Journey To The Savage Planet: Employee Of The Month', 'Aventura'),
(158, 'Jurassic World Evolution 2', 'Simulação'),
(159, 'Jusant', 'Aventura'),
(160, 'Keplerth', 'Aventura'),
(161, 'Killer Instinct: Anniversary Edition', 'Luta'),
(162, 'Kona II: Brume', 'Aventura'),
(163, 'Kunitsu-Gami: Path of the Goddess', 'Ação'),
(164, 'League of Legends', 'Multiplayer'),
(165, 'Legend of Mana', 'Aventura'),
(166, 'LEGO® Star Wars™: A Saga Skywalker', 'Aventura'),
(167, 'Lies of P', 'Ação'),
(168, 'Lightyear Frontier', 'Simulação'),
(169, 'Like a Dragon: Ishin!', 'Ação'),
(170, 'Lil Gator Game', 'Aventura'),
(171, 'LIMBO', 'Plataforma'),
(172, 'Lonely Mountains: Downhill', 'Corrida'),
(173, 'Lords of the Fallen', 'Ação'),
(174, 'Lost in Random™', 'Aventura'),
(175, 'Madden NFL 23', 'Esportes'),
(176, 'Madden NFL 24', 'Esportes'),
(177, 'Mafia: Definitive Edition', 'Aventura'),
(178, 'Maneater', 'Ação'),
(179, 'Manor Lords', 'Simulação'),
(180, 'Maquette', 'Aventura'),
(181, 'Mass Effect 2', 'RPG'),
(182, 'Mass Effect 3', 'RPG'),
(183, 'Mass Effect™ Legendary Edition', 'RPG'),
(184, 'McPixel 3', 'Aventura'),
(185, 'MechWarrior 5: Mercenaries', 'Ação'),
(186, 'Medieval Dynasty', 'Simulação'),
(187, 'Merge And Blade', 'Aventura'),
(188, 'Microsoft Flight Simulator', 'Simulação'),
(189, 'Minecraft Dungeons', 'Ação'),
(190, 'Minecraft Legends', 'Ação'),
(191, 'Minecraft: Java & Bedrock Edition', 'Sandbox'),
(192, 'Minekos Night Market', 'Aventura'),
(193, 'Mirrors Edge', 'Ação'),
(194, 'Mirrors Edge Catalyst', 'Ação'),
(195, 'Monster Hunter Rise', 'Ação'),
(196, 'Moonlighter', 'Aventura'),
(197, 'Mortal Kombat 11', 'Luta'),
(198, 'Mount & Blade II: Bannerlord', 'Ação'),
(199, 'Moving Out 2', 'Ação'),
(200, 'My Time at Sandrock', 'Simulação'),
(201, 'Need for Speed™ Heat', 'Corrida'),
(202, 'Need for Speed™ Hot Pursuit Remastered', 'Corrida'),
(203, 'Neon Abyss', 'Ação'),
(204, 'Neon White', 'Ação'),
(205, 'New Super Luckys Tale', 'Plataforma'),
(206, 'Ni no Kuni II: Revenant Kingdom', 'RPG'),
(207, 'Nickelodeon All-Star Brawl 2', 'Luta'),
(208, 'NINJA GAIDEN Σ', 'Ação'),
(209, 'NINJA GAIDEN Σ2', 'Ação'),
(210, 'No Mans Sky', 'Sandbox'),
(211, 'OCTOPATH TRAVELER', 'RPG'),
(212, 'OCTOPATH TRAVELER II', 'RPG'),
(213, 'Ori and the Blind Forest: Definitive Edition', 'Plataforma'),
(214, 'Ori and the Will of the Wisps', 'Plataforma'),
(215, 'Overcooked! 2', 'Multiplayer'),
(216, 'Overwatch® 2', 'Multiplayer'),
(217, 'Pentiment', 'Aventura'),
(218, 'Persona 3 Reload', 'RPG'),
(219, 'Persona 5 Tactica', 'RPG'),
(220, 'Phoenix Point', 'Ação'),
(221, 'Pillars of Eternity 2: Deadfire - Ultimate Edition', 'RPG'),
(222, 'Planet of Lana', 'Aventura'),
(223, 'Plants vs. Zombies Garden Warfare', 'Ação'),
(224, 'PowerWash Simulator', 'Simulação'),
(225, 'Prey', 'Ação'),
(226, 'Psychonauts 2', 'Plataforma'),
(227, 'Quake', 'Tiro'),
(228, 'Quantum Break', 'Ação'),
(229, 'RAGE 2', 'Tiro'),
(230, 'Rainbow Billy: The Curse of the Leviathan', 'Aventura'),
(231, 'ReCore', 'Ação'),
(232, 'Redfall', 'Tiro'),
(233, 'Remnant II', 'Ação'),
(234, 'Resident Evil 2', 'Ação'),
(235, 'Return to Monkey Island', 'Aventura'),
(236, 'Rise of the Tomb Raider', 'Ação'),
(237, 'Roboquest', 'Tiro'),
(238, 'Rollerdrome', 'Ação'),
(239, 'Sea of Stars', 'RPG'),
(240, 'Sea of Thieves: 2024 Edition', 'Multiplayer'),
(241, 'Serious Sam: Siberian Mayhem', 'Tiro'),
(242, 'Shadow of the Tomb Raider Definitive Edition', 'Ação'),
(243, 'Sifu', 'Luta'),
(244, 'Slay The Spire', 'Aventura'),
(245, 'Sniper Elite 5', 'Tiro'),
(246, 'Somerville', 'Aventura'),
(247, 'Space Engineers', 'Sandbox'),
(248, 'Star Wars Jedi: Survivor™', 'Ação'),
(249, 'Starfield', 'Aventura'),
(250, 'State of Decay 2: Juggernaut Edition', 'Ação'),
(251, 'SteamWorld Build', 'Simulação'),
(252, 'Stellaris', 'Simulação'),
(253, 'Still Wakes the Deep', 'Aventura'),
(254, 'Sunset Overdrive', 'Ação'),
(255, 'Super Luckys Tale', 'Plataforma'),
(256, 'Super Mega Baseball 3', 'Esportes'),
(257, 'Super Mega Baseball 4', 'Esportes'),
(258, 'SUPERHOT: MIND CONTROL DELETE', 'Tiro'),
(259, 'Superliminal', 'Aventura'),
(260, 'Tales of Arise', 'RPG'),
(261, 'Tchia', 'Aventura'),
(262, 'Teamfight Tactics', 'Multiplayer'),
(263, 'Techtonica', 'Simulação'),
(264, 'Teenage Mutant Ninja Turtles: Shredders Revenge', 'Ação'),
(265, 'Tell Me Why', 'Aventura'),
(266, 'Terra Invicta', 'Simulação'),
(267, 'The Bards Tale ARPG', 'Aventura'),
(268, 'The Bards Tale IV: Directors Cut', 'RPG'),
(269, 'The Bards Tale Trilogy', 'RPG'),
(270, 'The Big Con', 'Aventura'),
(271, 'The Callisto Protocol™', 'Ação'),
(272, 'The Case of the Golden Idol', 'Aventura'),
(273, 'The Elder Scrolls III: Morrowind', 'RPG'),
(274, 'The Elder Scrolls IV: Oblivion', 'RPG'),
(275, 'The Elder Scrolls V: Skyrim Special Edition', 'RPG'),
(276, 'The Evil Within', 'Ação'),
(277, 'The Evil Within 2', 'Ação'),
(278, 'The Gunk', 'Ação'),
(279, 'The Lamplighters League', 'Aventura'),
(280, 'The Last Case of Benedict Fox', 'Aventura'),
(281, 'The Outer Worlds', 'RPG'),
(282, 'The Rewinder', 'Aventura'),
(283, 'The Saboteur™', 'Ação'),
(284, 'The Sims™ 3', 'Simulação'),
(285, 'The Sims™ 4', 'Simulação'),
(286, 'The Walking Dead: A Temporada Final', 'Aventura'),
(287, 'theHunter: Call of the Wild™', 'Simulação'),
(288, 'Thirsty Suitors', 'Ação'),
(289, 'This War of Mine: Final Cut', 'Aventura'),
(290, 'Those Who Remain', 'Aventura'),
(291, 'Tin Hearts', 'Aventura'),
(292, 'Titanfall™ 2', 'Tiro'),
(293, 'Tom Clancys Rainbow Six® Siege', 'Tiro'),
(294, 'Tom Clancys Ghost Recon® Wildlands', 'Ação'),
(295, 'Tomb Raider: Definitive Edition', 'Ação'),
(296, 'Torment: Tides of Numenera', 'RPG'),
(297, 'Totally Accurate Battle Simulator', 'Simulação'),
(298, 'Totally Reliable Delivery Service', 'Aventura'),
(299, 'Trailmakers', 'Aventura'),
(300, 'Train Sim World® 5', 'Simulação'),
(301, 'TRIALS of MANA', 'RPG'),
(302, 'TUNIC', 'Aventura'),
(303, 'Turbo Golf Racing', 'Corrida'),
(304, 'Turnip Boy Robs a Bank', 'Ação'),
(305, 'Unpacking', 'Simulação'),
(306, 'Unravel', 'Aventura'),
(307, 'Unravel Two', 'Aventura'),
(308, 'VALORANT', 'Tiro'),
(309, 'Vampire Survivors', 'Ação'),
(310, 'Wargroove 2', 'Estratégia'),
(311, 'Warhammer 40,000: Boltgun', 'Tiro'),
(312, 'Warhammer 40,000: Darktide', 'Tiro'),
(313, 'Wartales', 'Aventura'),
(314, 'Wasteland 2: Directors Cut', 'RPG'),
(315, 'Wasteland 3', 'RPG'),
(316, 'Wasteland Remastered', 'RPG'),
(317, 'Watch Dogs® 2', 'Ação'),
(318, 'We Happy Few', 'Ação'),
(319, 'We Love Katamari REROLL+ Royal Reverie', 'Plataforma'),
(320, 'While the Irons Hot', 'Aventura'),
(321, 'WILD HEARTS™', 'Ação'),
(322, 'Wo Long: Fallen Dynasty', 'Ação'),
(323, 'Wolfenstein 3D', 'Tiro'),
(324, 'Wolfenstein II: Standard Edition', 'Tiro'),
(325, 'Wolfenstein The New Order', 'Tiro'),
(326, 'Wolfenstein The Old Blood', 'Tiro'),
(327, 'Wolfenstein Youngblood', 'Tiro'),
(328, 'World War Z Aftermath', 'Tiro'),
(329, 'Wreckfest', 'Corrida'),
(330, 'Yakuza 0', 'Ação'),
(331, 'Yakuza 3 Remastered', 'Ação'),
(332, 'Yakuza 4 Remastered', 'Ação'),
(333, 'Yakuza 5 Remastered', 'Ação'),
(334, 'Yakuza 6 The Song of Life', 'Ação'),
(335, 'Yakuza Kiwami', 'Ação'),
(336, 'Yakuza Kiwami 2', 'Ação'),
(337, 'Yakuza: Like a Dragon', 'RPG'),
(338, 'Zombie Army 4: Dead War', 'Tiro'),
(339, 'Zoo Tycoon: Ultimate Animal Collection', 'Simulação');
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
        val query = "SELECT Id_Jogo, Nome, Tipo FROM Jogo order by tipo, nome"
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

    fun inserirJogo(nome_Jogo: String?, tipo: String?): Long{
        val db = this.writableDatabase
        val jooj = ContentValues()
        jooj.put("Nome", nome_Jogo)
        jooj.put("Tipo", tipo)
        return db.insert("Jogo", null, jooj)
    }

    fun adicionarFavorito(userId: Int, jogoId: Int): Long {
        val bd = this.readableDatabase
        val query = "SELECT * FROM Favorita WHERE fk_User_Id_User = ? AND fk_Jogo_Id_Jogo = ?"
        val cursor = bd.rawQuery(query, arrayOf(userId.toString(), jogoId.toString()))
        if (cursor.count == 0) {
            val db = this.writableDatabase
            val values = ContentValues().apply {
                put("fk_User_Id_User", userId)
                put("fk_Jogo_Id_Jogo", jogoId)
            }
            return db.insert("Favorita", null, values)
        } else {
            return -1
        }
    }

    fun removerFavorito(userId: Int, jogoId: Int): Int {
        val db = this.writableDatabase
        return db.delete("Favorita", "fk_User_Id_User = ? AND fk_Jogo_Id_Jogo = ?", arrayOf(userId.toString(), jogoId.toString()))
    }

}
