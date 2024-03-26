package pojo;

public class Selection {
	
	private String magazine;
	private String selectionType;
	private String segment;
	private String selectionDate;
	private String selectionFromDate;
	private String selectionToDate;
	private String segmentInactive;
	private String effort;
	private String keyDate;
	private String keyDateFrom;
	private String keyDateThru;
	private String Package;
	private String comboPackage;
	private String offer;
	private int actualQuantity;
	private int estimatedQuantity;
	private String estimatedQuantityUpdatedDate;
	private boolean estimateOverride;
	private String mailDate;
	private String mailFromDate;
	private String mailToDate;
	private String deliveryMethod;
	private int selectionNumber;
	private String selectionNumberFrom;
	private String selectionNumberThru;
	private boolean skipSelection;
	private String created;
	private String createdBy;
	private String modified;
	private String modifiedBy;
	private String createdDateFrom;
	private String createdDateThru;
	private String modifiedDateFrom;
	private String modifiedDateThru;
	
	public String getMagazine() {
		return magazine;
	}
	public void setMagazine(String magazine) {
		this.magazine = magazine;
	}
	public String getSelectionType() {
		return selectionType;
	}
	public void setSelectionType(String selectionType) {
		this.selectionType = selectionType;
	}
	public String getSegment() {
		return segment;
	}
	public void setSegment(String segment) {
		this.segment = segment;
	}
	public String getSelectionDate() {
		return selectionDate;
	}
	public void setSelectionDate(String selectionDate) {
		this.selectionDate = selectionDate;
	}
	public String getSelectionFromDate() {
		return selectionFromDate;
	}
	public void setSelectionFromDate(String selectionFromDate) {
		this.selectionFromDate = selectionFromDate;
	}
	public String getSelectionToDate() {
		return selectionToDate;
	}
	public void setSelectionToDate(String selectionToDate) {
		this.selectionToDate = selectionToDate;
	}
	public String getSegmentInactive() {
		return segmentInactive;
	}
	public void setSegmentInactive(String segmentInactive) {
		this.segmentInactive = segmentInactive;
	}
	public String getEffort() {
		return effort;
	}
	public void setEffort(String effort) {
		this.effort = effort;
	}
	public String getKeyDate() {
		return keyDate;
	}
	public void setKeyDate(String keyDate) {
		this.keyDate = keyDate;
	}
	public String getKeyDateFrom() {
		return keyDateFrom;
	}
	public void setKeyDateFrom(String keyDateFrom) {
		this.keyDateFrom = keyDateFrom;
	}
	public String getKeyDateThru() {
		return keyDateThru;
	}
	public void setKeyDateThru(String keyDateThru) {
		this.keyDateThru = keyDateThru;
	}
	public String getPackage() {
		return Package;
	}
	public void setPackage(String package1) {
		Package = package1;
	}
	public String getComboPackage() {
		return comboPackage;
	}
	public void setComboPackage(String comboPackage) {
		this.comboPackage = comboPackage;
	}
	public String getOffer() {
		return offer;
	}
	public void setOffer(String offer) {
		this.offer = offer;
	}
	public int getActualQuantity() {
		return actualQuantity;
	}
	public void setActualQuantity(int actualQuantity) {
		this.actualQuantity = actualQuantity;
	}
	public int getEstimatedQuantity() {
		return estimatedQuantity;
	}
	public void setEstimatedQuantity(int estimatedQuantity) {
		this.estimatedQuantity = estimatedQuantity;
	}
	public String getEstimatedQuantityUpdatedDate() {
		return estimatedQuantityUpdatedDate;
	}
	public void setEstimatedQuantityUpdatedDate(String estimatedQuantityUpdatedDate) {
		this.estimatedQuantityUpdatedDate = estimatedQuantityUpdatedDate;
	}
	public boolean isEstimateOverride() {
		return estimateOverride;
	}
	public void setEstimateOverride(boolean estimateOverride) {
		this.estimateOverride = estimateOverride;
	}
	public String getMailDate() {
		return mailDate;
	}
	public void setMailDate(String mailDate) {
		this.mailDate = mailDate;
	}
	public String getMailFromDate() {
		return mailFromDate;
	}
	public void setMailFromDate(String mailFromDate) {
		this.mailFromDate = mailFromDate;
	}
	public String getMailToDate() {
		return mailToDate;
	}
	public void setMailToDate(String mailToDate) {
		this.mailToDate = mailToDate;
	}
	public String getDeliveryMethod() {
		return deliveryMethod;
	}
	public void setDeliveryMethod(String deliveryMethod) {
		this.deliveryMethod = deliveryMethod;
	}
	public int getSelectionNumber() {
		return selectionNumber;
	}
	public void setSelectionNumber(int selectionNumber) {
		this.selectionNumber = selectionNumber;
	}
	public String getSelectionNumberFrom() {
		return selectionNumberFrom;
	}
	public void setSelectionNumberFrom(String selectionNumberFrom) {
		this.selectionNumberFrom = selectionNumberFrom;
	}
	public String getSelectionNumberThru() {
		return selectionNumberThru;
	}
	public void setSelectionNumberThru(String selectionNumberThru) {
		this.selectionNumberThru = selectionNumberThru;
	}
	public boolean isSkipSelection() {
		return skipSelection;
	}
	public void setSkipSelection(boolean skipSelection) {
		this.skipSelection = skipSelection;
	}
	public String getCreated() {
		return created;
	}
	public void setCreated(String created) {
		this.created = created;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public String getModified() {
		return modified;
	}
	public void setModified(String modified) {
		this.modified = modified;
	}
	public String getModifiedBy() {
		return modifiedBy;
	}
	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}
	public String getCreatedDateFrom() {
		return createdDateFrom;
	}
	public void setCreatedDateFrom(String createdDateFrom) {
		this.createdDateFrom = createdDateFrom;
	}
	public String getCreatedDateThru() {
		return createdDateThru;
	}
	public void setCreatedDateThru(String createdDateThru) {
		this.createdDateThru = createdDateThru;
	}
	public String getModifiedDateFrom() {
		return modifiedDateFrom;
	}
	public void setModifiedDateFrom(String modifiedDateFrom) {
		this.modifiedDateFrom = modifiedDateFrom;
	}
	public String getModifiedDateThru() {
		return modifiedDateThru;
	}
	public void setModifiedDateThru(String modifiedDateThru) {
		this.modifiedDateThru = modifiedDateThru;
	}
	
	@Override
	public String toString() {
		return "Selection [magazine=" + magazine + ", selectionType=" + selectionType + ", segment=" + segment
				+ ", selectionDate=" + selectionDate + ", selectionFromDate=" + selectionFromDate + ", selectionToDate="
				+ selectionToDate + ", segmentInactive=" + segmentInactive + ", effort=" + effort + ", keyDate="
				+ keyDate + ", keyDateFrom=" + keyDateFrom + ", keyDateThru=" + keyDateThru + ", Package=" + Package
				+ ", comboPackage=" + comboPackage + ", offer=" + offer + ", actualQuantity=" + actualQuantity
				+ ", estimatedQuantity=" + estimatedQuantity + ", estimatedQuantityUpdatedDate="
				+ estimatedQuantityUpdatedDate + ", estimateOverride=" + estimateOverride + ", mailDate=" + mailDate
				+ ", mailFromDate=" + mailFromDate + ", mailToDate=" + mailToDate + ", deliveryMethod=" + deliveryMethod
				+ ", selectionNumber=" + selectionNumber + ", selectionNumberFrom=" + selectionNumberFrom
				+ ", selectionNumberThru=" + selectionNumberThru + ", skipSelection=" + skipSelection + ", created="
				+ created + ", createdBy=" + createdBy + ", modified=" + modified + ", modifiedBy=" + modifiedBy
				+ ", createdDateFrom=" + createdDateFrom + ", createdDateThru=" + createdDateThru
				+ ", modifiedDateFrom=" + modifiedDateFrom + ", modifiedDateThru=" + modifiedDateThru + "]";
	}
	
}
