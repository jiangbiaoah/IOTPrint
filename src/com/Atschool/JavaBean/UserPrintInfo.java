package com.Atschool.JavaBean;

/**
 * userPrintInfo�û���ӡ�ļ���Ϣ
 * 
 * @author Tigerkin
 */
public class UserPrintInfo {
	private String userName; // 1.�û���
	private String fileName = "δѡ��";// 2.�ļ���
	private int pages = 0; // 3.�ĵ�ҳ��
	private int copies = 1; // 4.��ӡ����
	private int collated; // 5.�Ƿ���ݴ�ӡ��0��false 1��true��
	private int color = 0; // 6.�Ƿ��ɫ��ӡ��0��false 1��true��
	private int singleSide = 1; // 7.�Ƿ����ӡ��0��false 1��true��
	private double costEach = 0; // 8.���ĵ��Ĵ�ӡ����
	private String submitOrderTime; // 9.�ύ����ʱ��
	private String completePrintTime;// 10.�ĵ���ӡ���ʱ��
	private String completeOrderTime;// 11.��ɶ���ʱ��
	//private String filePath; // 12.�ļ�·��

	// 1.�û���
	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserName() {
		return userName;
	}

	// 2.�ļ���
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFileName() {
		// ��ȡ�ļ���ʱ�Զ����˵����ڣ�������Ϊ�ļ��ϴ��������������ϵ�
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
		// ��ȡ�ļ��������������ļ��ϴ��������������ϵ�����
		return fileName;
	}

	// 3.�ĵ�ҳ��
	public void setPages(int pages) {
		this.pages = pages;
	}

	public int getPages() {
		return pages;
	}

	// 4.��ӡ����
	public void setCopies(int copies) {
		this.copies = copies;
	}

	public int getCopies() {
		return copies;
	}

	// 5.�Ƿ���ݴ�ӡ��0��false 1��true��
	public void setCollated(int collated) {
		this.collated = collated;
	}

	public int getCollated() {
		return collated;
	}

	// 6.�Ƿ��ɫ��ӡ��0��false 1��true��
	public void setColor(int color) {
		this.color = color;
	}

	public int getColor() {
		return color;
	}

	// 7.�Ƿ����ӡ��0��false 1��true��
	public void setSingleSide(int singleSide) {
		this.singleSide = singleSide;
	}

	public int getSingleSide() {
		return singleSide;
	}

	// 8.���ĵ��Ĵ�ӡ����
	public void setCostEach(double costEach) {
		this.costEach = costEach;
	}

	public double getCostEach() {
		return costEach;
	}

	// 9.�ύ����ʱ��
	public void setSubmitOrderTime(String submitOrderTime) {
		this.submitOrderTime = submitOrderTime;
	}

	public String getSubmitOrderTime() {
		return submitOrderTime;
	}

	// 10.�ĵ���ӡ���ʱ��
	public void setCompletePrintTime(String completePrintTime) {
		this.completePrintTime = completePrintTime;
	}

	public String getCompletePrintTime() {
		return completePrintTime;
	}

	// 11.��ɶ���ʱ��
	public void setCompleteOrderTime(String completeOrderTime) {
		this.completeOrderTime = completeOrderTime;
	}

	public String getCompleteOrderTime() {
		return completeOrderTime;
	}

	// 11.�ļ�·��
//	public void setFilePath(String filePath) {
//		this.filePath = filePath;
//	}

	public String getFilePath() {
		String filePath=Configuration.serverFolderPath + userName + "//" + fileName;
		return filePath;
	}
}
