package com.hornmicro.curly.ui

import groovy.transform.CompileStatic

import org.eclipse.jface.layout.GridDataFactory
import org.eclipse.swt.SWT
import org.eclipse.swt.custom.SashForm
import org.eclipse.swt.layout.FillLayout
import org.eclipse.swt.layout.GridData
import org.eclipse.swt.layout.GridLayout
import org.eclipse.swt.widgets.Composite
import org.eclipse.swt.widgets.Display
import org.eclipse.swt.widgets.Shell
import org.eclipse.swt.widgets.Text
import org.eclipse.swt.widgets.ToolBar
import org.eclipse.swt.widgets.ToolItem

import com.hornmicro.util.MainThreader
import com.hornmicro.util.Resources

@CompileStatic
class MainView extends Composite {
	ToolBar toolbar
	ToolItem execute
	ToolItem playOne
	ToolItem playAll
	ToolItem pause
	ToolItem step
	ToolItem setBreak
	ToolItem refresh
	ToolItem settings
	
	StatusbarView statusbarView
	SidebarView sidebarView
	
	public MainView(Composite parent, int style) {
		super(parent, style)
	}
	
	public MainView(Composite parent) {
		this(parent, SWT.NONE)
	}
	
	void createContents() {
		setBackground(Resources.getColor(0xb4, 0xb4, 0xb4))
		GridLayout gLayout = new GridLayout(1, false)
		gLayout.with {
			marginWidth = marginHeight = 0
			verticalSpacing = 1
		}
		setLayout(gLayout)
		
		createToolbar()
		
		SashForm sform = new SashForm(this, SWT.HORIZONTAL)
		sform.setSashWidth(1)
		sform.setBackground(getBackground())
		sform.setLayoutData(new GridData(GridData.FILL_BOTH))

		sidebarView = new SidebarView(sform, SWT.NONE)
//		commandView = new commandView(sform, SWT.NONE)
		
		Text test1 = new Text(sform, SWT.NONE)
		test1.text = "wow this works just fine"

		sform.setWeights([1, 2] as int[])

		// horiz align, vert align, grab horiz, grab vert, horiz span, vert span
		sform.layoutData = new GridData (SWT.FILL, SWT.FILL, true, true, 1, 1)

		statusbarView = new StatusbarView(this)
		GridDataFactory
			.fillDefaults()
			.grab(true, false)
			.align(SWT.FILL, SWT.FILL)
			.hint(SWT.DEFAULT, 22)
			.applyTo(statusbarView)
	}
	
	void createToolbar() {
		toolbar = shell.getToolBar()
		
		execute = new ToolItem(toolbar, SWT.PUSH)
		execute.enabled = true
		execute.text = "Execute"
		execute.image = Resources.getImage("gfx/22gray/bolt.png")
		
		playOne = new ToolItem(toolbar, SWT.PUSH)
		playOne.enabled = true
		playOne.text = "Play One"
		playOne.image = Resources.getImage("gfx/22gray/play.png")
		
		playAll = new ToolItem(toolbar, SWT.PUSH)
		playAll.enabled = true
		playAll.text = "Play All"
		playAll.image = Resources.getImage("gfx/22gray/forward.png")
		
		pause = new ToolItem(toolbar, SWT.PUSH)
		pause.enabled = true
		pause.text = "Pause"
		pause.image = Resources.getImage("gfx/22gray/pause.png")
		
		step = new ToolItem(toolbar, SWT.PUSH)
		step.enabled = true
		step.text = "Step"
		step.image = Resources.getImage("gfx/22gray/step-forward.png")
		
		setBreak = new ToolItem(toolbar, SWT.PUSH)
		setBreak.enabled = true
		setBreak.text = "Set Breakpoint"
		setBreak.image = Resources.getImage("gfx/22gray/minus-sign.png")
		
		
		ToolItem item = new ToolItem(toolbar, SWT.SEPARATOR)
		item.setWidth(SWT.SEPARATOR_FILL)
		
		refresh = new ToolItem(toolbar, SWT.PUSH)
		refresh.text = "Refresh"
		refresh.image = Resources.getImage("gfx/22gray/refresh.png")
		
		settings = new ToolItem(toolbar, SWT.PUSH)
		settings.text = "Settings"
		settings.image = Resources.getImage("gfx/22gray/cog.png")
		settings.enabled = false
	}
	
	static main(args) {
		MainThreader.run {
			Display display = new Display()
			Shell shell = new Shell(display)
			shell.text = "Curly"
			shell.setLayout(new FillLayout())

			MainView view = new MainView(shell)
			
			view.createContents()
			view.sidebarView.createContents()
			
			shell.pack()
			shell.open()
			while (!shell.isDisposed()) {
				if (!display.readAndDispatch())
					display.sleep()
			}
			display.dispose()
		}	
	}

}
