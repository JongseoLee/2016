package com.js.ens.leveller.core;

import org.apache.log4j.Logger;
import org.eclipse.jface.viewers.ICellModifier;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.widgets.Item;

import com.js.ens.leveller.Application;
import com.js.ens.leveller.core.Mediator;
import com.js.ens.leveller.core.UpTableDataContent;
import com.js.ens.leveller.core.DownTableDataContent;
import com.js.ens.leveller.core.TableColumnLabel;


public class TableViewerModifier implements ICellModifier {
	private Logger log = Logger.getLogger(TableViewerModifier.class);
	
	private Mediator med;
	private Viewer tableViewer;
	private UpTableDataContent UTDCObj;
	private DownTableDataContent DTDCObj;
	
	public TableViewerModifier(Viewer viewer){
		this.med = Mediator.getInstance();
		this.tableViewer = viewer;
	}
	
	@Override
	public boolean canModify(Object element, String property) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public Object getValue(Object element, String property) {
		// TODO Auto-generated method stub
		
		try {
			if (this.tableViewer == med.getTableViewerUpperRoll()) {
				UTDCObj = (UpTableDataContent) element;

				// if(TableColumnLabel.COL_0.equals(property)){
				// return UTDCObj.getNo();
				// }else if(TableColumnLabel.COL_1.equals(property)){
				if (TableColumnLabel.COL_1.equals(property)) {
					return UTDCObj.getGap();
				} else if (TableColumnLabel.COL_2.equals(property)) {
					return UTDCObj.getFriction();
				} else if (TableColumnLabel.COL_3.equals(property)) {
					return UTDCObj.getDiameter();
				}

			} else if (this.tableViewer == med.getTableViewerLowerRoll()) {
				DTDCObj = (DownTableDataContent) element;

				// if(TableColumnLabel.COL_0.equals(property)){
				// return DTDCObj.getNo();
				// }else if(TableColumnLabel.COL_1.equals(property)){

				if (TableColumnLabel.COL_1.equals(property)) {
					return DTDCObj.getGap();
				} else if (TableColumnLabel.COL_2.equals(property)) {
					return DTDCObj.getFriction();
				} else if (TableColumnLabel.COL_3.equals(property)) {
					return DTDCObj.getDiameter();
				}
			}
			
			else if (this.tableViewer == med.getTableViewerUpperRoll_2D()) {
				UTDCObj = (UpTableDataContent) element;

				// if(TableColumnLabel.COL_0.equals(property)){
				// return UTDCObj.getNo();
				// }else if(TableColumnLabel.COL_1.equals(property)){
				if (TableColumnLabel.COL_1.equals(property)) {
					return UTDCObj.getGap();
				} else if (TableColumnLabel.COL_2.equals(property)) {
					return UTDCObj.getFriction();
				} else if (TableColumnLabel.COL_3.equals(property)) {
					return UTDCObj.getDiameter();
				}

			} else if (this.tableViewer == med.getTableViewerLowerRoll_2D()) {
				DTDCObj = (DownTableDataContent) element;

				// if(TableColumnLabel.COL_0.equals(property)){
				// return DTDCObj.getNo();
				// }else if(TableColumnLabel.COL_1.equals(property)){

				if (TableColumnLabel.COL_1.equals(property)) {
					return DTDCObj.getGap();
				} else if (TableColumnLabel.COL_2.equals(property)) {
					return DTDCObj.getFriction();
				} else if (TableColumnLabel.COL_3.equals(property)) {
					return DTDCObj.getDiameter();
				}
			}
			
		} catch (Exception e) {

		}
	
		this.tableViewer.refresh();
		
		return null;
	}
	
	@Override
	public void modify(Object element, String property, Object value) {
		// TODO Auto-generated method stub
		
		try{
			if(element instanceof Item){
				element = ((Item) element).getData();
			}
			
			if(this.tableViewer == med.getTableViewerUpperRoll()){
				UTDCObj = (UpTableDataContent) element;
				
				if(TableColumnLabel.COL_1.equals(property)){
					UTDCObj.setGap((String) value);
				}else if(TableColumnLabel.COL_2.equals(property)){
					UTDCObj.setFriction((String) value);
				}else if(TableColumnLabel.COL_3.equals(property)){
					UTDCObj.setDiameter((String) value);
				}
			}else if(this.tableViewer == med.getTableViewerLowerRoll()){
				DTDCObj = (DownTableDataContent) element;
				
				if(TableColumnLabel.COL_1.equals(property)){
					DTDCObj.setGap((String) value);
				}else if(TableColumnLabel.COL_2.equals(property)){
					DTDCObj.setFriction((String) value);
				}else if(TableColumnLabel.COL_3.equals(property)){
					DTDCObj.setDiameter((String) value);
				}
			}
			else if(this.tableViewer == med.getTableViewerUpperRoll_2D()){
				UTDCObj = (UpTableDataContent) element;
				
				if(TableColumnLabel.COL_1.equals(property)){
					UTDCObj.setGap((String) value);
				}else if(TableColumnLabel.COL_2.equals(property)){
					UTDCObj.setFriction((String) value);
				}else if(TableColumnLabel.COL_3.equals(property)){
					UTDCObj.setDiameter((String) value);
				}
			}else if(this.tableViewer == med.getTableViewerLowerRoll_2D()){
				DTDCObj = (DownTableDataContent) element;
				
				if(TableColumnLabel.COL_1.equals(property)){
					DTDCObj.setGap((String) value);
				}else if(TableColumnLabel.COL_2.equals(property)){
					DTDCObj.setFriction((String) value);
				}else if(TableColumnLabel.COL_3.equals(property)){
					DTDCObj.setDiameter((String) value);
				}
			}
		}catch(Exception e){
			log.error("Table Data Save Error");
		}
		
		this.tableViewer.refresh();
	}

}
