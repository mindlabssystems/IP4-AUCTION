package com.xsusenet.ip4.Models.Detail;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PurchaseResult {
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("user_id")
    @Expose
    private Integer userId;
    @SerializedName("list_id")
    @Expose
    private Integer listId;
    @SerializedName("list_amount")
    @Expose
    private Double listAmount;
    @SerializedName("commission")
    @Expose
    private Double commission;
    @SerializedName("transfer_fee")
    @Expose
    private Double transferFee;
    @SerializedName("total_amount")
    @Expose
    private Double totalAmount;
    @SerializedName("payment_type_id")
    @Expose
    private Integer paymentTypeId;
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
    private Object paidDate;
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
    private Object abuseEmail;
    @SerializedName("abuse_expected")
    @Expose
    private String abuseExpected;
    @SerializedName("business_since")
    @Expose
    private Object businessSince;
    @SerializedName("industries_by_company")
    @Expose
    private Object industriesByCompany;
    @SerializedName("company_size")
    @Expose
    private Object companySize;
    @SerializedName("stripe_plan_id")
    @Expose
    private Object stripePlanId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getListId() {
        return listId;
    }

    public void setListId(Integer listId) {
        this.listId = listId;
    }

    public Double getListAmount() {
        return listAmount;
    }

    public void setListAmount(Double listAmount) {
        this.listAmount = listAmount;
    }

    public Double getCommission() {
        return commission;
    }

    public void setCommission(Double commission) {
        this.commission = commission;
    }

    public Double getTransferFee() {
        return transferFee;
    }

    public void setTransferFee(Double transferFee) {
        this.transferFee = transferFee;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Integer getPaymentTypeId() {
        return paymentTypeId;
    }

    public void setPaymentTypeId(Integer paymentTypeId) {
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

    public Object getPaidDate() {
        return paidDate;
    }

    public void setPaidDate(Object paidDate) {
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

    public Object getAbuseEmail() {
        return abuseEmail;
    }

    public void setAbuseEmail(Object abuseEmail) {
        this.abuseEmail = abuseEmail;
    }

    public String getAbuseExpected() {
        return abuseExpected;
    }

    public void setAbuseExpected(String abuseExpected) {
        this.abuseExpected = abuseExpected;
    }

    public Object getBusinessSince() {
        return businessSince;
    }

    public void setBusinessSince(Object businessSince) {
        this.businessSince = businessSince;
    }

    public Object getIndustriesByCompany() {
        return industriesByCompany;
    }

    public void setIndustriesByCompany(Object industriesByCompany) {
        this.industriesByCompany = industriesByCompany;
    }

    public Object getCompanySize() {
        return companySize;
    }

    public void setCompanySize(Object companySize) {
        this.companySize = companySize;
    }

    public Object getStripePlanId() {
        return stripePlanId;
    }

    public void setStripePlanId(Object stripePlanId) {
        this.stripePlanId = stripePlanId;
    }
}
