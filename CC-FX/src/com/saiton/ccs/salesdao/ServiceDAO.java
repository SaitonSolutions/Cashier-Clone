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

    public String generateID() {

        Integer id = null;
        String cid = null;
        String final_id = null;
        if (star.con == null) {
            log.error("Databse connection failiure.");
            return null;
        } else {
            try {

                Statement st = star.con.createStatement();
                Statement ste = star.con.createStatement();
                ResultSet rs = st.executeQuery("SELECT MAX(id) as ID FROM item");

                while (rs.next()) {
                    id = rs.getInt("id");
                }
                ResultSet rss = ste.executeQuery(
                        "SELECT item_id FROM item WHERE id= " + id + "");

                while (rss.next()) {
                    cid = rss.getString("item_id");
                }

                if (id != 0) {
                    String original = cid.split("M")[1];
                    int i = Integer.parseInt(original) + 1;

                    if (i < 10) {
                        final_id = "ITM000" + i;
                    } else if (i >= 10 && i < 100) {
                        final_id = "ITM00" + i;
                    } else if (i >= 100 && i < 1000) {
                        final_id = "ITM0" + i;
                    } else if (i >= 1000 && i < 10000) {
                        final_id = "ITM" + i;
                    }
                    return final_id;

                } else {
                    return "ITM0001";
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

    public String generateIDOOnDemand(int no) {

        Integer id = null;
        String cid = null;
        String final_id = null;
        if (star.con == null) {
            log.error("Databse connection failiure.");
            return null;
        } else {
            try {

                Statement st = star.con.createStatement();
                Statement ste = star.con.createStatement();
                ResultSet rs = st.executeQuery("SELECT MAX(id) as ID FROM item");

                while (rs.next()) {
                    id = rs.getInt("id");
                }
                ResultSet rss = ste.executeQuery(
                        "SELECT item_id FROM item WHERE id= " + id + "");

                while (rss.next()) {
                    cid = rss.getString("item_id");
                }

                if (id != 0) {
                    String original = cid.split("M")[1];
                    int i = Integer.parseInt(original) + no;

                    if (i < 10) {
                        final_id = "ITM000" + i;
                    } else if (i >= 10 && i < 100) {
                        final_id = "ITM00" + i;
                    } else if (i >= 100 && i < 1000) {
                        final_id = "ITM0" + i;
                    } else if (i >= 1000 && i < 10000) {
                        final_id = "ITM" + i;
                    }
                    return final_id;

                } else {
                    int i = no;
                    if (i < 10) {
                        final_id = "ITM000" + i;
                    } else if (i >= 10 && i < 100) {
                        final_id = "ITM00" + i;
                    } else if (i >= 100 && i < 1000) {
                        final_id = "ITM0" + i;
                    } else if (i >= 1000 && i < 10000) {
                        final_id = "ITM" + i;
                    }
                    return final_id;
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

    
    public boolean checkingItemNameAvailability(String itemName) {

//        String encodedItemName = ESAPI.encoder().encodeForSQL(ORACLE_CODEC, itemName);
        String encodedItemName = itemName;
        boolean available = false;

        if (star.con == null) {

            log.error("Exception tag --> " + "Databse connection failiure. ");

        } else {
            try {

                String query = "SELECT * FROM item  where item_name= ?";

                PreparedStatement pstmt = star.con.prepareStatement(query);
                pstmt.setString(1, encodedItemName);

                ResultSet r = pstmt.executeQuery();

                while (r.next()) {
                    available = true;
                }

            } catch (NullPointerException | SQLException e) {
                if (e instanceof NullPointerException) {
                    log.error("Exception tag --> " + "Empty entry passed");

                } else if (e instanceof SQLException) {
                    log.error("Exception tag --> " + "Invalid sql statement");
                }
                return false;
            } catch (Exception e) {
                log.error("Exception tag --> " + "Error");
                return false;
            }
        }
        return available;
    }

   }
