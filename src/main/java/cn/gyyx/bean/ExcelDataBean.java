package cn.gyyx.bean;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table(name="sheet_info_tb")
public class ExcelDataBean implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id ;
	
	private int columnNum;
	
	private String lineData;

	private String sheetName;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public int getColumnNum() {
		return columnNum;
	}

	public void setColumnNum(int columnNum) {
		this.columnNum = columnNum;
	}

	public String getLineData() {
		return lineData;
	}

	public void setLineData(String lineData) {
		this.lineData = lineData;
	}

	public String getSheetName() {
		return sheetName;
	}

	public void setSheetName(String sheetName) {
		this.sheetName = sheetName;
	}
	
}
