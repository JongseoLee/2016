package com.js.ens.leveller;

import javax.swing.JOptionPane;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.eclipse.equinox.app.IApplication;
import org.eclipse.equinox.app.IApplicationContext;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.PlatformUI;

import com.js.ens.leveller.core.LicenseCheck;
import com.js.util.myUtil;


/**
 * This class controls all aspects of the application's execution
 */
public class Application implements IApplication {

	/* (non-Javadoc)
	 * @see org.eclipse.equinox.app.IApplication#start(org.eclipse.equinox.app.IApplicationContext)
	 */
	private Logger log = Logger.getLogger(Application.class);
	
	public Object start(IApplicationContext context) {
		Display display = PlatformUI.createDisplay();
		System.setProperty("LogPath.ens",myUtil.setPath(System.getProperty("user.dir"),"log"));
		PropertyConfigurator.configure(myUtil.setPath(System.getProperty("user.dir"),"log4j.properties"));
		log.info("Start ENSLeveller");
		
		/* */ 
		LicenseCheck engine = new LicenseCheck();
		try {
			int returnCode = 0;
			if(engine.running()){
				//License OK
				log.info("License is OK..."+myUtil.getCurrentDate());
				returnCode = PlatformUI.createAndRunWorkbench(display, new ApplicationWorkbenchAdvisor());
			}else{
				//LIcense Error
				log.error("License is Error..."+myUtil.getCurrentDate());
				JOptionPane.showMessageDialog(null,"License Error" , "Leveller", JOptionPane.ERROR_MESSAGE);
				returnCode = IApplication.EXIT_OK;
			}
			
			if (returnCode == PlatformUI.RETURN_RESTART) {
				return IApplication.EXIT_RESTART;
			}
			
			return IApplication.EXIT_OK;
		}catch(Exception e){
			log.error("License is Error..."+myUtil.getCurrentDate());
			JOptionPane.showMessageDialog(null, "License Error", "Leveller", JOptionPane.ERROR_MESSAGE);
			return IApplication.EXIT_OK;
		}finally {
			display.dispose();
		}
		//*/
		/*
		try {
			int returnCode = PlatformUI.createAndRunWorkbench(display, new ApplicationWorkbenchAdvisor());
			if (returnCode == PlatformUI.RETURN_RESTART) {
				return IApplication.EXIT_RESTART;
			}
			return IApplication.EXIT_OK;
		} finally {
			display.dispose();
		}
		// */
	}

	/* (non-Javadoc)
	 * @see org.eclipse.equinox.app.IApplication#stop()
	 */
	public void stop() {
		if (!PlatformUI.isWorkbenchRunning())
			return;
		final IWorkbench workbench = PlatformUI.getWorkbench();
		final Display display = workbench.getDisplay();
		display.syncExec(new Runnable() {
			public void run() {
				if (!display.isDisposed())
					workbench.close();
			}
		});
	}
}
