package com.msc.adams.automation.dialog;


	import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.*;
	import org.eclipse.swt.widgets.*;

	/**
	 * This class demonstrates ProgressBar
	 */
	public class ProgressBarExample {
	  public static void main(String[] args) {
	    Display display = new Display();
	    Shell shell = new Shell(display);
	    shell.setLayout(new GridLayout());
	    
	    // Create a smooth progress bar
	    final ProgressBar pb1 = new ProgressBar(shell, SWT.HORIZONTAL | SWT.SMOOTH);
	    pb1.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
	    pb1.setMinimum(0);
	    pb1.setMaximum(10);
	    // Create an indeterminate progress bar
	    final ProgressBar pb2 = new ProgressBar(shell, SWT.HORIZONTAL | SWT.INDETERMINATE);
	    pb2.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
	    //pb2.setMinimum(0);
	    pb2.setMaximum(100);
	    
	    Button btn = new Button(shell,SWT.NONE);
	    btn.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
	    btn.setText("stop");
	    btn.addSelectionListener(new SelectionListener(){
	    
			@Override
			public void widgetSelected(SelectionEvent e) {
				// TODO Auto-generated method stub
				//pb2.setState(SWT.PAUSED);
				//pb2.setVisible(false);
				pb1.setSelection(10);
				//System.out.println("asdfasdf");
				
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub
				
			}
	    	
	    });
	    
	    Button btn2 = new Button(shell,SWT.NONE);
	    btn2.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
	    btn2.setText("start");
	    btn2.addSelectionListener(new SelectionListener(){
	    
			@Override
			public void widgetSelected(SelectionEvent e) {
				// TODO Auto-generated method stub
				//pb2.setVisible(true);
				
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub
				
			}
	    	
	    });
	    
	    
	    // Start the first progress bar
	    //new LongRunningOperation(display, pb1).start();
	    
	    shell.open();
	    while (!shell.isDisposed()) {
	      if (!display.readAndDispatch()) {
	        display.sleep();
	      }
	    }
	  }
	}
	  
	/**
	 * This class simulates a long running operation
	 */
	class LongRunningOperation extends Thread {
	  private Display display;
	  private ProgressBar progressBar;

	  public LongRunningOperation(Display display, ProgressBar progressBar) {
	    this.display = display;
	    this.progressBar = progressBar;
	  }
	  
	  public void run() {
	    // Perform work here--this operation just sleeps
	    for (int i = 0; i < 30; i++) {
	      try {
	        Thread.sleep(1000);
	      } catch (InterruptedException e) {
	        // Do nothing
	      }
	      display.asyncExec(new Runnable() {
	        public void run() {
	          if (progressBar.isDisposed()) return;
	          
	          // Increment the progress bar
	          progressBar.setSelection(progressBar.getSelection() + 1);
	        }
	      });
	    }
	  }
	}