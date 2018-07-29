package com.Atschool.JavaBean;

/**
 * userPrintInfo用户打印文件信息
 * 
 * @author Tigerkin
 */
public class UserPrintInfo {
	private String userName; // 1.用户名
	private String fileName = "未选择";// 2.文件名
	private int pages = 0; // 3.文档页数
	private int copies = 1; // 4.打印份数
	private int collated; // 5.是否逐份打印（0：false 1：true）
	private int color = 0; // 6.是否彩色打印（0：false 1：true）
	private int singleSide = 1; // 7.是否单面打印（0：false 1：true）
	private double costEach = 0; // 8.该文档的打印费用
	private String submitOrderTime; // 9.提交订单时间
	private String completePrintTime;// 10.文档打印完成时间
	private String completeOrderTime;// 11.完成订单时间
	//private String filePath; // 12.文件路径

	// 1.用户名
	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserName() {
		return userName;
	}

	// 2.文件名
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFileName() {
		// 获取文件名时自动过滤掉日期，该日期为文件上传后重命名所加上的
		String strFileName=null;
		if (fileName.contains("_")) {
			strFileName = fileName.substring(0, fileName.lastIndexOf("_"))
					+ fileName.substring(fileName.lastIndexOf("."));
		} else {
			strFileName = fileName;
		}
		return strFileName;
	}
	public String getFileReamName() {
		// 获取文件完整名，包含文件上传后重命名所加上的日期
		return fileName;
	}

	// 3.文档页数
	public void setPages(int pages) {
		this.pages = pages;
	}

	public int getPages() {
		return pages;
	}

	// 4.打印份数
	public void setCopies(int copies) {
		this.copies = copies;
	}

	public int getCopies() {
		return copies;
	}

	// 5.是否逐份打印（0：false 1：true）
	public void setCollated(int collated) {
		this.collated = collated;
	}

	public int getCollated() {
		return collated;
	}

	// 6.是否彩色打印（0：false 1：true）
	public void setColor(int color) {
		this.color = color;
	}

	public int getColor() {
		return color;
	}

	// 7.是否单面打印（0：false 1：true）
	public void setSingleSide(int singleSide) {
		this.singleSide = singleSide;
	}

	public int getSingleSide() {
		return singleSide;
	}

	// 8.该文档的打印费用
	public void setCostEach(double costEach) {
		this.costEach = costEach;
	}

	public double getCostEach() {
		return costEach;
	}

	// 9.提交订单时间
	public void setSubmitOrderTime(String submitOrderTime) {
		this.submitOrderTime = submitOrderTime;
	}

	public String getSubmitOrderTime() {
		return submitOrderTime;
	}

	// 10.文档打印完成时间
	public void setCompletePrintTime(String completePrintTime) {
		this.completePrintTime = completePrintTime;
	}

	public String getCompletePrintTime() {
		return completePrintTime;
	}

	// 11.完成订单时间
	public void setCompleteOrderTime(String completeOrderTime) {
		this.completeOrderTime = completeOrderTime;
	}

	public String getCompleteOrderTime() {
		return completeOrderTime;
	}

	// 11.文件路径
//	public void setFilePath(String filePath) {
//		this.filePath = filePath;
//	}

	public String getFilePath() {
		String filePath=Configuration.serverFolderPath + userName + "//" + fileName;
		return filePath;
	}
}
