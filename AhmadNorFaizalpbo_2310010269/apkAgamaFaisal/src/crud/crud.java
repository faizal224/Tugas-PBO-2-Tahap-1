/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package crud;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTable;
import java.sql.ResultSetMetaData;
/**
 *
 * @author User
 */
    public class crud {
    private Connection Koneksidb;
    private String username="root";
    private String password="";
    private String dbname="db_agama"; 
    private String urlKoneksi="jdbc:mysql://localhost/"+dbname;
    public boolean duplikasi=false;

    public String CEK_ID_ADMIN_PEN, CEK_KODE_KELURAHAN_PEN, CEK_TGL_PENDAFTARAN, CEK_TGL_PERNIKAHAN, CEK_STATUS_PEN = null;
    public String CEK_ID_USER_ML, CEK_NO_PENDAFTARAN_ML, CEK_NAMA_ML, CEK_TEMPAT_LAHIR_ML, CEK_TGL_LAHIR_ML, CEK_USIA_ML, CEK_KWN_ML, CEK_AGAMA_ML, CEK_PEKERJAAN_ML, CEK_ALAMAT_ML, CEK_PENDIDIKAN_ML, CEK_STATUS_ML = null; // FOTO DIHILANGKAN
    public String CEK_ID_USER_MP, CEK_NO_PENDAFTARAN_MP, CEK_NAMA_MP, CEK_TEMPAT_LAHIR_MP, CEK_TGL_LAHIR_MP, CEK_USIA_MP, CEK_KWN_MP, CEK_AGAMA_MP, CEK_PEKERJAAN_MP, CEK_ALAMAT_MP, CEK_PENDIDIKAN_MP, CEK_STATUS_MP = null; // FOTO DIHILANGKAN
    public String CEK_ID_ADMIN_VAL, CEK_NO_PENDAFTARAN_VAL, CEK_TGL_VALIDASI, CEK_CATATAN_TAMBAHAN, CEK_HASIL = null;

    
    public crud(){
        try {
            Driver dbdriver = new com.mysql.jdbc.Driver();
            DriverManager.registerDriver(dbdriver);
            Koneksidb=DriverManager.getConnection(urlKoneksi,username,password);
            System.out.print("Database Terkoneksi");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,e.toString());
        }
    }
    
    public void simpanPendaftaran01(String no_pendaftaran, String id_admin, String kode_kelurahan, String tgl_pendaftaran, String tgl_pernikahan, String status){
        try {
            String sqlsimpan="insert into pendaftaran(no_pendaftaran, id_admin, kode_kelurahan, tgl_pendaftaran, tgl_pernikahan, status) value"
                    + " ('"+no_pendaftaran+"', '"+id_admin+"', '"+kode_kelurahan+"', '"+tgl_pendaftaran+"', '"+tgl_pernikahan+"', '"+status+"')";
            String sqlcari="select*from pendaftaran where no_pendaftaran='"+no_pendaftaran+"'";
            
            Statement cari=Koneksidb.createStatement();
            ResultSet data=cari.executeQuery(sqlcari);
            
            if (data.next()){
                JOptionPane.showMessageDialog(null, "No Pendaftaran sudah terdaftar");
            } else {
                Statement perintah=Koneksidb.createStatement();
                perintah.execute(sqlsimpan);
                JOptionPane.showMessageDialog(null, "Data Pendaftaran berhasil disimpan");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
    
    // Pola 02 (Aman)
    public void simpanPendaftaran02(String no_pendaftaran, String id_admin, String kode_kelurahan, String tgl_pendaftaran, String tgl_pernikahan, String status){
        try {
            String sqlsimpan="INSERT INTO pendaftaran (no_pendaftaran, id_admin, kode_kelurahan, tgl_pendaftaran, tgl_pernikahan, status) VALUES (?, ?, ?, ?, ?, ?)";
            String sqlcari= "SELECT*FROM pendaftaran WHERE no_pendaftaran = ?";
            
            PreparedStatement cari = Koneksidb.prepareStatement(sqlcari);
            cari.setString(1, no_pendaftaran);
            ResultSet data = cari.executeQuery();
            
            if (data.next()){
                JOptionPane.showMessageDialog(null, "No Pendaftaran sudah terdaftar");
                this.duplikasi = true;
                this.CEK_ID_ADMIN_PEN = data.getString("id_admin");
                this.CEK_KODE_KELURAHAN_PEN = data.getString("kode_kelurahan");
                this.CEK_TGL_PENDAFTARAN = data.getString("tgl_pendaftaran");
                this.CEK_TGL_PERNIKAHAN = data.getString("tgl_pernikahan");
                this.CEK_STATUS_PEN = data.getString("status");
            } else {
                this.duplikasi = false;
                this.CEK_ID_ADMIN_PEN = null;
                this.CEK_KODE_KELURAHAN_PEN = null;
                this.CEK_TGL_PENDAFTARAN = null;
                this.CEK_TGL_PERNIKAHAN = null;
                this.CEK_STATUS_PEN = null;
                
                PreparedStatement perintah = Koneksidb.prepareStatement(sqlsimpan);
                perintah.setString(1, no_pendaftaran);
                perintah.setString(2, id_admin);
                perintah.setString(3, kode_kelurahan);
                perintah.setString(4, tgl_pendaftaran);
                perintah.setString(5, tgl_pernikahan);
                perintah.setString(6, status);
                perintah.executeUpdate();
                JOptionPane.showMessageDialog(null, "Data Pendaftaran berhasil disimpan");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
    
    public void ubahPendaftaran(String no_pendaftaran, String id_admin, String kode_kelurahan, String tgl_pendaftaran, String tgl_pernikahan, String status){
        try {
            String sqlubah="UPDATE pendaftaran SET id_admin = ?, kode_kelurahan = ?, tgl_pendaftaran = ?, tgl_pernikahan = ?, status = ? WHERE no_pendaftaran = ?";
            PreparedStatement perintah = Koneksidb.prepareStatement(sqlubah);
            perintah.setString(1, id_admin);
            perintah.setString(2, kode_kelurahan);
            perintah.setString(3, tgl_pendaftaran);
            perintah.setString(4, tgl_pernikahan);
            perintah.setString(5, status);
            perintah.setString(6, no_pendaftaran);
            perintah.executeUpdate();
            JOptionPane.showMessageDialog(null, "Data Pendaftaran berhasil diubah");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
    
    public void hapusPendaftaran(String no_pendaftaran){
        try {
            String sqlhapus="DELETE FROM pendaftaran WHERE no_pendaftaran = ?";
            PreparedStatement perintah = Koneksidb.prepareStatement(sqlhapus);
            perintah.setString(1, no_pendaftaran);
            perintah.executeUpdate();
            JOptionPane.showMessageDialog(null, "Data Pendaftaran berhasil dihapus");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
    
    public void tampilDataPendaftaran(JTable komponentabel, String SQL){
        try {
            PreparedStatement perintah = Koneksidb.prepareStatement(SQL);
            ResultSet data = perintah.executeQuery();
            ResultSetMetaData meta = data.getMetaData();
            int jumlahkolom = meta.getColumnCount();
            DefaultTableModel modeltabel = new DefaultTableModel();
            
            modeltabel.addColumn("No Pendaftaran");
            modeltabel.addColumn("ID Admin");
            modeltabel.addColumn("Kode Kelurahan");
            modeltabel.addColumn("Tgl Pendaftaran");
            modeltabel.addColumn("Tgl Pernikahan");
            modeltabel.addColumn("Status");
            
            while(data.next()){
                Object[] row = new Object[jumlahkolom];
                for(int i=1; i<=jumlahkolom; i++){
                    row[i-1]=data.getObject(i);
                }
                modeltabel.addRow(row);
            }
            komponentabel.setModel(modeltabel);
        } catch (Exception e) {
        }
    }

    public void simpanMempelaiLaki01(String id_mempelai_laki, String id_user, String no_pendaftaran, String nama, String tempat_lahir, String tgl_lahir, String usia, String kwn, String agama, String pekerjaan, String alamat, String pendidikan, String status){
        try {
            // Kolom 'foto' DIHILANGKAN
            String sqlsimpan="insert into mempelai_laki_laki(id_mempelai_laki, id_user, no_pendaftaran, nama, tempat_lahir, tgl_lahir, usia, kwn, agama, pekerjaan, alamat, pendidikan, status) value"
                    + " ('"+id_mempelai_laki+"', '"+id_user+"', '"+no_pendaftaran+"', '"+nama+"', '"+tempat_lahir+"', '"+tgl_lahir+"', '"+usia+"', '"+kwn+"', '"+agama+"', '"+pekerjaan+"', '"+alamat+"', '"+pendidikan+"', '"+status+"')";
            String sqlcari="select*from mempelai_laki_laki where id_mempelai_laki='"+id_mempelai_laki+"'";
            
            Statement cari=Koneksidb.createStatement();
            ResultSet data=cari.executeQuery(sqlcari);
            
            if (data.next()){
                JOptionPane.showMessageDialog(null, "ID Mempelai Laki-laki sudah terdaftar");
            } else {
                Statement perintah=Koneksidb.createStatement();
                perintah.execute(sqlsimpan);
                JOptionPane.showMessageDialog(null, "Data Mempelai Laki-laki berhasil disimpan");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    public void simpanMempelaiLaki02(String id_mempelai_laki, String id_user, String no_pendaftaran, String nama, String tempat_lahir, String tgl_lahir, String usia, String kwn, String agama, String pekerjaan, String alamat, String pendidikan, String status){
        try {
            String sqlsimpan="INSERT INTO mempelai_laki_laki (id_mempelai_laki, id_user, no_pendaftaran, nama, tempat_lahir, tgl_lahir, usia, kwn, agama, pekerjaan, alamat, pendidikan, status) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            String sqlcari= "SELECT*FROM mempelai_laki_laki WHERE id_mempelai_laki = ?";
            
            PreparedStatement cari = Koneksidb.prepareStatement(sqlcari);
            cari.setString(1, id_mempelai_laki);
            ResultSet data = cari.executeQuery();
            
            if (data.next()){
                JOptionPane.showMessageDialog(null, "ID Mempelai Laki-laki sudah terdaftar");
                this.duplikasi = true;
                this.CEK_ID_USER_ML = data.getString("id_user");
                this.CEK_NO_PENDAFTARAN_ML = data.getString("no_pendaftaran");
                this.CEK_NAMA_ML = data.getString("nama");
                this.CEK_TEMPAT_LAHIR_ML = data.getString("tempat_lahir");
                this.CEK_TGL_LAHIR_ML = data.getString("tgl_lahir");
                this.CEK_USIA_ML = data.getString("usia");
                this.CEK_KWN_ML = data.getString("kwn");
                this.CEK_AGAMA_ML = data.getString("agama");
                this.CEK_PEKERJAAN_ML = data.getString("pekerjaan");
                this.CEK_ALAMAT_ML = data.getString("alamat");
                this.CEK_PENDIDIKAN_ML = data.getString("pendidikan");
                this.CEK_STATUS_ML = data.getString("status");
            } else {
                this.duplikasi = false;
                this.CEK_ID_USER_ML = null;
                this.CEK_NO_PENDAFTARAN_ML = null;
                this.CEK_NAMA_ML = null;
                this.CEK_TEMPAT_LAHIR_ML = null;
                this.CEK_TGL_LAHIR_ML = null;
                this.CEK_USIA_ML = null;
                this.CEK_KWN_ML = null;
                this.CEK_AGAMA_ML = null;
                this.CEK_PEKERJAAN_ML = null;
                this.CEK_ALAMAT_ML = null;
                this.CEK_PENDIDIKAN_ML = null;
                this.CEK_STATUS_ML = null;
                
                PreparedStatement perintah = Koneksidb.prepareStatement(sqlsimpan);
                perintah.setString(1, id_mempelai_laki);
                perintah.setString(2, id_user);
                perintah.setString(3, no_pendaftaran);
                perintah.setString(4, nama);
                perintah.setString(5, tempat_lahir);
                perintah.setString(6, tgl_lahir);
                perintah.setString(7, usia);
                perintah.setString(8, kwn);
                perintah.setString(9, agama);
                perintah.setString(10, pekerjaan);
                perintah.setString(11, alamat);
                perintah.setString(12, pendidikan);
                perintah.setString(13, status);
                perintah.executeUpdate();
                JOptionPane.showMessageDialog(null, "Data Mempelai Laki-laki berhasil disimpan");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
    
    public void ubahMempelaiLaki(String id_mempelai_laki, String id_user, String no_pendaftaran, String nama, String tempat_lahir, String tgl_lahir, String usia, String kwn, String agama, String pekerjaan, String alamat, String pendidikan, String status){
        try {
            String sqlubah="UPDATE mempelai_laki_laki SET id_user = ?, no_pendaftaran = ?, nama = ?, tempat_lahir = ?, tgl_lahir = ?, usia = ?, kwn = ?, agama = ?, pekerjaan = ?, alamat = ?, pendidikan = ?, status = ? WHERE id_mempelai_laki = ?";
            PreparedStatement perintah = Koneksidb.prepareStatement(sqlubah);
            perintah.setString(1, id_user);
            perintah.setString(2, no_pendaftaran);
            perintah.setString(3, nama);
            perintah.setString(4, tempat_lahir);
            perintah.setString(5, tgl_lahir);
            perintah.setString(6, usia);
            perintah.setString(7, kwn);
            perintah.setString(8, agama);
            perintah.setString(9, pekerjaan);
            perintah.setString(10, alamat);
            perintah.setString(11, pendidikan);
            perintah.setString(12, status);
            perintah.setString(13, id_mempelai_laki); 
            perintah.executeUpdate();
            JOptionPane.showMessageDialog(null, "Data Mempelai Laki-laki berhasil diubah");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
    
    public void hapusMempelaiLaki(String id_mempelai_laki){
        try {
            String sqlhapus="DELETE FROM mempelai_laki_laki WHERE id_mempelai_laki = ?";
            PreparedStatement perintah = Koneksidb.prepareStatement(sqlhapus);
            perintah.setString(1, id_mempelai_laki);
            perintah.executeUpdate();
            JOptionPane.showMessageDialog(null, "Data Mempelai Laki-laki berhasil dihapus");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
    
    public void tampilDataMempelaiLaki(JTable komponentabel, String SQL){
        try {
            PreparedStatement perintah = Koneksidb.prepareStatement(SQL);
            ResultSet data = perintah.executeQuery();
            ResultSetMetaData meta = data.getMetaData();
            int jumlahkolom = meta.getColumnCount();
            DefaultTableModel modeltabel = new DefaultTableModel();
            
            modeltabel.addColumn("ID Mempelai Laki");
            modeltabel.addColumn("ID User");
            modeltabel.addColumn("No Pendaftaran");
            modeltabel.addColumn("Nama");
            modeltabel.addColumn("Tempat Lahir");
            modeltabel.addColumn("Tgl Lahir");
            modeltabel.addColumn("Usia");
            modeltabel.addColumn("KWN");
            modeltabel.addColumn("Agama");
            modeltabel.addColumn("Pekerjaan");
            modeltabel.addColumn("Alamat");
            modeltabel.addColumn("Pendidikan");
            modeltabel.addColumn("Status");
            // Kolom Foto DIHILANGKAN
            
            while(data.next()){
                Object[] row = new Object[jumlahkolom];
                for(int i=1; i<=jumlahkolom; i++){
                    row[i-1]=data.getObject(i);
                }
                modeltabel.addRow(row);
            }
            komponentabel.setModel(modeltabel);
        } catch (Exception e) {
        }
    }

    // Pola 01 (Tidak Aman)
    public void simpanMempelaiPerempuan01(String id_mempelai_perempuan, String id_user, String no_pendaftaran, String nama, String tempat_lahir, String tgl_lahir, String usia, String kwn, String agama, String pekerjaan, String alamat, String pendidikan, String status){
        try {
            // Kolom 'foto' DIHILANGKAN
            String sqlsimpan="insert into mempelai_perempuan(id_mempelai_perempuan, id_user, no_pendaftaran, nama, tempat_lahir, tgl_lahir, usia, kwn, agama, pekerjaan, alamat, pendidikan, status) value"
                    + " ('"+id_mempelai_perempuan+"', '"+id_user+"', '"+no_pendaftaran+"', '"+nama+"', '"+tempat_lahir+"', '"+tgl_lahir+"', '"+usia+"', '"+kwn+"', '"+agama+"', '"+pekerjaan+"', '"+alamat+"', '"+pendidikan+"', '"+status+"')";
            String sqlcari="select*from mempelai_perempuan where id_mempelai_perempuan='"+id_mempelai_perempuan+"'";
            
            Statement cari=Koneksidb.createStatement();
            ResultSet data=cari.executeQuery(sqlcari);
            
            if (data.next()){
                JOptionPane.showMessageDialog(null, "ID Mempelai Perempuan sudah terdaftar");
            } else {
                Statement perintah=Koneksidb.createStatement();
                perintah.execute(sqlsimpan);
                JOptionPane.showMessageDialog(null, "Data Mempelai Perempuan berhasil disimpan");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
    
    // Pola 02 (Aman)
    public void simpanMempelaiPerempuan02(String id_mempelai_perempuan, String id_user, String no_pendaftaran, String nama, String tempat_lahir, String tgl_lahir, String usia, String kwn, String agama, String pekerjaan, String alamat, String pendidikan, String status){
        try {
            // Kolom 'foto' DIHILANGKAN
            String sqlsimpan="INSERT INTO mempelai_perempuan (id_mempelai_perempuan, id_user, no_pendaftaran, nama, tempat_lahir, tgl_lahir, usia, kwn, agama, pekerjaan, alamat, pendidikan, status) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            String sqlcari= "SELECT*FROM mempelai_perempuan WHERE id_mempelai_perempuan = ?";
            
            PreparedStatement cari = Koneksidb.prepareStatement(sqlcari);
            cari.setString(1, id_mempelai_perempuan);
            ResultSet data = cari.executeQuery();
            
            if (data.next()){
                JOptionPane.showMessageDialog(null, "ID Mempelai Perempuan sudah terdaftar");
                this.duplikasi = true;
                this.CEK_ID_USER_MP = data.getString("id_user");
                this.CEK_NO_PENDAFTARAN_MP = data.getString("no_pendaftaran");
                this.CEK_NAMA_MP = data.getString("nama");
                this.CEK_TEMPAT_LAHIR_MP = data.getString("tempat_lahir");
                this.CEK_TGL_LAHIR_MP = data.getString("tgl_lahir");
                this.CEK_USIA_MP = data.getString("usia");
                this.CEK_KWN_MP = data.getString("kwn");
                this.CEK_AGAMA_MP = data.getString("agama");
                this.CEK_PEKERJAAN_MP = data.getString("pekerjaan");
                this.CEK_ALAMAT_MP = data.getString("alamat");
                this.CEK_PENDIDIKAN_MP = data.getString("pendidikan");
                this.CEK_STATUS_MP = data.getString("status");
            } else {
                this.duplikasi = false;
                this.CEK_ID_USER_MP = null;
                this.CEK_NO_PENDAFTARAN_MP = null;
                this.CEK_NAMA_MP = null;
                this.CEK_TEMPAT_LAHIR_MP = null;
                this.CEK_TGL_LAHIR_MP = null;
                this.CEK_USIA_MP = null;
                this.CEK_KWN_MP = null;
                this.CEK_AGAMA_MP = null;
                this.CEK_PEKERJAAN_MP = null;
                this.CEK_ALAMAT_MP = null;
                this.CEK_PENDIDIKAN_MP = null;
                this.CEK_STATUS_MP = null;
                
                PreparedStatement perintah = Koneksidb.prepareStatement(sqlsimpan);
                perintah.setString(1, id_mempelai_perempuan);
                perintah.setString(2, id_user);
                perintah.setString(3, no_pendaftaran);
                perintah.setString(4, nama);
                perintah.setString(5, tempat_lahir);
                perintah.setString(6, tgl_lahir);
                perintah.setString(7, usia);
                perintah.setString(8, kwn);
                perintah.setString(9, agama);
                perintah.setString(10, pekerjaan);
                perintah.setString(11, alamat);
                perintah.setString(12, pendidikan);
                perintah.setString(13, status);
                perintah.executeUpdate();
                JOptionPane.showMessageDialog(null, "Data Mempelai Perempuan berhasil disimpan");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
    
    public void ubahMempelaiPerempuan(String id_mempelai_perempuan, String id_user, String no_pendaftaran, String nama, String tempat_lahir, String tgl_lahir, String usia, String kwn, String agama, String pekerjaan, String alamat, String pendidikan, String status){
        try {
            // Kolom 'foto' DIHILANGKAN
            String sqlubah="UPDATE mempelai_perempuan SET id_user = ?, no_pendaftaran = ?, nama = ?, tempat_lahir = ?, tgl_lahir = ?, usia = ?, kwn = ?, agama = ?, pekerjaan = ?, alamat = ?, pendidikan = ?, status = ? WHERE id_mempelai_perempuan = ?";
            PreparedStatement perintah = Koneksidb.prepareStatement(sqlubah);
            perintah.setString(1, id_user);
            perintah.setString(2, no_pendaftaran);
            perintah.setString(3, nama);
            perintah.setString(4, tempat_lahir);
            perintah.setString(5, tgl_lahir);
            perintah.setString(6, usia);
            perintah.setString(7, kwn);
            perintah.setString(8, agama);
            perintah.setString(9, pekerjaan);
            perintah.setString(10, alamat);
            perintah.setString(11, pendidikan);
            perintah.setString(12, status);
            perintah.setString(13, id_mempelai_perempuan); // ID sebagai parameter ke-13
            perintah.executeUpdate();
            JOptionPane.showMessageDialog(null, "Data Mempelai Perempuan berhasil diubah");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
    
    public void hapusMempelaiPerempuan(String id_mempelai_perempuan){
        try {
            String sqlhapus="DELETE FROM mempelai_perempuan WHERE id_mempelai_perempuan = ?";
            PreparedStatement perintah = Koneksidb.prepareStatement(sqlhapus);
            perintah.setString(1, id_mempelai_perempuan);
            perintah.executeUpdate();
            JOptionPane.showMessageDialog(null, "Data Mempelai Perempuan berhasil dihapus");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
    
    public void tampilDataMempelaiPerempuan(JTable komponentabel, String SQL){
        try {
            PreparedStatement perintah = Koneksidb.prepareStatement(SQL);
            ResultSet data = perintah.executeQuery();
            ResultSetMetaData meta = data.getMetaData();
            int jumlahkolom = meta.getColumnCount();
            DefaultTableModel modeltabel = new DefaultTableModel();
            
            modeltabel.addColumn("ID Mempelai Perempuan");
            modeltabel.addColumn("ID User");
            modeltabel.addColumn("No Pendaftaran");
            modeltabel.addColumn("Nama");
            modeltabel.addColumn("Tempat Lahir");
            modeltabel.addColumn("Tgl Lahir");
            modeltabel.addColumn("Usia");
            modeltabel.addColumn("KWN");
            modeltabel.addColumn("Agama");
            modeltabel.addColumn("Pekerjaan");
            modeltabel.addColumn("Alamat");
            modeltabel.addColumn("Pendidikan");
            modeltabel.addColumn("Status");
            // Kolom Foto DIHILANGKAN
            
            while(data.next()){
                Object[] row = new Object[jumlahkolom];
                for(int i=1; i<=jumlahkolom; i++){
                    row[i-1]=data.getObject(i);
                }
                modeltabel.addRow(row);
            }
            komponentabel.setModel(modeltabel);
        } catch (Exception e) {
        }
    }

    public void simpanValidasi01(String no_validasi, String id_admin, String no_pendaftaran, String tgl_validasi, String catatan_tambahan, String hasil){
        try {
            String sqlsimpan="insert into validasi(no_validasi, id_admin, no_pendaftaran, tgl_validasi, catatan_tambahan, hasil) value"
                    + " ('"+no_validasi+"', '"+id_admin+"', '"+no_pendaftaran+"', '"+tgl_validasi+"', '"+catatan_tambahan+"', '"+hasil+"')";
            String sqlcari="select*from validasi where no_validasi='"+no_validasi+"'";
            
            Statement cari=Koneksidb.createStatement();
            ResultSet data=cari.executeQuery(sqlcari);
            
            if (data.next()){
                JOptionPane.showMessageDialog(null, "No Validasi sudah terdaftar");
            } else {
                Statement perintah=Koneksidb.createStatement();
                perintah.execute(sqlsimpan);
                JOptionPane.showMessageDialog(null, "Data Validasi berhasil disimpan");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
    
    // Pola 02 (Aman)
    public void simpanValidasi02(String no_validasi, String id_admin, String no_pendaftaran, String tgl_validasi, String catatan_tambahan, String hasil){
        try {
            String sqlsimpan="INSERT INTO validasi (no_validasi, id_admin, no_pendaftaran, tgl_validasi, catatan_tambahan, hasil) VALUES (?, ?, ?, ?, ?, ?)";
            String sqlcari= "SELECT*FROM validasi WHERE no_validasi = ?";
            
            PreparedStatement cari = Koneksidb.prepareStatement(sqlcari);
            cari.setString(1, no_validasi);
            ResultSet data = cari.executeQuery();
            
            if (data.next()){
                JOptionPane.showMessageDialog(null, "No Validasi sudah terdaftar");
                this.duplikasi = true;
                this.CEK_ID_ADMIN_VAL = data.getString("id_admin");
                this.CEK_NO_PENDAFTARAN_VAL = data.getString("no_pendaftaran");
                this.CEK_TGL_VALIDASI = data.getString("tgl_validasi");
                this.CEK_CATATAN_TAMBAHAN = data.getString("catatan_tambahan");
                this.CEK_HASIL = data.getString("hasil");
            } else {
                this.duplikasi = false;
                this.CEK_ID_ADMIN_VAL = null;
                this.CEK_NO_PENDAFTARAN_VAL = null;
                this.CEK_TGL_VALIDASI = null;
                this.CEK_CATATAN_TAMBAHAN = null;
                this.CEK_HASIL = null;
                
                PreparedStatement perintah = Koneksidb.prepareStatement(sqlsimpan);
                perintah.setString(1, no_validasi);
                perintah.setString(2, id_admin);
                perintah.setString(3, no_pendaftaran);
                perintah.setString(4, tgl_validasi);
                perintah.setString(5, catatan_tambahan);
                perintah.setString(6, hasil);
                perintah.executeUpdate();
                JOptionPane.showMessageDialog(null, "Data Validasi berhasil disimpan");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
    
    public void ubahValidasi(String no_validasi, String id_admin, String no_pendaftaran, String tgl_validasi, String catatan_tambahan, String hasil){
        try {
            String sqlubah="UPDATE validasi SET id_admin = ?, no_pendaftaran = ?, tgl_validasi = ?, catatan_tambahan = ?, hasil = ? WHERE no_validasi = ?";
            PreparedStatement perintah = Koneksidb.prepareStatement(sqlubah);
            perintah.setString(1, id_admin);
            perintah.setString(2, no_pendaftaran);
            perintah.setString(3, tgl_validasi);
            perintah.setString(4, catatan_tambahan);
            perintah.setString(5, hasil);
            perintah.setString(6, no_validasi); 
            perintah.executeUpdate();
            JOptionPane.showMessageDialog(null, "Data Validasi berhasil diubah");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
    
    public void hapusValidasi(String no_validasi){
        try {
            String sqlhapus="DELETE FROM validasi WHERE no_validasi = ?";
            PreparedStatement perintah = Koneksidb.prepareStatement(sqlhapus);
            perintah.setString(1, no_validasi);
            perintah.executeUpdate();
            JOptionPane.showMessageDialog(null, "Data Validasi berhasil dihapus");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
    
    public void tampilDataValidasi(JTable komponentabel, String SQL){
        try {
            PreparedStatement perintah = Koneksidb.prepareStatement(SQL);
            ResultSet data = perintah.executeQuery();
            ResultSetMetaData meta = data.getMetaData();
            int jumlahkolom = meta.getColumnCount();
            DefaultTableModel modeltabel = new DefaultTableModel();
            
            modeltabel.addColumn("No Validasi");
            modeltabel.addColumn("ID Admin");
            modeltabel.addColumn("No Pendaftaran");
            modeltabel.addColumn("Tgl Validasi");
            modeltabel.addColumn("Catatan Tambahan");
            modeltabel.addColumn("Hasil");
            
            while(data.next()){
                Object[] row = new Object[jumlahkolom];
                for(int i=1; i<=jumlahkolom; i++){
                    row[i-1]=data.getObject(i);
                }
                modeltabel.addRow(row);
            }
            komponentabel.setModel(modeltabel);
        } catch (Exception e) {
        }
    }
}
    
