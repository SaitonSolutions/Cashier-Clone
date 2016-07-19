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

public class InvoiceDAO {

    public static Starter star;//db connection
    Codec ORACLE_CODEC = new OracleCodec();
    private final Logger log = Logger.getLogger(this.getClass());

    

    public String generateId() {

        Integer id = null;
        String cid = null;
        String final_id = null;
        if (star.con == null) {
            log.error("Database connection failiure.");
            return null;
        } else {
            try {

                Statement st = star.con.createStatement();
                Statement ste = star.con.createStatement();
                ResultSet rs = st.executeQuery("SELECT MAX(id) as ID FROM invoice ");

                while (rs.next()) {
                    id = rs.getInt("id");
                }
                ResultSet rss = ste.executeQuery(
                        "SELECT inv_no FROM invoice WHERE id= " + id + "");

                while (rss.next()) {
                    cid = rss.getString("inv_no");
                }

                if (id != 0) {
                    String original = cid.split("T")[1];
                    int i = Integer.parseInt(original) + 1;

                    if (i < 10) {
                        final_id = "INT000" + i;
                    } else if (i >= 10 && i < 100) {
                        final_id = "INT00" + i;
                    } else if (i >= 100 && i < 1000) {
                        final_id = "INT0" + i;
                    } else if (i >= 1000 && i < 10000) {
                        final_id = "INT" + i;
                    }
                    return final_id;

                } else {
                    return "INT0001";
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
    
    public ArrayList loadCustomerType() {

        String customerType = null;
        ArrayList cutomerTypeList = new ArrayList();

        if (star.con == null) {
            log.error("Database connection failiure.");
        } else {
            try {
                Statement stt = star.con.createStatement();
                ResultSet r = stt.
                        executeQuery("SELECT * FROM customer_type");
                while (r.next()) {
                    customerType = r.getString("customer_type");

                    cutomerTypeList.add(customerType);
                }

            } catch (ArrayIndexOutOfBoundsException | SQLException |
                    NullPointerException e) {
                if (e instanceof ArrayIndexOutOfBoundsException) {
                    log.error("Exception tag --> "
                            + "Invalid entry location for list");
                } else if (e instanceof SQLException) {
                    log.error("Exception tag --> " + "Invalid sql statement "
                            + e.getMessage());
                } else if (e instanceof NullPointerException) {
                    log.error("Exception tag --> " + "Empty entry for list");
                }
                return null;
            } catch (Exception e) {
                log.error("Exception tag --> " + "Error");
                return null;
            }
        }
        return cutomerTypeList;
    }

    public Boolean insertInvoice(String InvoiceNo,
            String IsTaxInvoice,
            String Date,
            String PONumber,
            String PODate,
            String CusID,
            String SalesExecutive,
            String PaymentTerm,
            String WarrentyPeriod,
            String Warrenty_Month_Year,
            Double Total,
            Double NBT,
            Double VAT,
            Double NetAmt,
            String AmountInWords,
            String UserID,
            Double Totaldiscount,
            Double nbtRate,
            Double vatRate
    ) {

        String EncodedInvoiceNo = ESAPI.encoder().encodeForSQL(ORACLE_CODEC,
                InvoiceNo);
        String EncodedIsTaxInvoice = ESAPI.encoder().encodeForSQL(ORACLE_CODEC,
                IsTaxInvoice);
        String EncodedDate = ESAPI.encoder().encodeForSQL(ORACLE_CODEC, Date);
        String EncodedPONumber = ESAPI.encoder().encodeForSQL(ORACLE_CODEC,
                PONumber);
        String EncodedPODate = ESAPI.encoder().
                encodeForSQL(ORACLE_CODEC, PODate);
        String EncodedCusID = ESAPI.encoder().encodeForSQL(ORACLE_CODEC, CusID);
        String EncodedSalesExecutive = ESAPI.encoder().
                encodeForSQL(ORACLE_CODEC, SalesExecutive);
        String EncodedPaymentTerm = ESAPI.encoder().encodeForSQL(ORACLE_CODEC,
                PaymentTerm);
        String EncodedWarrentyPeriod = ESAPI.encoder().
                encodeForSQL(ORACLE_CODEC, WarrentyPeriod);
        String EncodedWarrenty_Month_Year = ESAPI.encoder().encodeForSQL(
                ORACLE_CODEC, Warrenty_Month_Year);
        String EncodedAmountInWords = ESAPI.encoder().encodeForSQL(ORACLE_CODEC,
                AmountInWords);
        String EncodedUserID = ESAPI.encoder().
                encodeForSQL(ORACLE_CODEC, UserID);

        if (star.con == null) {
            log.error("Database Connection failure");
            return false;
        } else {

            try {
                PreparedStatement ps = star.con.prepareStatement(
                        "INSERT INTO `invoice` (`inv_no`, `is_tax_inv`, `date`, "
                        + "`po_no`, `po_date`, `cus_id`, `salse_executive`,"
                        + " `payment_term`, `warrenty_period`, "
                        + "`warrenty_month_year`, `total`, `nbt`, `vat`, "
                        + "`net_amount`, `net_amount_word`, `user_id`, "
                        + "`total_discount`, `nbt_rate`, `vat_rate`) VALUES "
                        + "(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,"
                        + " ?, ?)");

                ps.setString(1, EncodedInvoiceNo);
                ps.setString(2, EncodedIsTaxInvoice);
                ps.setString(3, EncodedDate);
                ps.setString(4, EncodedPONumber);
                ps.setString(5, EncodedPODate);
                ps.setString(6, EncodedCusID);
                ps.setString(7, EncodedSalesExecutive);
                ps.setString(8, EncodedPaymentTerm);
                ps.setString(9, EncodedWarrentyPeriod);
                ps.setString(10, EncodedWarrenty_Month_Year);
                ps.setDouble(11, Total);
                ps.setDouble(12, NBT);
                ps.setDouble(13, VAT);
                ps.setDouble(14, NetAmt);
                ps.setString(15, AmountInWords);
                ps.setString(16, UserID);
                ps.setDouble(17, Totaldiscount);
                ps.setDouble(18, nbtRate);
                ps.setDouble(19, vatRate);

                int val = ps.executeUpdate();
                if (val == 1) {
                    return true;
                } else {
                    return false;
                }

            } catch (SQLException ex) {
                if (ex instanceof SQLException) {
                    log.error("Exception Tag -->" + "Invalid Sql Statement");
                    ex.printStackTrace();
                }
                return false;
            } catch (Exception e) {
                log.error("Exception Tag -->" + "Error");
                return false;
            }
        }

    }

    public ArrayList<ArrayList<String>> searchItemDetails(String search) {

        String encodedSearch = ESAPI.encoder().
                encodeForSQL(ORACLE_CODEC, search);

        String itemId = null;
        String batchNo = null;
        String itemName = null;
        String unit = null;
//        String cus_title = null;

        ArrayList<ArrayList<String>> Mainlist
                = new ArrayList<ArrayList<String>>();

        if (star.con == null) {

            log.info(" Exception tag --> " + "Databse connection failiure. ");
            return null;

        } else {
            try {

                String query = "SELECT * FROM item "
                        + "join item_sub on item.item_id = item_sub.item_id "
                        + "WHERE (item.item_id LIKE ? OR item.item_name LIKE?)";

                PreparedStatement pstmt = star.con.prepareStatement(query);
                pstmt.setString(1, encodedSearch + "%");
                pstmt.setString(2, encodedSearch + "%");
                ResultSet r = pstmt.executeQuery();

                while (r.next()) {

                    ArrayList<String> list = new ArrayList<String>();

                    itemId = r.getString("item_id");
                    batchNo = r.getString("batch_no");
                    itemName = r.getString("item_name");
                    unit = r.getString("price");

                    list.add(itemId);
                    list.add(batchNo);
                    list.add(itemName);
                    list.add(unit);

                    Mainlist.add(list);

                }

            } catch (ArrayIndexOutOfBoundsException | SQLException |
                    NullPointerException e) {

                if (e instanceof ArrayIndexOutOfBoundsException) {

                    log.error("Exception tag --> "
                            + "Invalid entry location for list");

                } else if (e instanceof SQLException) {

                    log.error("Exception tag --> " + "Invalid sql statement");

                } else if (e instanceof NullPointerException) {

                    log.error("Exception tag --> " + "Empty entry for list");

                }
                return null;
            } catch (Exception e) {

                log.error("Exception tag --> " + "Error");

                return null;
            }
        }
        return Mainlist;
    }

//    public Boolean insertInvoice(
//            String invoiceNo,
//            String isTaxInvoice,
//            String date,
//            String poNo,
//            String poDate,
//            String cusId,
//            String creditAllowed,
//            String salesExecutive,
//            String paymentTerm,
//            String warrantyNo,
//            double total,
//            double nbt,
//            double vat,
//            double netAmount,
//            String amountInWords,
//            String userId) {
//
//       
//
//        String encodedInvoiceNo = ESAPI.encoder().encodeForSQL(ORACLE_CODEC,
//                invoiceNo);
//        String encodedIsTaxInvoice = ESAPI.encoder().encodeForSQL(ORACLE_CODEC,
//                isTaxInvoice);
//        String encodedDate = ESAPI.encoder().encodeForSQL(ORACLE_CODEC,
//                date);
//        String encodedPONO = ESAPI.encoder().encodeForSQL(ORACLE_CODEC,
//                poNo);
//        String encodedUserId = ESAPI.encoder().
//                encodeForSQL(ORACLE_CODEC, userId);
//
//        String encodedPODate = ESAPI.encoder().encodeForSQL(ORACLE_CODEC,
//                poDate);
//        String encodedCusId = ESAPI.encoder().encodeForSQL(ORACLE_CODEC,
//                cusId);
//        String encodedCreditAllowed = ESAPI.encoder().encodeForSQL(ORACLE_CODEC,
//                creditAllowed);
//        String encodedSalesExecutive = ESAPI.encoder().
//                encodeForSQL(ORACLE_CODEC,
//                        salesExecutive);
//
//        String encodedWarrantyNo = ESAPI.encoder().encodeForSQL(ORACLE_CODEC,
//                warrantyNo);
//
//       
//        String encodedAmountInWords = ESAPI.encoder().encodeForSQL(ORACLE_CODEC,
//                amountInWords);
//        String encodedPaymentTerm = ESAPI.encoder().
//                encodeForSQL(ORACLE_CODEC, paymentTerm);
//
//        if (star.con == null) {
//            log.error("Databse connection failiure.");
//            return false;
//        } else {
//            try {
//
//                PreparedStatement ps = star.con.prepareStatement(
//                        "INSERT INTO `invoice` ("
//                        + " `inv_no`, "
//                        + "`is_tax_inv`, "
//                        + "`date`,"
//                        + " `po_no`,"
//                        + " `po_date`, "
//                        + "`cus_id`, "
//                        + "`credit_allow`, "
//                        + "`salse_executive`, "
//                        + "`payment_term`, "
//                        + "`warrenty_no`,"
//                        + " `total`,"
//                        + " `nbt`, "
//                        + "`vat`, "
//                        + "`net_amount`,"
//                        + " `net_amount_word`,"
//                                + " `user_id`"
//                        + ") VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
//                ;
//
//                ps.setString(1, encodedInvoiceNo);
//                ps.setString(2, encodedIsTaxInvoice);
//                ps.setString(3, encodedDate);
//                ps.setString(4, encodedPONO);
//
//                ps.setString(5, encodedPODate);
//                ps.setString(6, encodedCusId);
//                ps.setString(7, encodedCreditAllowed);
//                ps.setString(8, encodedSalesExecutive);
//                ps.setString(9, encodedPaymentTerm);
//
//                ps.setString(10, encodedWarrantyNo);
//                 ps.setDouble(11, total);
//                ps.setDouble(12, nbt);
//                ps.setDouble(13, vat);
//                ps.setDouble(14, netAmount);
//                ps.setString(15, encodedAmountInWords);
//
//                ps.setString(16, encodedUserId);
//
//                int val = ps.executeUpdate();
//                if (val == 1) {
//                    return true;
//                } else {
//                    return false;
//                }
//
//            } catch (SQLException e) {
//
//                if (e instanceof SQLException) {
//                    log.error("Exception tag --> " + "Invalid sql statement "
//                            + e.getMessage());
//                }
//                return false;
//
//            } catch (Exception e) {
//                log.error("Exception tag --> " + "Error");
//                return false;
//            }
//        }
//    }
    public Boolean insertInvoiceItems(
            String invoiceNo,
            String itemCode,
            String description,
            double qty,
            double price,
            double netPrice,
            double discount,
            double discountRate,
            String batchNo
    ) {
        String encodedInvoiceNo = ESAPI.encoder().encodeForSQL(ORACLE_CODEC,
                invoiceNo);
        String encodedItemCode = ESAPI.encoder().encodeForSQL(ORACLE_CODEC,
                itemCode);
//        String encodedDescription = ESAPI.encoder().encodeForSQL(ORACLE_CODEC,
//                description);
        String encodedDescription = description;
        
        String encodedBatchNo = ESAPI.encoder().encodeForSQL(ORACLE_CODEC,
                batchNo);

        if (star.con == null) {
            log.error("Databse connection failiure.");
            return false;
        } else {
            try {

                PreparedStatement ps = star.con.prepareStatement(
                        "INSERT INTO `ccs_dl`.`invoice_item`(`inv_no`,"
                        + " `item_id`"
                        + ", `description`, `qty`, `price`, `net_price`, "
                        + "`discount`,"
                        + " `discount_rate`,`batch_no`) VALUES "
                        + "(?, ?, ?,?, ?, ?, ?, ?,?)");

                ps.setString(1, encodedInvoiceNo);
                ps.setString(2, encodedItemCode);
                ps.setString(3, encodedDescription);
                ps.setDouble(4, qty);
                ps.setDouble(5, price);
                ps.setDouble(6, netPrice);
                ps.setDouble(7, discount);
                ps.setDouble(8, discountRate);
                ps.setString(9, batchNo);

                int val = ps.executeUpdate();

                if (val == 1) {
                    return true;
                } else {
                    return false;
                }

            } catch (SQLException e) {

                if (e instanceof SQLException) {
                    log.error("Exception tag --> " + "Invalid sql statement "
                            + e.getMessage());
                }
                return false;

            } catch (Exception e) {
                log.error("Exception tag --> " + "Error");
                return false;
            }
        }
    }

    /* public ArrayList<String> loadCustomerInfo(String CusID) {
     String EncodedCusID = ESAPI.encoder().encodeForSQL(ORACLE_CODEC, CusID);

     ArrayList newList = new ArrayList();
     String cusId = null;
     String cusIdType = null;

     if (star.con == null) {
     log.error("Database connection failed");
     } else {

     try {
     PreparedStatement ps = star.con.prepareStatement("select * from customer where cus_id = ?");
     ps.setString(1, EncodedCusID);
     ResultSet r = ps.executeQuery();

     while (r.next()) {
     cusIdType = r.getString("cus_nic_passport_id_Type");
     newList.add(cusIdType);
     }

     } catch (SQLException ex) {
     java.util.logging.Logger.getLogger(InvoiceDAO.class.getName()).log(Level.SEVERE, null, ex);
     }

     }
     return newList;
     }*/
    public ArrayList<String> loadInvoiceInfo(String InvoiceID) {
        String EncodedInvoiceID = ESAPI.encoder().encodeForSQL(ORACLE_CODEC,
                InvoiceID);

        ArrayList newList = new ArrayList();
        String InvoiceNo = null;
        String IsTaxInviced = null;
        String Date = null;
        String PONo = null;
        String PODate = null;
        String total = null;
        String nbt = null;
        String vat = null;
        String netAmount = null;
        String discount = null;
        String amountInWords = null;
        if (star.con == null) {
            log.error("Database Connection Failure");
        } else {
            PreparedStatement ps;
            try {
                ps = star.con.prepareStatement(
                        "SELECT * FROM invoice WHERE inv_no LIKE ?");
                ps.setString(1, EncodedInvoiceID);
                ResultSet r = ps.executeQuery();

                while (r.next()) {
//     InvoiceNo = r.getString("inv_no");
                    IsTaxInviced = r.getString("is_tax_inv");
                    Date = r.getString("date");
                    PONo = r.getString("po_no");
                    PODate = r.getString("po_date");
                    total = r.getString("total");
                    nbt = r.getString("nbt");
                    vat = r.getString("vat");
                    netAmount = r.getString("net_amount");
                    discount = r.getString("total_discount");
                    amountInWords = r.getString("net_amount_word");

                    newList.add(IsTaxInviced);
                    newList.add(Date);
                    newList.add(PONo);
                    newList.add(PODate);
                    newList.add(total);
                    newList.add(nbt);
                    newList.add(vat);
                    newList.add(netAmount);
                    newList.add(discount);
                    newList.add(amountInWords);
                }

            } catch (SQLException ex) {
                java.util.logging.Logger.getLogger(InvoiceDAO.class.getName()).
                        log(Level.SEVERE, null, ex);
            }

        }
        return newList;
    }

    public ArrayList<String> loadInvoiceCusInfo(String InvoiceID) {
        String EncodedInvoiceID = ESAPI.encoder().encodeForSQL(ORACLE_CODEC,
                InvoiceID);

        ArrayList newList = new ArrayList();
        String cusName = null;
        String cusAddress = null;
        String salesExecutive = null;
        String warrentyPeriod = null;
        String warrenty_month_year = null;
        String paymentTerms = null;

        if (star.con == null) {
            log.error("Database Connection Failure");
        } else {
            PreparedStatement ps;
            try {
                ps = star.con.prepareStatement(
                        "SELECT * FROM invoice join customer on invoice.cus_id=customer.cus_id WHERE inv_no LIKE ?");
                ps.setString(1, EncodedInvoiceID);
                ResultSet r = ps.executeQuery();

                while (r.next()) {

                    cusName = r.getString("cus_name");
                    cusAddress = r.getString("cus_address");
                    salesExecutive = r.getString("salse_executive");
                    warrentyPeriod = r.getString("warrenty_period");
                    warrenty_month_year = r.getString("warrenty_month_year");
                    paymentTerms = r.getString("payment_term");

                    newList.add(cusName);
                    newList.add(cusAddress);
                    newList.add(salesExecutive);
                    newList.add(warrentyPeriod);
                    newList.add(warrenty_month_year);
                    newList.add(paymentTerms);
                }

            } catch (SQLException ex) {
                java.util.logging.Logger.getLogger(InvoiceDAO.class.getName()).
                        log(Level.SEVERE, null, ex);
            }

        }
        return newList;
    }

    public ArrayList<ArrayList<String>> searchInvoiceDetails(String search) {

        String encodedSearch = ESAPI.encoder().
                encodeForSQL(ORACLE_CODEC, search);

        String invoiceNo = null;
        String isTaxInvoiced = null;
        String date = null;
        String PONo = null;
        String PODate = null;

        ArrayList<ArrayList<String>> Mainlist
                = new ArrayList<ArrayList<String>>();

        if (star.con == null) {

            log.info(" Exception tag --> " + "Databse connection failiure. ");
            return null;

        } else {
            try {

                String query = "SELECT * FROM invoice WHERE inv_no LIKE ?";

                PreparedStatement pstmt = star.con.prepareStatement(query);
                pstmt.setString(1, encodedSearch + "%");

                ResultSet r = pstmt.executeQuery();

                while (r.next()) {

                    ArrayList<String> list = new ArrayList<String>();

                    invoiceNo = r.getString("inv_no");
                    isTaxInvoiced = r.getString("is_tax_inv");
                    date = r.getString("date");
                    PONo = r.getString("po_no");
                    PODate = r.getString("po_date");

                    list.add(invoiceNo);
                    list.add(isTaxInvoiced);
                    list.add(date);
                    list.add(PONo);
                    list.add(PODate);

                    Mainlist.add(list);

                }

            } catch (ArrayIndexOutOfBoundsException | SQLException |
                    NullPointerException e) {

                if (e instanceof ArrayIndexOutOfBoundsException) {

                    log.error("Exception tag --> "
                            + "Invalid entry location for list");

                } else if (e instanceof SQLException) {

                    log.error("Exception tag --> " + "Invalid sql statement");

                } else if (e instanceof NullPointerException) {

                    log.error("Exception tag --> " + "Empty entry for list");

                }
                return null;
            } catch (Exception e) {

                log.error("Exception tag --> " + "Error");

                return null;
            }
        }
        return Mainlist;
    }

    public ArrayList<ArrayList<String>> searchInvoiceItems(String search) {
        String encodedSearch = ESAPI.encoder().
                encodeForSQL(ORACLE_CODEC, search);

        String itemCode = null;
        String description = null;
        String batchNo = null;
        String quantity = null;
        String discount = null;
        String discountAmt = null;
        String unitPrice = null;
        String value = null;

        ArrayList<ArrayList<String>> MainList
                = new ArrayList<ArrayList<String>>();

        if (star.con == null) {
            log.error("Database connection failure");
            return null;
        } else {
            try {

                String query = "select * from invoice_item where inv_no = ?";

                PreparedStatement ps = star.con.prepareStatement(query);

                ps.setString(1, encodedSearch);

                ResultSet r = ps.executeQuery();

                while (r.next()) {
                    ArrayList<String> list = new ArrayList<String>();

                    itemCode = r.getString("item_id");
                    description = r.getString("description");
                    batchNo = r.getString("batch_no");
                    quantity = r.getString("qty");
                    discount = r.getString("discount_rate");
                    discountAmt = r.getString("discount");
                    unitPrice = r.getString("price");
                    value = r.getString("net_price");

                    list.add(itemCode);
                    list.add(description);
                    list.add(batchNo);
                    list.add(quantity);
                    list.add(discount);
                    list.add(discountAmt);
                    list.add(unitPrice);
                    list.add(value);

                    MainList.add(list);

                }

            } catch (ArrayIndexOutOfBoundsException | SQLException |
                    NullPointerException e) {

                if (e instanceof ArrayIndexOutOfBoundsException) {

                    log.error("Exception tag --> "
                            + "Invalid entry location for list");

                } else if (e instanceof SQLException) {

                    log.error("Exception tag --> " + "Invalid sql statement "
                            + e.getMessage());

                } else if (e instanceof NullPointerException) {

                    log.error("Exception tag --> " + "Empty entry for list");

                }
                return null;
            } catch (Exception e) {

                log.error("Exception tag --> " + "Error");

                return null;
            }
        }

        return MainList;
    }

    public ArrayList<String> loadingCustomerInfo(String customerId) {
        String encodedCusId = ESAPI.encoder().encodeForSQL(ORACLE_CODEC,
                customerId);
        ArrayList list = new ArrayList();
        String cusId = null;
        String cusName = null;
        String cusAddress = null;
        String cusTitle = null;

        if (star.con == null) {
            log.error("Databse connection failiure.");
        } else {

            try {
                Statement stt = star.con.createStatement();
                ResultSet r = stt.executeQuery(
                        "SELECT * FROM customer WHERE cus_id ='" + encodedCusId
                        + "'");
                while (r.next()) {

                    cusAddress = r.getString("cus_address");
                    cusTitle = r.getString("cus_title");

                    list.add(cusAddress);
                    list.add(cusTitle);

                }
            } catch (NullPointerException | SQLException e) {
                if (e instanceof NullPointerException) {
                    log.error("Exception tag --> " + "Empty entry passed");
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
        return list;
    }

    public ArrayList<String> loadingItemInfo(String ItemID) {
        String EncodedItemID = ESAPI.encoder().
                encodeForSQL(ORACLE_CODEC, ItemID);
        ArrayList list = new ArrayList();
        String itemId = null;
        String batchNo = null;
        String itemName = null;
        String unit = null;
        //String description = null;

        if (star.con == null) {
            log.error("Database Connection Failure");
        } else {
            try {

                Statement stt = star.con.createStatement();
                ResultSet r = stt.executeQuery("SELECT * FROM item "
                        + "join item_sub on item.item_id = item_sub.item_id WHERE"
                        + " item.item_id = '" + EncodedItemID + "'");

                while (r.next()) {
                    itemName = r.getString("item_name");
                    unit = r.getString("price");
                    batchNo = r.getString("batch_no");

                    list.add(itemName);
                    list.add(unit);
                    list.add(batchNo);
                }

            } catch (NullPointerException | SQLException e) {
                if (e instanceof NullPointerException) {
                    log.error("Exception tag --> " + "Empty entry passed");
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
        return list;
    }

    public boolean updateItemTableQty(String itemCode,
            String batchNo, double qtyy) {
        ArrayList<String> itemList = new ArrayList<>();

        String encodedItemCode = ESAPI.encoder().encodeForSQL(ORACLE_CODEC,
                itemCode);
        String encodedBatchNo = ESAPI.encoder().encodeForSQL(ORACLE_CODEC,
                batchNo);

        if (star.con == null) {
            log.info(" Exception tag --> " + "Databse connection failiure. ");
            return false;
        } else {

            try {
                String sql = "update item i inner join item_sub it on "
                        + "(i.item_id = it.item_id)"
                        + "set i.qty = i.qty - ?,it.qty = it.qty - ?"
                        + "where i.item_id = ? AND "
                        + "it.batch_no = ?";
                PreparedStatement ps = star.con.prepareStatement(sql);

                ps.setDouble(1, qtyy);
                ps.setDouble(2, qtyy);
                ps.setString(3, encodedItemCode);
                ps.setString(4, encodedBatchNo);

                int val = ps.executeUpdate();

                if (val == 1) {
                    return true;
                } else {
                    return false;
                }

            } catch (SQLException e) {
                log.error("Exception tag --> " + "Invalid sql statement " + e.
                        getMessage());
                return false;

            } catch (Exception e) {
                log.error("Exception tag --> " + "Error");
                return false;
            }

        }

    }

    public boolean cancelInvoice(String invoiceNo, int status) {
        String encodedInvoiceNo = ESAPI.encoder().encodeForSQL(ORACLE_CODEC,
                invoiceNo);

        if (star.con == null) {
            log.info(" Exception tag --> " + "Databse connection failiure. ");
            return false;
        } else {
            try {
                String sql = "UPDATE `invoice` SET `status`=? WHERE "
                        + "`inv_no`=?";

                PreparedStatement ps = star.con.prepareStatement(sql);
                ps.setInt(1, status);
                ps.setString(2, encodedInvoiceNo);

                ps.executeUpdate();

            } catch (SQLException e) {

                if (e instanceof SQLException) {
                    log.error("Exception tag --> " + "Invalid sql statement "
                            + e.getMessage());
                }
                return false;
            } catch (Exception e) {
                log.error("Exception tag --> " + "Error");
                return false;
            }
        }
        return true;
    }

    public boolean updateItem_InvoiceDeleted(String itemCode,
            String batchNo, double qtyy) {
        ArrayList<String> itemList = new ArrayList<>();
        String encodedItemCode = ESAPI.encoder().encodeForSQL(ORACLE_CODEC,
                itemCode);
        String encodedBatchNo = ESAPI.encoder().encodeForSQL(ORACLE_CODEC,
                batchNo);

        if (star.con == null) {
            log.info(" Exception tag --> " + "Databse connection failiure. ");
            return false;
        } else {

            try {
                String sql = "update item i inner join item_sub it on "
                        + "(i.item_id = it.item_id)"
                        + "set i.qty = i.qty + ?,it.qty = it.qty + ?"
                        + "where i.item_id = ? AND "
                        + "it.batch_no = ?";
                PreparedStatement ps = star.con.prepareStatement(sql);

                ps.setDouble(1, qtyy);
                ps.setDouble(2, qtyy);
                ps.setString(3, encodedItemCode);
                ps.setString(4, encodedBatchNo);

                int val = ps.executeUpdate();

                if (val == 1) {
                    return true;
                } else {
                    return false;
                }

            } catch (SQLException e) {
                log.error("Exception tag --> " + "Invalid sql statement " + e.
                        getMessage());
                return false;

            } catch (Exception e) {
                log.error("Exception tag --> " + "Error");
                return false;
            }

        }

    }

    public boolean checkInvoiceAvailability(String invId) {
        String encodedInvId = ESAPI.encoder().encodeForSQL(ORACLE_CODEC,
                invId);

        boolean available = false;

        if (star.con == null) {
            log.error("Databse connection failiure.");
            return false;
        } else {

            try {
                String query
                        = "SELECT * FROM  invoice where inv_no= ? ";
                PreparedStatement pstmt = star.con.prepareStatement(query);
                pstmt.setString(1, encodedInvId);

                ResultSet r = pstmt.executeQuery();

                while (r.next()) {
                    available = true;
                }

            } catch (NullPointerException | SQLException e) {

                if (e instanceof NullPointerException) {
                    log.error("Exception tag --> " + "Empty entry passed");
                } else if (e instanceof SQLException) {
                    log.error("Exception tag --> " + "Invalid sql statement "
                            + e.getMessage());
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
