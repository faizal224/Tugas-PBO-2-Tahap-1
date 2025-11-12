-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Nov 11, 2025 at 11:07 AM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `db_agama`
--

-- --------------------------------------------------------

--
-- Table structure for table `mempelai_laki_laki`
--

CREATE TABLE `mempelai_laki_laki` (
  `id_mempelai_laki` varchar(20) NOT NULL,
  `id_user` varchar(20) DEFAULT NULL,
  `no_pendaftaran` varchar(20) DEFAULT NULL,
  `nama` varchar(100) DEFAULT NULL,
  `tempat_lahir` varchar(100) DEFAULT NULL,
  `tgl_lahir` varchar(50) DEFAULT NULL,
  `usia` varchar(10) DEFAULT NULL,
  `kwn` varchar(50) DEFAULT NULL,
  `agama` varchar(50) DEFAULT NULL,
  `pekerjaan` varchar(100) DEFAULT NULL,
  `alamat` varchar(255) DEFAULT NULL,
  `pendidikan` varchar(100) DEFAULT NULL,
  `status` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `mempelai_perempuan`
--

CREATE TABLE `mempelai_perempuan` (
  `id_mempelai_perempuan` varchar(20) NOT NULL,
  `id_user` varchar(20) DEFAULT NULL,
  `no_pendaftaran` varchar(20) DEFAULT NULL,
  `nama` varchar(100) DEFAULT NULL,
  `tempat_lahir` varchar(100) DEFAULT NULL,
  `tgl_lahir` varchar(50) DEFAULT NULL,
  `usia` varchar(10) DEFAULT NULL,
  `kwn` varchar(50) DEFAULT NULL,
  `agama` varchar(50) DEFAULT NULL,
  `pekerjaan` varchar(100) DEFAULT NULL,
  `alamat` varchar(255) DEFAULT NULL,
  `pendidikan` varchar(100) DEFAULT NULL,
  `status` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `pendaftaran`
--

CREATE TABLE `pendaftaran` (
  `no_pendaftaran` varchar(20) NOT NULL,
  `id_admin` varchar(20) DEFAULT NULL,
  `kode_kelurahan` varchar(50) DEFAULT NULL,
  `tgl_pendaftaran` varchar(50) DEFAULT NULL,
  `tgl_pernikahan` varchar(50) DEFAULT NULL,
  `status` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `validasi`
--

CREATE TABLE `validasi` (
  `no_validasi` varchar(20) NOT NULL,
  `id_admin` varchar(20) DEFAULT NULL,
  `no_pendaftaran` varchar(20) DEFAULT NULL,
  `tgl_validasi` varchar(50) DEFAULT NULL,
  `catatan_tambahan` varchar(255) DEFAULT NULL,
  `hasil` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `mempelai_laki_laki`
--
ALTER TABLE `mempelai_laki_laki`
  ADD PRIMARY KEY (`id_mempelai_laki`);

--
-- Indexes for table `mempelai_perempuan`
--
ALTER TABLE `mempelai_perempuan`
  ADD PRIMARY KEY (`id_mempelai_perempuan`);

--
-- Indexes for table `pendaftaran`
--
ALTER TABLE `pendaftaran`
  ADD PRIMARY KEY (`no_pendaftaran`);

--
-- Indexes for table `validasi`
--
ALTER TABLE `validasi`
  ADD PRIMARY KEY (`no_validasi`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
