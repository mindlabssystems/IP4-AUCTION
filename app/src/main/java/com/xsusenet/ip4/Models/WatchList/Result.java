package com.xsusenet.ip4.Models.WatchList;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.xsusenet.ip4.Models.Sales.TransferableTo;

import java.util.List;

public class Result {
    @SerializedName("watchlist_id")
    @Expose
    private String watchlistId;
    @SerializedName("user_id")
    @Expose
    private String userId;
    @SerializedName("list_id")
    @Expose
    private String listId;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("sale_or_rent")
    @Expose
    private String saleOrRent;
    @SerializedName("sale_method")
    @Expose
    private String saleMethod;
    @SerializedName("rent_min_term")
    @Expose
    private String rentMinTerm;
    @SerializedName("sales_type_id")
    @Expose
    private String salesTypeId;
    @SerializedName("size_id")
    @Expose
    private String sizeId;
    @SerializedName("region_id")
    @Expose
    private String regionId;
    @SerializedName("category_id")
    @Expose
    private String categoryId;
    @SerializedName("end_date")
    @Expose
    private String endDate;
    @SerializedName("sale_price")
    @Expose
    private String salePrice;
    @SerializedName("opening_bid")
    @Expose
    private String openingBid;
    @SerializedName("no_of_address")
    @Expose
    private String noOfAddress;
    @SerializedName("starting_ip")
    @Expose
    private String startingIp;
    @SerializedName("ending_ip")
    @Expose
    private String endingIp;
    @SerializedName("detail_info_link")
    @Expose
    private String detailInfoLink;
    @SerializedName("additional_note")
    @Expose
    private String additionalNote;
    @SerializedName("list_status")
    @Expose
    private String listStatus;
    @SerializedName("sale_status")
    @Expose
    private String saleStatus;
    @SerializedName("winner")
    @Expose
    private String winner;
    @SerializedName("winning_amount")
    @Expose
    private String winningAmount;
    @SerializedName("transaction_status")
    @Expose
    private String transactionStatus;
    @SerializedName("funding_status")
    @Expose
    private String fundingStatus;
    @SerializedName("ip_transfer_status")
    @Expose
    private String ipTransferStatus;
    @SerializedName("no_of_bids")
    @Expose
    private String noOfBids;
    @SerializedName("current_bid")
    @Expose
    private String currentBid;
    @SerializedName("block_size")
    @Expose
    private String blockSize;
    @SerializedName("region_name")
    @Expose
    private String regionName;
    @SerializedName("transferrable_to")
    @Expose
    private List<TransferableTo> transferrableTo = null;

    public String getWatchlistId() {
        return watchlistId;
    }

    public void setWatchlistId(String watchlistId) {
        this.watchlistId = watchlistId;
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSaleOrRent() {
        return saleOrRent;
    }

    public void setSaleOrRent(String saleOrRent) {
        this.saleOrRent = saleOrRent;
    }

    public String getSaleMethod() {
        return saleMethod;
    }

    public void setSaleMethod(String saleMethod) {
        this.saleMethod = saleMethod;
    }

    public String getRentMinTerm() {
        return rentMinTerm;
    }

    public void setRentMinTerm(String rentMinTerm) {
        this.rentMinTerm = rentMinTerm;
    }

    public String getSalesTypeId() {
        return salesTypeId;
    }

    public void setSalesTypeId(String salesTypeId) {
        this.salesTypeId = salesTypeId;
    }

    public String getSizeId() {
        return sizeId;
    }

    public void setSizeId(String sizeId) {
        this.sizeId = sizeId;
    }

    public String getRegionId() {
        return regionId;
    }

    public void setRegionId(String regionId) {
        this.regionId = regionId;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(String salePrice) {
        this.salePrice = salePrice;
    }

    public String getOpeningBid() {
        return openingBid;
    }

    public void setOpeningBid(String openingBid) {
        this.openingBid = openingBid;
    }

    public String getNoOfAddress() {
        return noOfAddress;
    }

    public void setNoOfAddress(String noOfAddress) {
        this.noOfAddress = noOfAddress;
    }

    public String getStartingIp() {
        return startingIp;
    }

    public void setStartingIp(String startingIp) {
        this.startingIp = startingIp;
    }

    public String getEndingIp() {
        return endingIp;
    }

    public void setEndingIp(String endingIp) {
        this.endingIp = endingIp;
    }

    public String getDetailInfoLink() {
        return detailInfoLink;
    }

    public void setDetailInfoLink(String detailInfoLink) {
        this.detailInfoLink = detailInfoLink;
    }

    public String getAdditionalNote() {
        return additionalNote;
    }

    public void setAdditionalNote(String additionalNote) {
        this.additionalNote = additionalNote;
    }

    public String getListStatus() {
        return listStatus;
    }

    public void setListStatus(String listStatus) {
        this.listStatus = listStatus;
    }

    public String getSaleStatus() {
        return saleStatus;
    }

    public void setSaleStatus(String saleStatus) {
        this.saleStatus = saleStatus;
    }

    public String getWinner() {
        return winner;
    }

    public void setWinner(String winner) {
        this.winner = winner;
    }

    public String getWinningAmount() {
        return winningAmount;
    }

    public void setWinningAmount(String winningAmount) {
        this.winningAmount = winningAmount;
    }

    public String getTransactionStatus() {
        return transactionStatus;
    }

    public void setTransactionStatus(String transactionStatus) {
        this.transactionStatus = transactionStatus;
    }

    public String getFundingStatus() {
        return fundingStatus;
    }

    public void setFundingStatus(String fundingStatus) {
        this.fundingStatus = fundingStatus;
    }

    public String getIpTransferStatus() {
        return ipTransferStatus;
    }

    public void setIpTransferStatus(String ipTransferStatus) {
        this.ipTransferStatus = ipTransferStatus;
    }

    public String getNoOfBids() {
        return noOfBids;
    }

    public void setNoOfBids(String noOfBids) {
        this.noOfBids = noOfBids;
    }

    public String getCurrentBid() {
        return currentBid;
    }

    public void setCurrentBid(String currentBid) {
        this.currentBid = currentBid;
    }

    public String getBlockSize() {
        return blockSize;
    }

    public void setBlockSize(String blockSize) {
        this.blockSize = blockSize;
    }

    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }

    public List<TransferableTo> getTransferrableTo() {
        return transferrableTo;
    }

    public void setTransferrableTo(List<TransferableTo> transferrableTo) {
        this.transferrableTo = transferrableTo;
    }
    /*@SerializedName("watchlist_id")
    @Expose
    private String watchlistId;
    @SerializedName("user_id")
    @Expose
    private String userId;
    @SerializedName("list_id")
    @Expose
    private String listId;
    @SerializedName("added_date")
    @Expose
    private String addedDate;
    @SerializedName("sale_or_rent")
    @Expose
    private String saleOrRent;
    @SerializedName("sale_method")
    @Expose
    private String saleMethod;
    @SerializedName("rent_min_term")
    @Expose
    private String rentMinTerm;
    @SerializedName("sales_type_id")
    @Expose
    private String salesTypeId;
    @SerializedName("size_id")
    @Expose
    private String sizeId;
    @SerializedName("region_id")
    @Expose
    private String regionId;
    @SerializedName("category_id")
    @Expose
    private String categoryId;
    @SerializedName("end_date")
    @Expose
    private String endDate;
    @SerializedName("sale_price")
    @Expose
    private String salePrice;
    @SerializedName("opening_bid")
    @Expose
    private String openingBid;
    @SerializedName("no_of_address")
    @Expose
    private String noOfAddress;
    @SerializedName("starting_ip")
    @Expose
    private String startingIp;
    @SerializedName("ending_ip")
    @Expose
    private String endingIp;
    @SerializedName("detail_info_link")
    @Expose
    private String detailInfoLink;
    @SerializedName("additional_note")
    @Expose
    private String additionalNote;
    @SerializedName("list_status")
    @Expose
    private String listStatus;
    @SerializedName("sale_status")
    @Expose
    private String saleStatus;
    @SerializedName("winner_id")
    @Expose
    private String winnerId;
    @SerializedName("winning_amount")
    @Expose
    private String winningAmount;

    public String getWatchlistId() {
        return watchlistId;
    }

    public void setWatchlistId(String watchlistId) {
        this.watchlistId = watchlistId;
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

    public String getAddedDate() {
        return addedDate;
    }

    public void setAddedDate(String addedDate) {
        this.addedDate = addedDate;
    }

    public String getSaleOrRent() {
        return saleOrRent;
    }

    public void setSaleOrRent(String saleOrRent) {
        this.saleOrRent = saleOrRent;
    }

    public String getSaleMethod() {
        return saleMethod;
    }

    public void setSaleMethod(String saleMethod) {
        this.saleMethod = saleMethod;
    }

    public String getRentMinTerm() {
        return rentMinTerm;
    }

    public void setRentMinTerm(String rentMinTerm) {
        this.rentMinTerm = rentMinTerm;
    }

    public String getSalesTypeId() {
        return salesTypeId;
    }

    public void setSalesTypeId(String salesTypeId) {
        this.salesTypeId = salesTypeId;
    }

    public String getSizeId() {
        return sizeId;
    }

    public void setSizeId(String sizeId) {
        this.sizeId = sizeId;
    }

    public String getRegionId() {
        return regionId;
    }

    public void setRegionId(String regionId) {
        this.regionId = regionId;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(String salePrice) {
        this.salePrice = salePrice;
    }

    public String getOpeningBid() {
        return openingBid;
    }

    public void setOpeningBid(String openingBid) {
        this.openingBid = openingBid;
    }

    public String getNoOfAddress() {
        return noOfAddress;
    }

    public void setNoOfAddress(String noOfAddress) {
        this.noOfAddress = noOfAddress;
    }

    public String getStartingIp() {
        return startingIp;
    }

    public void setStartingIp(String startingIp) {
        this.startingIp = startingIp;
    }

    public String getEndingIp() {
        return endingIp;
    }

    public void setEndingIp(String endingIp) {
        this.endingIp = endingIp;
    }

    public String getDetailInfoLink() {
        return detailInfoLink;
    }

    public void setDetailInfoLink(String detailInfoLink) {
        this.detailInfoLink = detailInfoLink;
    }

    public String getAdditionalNote() {
        return additionalNote;
    }

    public void setAdditionalNote(String additionalNote) {
        this.additionalNote = additionalNote;
    }

    public String getListStatus() {
        return listStatus;
    }

    public void setListStatus(String listStatus) {
        this.listStatus = listStatus;
    }

    public String getSaleStatus() {
        return saleStatus;
    }

    public void setSaleStatus(String saleStatus) {
        this.saleStatus = saleStatus;
    }

    public String getWinnerId() {
        return winnerId;
    }

    public void setWinnerId(String winnerId) {
        this.winnerId = winnerId;
    }

    public String getWinningAmount() {
        return winningAmount;
    }

    public void setWinningAmount(String winningAmount) {
        this.winningAmount = winningAmount;
    }*/
}
