package com.xsusenet.ip4.Models.Detail;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.xsusenet.ip4.Models.Sales.TransferableTo;

public class DetailList {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("user_id")
    @Expose
    private Integer userId;
    @SerializedName("sale_or_rent")
    @Expose
    private String saleOrRent;
    @SerializedName("sale_method")
    @Expose
    private String saleMethod;
    @SerializedName("rent_min_term")
    @Expose
    private Integer rentMinTerm;
    @SerializedName("sales_type_id")
    @Expose
    private Integer salesTypeId;
    @SerializedName("size_id")
    @Expose
    private Integer sizeId;
    @SerializedName("region_id")
    @Expose
    private Integer regionId;
    @SerializedName("end_date")
    @Expose
    private String endDate;
    @SerializedName("sale_price")
    @Expose
    private Integer salePrice;
    @SerializedName("opening_bid")
    @Expose
    private Integer openingBid;
    @SerializedName("no_of_address")
    @Expose
    private Integer noOfAddress;
    @SerializedName("starting_ip")
    @Expose
    private String startingIp;
    @SerializedName("ending_ip")
    @Expose
    private String endingIp;
    @SerializedName("detail_info_link")
    @Expose
    private Object detailInfoLink;
    @SerializedName("additional_note")
    @Expose
    private Object additionalNote;
    @SerializedName("list_status")
    @Expose
    private String listStatus;
    @SerializedName("sale_status")
    @Expose
    private String saleStatus;
    @SerializedName("winner")
    @Expose
    private Integer winner;
    @SerializedName("winning_amount")
    @Expose
    private Integer winningAmount;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;
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
    private Integer noOfBids;
    @SerializedName("current_bid")
    @Expose
    private Integer currentBid;
    @SerializedName("block_size")
    @Expose
    private String blockSize;
    @SerializedName("region_name")
    @Expose
    private String regionName;
    //    @SerializedName("purchase_result")
//    @Expose
//    private String purchaseResult;
    @SerializedName("purchase_result")
    @Expose
    private java.util.List<PurchaseResult> purchaseResult = null;
    @SerializedName("bids_placed")
    @Expose
    private Integer bidsPlaced;
    @SerializedName("my_bid_amount")
    @Expose
    private Integer myBidAmount;
    @SerializedName("added_to_watchlist")
    @Expose
    private Integer addedToWatchlist;

    @SerializedName("transferrable_to")
    @Expose
    private java.util.List<TransferableTo> transferrableTo = null;

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

    public Integer getRentMinTerm() {
        return rentMinTerm;
    }

    public void setRentMinTerm(Integer rentMinTerm) {
        this.rentMinTerm = rentMinTerm;
    }

    public Integer getSalesTypeId() {
        return salesTypeId;
    }

    public void setSalesTypeId(Integer salesTypeId) {
        this.salesTypeId = salesTypeId;
    }

    public Integer getSizeId() {
        return sizeId;
    }

    public void setSizeId(Integer sizeId) {
        this.sizeId = sizeId;
    }

    public Integer getRegionId() {
        return regionId;
    }

    public void setRegionId(Integer regionId) {
        this.regionId = regionId;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public Integer getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(Integer salePrice) {
        this.salePrice = salePrice;
    }

    public Integer getOpeningBid() {
        return openingBid;
    }

    public void setOpeningBid(Integer openingBid) {
        this.openingBid = openingBid;
    }

    public Integer getNoOfAddress() {
        return noOfAddress;
    }

    public void setNoOfAddress(Integer noOfAddress) {
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

    public Object getDetailInfoLink() {
        return detailInfoLink;
    }

    public void setDetailInfoLink(Object detailInfoLink) {
        this.detailInfoLink = detailInfoLink;
    }

    public Object getAdditionalNote() {
        return additionalNote;
    }

    public void setAdditionalNote(Object additionalNote) {
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

    public Integer getWinner() {
        return winner;
    }

    public void setWinner(Integer winner) {
        this.winner = winner;
    }

    public Integer getWinningAmount() {
        return winningAmount;
    }

    public void setWinningAmount(Integer winningAmount) {
        this.winningAmount = winningAmount;
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

    public Integer getNoOfBids() {
        return noOfBids;
    }

    public void setNoOfBids(Integer noOfBids) {
        this.noOfBids = noOfBids;
    }

    public Integer getCurrentBid() {
        return currentBid;
    }

    public void setCurrentBid(Integer currentBid) {
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

    //    public String getPurchaseResult() {
//        return purchaseResult;
//    }
//
//    public void setPurchaseResult(String purchaseResult) {
//        this.purchaseResult = purchaseResult;
//    }
    public java.util.List<PurchaseResult> getPurchaseResult() {
        return purchaseResult;
    }

    public void setPurchaseResult(java.util.List<PurchaseResult> purchaseResult) {
        this.purchaseResult = purchaseResult;
    }

    public Integer getBidsPlaced() {
        return bidsPlaced;
    }

    public void setBidsPlaced(Integer bidsPlaced) {
        this.bidsPlaced = bidsPlaced;
    }

    public Integer getMyBidAmount() {
        return myBidAmount;
    }

    public void setMyBidAmount(Integer myBidAmount) {
        this.myBidAmount = myBidAmount;
    }

    public Integer getAddedToWatchlist() {
        return addedToWatchlist;
    }

    public void setAddedToWatchlist(Integer addedToWatchlist) {
        this.addedToWatchlist = addedToWatchlist;
    }

    public java.util.List<TransferableTo> getTransferrableTo() {
        return transferrableTo;
    }

    public void setTransferrableTo(java.util.List<TransferableTo> transferrableTo) {
        this.transferrableTo = transferrableTo;
    }
   /* @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("user_id")
    @Expose
    private Integer userId;
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
    private Integer salesTypeId;
    @SerializedName("size_id")
    @Expose
    private Integer sizeId;
    @SerializedName("region_id")
    @Expose
    private Integer regionId;
    @SerializedName("category_id")
    @Expose
    private Integer categoryId;
    @SerializedName("end_date")
    @Expose
    private String endDate;
    @SerializedName("sale_price")
    @Expose
    private Integer salePrice;
    @SerializedName("opening_bid")
    @Expose
    private Integer openingBid;
    @SerializedName("no_of_address")
    @Expose
    private Integer noOfAddress;
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
    private Object additionalNote;
    @SerializedName("list_status")
    @Expose
    private String listStatus;
    @SerializedName("sale_status")
    @Expose
    private String saleStatus;
    @SerializedName("winning_amount")
    @Expose
    private Integer winningAmount;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;
    @SerializedName("no_of_bids")
    @Expose
    private Integer noOfBids;
    @SerializedName("block_size")
    @Expose
    private String blockSize;
    @SerializedName("region_name")
    @Expose
    private String regionName;
    @SerializedName("category_name")
    @Expose
    private Object categoryName;
    @SerializedName("added_to_watchlist")
    @Expose
    private Integer addedToWatchlist;
    @SerializedName("transferrable_to")
    @Expose
    private java.util.List<TransferableTo> transferrableTo = null;

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

    public Integer getSalesTypeId() {
        return salesTypeId;
    }

    public void setSalesTypeId(Integer salesTypeId) {
        this.salesTypeId = salesTypeId;
    }

    public Integer getSizeId() {
        return sizeId;
    }

    public void setSizeId(Integer sizeId) {
        this.sizeId = sizeId;
    }

    public Integer getRegionId() {
        return regionId;
    }

    public void setRegionId(Integer regionId) {
        this.regionId = regionId;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public Integer getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(Integer salePrice) {
        this.salePrice = salePrice;
    }

    public Integer getOpeningBid() {
        return openingBid;
    }

    public void setOpeningBid(Integer openingBid) {
        this.openingBid = openingBid;
    }

    public Integer getNoOfAddress() {
        return noOfAddress;
    }

    public void setNoOfAddress(Integer noOfAddress) {
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

    public Object getAdditionalNote() {
        return additionalNote;
    }

    public void setAdditionalNote(Object additionalNote) {
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

    public Integer getWinningAmount() {
        return winningAmount;
    }

    public void setWinningAmount(Integer winningAmount) {
        this.winningAmount = winningAmount;
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

    public Integer getNoOfBids() {
        return noOfBids;
    }

    public void setNoOfBids(Integer noOfBids) {
        this.noOfBids = noOfBids;
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

    public Object getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(Object categoryName) {
        this.categoryName = categoryName;
    }

    public Integer getAddedToWatchlist() {
        return addedToWatchlist;
    }

    public void setAddedToWatchlist(Integer addedToWatchlist) {
        this.addedToWatchlist = addedToWatchlist;
    }
    public java.util.List<TransferableTo> getTransferrableTo() {
        return transferrableTo;
    }

    public void setTransferrableTo(java.util.List<TransferableTo> transferrableTo) {
        this.transferrableTo = transferrableTo;
    }*/
/*    @SerializedName("list_id")
    @Expose
    private String listId;
    @SerializedName("user_id")
    @Expose
    private String userId;
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
    @SerializedName("added_date")
    @Expose
    private String addedDate;
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
    @SerializedName("no_of_bids")
    @Expose
    private String noOfBids;
    @SerializedName("block_size")
    @Expose
    private String blockSize;
    @SerializedName("region_name")
    @Expose
    private String regionName;
    @SerializedName("category_name")
    @Expose
    private String categoryName;
    @SerializedName("transferrable_to")
    @Expose
    private java.util.List<TransferableTo> transferrableTo = null;

    public String getListId() {
        return listId;
    }

    public void setListId(String listId) {
        this.listId = listId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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

    public String getAddedDate() {
        return addedDate;
    }

    public void setAddedDate(String addedDate) {
        this.addedDate = addedDate;
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
    }

    public String getNoOfBids() {
        return noOfBids;
    }

    public void setNoOfBids(String noOfBids) {
        this.noOfBids = noOfBids;
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

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public java.util.List<TransferableTo> getTransferrableTo() {
        return transferrableTo;
    }

    public void setTransferrableTo(java.util.List<TransferableTo> transferrableTo) {
        this.transferrableTo = transferrableTo;
    }*/
}
