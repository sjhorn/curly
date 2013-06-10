package com.hornmicro.curly.ui

import org.eclipse.jface.window.ApplicationWindow
import org.eclipse.swt.events.DisposeEvent
import org.eclipse.swt.events.DisposeListener
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell

import com.hornmicro.util.Resources

class MainController extends ApplicationWindow implements DisposeListener, Runnable {
	Composite parent
	
	MainController() {
		super(null)
	}
	
	protected void configureShell(Shell shell) {
		super.configureShell(shell)
		shell.setImage(Resources.getImage("gfx/icon_256x256.png"))
		shell.setBounds(10, 10, 800, 600)
		shell.addDisposeListener(this)
	}
	
	protected Control createContents(Composite parent) {
		this.parent = parent
		
		
	}
	
	void run() {
		blockOnOpen = true
		open()
	}

	void widgetDisposed(DisposeEvent arg0) {
		Resources.dispose()
	}

}
