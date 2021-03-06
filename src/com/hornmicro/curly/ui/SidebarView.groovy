package com.hornmicro.curly.ui

import groovy.transform.CompileStatic

import org.eclipse.swt.SWT
import org.eclipse.swt.events.SelectionAdapter
import org.eclipse.swt.events.SelectionEvent
import org.eclipse.swt.graphics.Image
import org.eclipse.swt.layout.FillLayout
import org.eclipse.swt.widgets.Composite
import org.eclipse.swt.widgets.Display
import org.eclipse.swt.widgets.Shell
import org.eclipse.swt.widgets.Tree
import org.eclipse.swt.widgets.TreeItem

import com.hornmicro.util.CocoaTools
import com.hornmicro.util.MainThreader
@CompileStatic
class SidebarView extends Composite {
    Tree tree
    TreeItem favorites
    TreeItem devices
    
    public SidebarView(Composite parent, int style) {
        super(parent, style)
    }
    
    void createContents() {
        setLayout(new FillLayout())
        tree = new Tree (this, SWT.SOURCE_LIST)
        
        // Tests
        favorites = new TreeItem (tree, SWT.GROUP_ITEM)
        favorites.text = "TESTS"
        shell.display.asyncExec {   
            favorites.expanded = true
        }
        
        // Library
        devices = new TreeItem (tree, SWT.GROUP_ITEM)
        devices.text = "LIBRARY"
        shell.display.asyncExec {
            devices.expanded = true
        }
    }
}
