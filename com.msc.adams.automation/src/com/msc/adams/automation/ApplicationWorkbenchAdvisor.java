package com.msc.adams.automation;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.ui.application.IWorkbenchWindowConfigurer;
import org.eclipse.ui.application.WorkbenchAdvisor;
import org.eclipse.ui.application.WorkbenchWindowAdvisor;

import com.msc.adams.automation.core.Mediator;

public class ApplicationWorkbenchAdvisor extends WorkbenchAdvisor {
	
	private Mediator med = Mediator.getInstance();
	
	private static final String PERSPECTIVE_ID = "com.msc.adams.automation.perspective";


	public WorkbenchWindowAdvisor createWorkbenchWindowAdvisor(
			IWorkbenchWindowConfigurer configurer) {
		return new ApplicationWorkbenchWindowAdvisor(configurer);
	}

	public String getInitialWindowPerspectiveId() {
		return PERSPECTIVE_ID;
	}

	
	/* (non-Javadoc)
	 * @see org.eclipse.ui.application.WorkbenchAdvisor#preShutdown()
	 */
	@Override
	public boolean preShutdown() {
		// TODO Auto-generated method stub
		//System.out.println("preShutdown");
		boolean result = MessageDialog.openConfirm(med.getParentView().getShell(), "Exit", "Do you want to quit?");
		if(result){
			return super.preShutdown();
		}else{
			return false;
		}
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ui.application.WorkbenchAdvisor#postShutdown()
	 */
	@Override
	public void postShutdown() {
		// TODO Auto-generated method stub
		//System.out.println("postShutdown");
		super.postShutdown();
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ui.application.WorkbenchAdvisor#preWindowShellClose(org.eclipse.ui.application.IWorkbenchWindowConfigurer)
	 */
	@Override
	public boolean preWindowShellClose(IWorkbenchWindowConfigurer configurer) {
		// TODO Auto-generated method stub
		//System.out.println("postWindowShellClose");
		return super.preWindowShellClose(configurer);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ui.application.WorkbenchAdvisor#postWindowClose(org.eclipse.ui.application.IWorkbenchWindowConfigurer)
	 */
	@Override
	public void postWindowClose(IWorkbenchWindowConfigurer configurer) {
		// TODO Auto-generated method stub
		//System.out.println("postWindowClose");
		super.postWindowClose(configurer);
	}
	
}
