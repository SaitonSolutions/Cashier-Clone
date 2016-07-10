package com.saiton.ccs.salesdao;

import com.saiton.ccs.database.Starter;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import org.apache.log4j.Logger;
import org.owasp.esapi.ESAPI;
import org.owasp.esapi.codecs.Codec;
import org.owasp.esapi.codecs.OracleCodec;

public class ServiceDAO {

    public static Starter star;//db connection
    Codec ORACLE_CODEC = new OracleCodec();
    private final Logger log = Logger.getLogger(this.getClass());

    public String generateServiceId() {

        Integer newId = null;
        String newCid = null;
        Integer resetId = null;
        String resetCid = null;
        String final_id = null;
        if (star.con == null) {
            log.error("Databse connection failiure.");
            return null;
        } else {
            try {

                Statement st = star.con.createStatement();
                Statement ste = star.con.createStatement();
                //New Id
                ResultSet rs = st.executeQuery(
                        "SELECT MAX(id) as ID FROM invoice WHERE is_tax_inv = '0'");

                while (rs.next()) {
                    newId = rs.getInt("id");
                }
                ResultSet rss = ste.executeQuery(
                        "SELECT inv_no FROM invoice WHERE id= " + newId
                        + "");

                while (rss.next()) {
                    newCid = rss.getString("inv_no");
                }

                //Reset Id
                String query = "select max(id) as ID "
                        + "from invoice_id_reset where is_tax_invoice = '0'";

                Statement st1 = star.con.createStatement();
                ResultSet rs1 = st1.executeQuery(query);

                while (rs1.next()) {
                    resetId = rs1.getInt("id");
                }
                String query2 = "select reset_invoice_id from "
                        + "invoice_id_reset where id = " + resetId;

                Statement st2 = star.con.createStatement();
                ResultSet rs2 = st2.executeQuery(query2);
                while (rs2.next()) {
                    resetCid = rs2.getString("reset_invoice_id");
                }
                int convertedResetCid = 0;
                if (resetCid != null) {
                    String original = resetCid.split("V")[1];
                    convertedResetCid = Integer.parseInt(original);
                } else {
                    resetCid = "INV0001";
                }
                //Check Conditions
                if (newId != 0) {
                    String original = newCid.split("V")[1];
                    int i = Integer.parseInt(original) + 1;
                    if (convertedResetCid <= i) {
                        if (i < 10) {
                            final_id = "INV000" + i;
                        } else if (i >= 10 && i < 100) {
                            final_id = "INV00" + i;
                        } else if (i >= 100 && i < 1000) {
                            final_id = "INV0" + i;
                        } else if (i >= 1000 && i < 10000) {
                            final_id = "INV" + i;
                        }
                        return final_id;
                    } else {
                        if (resetCid != null) {
                            original = resetCid.split("V")[1];
                            i = Integer.parseInt(original);
                            if (i < 10) {
                                final_id = "INV000" + i;
                            } else if (i >= 10 && i < 100) {
                                final_id = "INV00" + i;
                            } else if (i >= 100 && i < 1000) {
                                final_id = "INV0" + i;
                            } else if (i >= 1000 && i < 10000) {
                                final_id = "INV" + i;
                            }
                            return final_id;
                        } else {
                            return "INV0001";
                        }
                    }

                } else {
                    return "INV0001";
                }

            } catch (ArrayIndexOutOfBoundsException | NumberFormatException |
                    SQLException e) {

                if (e instanceof ArrayIndexOutOfBoundsException) {
                    log.error("Exception tag --> " + "Split character error");
                } else if (e instanceof NumberFormatException) {
                    log.error("Exception tag --> "
                            + "Invalid number found in current id");
                } else if (e instanceof SQLException) {
                    log.error("Exception tag --> " + "Invalid sql statement "
                            + e.getMessage());
                }
                return null;

            } catch (Exception e) {
                log.error("Exception tag --> " + "Error");
                return null;
            }
        }

    }

   }
