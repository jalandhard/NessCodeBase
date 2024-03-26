package pojo;

public class SelectionDates {
	
	private boolean attached;
	private String magazine;
	private String selectionType;
	private String issueDate;
	private String deliveryMethod;
	private String selectionDate;
	private String mailDate;
	private int daysBetween;
	private int selectionNumber;
	private String created;
	private String createdBy;
	private String modified;
	private String modifiedBy;
	
	public boolean isAttached() {
		return attached;
	}
	public void setAttached(boolean attached) {
		this.attached = attached;
	}
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
	public String getIssueDate() {
		return issueDate;
	}
	public void setIssueDate(String issueDate) {
		this.issueDate = issueDate;
	}
	public String getDeliveryMethod() {
		return deliveryMethod;
	}
	public void setDeliveryMethod(String deliveryMethod) {
		this.deliveryMethod = deliveryMethod;
	}
	public String getSelectionDate() {
		return selectionDate;
	}
	public void setSelectionDate(String selectionDate) {
		this.selectionDate = selectionDate;
	}
	public String getMailDate() {
		return mailDate;
	}
	public void setMailDate(String mailDate) {
		this.mailDate = mailDate;
	}
	public int getDaysBetween() {
		return daysBetween;
	}
	public void setDaysBetween(int daysBetween) {
		this.daysBetween = daysBetween;
	}
	public int getSelectionNumber() {
		return selectionNumber;
	}
	public void setSelectionNumber(int selectionNumber) {
		this.selectionNumber = selectionNumber;
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
	@Override
	public String toString() {
		return "SelectionDates [attached=" + attached + ", magazine=" + magazine + ", selectionType=" + selectionType
				+ ", issueDate=" + issueDate + ", deliveryMethod=" + deliveryMethod + ", selectionDate=" + selectionDate
				+ ", mailDate=" + mailDate + ", daysBetween=" + daysBetween + ", selectionNumber=" + selectionNumber
				+ ", created=" + created + ", createdBy=" + createdBy + ", modified=" + modified + ", modifiedBy="
				+ modifiedBy + "]";
	}

}
