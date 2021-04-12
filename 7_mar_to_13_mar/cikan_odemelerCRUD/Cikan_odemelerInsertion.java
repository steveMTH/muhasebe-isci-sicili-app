package cikan_odemelerCRUD;

import entity.Cikan_odemeler;
import entity.Cikan_odemelerBuilder;

import kasaCRUD.KasaSelection;

import postgreSQL.ConnectionUtil;

import type.Name;

import java.math.BigInteger;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class Cikan_odemelerInsertion {
    private Connection connection = null;
    private PreparedStatement pst = null;
    ConnectionUtil connectionUtil = null;

    public String insert(Cikan_odemeler odeme) {
        try {
            this.setPst(this.getConnection()
                            .prepareStatement("insert into cikan_odemeler values" +
                   "(default," + odeme.getKasa().getKasa_id() + "," +
                    odeme.getKimden() + "," + odeme.getKime() + ",'" + odeme.getOdeme_tarihi() + "',"
                    + odeme.getMiktar() + ")"));

            this.getPst().executeUpdate();

            //control
            System.out.println("Successfull insertion");

            return "Success";
        } catch (SQLException ex) {
            //control
            System.out.println(ex.getMessage());
            System.err.println("Wrong insertion ......");

            return "Error";
        }
    }

    public Connection getConnection() {
        this.connection = ConnectionUtil.postgreSQLConnection();

        return connection;
    }

    public PreparedStatement getPst() {
        return pst;
    }

    public void setPst(PreparedStatement pst) {
        this.pst = pst;
    }

    public ConnectionUtil getConnectionUtil() {
        this.connectionUtil = new ConnectionUtil();

        return connectionUtil;
    }

    public static void main(String[] args) {
        Cikan_odemelerInsertion odeme = new Cikan_odemelerInsertion();

        Cikan_odemelerBuilder cikan_odeme = new Cikan_odemelerBuilder();

        KasaSelection kasa = new KasaSelection();
        cikan_odeme.SetKasa(kasa.Find(1));

        Name kimden = new Name();
        kimden.setFirst_name("amin");
        kimden.setMiddle_name("taiseer");
        kimden.setLast_name("alhammouri");
        cikan_odeme.SetKimden(kimden.toString());

        Name kime = new Name();
        kime.setFirst_name("sait");
        kime.setMiddle_name("ahet");
        kime.setLast_name("aluan");
        cikan_odeme.SetKime(kime.toString());
        cikan_odeme.SetOdeme_tarihi(Date.valueOf("2020-02-02"));

        cikan_odeme.SetMiktar(BigInteger.valueOf(1000));

        odeme.insert(cikan_odeme.build());
    }
}
