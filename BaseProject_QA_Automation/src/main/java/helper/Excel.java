package helper;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.*;



public class Excel {
	
	
	private XSSFWorkbook workbookExt;
	private HSSFWorkbook workbookOld;
	private boolean extension;
	//private XSSFSheet sheet;	
	private String pathOrigin;
	private String pathDestin;
	private java.util.HashMap<String,XSSFSheet> hmSheetExt = new java.util.HashMap<String,XSSFSheet>(); 
	private java.util.HashMap<String,HSSFSheet> hmSheetOld = new java.util.HashMap<String,HSSFSheet>(); 
	
	
	public Excel(String pathOrigin,String pathDestin, String sheetname) {
		super();
		this.pathOrigin = pathOrigin;
		this.pathDestin = pathDestin;
		this.extension = isExtension(pathOrigin);
		try {
			if(extension){
				workbookExt = new XSSFWorkbook(
						OPCPackage.open(new FileInputStream(pathOrigin)));
				hmSheetExt.put(sheetname, workbookExt.getSheet(sheetname));
			}else{
				workbookOld = new HSSFWorkbook(new FileInputStream(pathOrigin));
				hmSheetOld.put(sheetname, workbookOld.getSheet(sheetname));
			}
		} catch (InvalidFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public Excel(String pathOrigin,String pathDestin) {
		super();
		this.pathOrigin = pathOrigin;
		this.pathDestin = pathDestin;
		this.extension = isExtension(pathOrigin);
		try {
			if(extension){
				workbookExt = new XSSFWorkbook(
						OPCPackage.open(new FileInputStream(pathOrigin)));
				String sheetname = workbookExt.getSheetAt(workbookExt.getActiveSheetIndex()).getSheetName();
				hmSheetExt.put(sheetname, workbookExt.getSheet(sheetname));
			}else{
				workbookOld = new HSSFWorkbook(new FileInputStream(pathOrigin));
				String sheetname = workbookOld.getSheetAt(workbookOld.getActiveSheetIndex()).getSheetName();
				hmSheetOld.put(sheetname, workbookOld.getSheet(sheetname));
			}
		} catch (InvalidFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private boolean isExtension(String path){
		File f = new File(path);
		if(!f.exists()){
			throw new IllegalArgumentException("File '"+path+"' is not existed");
		}
		if(path.toLowerCase().endsWith("xlsx")){
			return true;
		}else if(path.toLowerCase().endsWith("xls")){
			return false;
		}	
		throw new IllegalArgumentException("File has not 'xlsx' or 'xls' format");
	}

	private void checkDetinPath(){
		if(pathDestin == null){
			throw new IllegalArgumentException("Path 'pathDestin' is not set");
		}
		if(0==pathOrigin.compareToIgnoreCase(pathDestin)){
			throw new IllegalArgumentException("Paths 'pathOrigin' and 'pathDestin' have not been the same: '"+pathOrigin+"'");
		}
		String format = null;
		if(pathOrigin.toLowerCase().endsWith("xlsx")){
			format = "xlsx";
		}else if(pathOrigin.toLowerCase().endsWith("xls")){
			format = "xls";
		}
		if(!pathDestin.endsWith(format)){
			throw new IllegalArgumentException("Path '"+pathDestin+"' have to end with '"+format+"' format");
		}
		File f = new File(pathDestin);
		(new File(f.getParent())).mkdirs();
		f.delete();
	}

	public String readCell(int row,int col){
		try {
			if(extension){
				FormulaEvaluator evaluator = workbookExt.getCreationHelper().createFormulaEvaluator();
				String sheetname = workbookExt.getSheetAt(workbookExt.getActiveSheetIndex()).getSheetName();
				XSSFRow row1 = hmSheetExt.get(sheetname).getRow(row);
				XSSFCell cell1 = row1.getCell(col);
				return readCell(cell1,evaluator);
			}else{
				FormulaEvaluator evaluator = workbookOld.getCreationHelper().createFormulaEvaluator();
				String sheetname = workbookOld.getSheetAt(workbookOld.getActiveSheetIndex()).getSheetName();
				HSSFRow row1 = hmSheetOld.get(sheetname).getRow(row);
				HSSFCell cell1 = row1.getCell(col);
				return readCell(cell1,evaluator);
			}
		} catch (NullPointerException e) {
			return null;
		}
	}
	public String readCell(String sheetname,int row,int col){
		try {
			if(extension){
				FormulaEvaluator evaluator = workbookExt.getCreationHelper().createFormulaEvaluator();
				XSSFRow row1 = hmSheetExt.get(sheetname).getRow(row);
				XSSFCell cell1 = row1.getCell(col);
				return readCell(cell1,evaluator);
			}else{
				FormulaEvaluator evaluator = workbookOld.getCreationHelper().createFormulaEvaluator();
				HSSFRow row1 = hmSheetOld.get(sheetname).getRow(row);
				HSSFCell cell1 = row1.getCell(col);
				return readCell(cell1,evaluator);
			}
		} catch (NullPointerException e) {
			return null;
		}
	}

	public String readCell(int row,int col,String ifNull){
		try {
			if(extension){
				FormulaEvaluator evaluator = workbookExt.getCreationHelper().createFormulaEvaluator();
				String sheetname = workbookExt.getSheetAt(workbookExt.getActiveSheetIndex()).getSheetName();
				XSSFRow row1 = hmSheetExt.get(sheetname).getRow(row);
				XSSFCell cell1 = row1.getCell(col);
				return readCell(cell1,evaluator);
			}else{
				FormulaEvaluator evaluator = workbookOld.getCreationHelper().createFormulaEvaluator();
				String sheetname = workbookOld.getSheetAt(workbookOld.getActiveSheetIndex()).getSheetName();
				HSSFRow row1 = hmSheetOld.get(sheetname).getRow(row);
				HSSFCell cell1 = row1.getCell(col);
				return readCell(cell1,evaluator);
			}
		} catch (NullPointerException e) {
			return ifNull;
		}
	}
	public String readCell(String sheetname,int row,int col,String ifNull){
		try {
			if(extension){
				FormulaEvaluator evaluator = workbookExt.getCreationHelper().createFormulaEvaluator();
				XSSFRow row1 = hmSheetExt.get(sheetname).getRow(row);
				XSSFCell cell1 = row1.getCell(col);
				return readCell(cell1,evaluator);
			}else{
				FormulaEvaluator evaluator = workbookOld.getCreationHelper().createFormulaEvaluator();
				HSSFRow row1 = hmSheetOld.get(sheetname).getRow(row);
				HSSFCell cell1 = row1.getCell(col);
				return readCell(cell1,evaluator);
			}
		} catch (NullPointerException e) {
			return ifNull;
		}
	}
	private String readCell(XSSFCell cell1,FormulaEvaluator evaluator) throws NullPointerException{
		String value = null;
		switch (evaluator.evaluate(cell1).getCellTypeEnum()) {
		case STRING:
			value = evaluator.evaluate(cell1).getStringValue();
			break;
		case FORMULA:
			value = cell1.getCellFormula();
			break;
		case NUMERIC:
			if (DateUtil.isCellDateFormatted(cell1)) {
				long time = cell1.getDateCellValue().getTime();
				Date date = new Date(time);
				SimpleDateFormat df2 = new SimpleDateFormat("dd/MM/yyyy");
				value = df2.format(date);
			} else {
				DecimalFormat numberFormat = new DecimalFormat("#.##");
				double number = cell1.getNumericCellValue();
				value = numberFormat.format(number);
			}
			break;
		case BLANK:
			value = "";
			break;
		case BOOLEAN:
			if (evaluator.evaluate(cell1).getBooleanValue()) {
				value = "1";
			} else {
				value = "0";
			}
			break;
		case _NONE:
			value = null;
			break;
		case ERROR:
			value = evaluator.evaluate(cell1).formatAsString();
			break;
		default:
			value = cell1.toString();
			break;
		}
		return value;
	}
	
	private String readCell(HSSFCell cell1,FormulaEvaluator evaluator) throws NullPointerException{
		String value = null;
		switch (evaluator.evaluate(cell1).getCellTypeEnum()) {
		case STRING:
			value = evaluator.evaluate(cell1).getStringValue();
			break;
		case FORMULA:
			value = cell1.getCellFormula();
			break;
		case NUMERIC:
			if (DateUtil.isCellDateFormatted(cell1)) {
				long time = cell1.getDateCellValue().getTime();
				Date date = new Date(time);
				SimpleDateFormat df2 = new SimpleDateFormat("dd/MM/yyyy");
				value = df2.format(date);
			} else {
				DecimalFormat numberFormat = new DecimalFormat("#.##");
				double number = cell1.getNumericCellValue();
				value = numberFormat.format(number);
			}
			break;
		case BLANK:
			value = "";
			break;
		case BOOLEAN:
			if (evaluator.evaluate(cell1).getBooleanValue()) {
				value = "1";
			} else {
				value = "0";
			}
			break;
		case _NONE:
			value = null;
			break;
		case ERROR:
			value = evaluator.evaluate(cell1).formatAsString();
			break;
		default:
			value = cell1.toString();
			break;
		}
		return value;
	}

	public int getLastRow(String sheetname){
		if(extension){
			return hmSheetExt.get(sheetname).getLastRowNum();
		}else{
			return hmSheetOld.get(sheetname).getLastRowNum();
		}
	}

	public void createSheet(String sheetname) {
		if(sheetname == null){
			throw new IllegalArgumentException("sheetname is null");
		}
		if(extension){
			hmSheetExt.put(sheetname, workbookExt.createSheet(sheetname));
		}else{
			hmSheetOld.put(sheetname, workbookOld.createSheet(sheetname));
		}

	}
	public void addCell(int row,int col,Object value,String sheetname){
		addCell( row,col,false,value,sheetname);
	}
	
	public void addCell(int row,int col,boolean autoSize,Object value,String sheetname){
		Cell cell = null;
		cell = getCell(row,col,autoSize,sheetname);
        if (value instanceof String) {
            cell.setCellValue((String) value);
        } else if (value instanceof Boolean) {
            cell.setCellValue((Boolean) value);
        } else if (value instanceof java.util.Date) {
            cell.setCellValue((java.util.Date) value);
        } else if (value instanceof Double) {
            cell.setCellValue((Double) value);
        } else if (value instanceof Integer) {
            cell.setCellValue((Double) new Double((Integer)value));
        }
	}
	
	public void setMergedRegion(int fromRow,int fromCol,int toRow,int toCol,String sheetname){
		if(extension){
			XSSFSheet sheet = workbookExt.getSheetAt(workbookExt.getActiveSheetIndex());
			if(sheetname != null) {
				sheet = workbookExt.getSheet(sheetname);
			}
			sheet.addMergedRegion(new CellRangeAddress(fromRow,toRow,fromCol,toCol));
		}else{
			HSSFSheet sheet = workbookOld.getSheetAt(workbookOld.getActiveSheetIndex());
			if(sheetname != null) {
				sheet = workbookOld.getSheet(sheetname);
			}
			sheet.addMergedRegion(new CellRangeAddress(fromRow,toRow,fromCol,toCol));
		}
	}
	public void setBorderColor(int row, int col,short indexColors,
			boolean right,boolean left,boolean top,boolean bottom,String sheetname){
		CellStyle style = null;
		Cell cell = null;
	    cell = getCell(row,col,false,sheetname);
	    style = getStyle(row,col,sheetname);
	    if(right){
	    	style.setBorderRight(BorderStyle.THICK);
	    	style.setRightBorderColor(indexColors);
	    }
	    if(left){
	    	style.setBorderLeft(BorderStyle.THICK);
	    	style.setLeftBorderColor(indexColors);
	    }
	    if(top){
	    	style.setBorderTop(BorderStyle.THICK);
	    	style.setTopBorderColor(indexColors);
	    }
	    if(bottom){
	    	style.setBorderBottom(BorderStyle.THICK);
	    	style.setBottomBorderColor(indexColors);
	    }
	    cell.setCellStyle(style);
	  
	}
	public void setAlignmentHorizontalRight(int row, int col,String sheetname){
		setAlignment(1, row, col, sheetname);
	}
	public void setAlignmentHorizontalCenter(int row, int col,String sheetname){
		setAlignment(2, row, col, sheetname);
	}
	public void setAlignmentHorizontalLeft(int row, int col,String sheetname){
		setAlignment(3, row, col, sheetname);
	}
	public void setAlignmentVerticalTop(int row, int col,String sheetname){
		setAlignment(-1, row, col, sheetname);
	}
	public void setAlignmentVerticalCenter(int row, int col,String sheetname){
		setAlignment(-2, row, col, sheetname);
	}
	public void setAlignmentVerticalBottom(int row, int col,String sheetname){
		setAlignment(-3, row, col, sheetname);
	}

	private void setAlignment(int option, int row, int col, String sheetname) {
		CellStyle style = null;
		Cell cell = null;
	    cell = getCell(row,col,false,sheetname);
	    style = getStyle(row,col,sheetname);
		switch (option) {
		case 1:
			style.setAlignment(HorizontalAlignment.RIGHT);
			break;
		case 2:
			style.setAlignment(HorizontalAlignment.CENTER);
			break;
		case 3:
			style.setAlignment(HorizontalAlignment.LEFT);
			break;
		case -1:
			style.setVerticalAlignment(VerticalAlignment.TOP);
			break;
		case -2:
			style.setVerticalAlignment(VerticalAlignment.CENTER);
			break;
		case -3:
			style.setVerticalAlignment(VerticalAlignment.BOTTOM);
			break;
		default:
			break;
		}
	    cell.setCellStyle(style);
	}

	public final short BLACK = IndexedColors.BLACK.getIndex();
	public final short WHITE = IndexedColors.WHITE.getIndex();
	public final short RED = IndexedColors.RED.getIndex();
	public final short BRIGHT_GREEN = IndexedColors.BRIGHT_GREEN.getIndex();
	public final short BLUE = IndexedColors.BLUE.getIndex();
	public final short YELLOW = IndexedColors.YELLOW.getIndex();
	public final short PINK = IndexedColors.PINK.getIndex();
	public final short TURQUOISE = IndexedColors.TURQUOISE.getIndex();
	public final short DARK_RED = IndexedColors.DARK_RED.getIndex();
	public final short GREEN = IndexedColors.GREEN.getIndex();
	public final short DARK_BLUE = IndexedColors.DARK_BLUE.getIndex();
	public final short DARK_YELLOW = IndexedColors.DARK_YELLOW.getIndex();
	public final short VIOLET = IndexedColors.VIOLET.getIndex();
	public final short TEAL = IndexedColors.TEAL.getIndex();
	public final short GREY_25_PERCENT = IndexedColors.GREY_25_PERCENT.getIndex();
	public final short GREY_50_PERCENT = IndexedColors.GREY_50_PERCENT.getIndex();
	public final short CORNFLOWER_BLUE = IndexedColors.CORNFLOWER_BLUE.getIndex();
	public final short MAROON = IndexedColors.MAROON.getIndex();
	public final short LEMON_CHIFFON = IndexedColors.LEMON_CHIFFON.getIndex();
	public final short ORCHID = IndexedColors.ORCHID.getIndex();
	public final short CORAL = IndexedColors.CORAL.getIndex();
	public final short ROYAL_BLUE = IndexedColors.ROYAL_BLUE.getIndex();
	public final short LIGHT_CORNFLOWER_BLUE = IndexedColors.LIGHT_CORNFLOWER_BLUE.getIndex();
	public final short SKY_BLUE = IndexedColors.SKY_BLUE.getIndex();
	public final short LIGHT_TURQUOISE = IndexedColors.LIGHT_TURQUOISE.getIndex();
	public final short LIGHT_GREEN = IndexedColors.LIGHT_GREEN.getIndex();
	public final short LIGHT_YELLOW = IndexedColors.LIGHT_YELLOW.getIndex();
	public final short PALE_BLUE = IndexedColors.PALE_BLUE.getIndex();
	public final short ROSE = IndexedColors.ROSE.getIndex();
	public final short LAVENDER = IndexedColors.LAVENDER.getIndex();
	public final short TAN = IndexedColors.TAN.getIndex();
	public final short LIGHT_BLUE = IndexedColors.LIGHT_BLUE.getIndex();
	public final short AQUA = IndexedColors.AQUA.getIndex();
	public final short LIME = IndexedColors.LIME.getIndex();
	public final short GOLD = IndexedColors.GOLD.getIndex();
	public final short LIGHT_ORANGE = IndexedColors.LIGHT_ORANGE.getIndex();
	public final short ORANGE = IndexedColors.ORANGE.getIndex();
	public final short BLUE_GREY = IndexedColors.BLUE_GREY.getIndex();
	public final short GREY_40_PERCENT = IndexedColors.GREY_40_PERCENT.getIndex();
	public final short DARK_TEAL = IndexedColors.DARK_TEAL.getIndex();
	public final short SEA_GREEN = IndexedColors.SEA_GREEN.getIndex();
	public final short DARK_GREEN = IndexedColors.DARK_GREEN.getIndex();
	public final short OLIVE_GREEN = IndexedColors.OLIVE_GREEN.getIndex();
	public final short BROWN = IndexedColors.BROWN.getIndex();
	public final short PLUM = IndexedColors.PLUM.getIndex();
	public final short INDIGO = IndexedColors.INDIGO.getIndex();
	public final short GREY_80_PERCENT = IndexedColors.GREY_80_PERCENT.getIndex();
	public final short AUTOMATIC = IndexedColors.AUTOMATIC.getIndex();
	
	public void setColorCell(int row, int col,short indexColors,String sheetname){
		CellStyle style = null;
		Cell cell = null;
	    cell = getCell(row,col,false,sheetname);
	    style = getStyle(row,col,sheetname);
	    style.setFillForegroundColor(indexColors);
	    style.setFillPattern(FillPatternType.SOLID_FOREGROUND);  
 
	    cell.setCellStyle(style);
	}
	

	public void setSizeToText(int row, int col,boolean bold, boolean italic,
			int sizeText,short indexColors,String sheetname){
		CellStyle style = null;
		Font font = null;
		Cell cell = null;
	    cell = getCell(row,col,false,sheetname);
	    style = getStyle(row,col,sheetname);
	    font = getFont(row, col, sheetname);
	    if(indexColors > 0){
	    	font.setColor(indexColors);
	    }
	    if(sizeText > 0){
	    	font.setFontHeightInPoints((short)sizeText);
	    }
    	font.setBold(bold);
        font.setItalic(italic);
    	style.setFont(font); 
	    cell.setCellStyle(style);
	}
	
	private HashMap<String,Cell> hmCell = new HashMap<String,Cell>();
	private Cell getCell(int row,int col,boolean autoSize,String sheetname){
		if(null == hmCell.get(sheetname+":"+row+","+col)){
			Row rowSheet = null;
			if(extension){
				XSSFSheet sheet = workbookExt.getSheetAt(workbookExt.getActiveSheetIndex());
				if(sheetname != null) {
					sheet = workbookExt.getSheet(sheetname);
				}
				rowSheet = sheet.getRow(row);
				if(rowSheet == null) {
					rowSheet = sheet.createRow(row);
				}
				if(autoSize){
					sheet.autoSizeColumn(col);
				}
			}else{
				HSSFSheet sheet = workbookOld.getSheetAt(workbookOld.getActiveSheetIndex());
				if(sheetname != null) {
					sheet = workbookOld.getSheet(sheetname);
				}
				rowSheet = sheet.getRow(row);
				if(rowSheet == null) {
					rowSheet = sheet.createRow(row);
				}
				if(autoSize){
					sheet.autoSizeColumn(col);
				}
			}
			hmCell.put(sheetname+":"+row+","+col,rowSheet.createCell(col));
		}
		return hmCell.get(sheetname+":"+row+","+col);
	}

	private HashMap<String,CellStyle> hmStyle = new HashMap<String,CellStyle>();
	private CellStyle getStyle(int row,int col,String sheetname){		
		if(null == hmStyle.get(sheetname+":"+row+","+col)){
			if(extension){
				hmStyle.put(sheetname+":"+row+","+col, workbookExt.createCellStyle());	
			}else{
				hmStyle.put(sheetname+":"+row+","+col, workbookOld.createCellStyle());	
			}
		}		
		return hmStyle.get(sheetname+":"+row+","+col);
	}
	private HashMap<String,Font> hmFont = new HashMap<String,Font>();
	private Font getFont(int row,int col,String sheetname){
		if(null == hmFont.get(sheetname+":"+row+","+col)){
			if(extension){
				hmFont.put(sheetname+":"+row+","+col, workbookExt.createFont());	
			}else{
				hmFont.put(sheetname+":"+row+","+col, workbookOld.createFont());	
			}
		}
		return hmFont.get(sheetname+":"+row+","+col);
	}

	

	public void write() {
		try {
			checkDetinPath();
			if(extension){
				java.io.FileOutputStream os = new java.io.FileOutputStream(pathDestin);
				workbookExt.write(os);
			}else{
				java.io.FileOutputStream os = new java.io.FileOutputStream(pathDestin);
				workbookOld.write(os);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
}
