-- phpMyAdmin SQL Dump
-- version 4.9.2
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1:3306
-- Généré le :  lun. 24 fév. 2020 à 16:07
-- Version du serveur :  10.4.10-MariaDB
-- Version de PHP :  7.3.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données :  `gestudent`
--

-- --------------------------------------------------------

--
-- Structure de la table `behaviour`
--

DROP TABLE IF EXISTS `behaviour`;
CREATE TABLE IF NOT EXISTS `behaviour` (
  `idbeh` int(11) NOT NULL AUTO_INCREMENT,
  `attendance` int(11) NOT NULL,
  `award` int(11) NOT NULL,
  PRIMARY KEY (`idbeh`)
) ENGINE=MyISAM AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `behaviour`
--

INSERT INTO `behaviour` (`idbeh`, `attendance`, `award`) VALUES
(5, 3, 2);

-- --------------------------------------------------------

--
-- Structure de la table `class`
--

DROP TABLE IF EXISTS `class`;
CREATE TABLE IF NOT EXISTS `class` (
  `idclass` int(50) NOT NULL AUTO_INCREMENT,
  `nameC` varchar(50) NOT NULL,
  `date_c` date DEFAULT NULL,
  `grade` int(11) DEFAULT NULL,
  PRIMARY KEY (`idclass`),
  UNIQUE KEY `name` (`nameC`)
) ENGINE=MyISAM AUTO_INCREMENT=12 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `class`
--

INSERT INTO `class` (`idclass`, `nameC`, `date_c`, `grade`) VALUES
(9, '3a6', NULL, NULL),
(10, '3a8', NULL, NULL),
(11, '3a5', NULL, NULL);

-- --------------------------------------------------------

--
-- Structure de la table `classetudiant`
--

DROP TABLE IF EXISTS `classetudiant`;
CREATE TABLE IF NOT EXISTS `classetudiant` (
  `idclass` int(11) NOT NULL,
  `idEtudiant` int(11) NOT NULL,
  PRIMARY KEY (`idclass`,`idEtudiant`),
  UNIQUE KEY `idEtudiant` (`idEtudiant`),
  KEY `fk_idetudiant` (`idEtudiant`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `classetudiant`
--

INSERT INTO `classetudiant` (`idclass`, `idEtudiant`) VALUES
(9, 29),
(10, 11),
(10, 31);

-- --------------------------------------------------------

--
-- Structure de la table `club`
--

DROP TABLE IF EXISTS `club`;
CREATE TABLE IF NOT EXISTS `club` (
  `id_club` int(30) NOT NULL AUTO_INCREMENT,
  `nom` varchar(30) NOT NULL,
  `date` date NOT NULL,
  `email` varchar(30) NOT NULL,
  `numero` int(30) NOT NULL,
  `description` varchar(100) NOT NULL,
  `etat` int(10) NOT NULL,
  `id_president` int(30) NOT NULL,
  PRIMARY KEY (`id_club`),
  UNIQUE KEY `nom` (`nom`)
) ENGINE=MyISAM AUTO_INCREMENT=10 DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `cours`
--

DROP TABLE IF EXISTS `cours`;
CREATE TABLE IF NOT EXISTS `cours` (
  `name` varchar(50) NOT NULL,
  `idcour` int(50) NOT NULL AUTO_INCREMENT,
  `lesson` varchar(50) NOT NULL,
  `duration` int(50) NOT NULL,
  PRIMARY KEY (`idcour`)
) ENGINE=MyISAM AUTO_INCREMENT=44 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `cours`
--

INSERT INTO `cours` (`name`, `idcour`, `lesson`, `duration`) VALUES
('physique', 41, 'ph', 1),
('Base de donne', 42, 'sql', 2),
('English', 43, 'linkers', 2);

-- --------------------------------------------------------

--
-- Structure de la table `emprunt`
--

DROP TABLE IF EXISTS `emprunt`;
CREATE TABLE IF NOT EXISTS `emprunt` (
  `id_emprunt` int(11) NOT NULL AUTO_INCREMENT,
  `date_emprunt` date NOT NULL,
  `date_retour` date NOT NULL,
  `id` int(11) NOT NULL,
  `id_livre` int(11) NOT NULL,
  PRIMARY KEY (`id_emprunt`),
  KEY `fk_idL` (`id_livre`),
  KEY `fk_idU` (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `evenement`
--

DROP TABLE IF EXISTS `evenement`;
CREATE TABLE IF NOT EXISTS `evenement` (
  `id_event` int(30) NOT NULL AUTO_INCREMENT,
  `nom` varchar(30) NOT NULL,
  `description` varchar(30) NOT NULL,
  `date` date NOT NULL,
  `place` varchar(30) NOT NULL,
  `id_club` int(30) NOT NULL,
  PRIMARY KEY (`id_event`)
) ENGINE=MyISAM AUTO_INCREMENT=26 DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `exams`
--

DROP TABLE IF EXISTS `exams`;
CREATE TABLE IF NOT EXISTS `exams` (
  `idexa` int(11) NOT NULL AUTO_INCREMENT,
  `nomex` varchar(255) NOT NULL,
  `dateex` date NOT NULL,
  `duree` int(11) NOT NULL,
  PRIMARY KEY (`idexa`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `livres`
--

DROP TABLE IF EXISTS `livres`;
CREATE TABLE IF NOT EXISTS `livres` (
  `id_livre` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(30) NOT NULL,
  `image` varchar(30) NOT NULL,
  `author` varchar(30) NOT NULL,
  `url` varchar(30) NOT NULL,
  `categorie` varchar(30) NOT NULL,
  `quantite` int(11) NOT NULL,
  PRIMARY KEY (`id_livre`),
  UNIQUE KEY `name` (`name`)
) ENGINE=MyISAM AUTO_INCREMENT=11 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `livres`
--

INSERT INTO `livres` (`id_livre`, `name`, `image`, `author`, `url`, `categorie`, `quantite`) VALUES
(8, 'yassine', 'mahmoudi', 'ayadi', '989654', 'police', 20),
(6, 'pipo', '/img/ayadi1', 'mohamed', '24563168', 'police', 25);

-- --------------------------------------------------------

--
-- Structure de la table `meal`
--

DROP TABLE IF EXISTS `meal`;
CREATE TABLE IF NOT EXISTS `meal` (
  `id_meal` int(11) NOT NULL AUTO_INCREMENT,
  `image` varchar(20) NOT NULL,
  `name` varchar(20) NOT NULL,
  `type` varchar(200) NOT NULL,
  `rating` int(11) DEFAULT NULL,
  PRIMARY KEY (`id_meal`)
) ENGINE=InnoDB AUTO_INCREMENT=58 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `meal`
--

INSERT INTO `meal` (`id_meal`, `image`, `name`, `type`, `rating`) VALUES
(55, 'pizza1', 'PIZZA', 'plat principale', 4),
(56, 'koka', 'gazouza', 'dessert', 3),
(57, 'slta1', 'slata', 'dessert', 3);

-- --------------------------------------------------------

--
-- Structure de la table `menu`
--

DROP TABLE IF EXISTS `menu`;
CREATE TABLE IF NOT EXISTS `menu` (
  `id_menu` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) NOT NULL,
  `description` varchar(200) NOT NULL,
  PRIMARY KEY (`id_menu`),
  UNIQUE KEY `cnunique` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `menu`
--

INSERT INTO `menu` (`id_menu`, `name`, `description`) VALUES
(20, 'Alska', 'Alska1');

-- --------------------------------------------------------

--
-- Structure de la table `menumeal`
--

DROP TABLE IF EXISTS `menumeal`;
CREATE TABLE IF NOT EXISTS `menumeal` (
  `id_menu` int(11) NOT NULL,
  `id_meal` int(11) NOT NULL,
  `id` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`),
  KEY `FK_Menu` (`id_menu`),
  KEY `FK_Meal` (`id_meal`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `restaurant_subscription`
--

DROP TABLE IF EXISTS `restaurant_subscription`;
CREATE TABLE IF NOT EXISTS `restaurant_subscription` (
  `idsubscription` int(11) NOT NULL AUTO_INCREMENT,
  `id_user` int(11) NOT NULL,
  `duration` varchar(20) NOT NULL,
  `amount` double NOT NULL,
  PRIMARY KEY (`idsubscription`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `roles`
--

DROP TABLE IF EXISTS `roles`;
CREATE TABLE IF NOT EXISTS `roles` (
  `Role` varchar(25) NOT NULL,
  PRIMARY KEY (`Role`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `roles`
--

INSERT INTO `roles` (`Role`) VALUES
('adminstrator'),
('student'),
('teacher');

-- --------------------------------------------------------

--
-- Structure de la table `seq_user`
--

DROP TABLE IF EXISTS `seq_user`;
CREATE TABLE IF NOT EXISTS `seq_user` (
  `next_not_cached_value` bigint(21) NOT NULL,
  `minimum_value` bigint(21) NOT NULL,
  `maximum_value` bigint(21) NOT NULL,
  `start_value` bigint(21) NOT NULL COMMENT 'start value when sequences is created or value if RESTART is used',
  `increment` bigint(21) NOT NULL COMMENT 'increment value',
  `cache_size` bigint(21) UNSIGNED NOT NULL,
  `cycle_option` tinyint(1) UNSIGNED NOT NULL COMMENT '0 if no cycles are allowed, 1 if the sequence should begin a new cycle when maximum_value is passed',
  `cycle_count` bigint(21) NOT NULL COMMENT 'How many cycles have been done'
) ENGINE=MyISAM;

--
-- Déchargement des données de la table `seq_user`
--

INSERT INTO `seq_user` (`next_not_cached_value`, `minimum_value`, `maximum_value`, `start_value`, `increment`, `cache_size`, `cycle_option`, `cycle_count`) VALUES
(10234, 1, 9223372036854775806, 1234, 3, 1000, 0, 0);

-- --------------------------------------------------------

--
-- Structure de la table `tcc`
--

DROP TABLE IF EXISTS `tcc`;
CREATE TABLE IF NOT EXISTS `tcc` (
  `idclass` int(11) NOT NULL,
  `idteacher` int(11) NOT NULL,
  `idcours` int(11) NOT NULL,
  PRIMARY KEY (`idclass`,`idteacher`,`idcours`),
  KEY `FKTEACHER` (`idteacher`),
  KEY `idcours` (`idcours`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `tcc`
--

INSERT INTO `tcc` (`idclass`, `idteacher`, `idcours`) VALUES
(9, 1, 43),
(9, 2, 42),
(9, 20, 41),
(10, 2, 42),
(11, 1, 43),
(11, 2, 42),
(11, 20, 41),
(11, 20, 43);

-- --------------------------------------------------------

--
-- Structure de la table `user`
--

DROP TABLE IF EXISTS `user`;
CREATE TABLE IF NOT EXISTS `user` (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(180) DEFAULT NULL,
  `username_canonical` varchar(180) DEFAULT NULL,
  `lastname` varchar(180) DEFAULT NULL,
  `firstname` varchar(180) DEFAULT NULL,
  `image` varchar(180) DEFAULT NULL,
  `email` varchar(180) DEFAULT NULL,
  `email_canonical` varchar(180) DEFAULT NULL,
  `enabled` tinyint(1) DEFAULT 0,
  `salt` varchar(180) DEFAULT NULL,
  `password` varchar(180) DEFAULT NULL,
  `last_login` date DEFAULT NULL,
  `confirmation_token` varchar(180) DEFAULT NULL,
  `password_requested_at` date DEFAULT NULL,
  `roles` longtext DEFAULT NULL,
  `birthDay` date DEFAULT NULL,
  `phone` int(8) DEFAULT NULL,
  `pays` varchar(180) DEFAULT NULL,
  `adress` varchar(180) DEFAULT NULL,
  `gender` varchar(10) DEFAULT NULL,
  `iclass` int(50) DEFAULT NULL,
  `idcode` varchar(180) DEFAULT NULL,
  `salary` double DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`),
  UNIQUE KEY `idcode` (`idcode`),
  UNIQUE KEY `email` (`email`)
) ENGINE=MyISAM AUTO_INCREMENT=32 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `user`
--

INSERT INTO `user` (`id`, `username`, `username_canonical`, `lastname`, `firstname`, `image`, `email`, `email_canonical`, `enabled`, `salt`, `password`, `last_login`, `confirmation_token`, `password_requested_at`, `roles`, `birthDay`, `phone`, `pays`, `adress`, `gender`, `iclass`, `idcode`, `salary`) VALUES
(1, NULL, NULL, 'farah', 'chikhaoui', NULL, 'farah.chikhaoui@esprit.tn', NULL, 0, NULL, NULL, NULL, NULL, NULL, 'teacher', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(2, NULL, NULL, 'rania', 'kalel', NULL, 'rania.kalel@esprit.tn', NULL, 0, NULL, NULL, NULL, NULL, NULL, 'teacher', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(29, NULL, NULL, 'bougattya', 'neder', NULL, 'nader.bougataya@esprit.tn', NULL, 0, NULL, NULL, NULL, NULL, NULL, 'student', '2002-02-07', 29025104, 'tunis', 'tunis', 'male', NULL, '132GEM7303', NULL),
(11, 'ayadicss01', NULL, 'nouha', 'chikhaoui', 'C:\\Users\\Ayadi\\Desktop\\pi\\83305509_118621516150199_435242579798261760_n.jpg', 'nouha.chikhaoui@esprit.tn', NULL, 1, NULL, 'NeKo5qJde90P9Ocy3M+MYQ==', NULL, NULL, NULL, 'student', '1995-05-06', 22901120, 'Tunisia', 'bellvue', 'female', NULL, '100GEF1255', 0),
(31, 'ayadic', NULL, 'yassine', 'Ayadi', 'C:\\Users\\Ayadi\\Desktop\\pi\\badge\\yassie.png', 'ayadiyassine21@gmail.com', NULL, 1, NULL, 'Y5f3Nlf+uWQvkOEkGR5ZzQ==', NULL, NULL, NULL, 'student', '2005-02-17', 29025104, 'Tunisia', 'tunis', 'male', NULL, '164GEM7321', NULL),
(28, NULL, NULL, 'benchikh', 'amin', NULL, 'amin.bencheikh@esprit.tn', NULL, 0, NULL, NULL, NULL, NULL, NULL, 'student', '2002-02-07', 29025104, 'tunis', 'tunis', 'male', NULL, '186GEM7297', NULL),
(30, 'admin', NULL, 'Ayadi', 'Yassine', 'f_5e53dcd8c1c91.png', NULL, NULL, 0, NULL, 'cuajHuOtewq1w/3Hd3SHjA==', NULL, NULL, NULL, 'admin', '1998-08-27', 29025104, NULL, NULL, NULL, NULL, NULL, NULL),
(20, NULL, NULL, 'Ben Chiekh', 'Amin', NULL, 'amin.becnchiekh', NULL, 0, NULL, NULL, NULL, NULL, NULL, 'teacher', '1997-02-15', 25123654, 'Camroun', 'younde', 'male', NULL, '176GEM4273', 0);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
