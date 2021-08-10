package com.xsusenet.ip4.Models.Purchases;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class All {


    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("user_id")
    @Expose
    private String userId;
    @SerializedName("list_id")
    @Expose
    private String listId;
    @SerializedName("list_amount")
    @Expose
    private String listAmount;
    @SerializedName("commission")
    @Expose
    private String commission;
    @SerializedName("transfer_fee")
    @Expose
    private String transferFee;
    @SerializedName("total_amount")
    @Expose
    private String totalAmount;
    @SerializedName("payment_type_id")
    @Expose
    private String paymentTypeId;
    @SerializedName("payment_status")
    @Expose
    private String paymentStatus;
    @SerializedName("transaction_id")
    @Expose
    private String transactionId;
    @SerializedName("transaction_date")
    @Expose
    private String transactionDate;
    @SerializedName("transaction_token")
    @Expose
    private String transactionToken;
    @SerializedName("paid_date")
    @Expose
    private String paidDate;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;
    @SerializedName("gdpr_compliant")
    @Expose
    private String gdprCompliant;
    @SerializedName("mailing_involved")
    @Expose
    private String mailingInvolved;
    @SerializedName("abuse_department")
    @Expose
    private String abuseDepartment;
    @SerializedName("abuse_email")
    @Expose
    private String abuseEmail;
    @SerializedName("abuse_expected")
    @Expose
    private String abuseExpected;
    @SerializedName("business_since")
    @Expose
    private String businessSince;
    @SerializedName("industries_by_company")
    @Expose
    private String industriesByCompany;
    @SerializedName("company_size")
    @Expose
    private String companySize;
    @SerializedName("stripe_plan_id")
    @Expose
    private String stripePlanId;
    @SerializedName("stripe_status")
    @Expose
    private String stripeStatus;
    @SerializedName("payments")
    @Expose
    private List<Payment> payments = null;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getListId() {
        return listId;
    }

    public void setListId(String listId) {
        this.listId = listId;
    }

    public String getListAmount() {
        return listAmount;
    }

    public void setListAmount(String listAmount) {
        this.listAmount = listAmount;
    }

    public String getCommission() {
        return commission;
    }

    public void setCommission(String commission) {
        this.commission = commission;
    }

    public String getTransferFee() {
        return transferFee;
    }

    public void setTransferFee(String transferFee) {
        this.transferFee = transferFee;
    }

    public String getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(String totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getPaymentTypeId() {
        return paymentTypeId;
    }

    public void setPaymentTypeId(String paymentTypeId) {
        this.paymentTypeId = paymentTypeId;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(String transactionDate) {
        this.transactionDate = transactionDate;
    }

    public String getTransactionToken() {
        return transactionToken;
    }

    public void setTransactionToken(String transactionToken) {
        this.transactionToken = transactionToken;
    }

    public String getPaidDate() {
        return paidDate;
    }

    public void setPaidDate(String paidDate) {
        this.paidDate = paidDate;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getGdprCompliant() {
        return gdprCompliant;
    }

    public void setGdprCompliant(String gdprCompliant) {
        this.gdprCompliant = gdprCompliant;
    }

    public String getMailingInvolved() {
        return mailingInvolved;
    }

    public void setMailingInvolved(String mailingInvolved) {
        this.mailingInvolved = mailingInvolved;
    }

    public String getAbuseDepartment() {
        return abuseDepartment;
    }

    public void setAbuseDepartment(String abuseDepartment) {
        this.abuseDepartment = abuseDepartment;
    }

    public String getAbuseEmail() {
        return abuseEmail;
    }

    public void setAbuseEmail(String abuseEmail) {
        this.abuseEmail = abuseEmail;
    }

    public String getAbuseExpected() {
        return abuseExpected;
    }

    public void setAbuseExpected(String abuseExpected) {
        this.abuseExpected = abuseExpected;
    }

    public String getBusinessSince() {
        return businessSince;
    }

    public void setBusinessSince(String businessSince) {
        this.businessSince = businessSince;
    }

    public String getIndustriesByCompany() {
        return industriesByCompany;
    }

    public void setIndustriesByCompany(String industriesByCompany) {
        this.industriesByCompany = industriesByCompany;
    }

    public String getCompanySize() {
        return companySize;
    }

    public void setCompanySize(String companySize) {
        this.companySize = companySize;
    }

    public String getStripePlanId() {
        return stripePlanId;
    }

    public void setStripePlanId(String stripePlanId) {
        this.stripePlanId = stripePlanId;
    }

    public String getStripeStatus() {
        return stripeStatus;
    }

    public void setStripeStatus(String stripeStatus) {
        this.stripeStatus = stripeStatus;
    }

    public List<Payment> getPayments() {
        return payments;
    }

    public void setPayments(List<Payment> payments) {
        this.payments = payments;
    }

}